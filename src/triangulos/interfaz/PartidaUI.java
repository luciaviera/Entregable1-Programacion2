package triangulos.interfaz;
import java.util.List;
import triangulos.dominio.*;

public class PartidaUI {

         static void iniciarPartida(List<Jugador> judaoresSeleccioandos, ConfiguracionDePartida config) {
                  Partida partida = new Partida(judaoresSeleccioandos, config);
                  Consola.println("\n¡Comienza la partida!");
                  Consola.println("Blanco: " + partida.getBlanco() + "  vs  Negro: " + partida.getNegro()+"\n");
                  imprimirPuntuaciones();
                  imprimirTablero();
         }
         
         public static void imprimirTablero() {
                  String[] columnas = {"A","B","C","D","E","F","G","H","I","J","K","M","N"};
                  for (String col : columnas) {
                            Consola.print(col + " ");
                  }
                  Consola.println("\n");
                  Tablero tablero = new Tablero();
                  char[][] tableroVacio = tablero.getTableroInicial();
                  // Imprime la matriz
                  for (int i = 0; i < tableroVacio.length; i++) {
                        for (int j = 0; j < tableroVacio[i].length; j++) {
                            Consola.print(tableroVacio[i][j] + " ");
                        }
                        Consola.println("");
                    }
        }
         
         public static void imprimirPuntuaciones() {
                  Consola.println("\n Cantidad Blanco : ");
                  Consola.println(" Cantidad Negro : " + "\n");
         }
}