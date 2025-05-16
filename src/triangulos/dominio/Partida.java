package triangulos.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Partida {
         private  Jugador blanco;
         private  Jugador negro;
         private ConfiguracionDePartida config;
         private  Tablero tablero = new Tablero();         
         private  List<Movimiento> historial = new ArrayList<>();
         private char turno;
         private Jugador ganador;
         private boolean terminada = false;
         
         //Constructor
         public Partida(List<Jugador> jugadores, ConfiguracionDePartida config) {
                 
                  // Elegir blanco y negro aleatoriamente
                  Collections.shuffle(jugadores);
                  this.blanco = jugadores.get(0);
                  this.negro  = jugadores.get(1);
                  this.config  =  config;
                  tablero.getTableroInicial();
                  
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
         
         public void iniciarPartida () {
                    
         }
         
         
         public void realizarJuegada (String entrada, Tablero tablero){
                  Movimiento mov = Movimiento.crear (entrada, tablero);
                  
                  //Verifico si la jugada es admisible en el tablero actual y de serlo me la agrega
                  boolean jugadaValida = tablero.agregarBanda(mov, this.turno, this.config);
                  // if (!jugadaValida) throw new InvalidMoveException("Movimiento inválido (cruce o fuera de reglas)");
                  
                  //Actualizo el estado de la partida
                  terminada = (tablero.getBandas().size() == config.getCantMaxBandas());  
                  
                  if (jugadaValida) {
                           //Agrego jugada al historial
                           historial.add(mov);
                           
                           cambiarTurno();  
                  }

         }
         
         public boolean haTerminado() {
                  return terminada;
         }

         public ConfiguracionDePartida getConfig() {
                  return config;
         }

         public void abandonar(Jugador actual) {
                  terminada = true;
         }
         
         private void cambiarTurno() {
                  if (this.turno == 'B') {
                           this.turno = 'N';
                  } else {
                           this.turno = 'B';
                  }
         }

}  
