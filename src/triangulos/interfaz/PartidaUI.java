package triangulos.interfaz;
import java.util.List;
import triangulos.dominio.*;

public class PartidaUI {
         private Partida partida;
         private String turno;

         private boolean jugadaValida = false;
         
         
         public void iniciarPartida(List<Jugador> judaoresSeleccioandos, ConfiguracionDePartida config) {
                  this.partida = new Partida(judaoresSeleccioandos, config);
                  Consola.println("\n¡Comienza la partida!");

                  Consola.println("Blanco: " + partida.getBlanco() + "  vs  Negro: " + partida.getNegro()+"\n");
                  imprimirPuntuaciones(partida);
                  imprimirTablero(partida.getTablero().getTableroInicial());
                  
                  //Loop de turnos
                   while (!partida.haTerminado()) {
                            realizarJugada();
                  }
                   
                  mostrarResultados();    
         }
         
         // FALTA IMPLEMENTAR QUE TENGA DATOS REALES
         private void imprimirPuntuaciones(Partida partida) {
                  Consola.println("Cantidad Blanco : ");
                  Consola.println("Cantidad Negro : " + "\n");
         }
         
         private  void imprimirTablero(char[][] tablero) {
                  String[] columnas = {"A","B","C","D","E","F","G","H","I","J","K","M","N"};
                  for (String col : columnas) {
                            Consola.print(col + " ");
                  }
                  Consola.println("\n");
                  // Imprime la matriz
                  for (int i = 0; i < tablero.length; i++) {
                        for (int j = 0; j < tablero[i].length; j++) {
                            Consola.print(tablero[i][j] + " ");
                        }
                        Consola.println("");
                    }
        }
         
         private void realizarJugada() {
                  Jugador jugando = jugadorActual();
                  String entrada = Consola.readln("Jugador " + turno + " (" + jugando.getNombre() + ") es su turno. Ingrese su jugada :").toUpperCase();
                  procesarJugada(entrada,jugando); 
         }
         
         private Jugador jugadorActual() {
                  if (partida.getTurno() == 'B') {
                           this.turno = "Blanco";
                           return partida.getBlanco();
                  } else {
                           this.turno = "Negro";
                           return partida.getNegro();
                  }
         }
         
         private void procesarJugada(String entrada, Jugador jugador) {
                  while (!jugadaValida) {
                           switch (entrada) {
                                    // rendición
                                    case "X" -> {                    
                                             Consola.println(jugador.getNombre() + " se ha rendido.");
                                             jugadaValida = true;
                                             }
                                    // opción de historial
                                    case "H" -> mostrarHistorial();  
                                    default -> jugadaValida = validarJugada(entrada);
                           }
                           if (!jugadaValida) {
                                entrada = Consola.readln("Accion invalida, por favor reingrese: ");
                           }
                  }
         }
         
         private boolean validarJugada(String entrada) {
                  boolean valida;
                  if(entrada.length() > 4 || (entrada.length()==4 && !partida.getConfig().esLargoVariable()) ){
                        valida = false;
                  } else {
                           // Las conidiciones son negativas, es decir, si la cumplen no me sirve y deja de ser valida la entrada
                           char col  = entrada.charAt(0);
                           char fila = entrada.charAt(1);
                           char dir  = entrada.charAt(2); 
                           valida = !( (col < 'A' || col > 'M')  || (fila < '1' || fila > '7') || ("QEDCZA".indexOf(dir) == -1) ); 
                           if (entrada.length() == 4) {
                                    char largo = entrada.charAt(3);
                                    valida = !(largo < '1' || largo > '4') ;
                           } 
                  }
                  return valida;
         }
         
         private void mostrarResultados() {
                  if (partida.getGanador() != null) {
                           Consola.println("¡Ganó " + partida.getGanador().getNombre() + "!");
                           //FALTA IMPLEMENTAR EFECTO CONFETI
                  } else {
                           Consola.println("Empate.");
                  }
         }
         
         

         private void mostrarHistorial() {
                  throw new UnsupportedOperationException("Not supported yet."); 
         }

}