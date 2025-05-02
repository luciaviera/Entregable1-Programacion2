package triangulos.interfaz;
import java.util.List;
import triangulos.dominio.*;

public class PartidaUI {

         static void iniciarPartida(List<Jugador> judaoresSeleccioandos, ConfiguracionDePartida config) {
                  Partida partida = new Partida(judaoresSeleccioandos, config);
                  Consola.println("\n¡Comienza la partida!");
                  Consola.println("Blanco: " + partida.getBlanco() + "  vs  Negro: " + partida.getNegro()+"\n");
                  imprimirPuntuaciones();
                  imprimirTablero(partida.getTablero().getTableroInicial());
         }
         
         public static void imprimirTablero(char[][] tablero) {
                  String[] columnas = {"A","B","C","D","E","F","G","H","I","J","K","M","N"};
                  for (String col : columnas) {
                            Consola.print(col + " ");
                  }
                  Consola.println("\n");
                  // Imprime la matriz
                  for (int i = 0; i < tablero.length; i++) {
                        for (int j = 0; j < tablero[i].length; j++) {
                            Consola.print(tablero[i][j] + " ");
                        }
                        Consola.println("");
                    }
        }
         
         public static void imprimirPuntuaciones() {
                  Consola.println("Cantidad Blanco : ");
                  Consola.println(" Cantidad Negro : " + "\n");
         }
}