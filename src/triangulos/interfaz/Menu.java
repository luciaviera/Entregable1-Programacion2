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
                                             default:
                                                      Consola.error("Opción inválida. Por favor, intente nuevamente");
                                                      opcion = Consola.readln("Ingrese la opción deseada: ");
                                    }
                           } while (!opcionValida);
                           Consola.println("\nBIENVENIDO NUEVAMENTE AL MENU");
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
         
         private void  configurarPartidas() {
                  Consola.println("\nOPCIONES DE CONFIGURACIÓN: ");

                  boolean estadoContacto = this.config.isReglaDeContacto();
                  boolean estadoBandas = this.config.isLargoVariable();


                  Consola.println("\nA) " + this.mensajeDeEstado(estadoContacto) + " regla de contacto");
                  Consola.println("  (Al estar activada hace que desde la 2da jugada sea obligatorio que la banda ingresada este en contacto con al menos una de las bandas previas, para poder realizarse la jugada)");

                  Consola.println("\nB) " + this.mensajeDeEstado(estadoBandas) + " largo de bandas variable");
                  Consola.println("  (Al estar activado el jugador puede elegir el largo de la banda en cada jugada (entre 1 y 4), de estar desactivada las bandas tienen un largo fijo de 4)");

                  Consola.println("\nC) Configurar finalizacion de partida");
                  Consola.println("   Actualmente finaliza al colocarse " + this.config.getCantMaxBandas() + " bandas");

                  Consola.println("\nD) Configurar tableros mostrados en simultaneo");
                  Consola.println("  (Pueden mostrarse hasta 4 tableros)");
                  Consola.println("   Actualmente se muestran " + this.config.getHistorialDeTableros()+ " tableros");

                  String opcion = Consola.readln("\nIngrese la opción deseada: ");
                  boolean opcionValida = false;
                           do {
                                    switch (opcion) {
                                             case "A":
                                                      this.config.setReglaDeContacto(!estadoContacto);
                                                      estadoContacto = this.config.isReglaDeContacto();
                                                      Consola.println("La regla de contacto fue " + this.mensajeDeEstado2(estadoContacto));
                                                      opcionValida = true;
                                                      break;
                                             case "B":
                                                      this.config.setLargoVariable(!estadoBandas);
                                                      estadoBandas = this.config.isLargoVariable();
                                                      Consola.println("La posibilidad de elegir el largo de las bandas" + this.mensajeDeEstado2(estadoBandas));
                                                      opcionValida = true;
                                                      break;
                                             case "C":
                                                      this.configurarFinalizacion();
                                                      opcionValida = true;
                                                      break;
                                             case "D":
                                                      this.configurarMostrarTableros();
                                                      opcionValida = true;
                                                      break;
                                             default:
                                                      Consola.error("Opción inválida. Por favor, intente nuevamente");
                                                      opcion = Consola.readln("Ingrese la opción deseada: ");
                                        }
                           } while (!opcionValida);
         }

         
         public void configurarFinalizacion(){
                  boolean configurado = false;
                  while (!configurado) {
                           try {
                                    int nuevoMax = Menu.leerNumero("Ingrese la cantidad de bandas en las que desea que se finalice la partida: ");
                                    this.config.setCantMaxBandas(nuevoMax);
                                    Consola.println("Ahora la partida finaliza al colocarse " + this.config.getCantMaxBandas() + " bandas");
                                    configurado = true;
                           } catch (IllegalArgumentException e) {
                                    Consola.error(e.getMessage());                          
                           }
                  }
             
         }
         
         private void configurarMostrarTableros(){
                  boolean configurado = false;
                  while (!configurado) {
                           try {
                                    int cantidadActualizada = Menu.leerNumero("Ingrese la cantidad de tableros que desea que se muestren en simultaneo: ");
                                    this.config.setHistorialDeTableros(cantidadActualizada);
                                    Consola.println("Ahora se mostraran " + this.config.getHistorialDeTableros() + " tableros");
                                    configurado = true;
                           } catch (IllegalArgumentException e) {
                                    Consola.error(e.getMessage());          
                           }
                  }
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

         private String mensajeDeEstado(boolean estado){
                  String mensaje;
                  if (estado) {
                           mensaje = "Desactivar";
                  } else {
                           mensaje = "Activar";
                  }
                  return mensaje;
         }
          
          private String mensajeDeEstado2(boolean estado){
                  String mensaje;
                  if (estado) {
                           mensaje = "activada";
                  } else {
                           mensaje = "desactivada";
                  }
                  return mensaje;
          }
}

