package triangulos.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {
         private  Jugador blanco;
         private  Jugador negro;
         private ConfiguracionDePartida cfg;
         private  Tablero tablero = new Tablero();         
         private  List<Movimiento> historial = new ArrayList<>();
         
         //Constructor
         public Partida(Jugador jugador1, Jugador jugador2, ConfiguracionDePartida cfg) {
                  // Elegir blanco y negro aleatoriamente
                  if (new Random().nextBoolean()) {
                           this.blanco = jugador1;
                           this.negro  = jugador2;
                  } else {
                        this.blanco = jugador2;
                        this.negro  = jugador1;
                  }
                  // Asignar colores a los jugadores
                  
                  this.cfg   =  cfg;
         }
}  
