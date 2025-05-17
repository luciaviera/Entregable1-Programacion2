package triangulos.interfaz;

import java.util.List;
import triangulos.dominio.*;
import triangulos.interfaz.Consola;

public class PartidaUI {
         private Partida partida;
         
         public void iniciarPartida(Jugador[] judaoresSeleccioandos, ConfiguracionDePartida config) {
                  try {
                           this.partida = new Partida(judaoresSeleccioandos, config);
                            Consola.println("\n¡Comienza la partida!");

                           Consola.println("Blanco: " + partida.getBlanco() + "  vs  Negro: " + partida.getNegro()+"\n");
                           imprimirPuntuaciones(partida);
                           imprimirTablero(partida.getTablero().getTableroInicial());

                           //Loop de turnos
                           while (!partida.haTerminado()) {
                                    this.ingresarJugada();
                           }
                           
                            mostrarResultados();    
                            
                  } catch (IllegalArgumentException e) {
                           Consola.print(e.getMessage());
                  } 
         }
         
         private void imprimirPuntuaciones(Partida partida) {
                  Consola.println("Cantidad Blanco : " + partida.getPuntajeBlanco());
                  Consola.println("Cantidad Negro : " + partida.getPuntajeNegro() + "\n");
         }

         private  void imprimirTablero(char[][] tablero) {
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

         private void ingresarJugada() {
                  String entrada = Consola.readln("Jugador " + this.imprimirColor() + " (" + partida.jugadorActual().getNombre() + ") es su turno. Ingrese su jugada :");
                  procesarJugada(entrada); 
         }

         private String imprimirColor() {
                  String color;
                  if (this.partida.getTurno() == 'B') {
                           color = "Blanco";
                  } else {
                           color = "Negro";
                  }
                  return color;
         }
         
         private void procesarJugada(String entrada) {
                  boolean jugadaValida = false;
                  while (!jugadaValida) {
                           if (entrada.equals("H")) {
                                    jugadaValida = true;
                           } else {
                                    try {
                                             partida.realizarJuegada(entrada, partida.getTablero());
                                             jugadaValida = true;
                                     } catch (IllegalArgumentException e) {
                                                Consola.error(e.getMessage());
                                                entrada = Consola.readln("Accion invalida, por favor reingrese: ");
                                    }
                           }
                  }
         }
         
         private void mostrarResultados() {
                  if (partida.getGanador() != null) {
                           Consola.println("¡Ganó " + partida.getGanador().getNombre() + "!");
                           //FALTA IMPLEMENTAR EFECTO CONFETI
                  } else {
                           Consola.println("Empate.");
                  }
         }


}
