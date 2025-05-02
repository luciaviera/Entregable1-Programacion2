package triangulos.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Partida {
         private  Jugador blanco;
         private  Jugador negro;
         private ConfiguracionDePartida config;
         private  Tablero tablero = new Tablero();         
         private  List<Movimiento> historial = new ArrayList<>();
         
         //Constructor
         public Partida(List<Jugador> jugadores, ConfiguracionDePartida config) {
                  // Elegir blanco y negro aleatoriamente
                  Collections.shuffle(jugadores);
                  this.blanco = jugadores.get(0);
                  this.negro  = jugadores.get(1);
                  this.config  =  config;
         }
         
         //Getters
         public Jugador getBlanco() {
                  return this.blanco;
         }
         public Jugador getNegro() {
                  return this.negro;
         }
}  
