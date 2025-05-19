package triangulos.interfaz;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;


//CLASE HECHA CON  CHAT GPT
public class Confetti {
         //Tamaño ocupado en consola
         private static final int ancho  = 140;  // columnas del “lienzo”
         private static final int alto = 25;  // filas

         // Colores ANSI
         private static final String RESET = "\u001B[0m";
         private static final String[] colores = {
                  "\u001B[31m", // rojo
                  "\u001B[32m", // verde
                  "\u001B[33m", // amarillo
                  "\u001B[34m", // azul
                  "\u001B[35m", // magenta
                  "\u001B[36m"  // cian
         };

         // Símbolos: se usa uno distinto por explosión
         private static final char[] figuras= { '*', 'o', '+', 'x' };

         private static int framesTotales = 22;   // duración total
         private static int explosiones    = 8;    // nubecitas simultáneas
         private static int particulas     = 20;   // puntos por nube
         private static int radioX     = 6;    // dispersión horiz.
         private static int radioY     = 3;    // dispersión vert.
         private static int retrasoEntreFrames   = 150;  // milisegundos entre frames

         public static void tirar() throws InterruptedException {
                  Random rnd = new Random();

                  for (int frame = 0; frame < framesTotales; frame++) {
                           limpiarConsola();

                           //Lienzo
                           char[][]   canvas = new char[alto][ancho];
                           String[][] color  = new String[alto][ancho];
                           for (int y = 0; y < alto; y++) {
                                    for (int x = 0; x < ancho; x++) {
                                             canvas[y][x] = ' ';
                                    }
                           }
                                   
                           //Generacio nde explosiones
                           for (int e = 0; e < explosiones; e++) {

                                    int cx      = 10 + rnd.nextInt(ancho  - 20); // centro X
                                    int cy      =  4 + rnd.nextInt(alto -  3); // centro Y
                                    char symbol = figuras[rnd.nextInt(figuras.length)];
                                    String col  = colores[rnd.nextInt(colores.length)];

                                    for (int p = 0; p < particulas; p++) {
                                             int dx = rnd.nextInt(radioX* 2 + 1) - radioX;
                                             int dy = rnd.nextInt(radioY * 2 + 1) - radioY;
                                             int x  = cx + dx;
                                             int y  = cy + dy;

                                             if (x >= 0 && x < ancho && y >= 0 && y < alto) {
                                                      canvas[y][x] = symbol;
                                                      color [y][x] = col;
                                             }
                                    }
                            }

                           //Frame
                           StringBuilder sb = new StringBuilder();
                           for (int y = 0; y < alto; y++) {
                                    for (int x = 0; x < ancho; x++) {
                                             if (canvas[y][x] == ' ') {
                                                      sb.append(' ');
                                             } else {
                                                      sb.append(color[y][x]).append(canvas[y][x]).append(RESET);
                                             }
                                    }
                                    sb.append('\n');
                            }
                           System.out.print(sb.toString());
                           System.out.flush();
                           Thread.sleep(retrasoEntreFrames);
                  }
                  limpiarConsola();
         }

         //Limpia la consola
         private static void limpiarConsola() {
                  try {
                           Robot robot = new Robot();
                           robot.keyPress(KeyEvent.VK_CONTROL);
                           robot.keyPress(KeyEvent.VK_L);
                           robot.keyRelease(KeyEvent.VK_L);
                           robot.keyRelease(KeyEvent.VK_CONTROL);
                  } catch (AWTException e) {
                           System.out.print("\u001B[2J\u001B[H");
                  }
         }
}