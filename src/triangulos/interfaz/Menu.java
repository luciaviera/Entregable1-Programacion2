package triangulos.interfaz;

import java.util.ArrayList;
import triangulos.dominio.ConfiguracionDePartida;
import triangulos.dominio.Jugador;
import triangulos.dominio.RegistroDeJugadores;
import triangulos.interfaz.Consola;

public class Menu {
         
         private  RegistroDeJugadores registro = new RegistroDeJugadores();
         private ConfiguracionDePartida config = new ConfiguracionDePartida();
         
         public void mostrarMenu() {
                  while (true) {
                           Consola.println("A) Registrar jugador");
                           Consola.println("B) Configurar la partida");
                           Consola.println("C) Comienzo de partida");
                           Consola.println("D) Mostrar ranking y racha");
                           String opcion = Consola.readln("\nIngrese la opción deseada: ");
                           boolean opcionValida = false;
                           do {
                                    switch (opcion) {
                                             case "A":
                                                       this.registrarJugador();
                                                       
                                                       opcionValida = true;
                                                       break;
                                             case "B":
                                                       opcionValida = true;
                                                       break;
                                             case "C":
                                                       try {
                                                                this.comenzarPartida();
                                                       } catch ( IllegalArgumentException e){
                                                                Consola.error(e.getMessage());
                                                       }
                                                      opcionValida = true;
                                                      break;
                                             case "D":
                                                      opcionValida = true;
                                                      break;
                                             default:
                                                      Consola.error("Opción inválida. Por favor, intente nuevamente");
                                                      opcion = Consola.readln("Ingrese la opción deseada: ");
                                    }
                           } while (!opcionValida);
                           Consola.println("BIENVENIDO NUEVAMENTE AL MENU");
                  }
         }
         
         private void registrarJugador(){
                  boolean registrado = false;
                  while (!registrado) {
                           try {
                                    String nombre = Consola.readln("\nIngrese el nombre del jugador: ");
                                    int edad = leerNumero("Ingrese la edad del jugador: ");
                                    Jugador jugador = new Jugador(nombre, edad);
                                    this.registro.registrar(jugador);
                                    Consola.println("Jugador registrado: " + jugador + "\n");
                                    registrado = true;
                            } catch ( IllegalArgumentException e) {
                                    Consola.error(e.getMessage());
                                    Consola.println("No se ha podido realizar el registro. Por favor intente nuevamente.");
                            }
                  }
         }
         
         private void comenzarPartida(){
                  this.registro.hayMinimo();
                  this.mostrarJugadores();
                  Jugador[] judaoresSeleccioandos = this.seleccionarJugadores();
                  PartidaUI partidaUI = new PartidaUI();
                  partidaUI.iniciarPartida(judaoresSeleccioandos, config);
         }
         
         public  void mostrarJugadores(){
                  ArrayList<Jugador> jugadores = this.registro.getJugadores();
                  Consola.println("\nJUGADORES REGISTRADOS");
                  for (int i = 0; i <jugadores.size(); i++) {
                            Consola.println(i+1 + ". " +jugadores.get(i).getNombre());
                  }
         }

         private  Jugador[] seleccionarJugadores() {
                  boolean seleccionados = false;
                  Consola.println("\nSeleccion de jugadores: ");
                  Jugador[] jugadores = null;
                  while (!seleccionados) {
                           int indiceJ1 = this.leerNumero("Ingrese el numero correspondiente al primer jugador: ");
                           int indiceJ2 = this.leerNumero("Ingrece el numero correspondiente al segundo jugador: ");
                           try {
                                    jugadores = this.registro.seleccionarJugadores(indiceJ1, indiceJ2);
                                    seleccionados = true;
                           } catch (IllegalArgumentException e) {
                                    Consola.error(e.getMessage());
                                    Consola.println("Por favor vuelva a seleccionar.");
                           }
                  }
                  return jugadores;
        }
         
         public int leerNumero(String mensaje) {
                  boolean numeroIngresado = false;
                  String entrada = Consola.readln(mensaje);
                  int numero = 0;
                  while (!numeroIngresado) {
                           try {
                                    numero = Integer.parseInt(entrada); 
                                    numeroIngresado = true;
                           
                           } catch (NumberFormatException e) {
                                    Consola.error("Ingrese un numero");
                                    entrada = Consola.readln(mensaje);
                           }
                  }  
                  return numero;
         }
}