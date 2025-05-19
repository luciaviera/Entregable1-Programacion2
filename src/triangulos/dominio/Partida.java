package triangulos.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Partida {
         
         private char turno;
         private  Jugador blanco;
         private  Jugador negro;
         private ConfiguracionDePartida config;
         private int puntajeBlanco = 0;
         private int puntajeNegro = 0;
         private  Tablero tablero = new Tablero();         
         private  ArrayList<Movimiento> historial = new ArrayList<>();
         private Jugador ganador;
         private boolean terminada = false;
         
         //Constructor
         public Partida(Jugador[] jugadores, ConfiguracionDePartida config) {
                  this.config  =  config;
                  
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
                  return this.config;
         }
         public boolean haTerminado() {
                  return this.terminada;
         }
        
         public void realizarJuegada (String entrada, Tablero tablero){                 
                  
                  if (entrada.equals("X")) {
                           this.abandonar();
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
         
         //Devuelve el jugador del turno actual
         public Jugador jugadorActual() {
                  if (this.turno == 'B') {
                           return this.getBlanco();
                  } else {
                           return this.getNegro();
                  }
         }
         
         private void cambiarTurno() {
                  if (this.turno == 'B') {
                           this.turno = 'N';
                  } else {
                           this.turno = 'B';
                  }
         }
         
         public void abandonar() {
                  if (this.turno == 'B') {
                           this.ganador = this.negro;
                  } else {
                           this.ganador = this.blanco;
                  }
                  this.terminada = true;
         }
}  
