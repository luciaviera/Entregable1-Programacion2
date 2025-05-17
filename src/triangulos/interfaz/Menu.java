package triangulos.interfaz;

import java.util.ArrayList;
import java.util.List;
import triangulos.dominio.ConfiguracionDePartida;
import triangulos.dominio.Jugador;
import triangulos.dominio.RegistroDeJugadores;

public class Menu {
         
         private  RegistroDeJugadores registro = new RegistroDeJugadores();
         private ConfiguracionDePartida config = new ConfiguracionDePartida();
         
         
         public void mostrar() {
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
         
         private void registrarJugador(){
                  String nombre = Consola.readln("\nIngrese el nombre del jugador: ");
                  int edad = Integer.parseInt(Consola.readln("Ingrese la edad del jugador: "));
                  try {
                           Jugador jugador = new Jugador(nombre, edad);
                           registro.registrar(jugador);
                           Consola.println("Jugador registrado: " + jugador + "\n");
                           Consola.println("BIENVENIDO NUEVAMENTE AL MENU");
                           mostrar();
                  } catch (IllegalArgumentException e) {
                           Consola.error(e.getMessage());
                           Consola.println("BIENVENIDO NUEVAMENTE AL MENU");
                           mostrar();
                  }
         }
         
         private void comenzarPartida(){
                  
                  if (!registro.hayMinimo()){
                           
                           Consola.error("Necesitas al menos 2 jugadores registrados para empezar a jugar.");
                           Consola.println("BIENVENIDO NUEVAMENTE AL MENU");
                           mostrar();
                           return;
                  } 
                  
                  mostrarJugadores();
                  Jugador[] judaoresSeleccioandos = seleccionarJugadores();
                  PartidaUI partidaUI = new PartidaUI();
                  partidaUI.iniciarPartida(judaoresSeleccioandos, config);
         }
         
         
         public  void mostrarJugadores(){
                  ArrayList<Jugador> jugadores = registro.getJugadores();
                  Consola.println("\nJUGADORES REGISTRADOS");
                  for (int i = 0; i <jugadores.size(); i++) {
                            Consola.println(i+1 + ". " +jugadores.get(i).getNombre());
                  }
         }

        
         private  Jugador[] seleccionarJugadores() {
                  Consola.println("\nSeleccion de jugadores: ");
                  int indiceJ1 = this.leerIndice("Ingrese el numero correspondiente al primer jugador: ");
                  int indiceJ2 = this.leerIndice("Ingrece el numero correspondiente al segundo jugador: ");
                  
                  boolean seleccionados = false;
                  Jugador[] jugadores = null;
                  while (!seleccionados) { 
                           try {
                                    jugadores = registro.seleccionarJugadores(indiceJ1, indiceJ2);
                                    seleccionados = true;
                           } catch (IllegalArgumentException e) {
                                    Consola.error(e.getMessage());
                           }
                  }
                  return jugadores;
        }
         
         private int leerIndice(String mensaje) {
                  String entrada = Consola.readln(mensaje);
                  int indice = 0;
                  try {
                           indice = Integer.parseInt(entrada); 
                           
                  } catch (NumberFormatException e) {
                           Consola.error("Ingrese un numero");
                  }
                  return indice;
         }
}