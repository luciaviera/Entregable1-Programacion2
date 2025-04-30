package triangulos.interfaz;

public class Menu {
         public static void mostrar() {
                  Consola.println("Trabajo desarrollado por: LUCIA SOSA VIERA 247773 y JULIETA APELLIDO NRO EST");
                  Consola.println("A) Registrar jugador");
                  Consola.println("B) Configurar la partida");
                  Consola.println("C) Comienzo de partida");
                  Consola.println("D) Mostrar ranking y racha");
                  String opcion = Consola.readln("Ingrese la opción deseada ").toUpperCase();
                  boolean valida = true;
                  do {
                           switch (opcion) {
                                    case "A":
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
                                              opcion = Consola.readln("Ingrese la opción deseada ").toUpperCase();
                                              break;
                           }
                  } while (!valida);
         }
}
