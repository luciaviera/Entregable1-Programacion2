package triangulos.interfaz;
import java.util.List;
import triangulos.dominio.*;

public class PartidaUI {

         static void iniciarPartida(List<Jugador> judaoresSeleccioandos, ConfiguracionDePartida config) {
                  Partida partida = new Partida(judaoresSeleccioandos, config);
                  Consola.println("\n¡Comienza la partida!");
                  Consola.println("Blanco: " + partida.getBlanco() + "  vs  Negro: " + partida.getNegro());
 
         } 
}