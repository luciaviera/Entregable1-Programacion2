package triangulos.interfaz;

import triangulos.dominio.ConfiguracionDePartida;

public class ConfiguracionUI {
         
         private ConfiguracionDePartida config;
    
         public void  mostrarMenu(ConfiguracionDePartida config) {
                  
                  this.config = config;
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
                                                      Consola.println("La posibilidad de elegir el largo de las bandas fue " + this.mensajeDeEstado2(estadoBandas));
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
