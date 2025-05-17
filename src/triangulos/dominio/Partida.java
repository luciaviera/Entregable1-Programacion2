package triangulos.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Partida {
         private  Jugador blanco;
         private  Jugador negro;
         private ConfiguracionDePartida config;
         private  Tablero tablero = new Tablero();         
         private  List<Movimiento> historial = new ArrayList<>();
         private char turno;
         private int puntajeBlanco = 0;
         private int puntajeNegro = 0;
         private Jugador ganador;
         private boolean terminada = false;
         
         //Constructor
         public Partida(Jugador[] jugadores, ConfiguracionDePartida config) {
                  if (jugadores.length == 0) throw new IllegalArgumentException("No se han seleccioando jugadores");
                  if (jugadores.length != 2) throw new IllegalArgumentException("Se necesitan exactamente 2 jugadores");
                  
                  // Elegir blanco y negro aleatoriamente
                  List<Jugador> listaJugadores = Arrays.asList(jugadores);
                  Collections.shuffle(listaJugadores);
                  this.blanco = listaJugadores.get(0);
                  this.negro  = listaJugadores.get(1);
                  //Asignarle los colores correspondientes a cada jugador
                  this.blanco.setColor('B');
                  this.negro.setColor('N');
                  
                   //Arranca la partida jugando el jugador Blanco
                  this.turno = 'B';
                  
                  this.config  =  config;
         }
         
         //Getters
         public Jugador getBlanco() {
                  return this.blanco;
         }
         public Jugador getNegro() {
                  return this.negro;
         }
         public Tablero getTablero() {
                  return this.tablero;
         }
         public Jugador getGanador() {
                  return this.ganador;
         }
         public char getTurno() {
                  return this.turno;
         }
         public int getPuntajeBlanco() {
                  return this.puntajeBlanco;
         }
         public int getPuntajeNegro() {
                  return this.puntajeNegro;
         }
         
         
         public ConfiguracionDePartida getConfig() {
                  return config;
         }
        
         public void realizarJuegada (String entrada, Tablero tablero){                 
                  
                  if (entrada.equals("X")) {
                           this.abandonar(this.jugadorActual());
                  } else {
                           Movimiento mov = Movimiento.crear (entrada, tablero);
                           //Verifico si la jugada es admisible en el tablero actual y de serlo me la agrega
                           tablero.agregarBanda(mov, this.turno, this.config);
                           
                            //Actualizo el estado de la partida
                           historial.add(mov);  //Agrego jugada al historial
                           terminada = (tablero.getBandas().size() == config.getCantMaxBandas());   //Verifico si hay que terminar la partida
                           cambiarTurno();
                  }
         }
         
         public Jugador jugadorActual() {
                  if (this.turno == 'B') {
                           return this.getBlanco();
                  } else {
                           return this.getNegro();
                  }
         }
         
         public boolean haTerminado() {
                  return terminada;
         }

         private void cambiarTurno() {
                  if (this.turno == 'B') {
                           this.turno = 'N';
                  } else {
                           this.turno = 'B';
                  }
         }
         
         public void abandonar(Jugador actual) {
                  terminada = true;
         }
         
         

}  
