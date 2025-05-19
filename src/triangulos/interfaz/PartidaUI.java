package triangulos.interfaz;

import triangulos.dominio.*;

public class PartidaUI {
         private Partida partida;
         
         public void iniciarPartida(Jugador[] judaoresSeleccioandos, ConfiguracionDePartida config) {
                  
                  this.partida = new Partida(judaoresSeleccioandos, config);
                  Consola.println("\n¡Comienza la partida!");

                  Consola.println("Blanco: " + this.partida.getBlanco() + "  vs  Negro: " + this.partida.getNegro()+"\n");
                  this.imprimirPuntuaciones();
                  this.imprimirTablero(this.partida.getTablero().getRepTablero());
                  
                  Consola.println("\nLas jugadas deben tener el siguiente formato: <Columna><Fila><Dirección>[Largo]");
                  Consola.println("Columna: A–M, Fila: 1–7, Dirección: Q/E/D/C/Z/A, Largo (Debe estar activado el largo variable de bandas): 1–4");
                  Consola.print("Ejemplo: A4D o C3Q2\n");

                  //Loop de turnos
                  while (!this.partida.haTerminado()) {
                           this.ingresarJugada();
                           this.imprimirTablero(this.partida.getTablero().getRepTablero());
                  }        
                  this.mostrarResultados();    
         }
         
         private void imprimirPuntuaciones() {
                  Consola.println("Cantidad Blanco : " + this.partida.getPuntajeBlanco());
                  Consola.println("Cantidad Negro : " + this.partida.getPuntajeNegro() + "\n");
         }

         private  void imprimirTablero(String[][] tablero) {
                  String[] columnas = {"A","B","C","D","E","F","G","H","I","J","K","M","N"};
                  for (String col : columnas) {
                            Consola.print(col + " ");
                  }
                  Consola.println("\n");
                  // Imprime la matriz
                  for (int i = 0; i < tablero.length; i++) {
                           for (int j = 0; j < tablero[i].length; j++) {
                                    Consola.print(tablero[i][j]);
                           }
                           Consola.println("");
                  }
                  Consola.println("");
        }

         private void ingresarJugada() {
                  String entrada = Consola.readln("Jugador " + this.imprimirColor() + " (" + this.partida.jugadorActual().getNombre() + ") es su turno. Ingrese su jugada :");
                  boolean jugadaValida = false;
                  while (!jugadaValida) {
                           if (entrada.equals("H")) {
                                    this.mostrarHistorial();
                                    jugadaValida = true;
                           } else {
                                    try {
                                             this.partida.realizarJuegada(entrada, this.partida.getTablero());
                                             jugadaValida = true;
                                    } catch (IllegalArgumentException e) {
                                             Consola.error(e.getMessage());
                                             entrada = Consola.readln("Por favor reingrese: ");
                                    }
                           }
                  }
         }

         private String imprimirColor() {
                  String color;
                  if (this.partida.getTurno() == 'B') {
                           color = "Blanco";
                  } else {
                           color = "Negro";
                  }
                  return color;
         }

         private String mostrarHistorial(){
             return null; 
         }
         
         private void mostrarResultados() {
                  if (this.partida.getGanador() != null) {
                           Consola.println("¡Ganó " + this.partida.getGanador().getNombre() + "!");
                           //FALTA IMPLEMENTAR EFECTO CONFETI
                  } else {
                           Consola.println("Empate.");
                  }
         }

}
