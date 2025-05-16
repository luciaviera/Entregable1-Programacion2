package triangulos.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partida {
         private  Jugador blanco;
         private  Jugador negro;
         private ConfiguracionDePartida config;
         private  Tablero tablero = new Tablero();         
         private  List<Movimiento> historial = new ArrayList<>();
         private char turno;
         private Jugador ganador;
         private boolean partidaTerminada = false;
         
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
         
         public boolean haTerminado() {
                  return this.partidaTerminada;
         }

         public ConfiguracionDePartida getConfig() {
            return config;
         }

         public void abandonar(Jugador actual) {
                  throw new UnsupportedOperationException("Not supported yet."); 
         }

}  
