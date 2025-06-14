package triangulos.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Partida {
         
         private char turno;
         private  Jugador blanco;
         private  Jugador negro;
         private ConfiguracionDePartida config;
         private int puntajeBlanco = 0;
         private int puntajeNegro = 0;
         private  Tablero tablero = new Tablero(this); 
         private ArrayList<String[][]> tablerosVisibles = new ArrayList<>();
         private  ArrayList<Movimiento> historial = new ArrayList<>();
         private Jugador ganador;
         private Jugador perdedor;
         private boolean terminada = false;
         
         //Constructor
         public Partida(Jugador[] jugadores, ConfiguracionDePartida config) {
                  this.config  =  config;
                  
                  if (jugadores.length == 0) throw new IllegalArgumentException("No se han seleccioando jugadores");
                  if (jugadores.length != 2) throw new IllegalArgumentException("Se necesitan exactamente 2 jugadores");
                  
                  // Elegir blanco y negro aleatoriamente
                  List<Jugador> listaJugadores = Arrays.asList(jugadores);
                  Collections.shuffle(listaJugadores);
                  this.blanco = listaJugadores.get(0);
                  this.negro  = listaJugadores.get(1);
                  //Asignarle los colores correspondientes a cada jugador
                  this.blanco.setColor('B');
                  this.negro.setColor('N');
                  
                  //Arranca la partida jugando el jugador Blanco
                  this.turno = 'B';
                  
                  this.tablerosVisibles.add(clonarMatriz(this.tablero.getRepTablero()));
         }
         
         //Getters
         public Jugador getBlanco() {
                  return this.blanco;
         }
         public Jugador getNegro() {
                  return this.negro;
         }
         public Tablero getTablero() {
                  return this.tablero;
         }
         public Jugador getGanador() {
                  return this.ganador;
         }
         public char getTurno() {
                  return this.turno;
         }
         public int getPuntajeBlanco() {
                  return this.puntajeBlanco;
         }
         public int getPuntajeNegro() {
                  return this.puntajeNegro;
         }
         public ConfiguracionDePartida getConfig() {
                  return this.config;
         }
         public boolean haTerminado() {
                  return this.terminada;
         }
         public Jugador getPerdedor() {
                  return perdedor;
         }
         public ArrayList<Movimiento> getHistorial() {
                  return historial;
         }
         public ArrayList<String[][]> getTablerosVisibles() {
                  return tablerosVisibles;
         }
         
         //Setters
         public void setPuntajeBlanco(int puntajeBlanco) {
                  this.puntajeBlanco = puntajeBlanco;
         }
         public void setPuntajeNegro(int puntajeNegro) {
                  this.puntajeNegro = puntajeNegro;
         }
         
        
         public void realizarJuegada (String entrada, Tablero tablero){                 
                  
                  if (entrada.equals("X")) {
                           this.abandonar();
                  } else {
                           if (entrada.length() == 4 && !this.config.isLargoVariable()) throw new IllegalArgumentException("No ingrese el largo de la banda, largo variable esta desactivado");
                           
                           Movimiento mov = Movimiento.crear (entrada, tablero);
                           //Verifico si la jugada es admisible en el tablero actual y de serlo me la agrega
                           tablero.agregarBanda(mov);
                          
                           refrezcarTableros();
                            //Actualizo el estado de la partida
                           historial.add(mov);  //Agrego jugada al historial
                           this.terminada = (tablero.getBandas().size() == config.getCantMaxBandas());   //Verifico si hay que terminar la partida
                           if (terminada) {
                                    this.hallarGanador();
                                    this.agregarVictoria();
                                    this.aumentarRacha();
                           }
                           cambiarTurno();
                  }
         }
         
         //Devuelve el jugador del turno actual
         public Jugador jugadorActual() {
                  if (this.turno == 'B') {
                           return this.getBlanco();
                  } else {
                           return this.getNegro();
                  }
         }
         
         private void cambiarTurno() {
                  if (this.turno == 'B') {
                           this.turno = 'N';
                  } else {
                           this.turno = 'B';
                  }
         }
         
         public void abandonar() {
                  if (this.turno == 'B') {
                           this.ganador = this.negro;
                  } else {
                           this.ganador = this.blanco;
                  }
                  this.aumentarRacha();
                  this.agregarVictoria();
                  this.terminada = true;
         }
         
         private void hallarGanador(){
                  if (this.puntajeBlanco > this.puntajeNegro) {
                           this.ganador = this.blanco;
                  } 
                  if (this.puntajeBlanco < this.puntajeNegro) {
                           this.ganador = this.negro;
                  }
         }
         
         private void agregarVictoria(){
                  int vActualizadas = this.ganador.getVictorias() +1;
                  this.jugadorActual().setVictorias(vActualizadas);
                  
         }
         
         private void aumentarRacha(){
                  int rActualizada = this.ganador.getRacha() +1;
                  this.jugadorActual().setRacha(rActualizada);
         }
         
         private void refrezcarTableros() {
                  int cantAVerse = this.config.getHistorialDeTableros();
                  if (this.tablerosVisibles.size() >= cantAVerse) {
                           this.tablerosVisibles.remove(0);
                  }
                  this.tablerosVisibles.add(clonarMatriz(this.tablero.getRepTablero()));
         }
         
         private String[][] clonarMatriz(String[][] original) {
                  int filas = original.length;
                  int columnas = original[0].length;
                  String[][] copia = new String[filas][columnas];
                   for (int i = 0; i < filas; i++) {
                           for (int j = 0; j < columnas; j++) {
                                    copia[i][j] = original[i][j];
                           }
                   }
                  return copia;
         }
}  

