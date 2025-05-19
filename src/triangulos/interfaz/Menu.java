package triangulos.interfaz;

import java.util.ArrayList;
import triangulos.dominio.ConfiguracionDePartida;
import triangulos.dominio.Jugador;
import triangulos.dominio.RegistroDeJugadores;

public class Menu {
         
         private  RegistroDeJugadores registro = new RegistroDeJugadores();
         private ConfiguracionDePartida config = new ConfiguracionDePartida();
         
         
         public void mostrarMenu() {
                  this.config.confiugracionPorDefecto();
                  boolean salir = false;
                  while (!salir) {
                           Consola.println("A) Registrar un jugador");
                           Consola.println("B) Configurar la partida");
                           Consola.println("C) Comienzo de partida");
                           Consola.println("D) Mostrar ranking y racha");
                           Consola.println("E) Terminar el programa");
                           String opcion = Consola.readln("\nIngrese la opción deseada: ");
                           boolean opcionValida = false;
                           do {
                                    switch (opcion) {
                                             case "A":
                                                       this.registrarJugador();
                                                       
                                                       opcionValida = true;
                                                       break;
                                             case "B":
                                                      this.configurarPartidas();
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
                                             case "E":
                                                      opcionValida = true;
                                                      salir = true;
                                                      break;
                                             default:
                                                      Consola.error("Opción inválida. Por favor, intente nuevamente");
                                                      opcion = Consola.readln("Ingrese la opción deseada: ");
                                    }
                           } while (!opcionValida);
                           if (!salir) {
                                    Consola.println("\nBIENVENIDO NUEVAMENTE AL MENU");
                           }
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
                                    Consola.println("Por favor intente nuevamente.");
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
                           int indiceJ1 = leerNumero("Ingrese el numero correspondiente al primer jugador: ");
                           int indiceJ2 = leerNumero("Ingrece el numero correspondiente al segundo jugador: ");
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
         
         private void configurarPartidas(){
                  ConfiguracionUI configUI = new ConfiguracionUI();
                  configUI.mostrarMenu(this.config);
         }
         
         public static int leerNumero(String mensaje) {
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