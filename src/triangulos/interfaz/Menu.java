package triangulos.interfaz;

import java.util.List;
import triangulos.dominio.Jugador;
import triangulos.dominio.RegistroDeJugadores;

public class Menu {
         
         private static  RegistroDeJugadores registro = new RegistroDeJugadores();
         private static List<Jugador> jugadores = registro.getJugadores();
         
         
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
                                             comenzarPartida();
                                             break;
                                    case "D":
                                               break;
                                    default:
                                              valida = false;
                                              Consola.error("Opción inválida. Por favor, intente nuevamente");
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
                           Consola.println("Jugador registrado: " + jugador + "\n");
                           Consola.println("BIENVENIDO NUEVAMENTE AL MENU");
                           mostrar();
                  } catch (IllegalArgumentException ex) {
                           Consola.error(ex.getMessage());
                           Consola.println("BIENVENIDO NUEVAMENTE AL MENU");
                           mostrar();
                  }
         }
         
         public static void comenzarPartida(){
                  
                  if (!registro.hayMinimo()){
                           
                           Consola.error("Necesitas al menos 2 jugadores registrados para empezar a jugar.");
                           Consola.println("BIENVENIDO NUEVAMENTE AL MENU");
                           mostrar();
                           return;
                  } 
                  mostrarJugadores();
                  Consola.println("\nSeleccion de jugadores: ");
                  int indiceJ1 = leerIndice("Ingrece el numero correspondiente al primer jugador ");
                   int indiceJ2 = leerIndice("Ingrece el numero correspondiente al segundo jugador ");
                  Jugador jugador1 = jugadores.get(indiceJ1 - 1);
                  while (indiceJ1 == indiceJ2) {
                      Consola.error("No puede ser el mismo jugador. Elige otro.");
                      indiceJ2 = leerIndice("Ingrece el numero correspondiente al segundo jugador ");
                  }
                  Jugador jugador2 = jugadores.get(indiceJ2 - 1);
         }
         
         
         
         public static void mostrarJugadores(){
                  Consola.println("\nJUGADORES REGISTRADOS");
                  for (int i = 0; i <jugadores.size(); i++) {
                            Consola.println(i+1 + ". " +jugadores.get(i).getNombre());
                  }
         }
         
        private static  int leerIndice(String prompt) {
                while (true) {
                    String line = Consola.readln(prompt);
                    try {
                        int val = Integer.parseInt(line);
                        if (val >= 1 && val <= jugadores.size()) {
                            return val;
                        }
                    } catch (NumberFormatException ignored) { }
                    Consola.error("Por favor ingresa un número entre 1 y " + jugadores.size()+ ".");
                }
         }
         

}