package triangulos.interfaz;
import triangulos.dominio.*;

public class PartidaUI {
         private  Jugador blanco;
         private  Jugador negro;
         private Partida partida;
         
         // Contructor
         public void JuegoUI(Jugador blanco, Jugador negro, ConfiguracionDePartida cfg) {
                  this.blanco  = blanco;
                  this.negro   = negro;
         }
         
         //
         public void jugar() {
                  Consola.println("\n¡Comienza la partida!");
                  Consola.println("Blanco: " + blanco.getNombre() + "  vs  Negro: " + negro.getNombre());
                  

         }

         
}
