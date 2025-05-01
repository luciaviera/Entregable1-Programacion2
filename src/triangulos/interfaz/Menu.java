package triangulos.interfaz;

import triangulos.dominio.Jugador;
import triangulos.dominio.RegistroDeJugadores;

public class Menu {
         
         private static  RegistroDeJugadores registro = new RegistroDeJugadores();
         
         
         public static void mostrar() {
                  Consola.println("A) Registrar jugador");
                  Consola.println("B) Configurar la partida");
                  Consola.println("C) Comienzo de partida");
                  Consola.println("D) Mostrar ranking y racha");
                  String opcion = Consola.readln("\nIngrese la opción deseada: ");
                  boolean valida = true;
                  do {
                           switch (opcion) {
                                    case "A":
                                             registrarJugador();
                                             break;
                                    case "B":
                                               break;
                                    case "C":
                                               break;
                                    case "D":
                                               break;
                                    default:
                                              valida = false;
                                              Consola.error("Opción inválida. Por favor, intente nuevamente.");
                                              opcion = Consola.readln("Ingrese la opción deseada: ");
                           }
                  } while (!valida);
         }
         
         public static void registrarJugador(){
                  String nombre = Consola.readln("\nIngrese el nombre del jugador: ");
                  int edad = Integer.parseInt(Consola.readln("Ingrese la edad del jugador: "));
                  try {
                           Jugador jugador = new Jugador(nombre, edad);
                           registro.registrar(jugador);
                           Consola.println("Jugador registrado: " + jugador);
                           Consola.println("\nBIENVENIDO NUEVAMENTE AL MENU");
                           mostrar();
                  } catch (IllegalArgumentException ex) {
                           Consola.error(ex.getMessage());
                            Consola.println("\nBIENVENIDO NUEVAMENTE AL MENU");
                           mostrar();
                  }
         }
         
         
}