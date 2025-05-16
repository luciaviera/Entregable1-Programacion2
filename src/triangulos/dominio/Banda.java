package triangulos.dominio;

import java.util.ArrayList;

public class Banda {

         private Punto pInicial;
         private Punto pFinal;
         private char dir;      // 'Q','E','D','C','Z','A'
         private int  largo;    // 1..4
         private char color;  // 'B' o 'N'

         //Constructor privado
         private Banda(Punto p1, Punto p2, char dir, int largo, char color) {
                  this.pInicial = p1;
                  this.pFinal   = p2;
                  this.dir      = dir;
                  this.largo    = largo;
                  this.color = color;
          }
         
         //Creacion de Banda (de poderse)
         static Banda crear(Movimiento mov, char turno) {
                  Punto pInicial = mov.getOrigen();
                  char dir    = mov.getDir();
                  int  largo  = mov.getLargo();
                  char color = turno;
                  Punto pFinal;
                  try {
                           pFinal = hallarPuntoDestino(mov);
                  } catch (IllegalArgumentException e) {
                           throw new IllegalArgumentException("La Banda no se puede crear, se va del tablero");
                  }
                  return new Banda (pInicial, pFinal, dir, largo, color);
         }
         
         //Encuentro el punto final de una jugada de existir dentro del tablero
         public static Punto hallarPuntoDestino(Movimiento mov){
                  int filaInicial= mov.getOrigen().getFila();
                  char colInicial = mov.getOrigen().getColumna();
                  char dir = mov.getDir();
                  int largo = mov.getLargo();
                  //inicializo las variables
                  int filaFinal = 1;
                  char colFinal = 'A';
                  switch (dir) {
                           case 'Q' -> {
                               filaFinal = filaInicial - 1*largo;
                               colFinal = (char) (colInicial - 1*largo);
                           }
                           case 'E' -> {
                               filaFinal = filaInicial - 1*largo;
                               colFinal = (char) (colInicial + 1*largo);
                           }
                           case 'D' -> {
                               filaFinal = filaInicial;
                               colFinal = (char) (colInicial + 1*largo);
                           }
                           case 'C' -> {
                               filaFinal = filaInicial + 1*largo;
                               colFinal = (char) (colInicial + 1*largo);
                           }
                           case 'Z' -> {
                               filaFinal = filaInicial + 1*largo;
                               colFinal = (char) (colInicial - 1*largo);
                           }
                           case 'A' -> {
                               filaFinal = filaInicial;
                               colFinal = (char) (colInicial - 1*largo);
                           }
                  }
                  return Punto.crear(colFinal, filaFinal);
         }
         
         //Obteine todos los puntos pertenecientes a una banda
         public ArrayList<Punto> getPuntosInternos(){
                  ArrayList<Punto> puntos = new ArrayList<>();
                  int fila = this.pInicial.getFila();
                  char col = this.pInicial.getColumna();
                  for (int i = 0; i <= largo; i++) { 
                           int filaIntermedia = fila;
                           char colIntermedia = col;
                           switch (dir) {
                                    case 'Q' -> {
                                             filaIntermedia= fila - i;
                                             colIntermedia = (char) (col - i);
                                    }
                                    case 'E' -> {
                                             filaIntermedia = fila - i;
                                             colIntermedia = (char) (col + i);
                                    }
                                    case 'D' -> {
                                             filaIntermedia = fila;
                                             colIntermedia = (char) (col + i);
                                    }
                                    case 'C' -> {
                                             filaIntermedia = fila + i;
                                             colIntermedia = (char) (col + i);
                                    }
                                    case 'Z' -> {
                                             filaIntermedia = fila + i;
                                             colIntermedia= (char) (col - i);
                                    }
                                    case 'A' -> {
                                             filaIntermedia = fila;
                                             colIntermedia= (char) (col - i);
                                    }
                           }
                           puntos.add(Punto.crear(colIntermedia, filaIntermedia));
                  }
                  return puntos;
         }
         
         public boolean compartePuntosCon(Banda otra) {
                  ArrayList<Punto> misPuntos = this.getPuntosInternos();
                  ArrayList<Punto> susPuntos = otra.getPuntosInternos();
                  boolean comparten = false;
                  for (Punto punto : misPuntos) {
                           if (susPuntos.contains(punto)) {
                                    comparten = true;
                            }
                  }
                  return comparten;
         }
}
