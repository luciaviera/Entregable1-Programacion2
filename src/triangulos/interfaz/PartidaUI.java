package triangulos.interfaz;

import java.util.ArrayList;
import triangulos.dominio.*;

public class PartidaUI {
         private Partida partida;
         
         public void iniciarPartida(Jugador[] judaoresSeleccioandos, ConfiguracionDePartida config) {
                  
                  this.partida = new Partida(judaoresSeleccioandos, config);
                  Consola.println("\n¡Comienza la partida!");

                  Consola.println("Blanco: " + this.partida.getBlanco() + "  vs  Negro: " + this.partida.getNegro()+"\n");
                  this.imprimirPuntuaciones();
                  this.imprimirTableros(this.partida.getTablerosVisibles());
                  
                  Consola.print("INSTRUCCIONES:");
                  Consola.println("\nLas jugadas deben tener el siguiente formato: <Columna><Fila><Dirección>[Largo]");
                  Consola.println("Columna: A–M, Fila: 1–7, Dirección: Q/E/D/C/Z/A, Largo (Debe estar activado el largo variable de bandas): 1–4");
                  Consola.println("Ejemplo: A4D o C3Q2");
                  Consola.println("En caso de querer rendisre y abandonar lapartida ingrese: X");
                  Consola.println("En caso de querer ver las jugadas previas ingrese: H\n");
                  //Loop de turnos
                  while (!this.partida.haTerminado()) {
                           this.ingresarJugada();
                           this.imprimirPuntuaciones();
                           this.imprimirTableros(this.partida.getTablerosVisibles());
                  }        
                  this.mostrarResultados();    
         }
         
         private void imprimirPuntuaciones() {
                  Consola.println("Cantidad Blanco : " + this.partida.getPuntajeBlanco());
                  Consola.println("Cantidad Negro : " + this.partida.getPuntajeNegro() + "\n");
         }

         private void imprimirTableros(ArrayList<String[][]> tableros) {

                  String[] columnas = {"A","B","C","D","E","F","G","H","I","J","K","M","N"};

                  // Imprimo los títulos de columnas para cada tablero
                  for (int t = 0; t < tableros.size(); t++) {
                           for (String col : columnas) {
                                    Consola.print(col + " ");
                           }
                           Consola.print("    "); // espacio entre tableros
                  }
                  Consola.println("");

                  int filas = tableros.get(0).length;

                  // Imprimir todas las filas de todos los tableros en paralelo
                  for (int i = 0; i < filas; i++) {
                           for (String[][] tablero : tableros) {
                                    for (int j = 0; j < tablero[i].length; j++) {
                                             Consola.print(tablero[i][j]);
                                    }
                                    Consola.print("     "); // espacio entre tableros
                           }
                           Consola.println("");
                  }
                  Consola.println("");
        }

         private void ingresarJugada() {
                  String entrada = Consola.readln("Jugador " + this.imprimirColor() + " (" + this.partida.jugadorActual().getNombre() + ") es su turno. Ingrese su jugada :");
                  boolean jugadaValida = false;
                  while (!jugadaValida) {
                           if (entrada.equals("H")) {
                                    this.mostrarHistorial();
                                    entrada = Consola.readln("Jugador " + this.imprimirColor() + " (" + this.partida.jugadorActual().getNombre() + ") es su turno. Ingrese su jugada: ");
                           }
                           try {
                                    this.partida.realizarJuegada(entrada, this.partida.getTablero());
                                    jugadaValida = true;
                           } catch (IllegalArgumentException e) {
                                    Consola.error(e.getMessage());
                                    entrada = Consola.readln("Por favor reingrese: ");
                                    Consola.println("");
                           }
                  }
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

         private void mostrarHistorial(){
                  ArrayList<Movimiento> historial = this.partida.getHistorial();
                  String hStr;
                  if (historial.isEmpty()) {
                           hStr = "Todavia no se han realizado jugadas\n";
                  } else {
                           hStr = "\nJugadas realizadas: ";
                           for ( int i= 0; i < historial.size(); i++) {
                                    Movimiento mov = historial.get(i);
                                    if (i < historial.size() -1) {
                                             hStr += mov + ", ";
                                    } else {
                                             hStr += mov + "\n";
                                    }
                           }
                  }
                  Consola.println(hStr);
         }
         
         private void mostrarResultados(){
                   if (this.partida.getGanador() != null) {
                            Consola.println("¡GANO " + this.partida.getGanador().getNombre() + "!");
                  } else {
                           Consola.println("EMPATE");
                  }
                  try {
                           Thread.sleep(900);
                           Confetti.tirar();
                           Thread.sleep(800);
                   } catch (InterruptedException e) {
                   }
         } 
         

}
