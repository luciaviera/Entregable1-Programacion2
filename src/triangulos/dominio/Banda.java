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
                           System.out.println("Se creo una banda");
                  } catch (IllegalArgumentException e) {
                           throw new IllegalArgumentException("La Banda no se puede crear, se va del tablero");
                  }
                  return new Banda (pInicial, pFinal, dir, largo, color);
         }
         
         //Getters
         public Punto getpInicial() {
                  return pInicial;
         }
         public Punto getpFinal() {
                  return pFinal;
         }
         public char getDir() {
                  return dir;
         }
         public int getLargo() {
                  return largo;
         }

         //Encuentro el punto final de una banda de existir
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
                               colFinal = (char) (colInicial + 2*largo);
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
                               colFinal = (char) (colInicial - 2*largo);
                           }
                  }
                  return Punto.crear(colFinal, filaFinal);
         }
         
         //Obteine todos los puntos pertenecientes a una banda
         public ArrayList<Punto> getPuntosInternos(){
                  ArrayList<Punto> puntos = new ArrayList<>();
                  int filaInicial = this.pInicial.getFila();
                  char colInicial = this.pInicial.getColumna();
                  for (int i = 0; i <= largo; i++) { 
                           int fila = filaInicial;
                           char col = colInicial;
                           switch (dir) {
                                    case 'Q' -> {
                                             fila= filaInicial - i;
                                             col = (char) (colInicial - i);
                                    }
                                    case 'E' -> {
                                             fila = filaInicial - i;
                                             col = (char) (colInicial + i);
                                    }
                                    case 'D' -> {
                                             col = (char) (colInicial + 2*i);
                                    }
                                    case 'C' -> {
                                             fila = filaInicial + i;
                                             col = (char) (colInicial + i);
                                    }
                                    case 'Z' -> {
                                             fila = filaInicial + i;
                                             col = (char) (colInicial - i);
                                    }
                                    case 'A' -> {
                                             col= (char) (colInicial - 2*i);
                                    }
                           }
                           
                           puntos.add(Punto.crear(col, fila));
                  }
                  return puntos;
         }
         
         //Verifica si 2 bandas tienen algun punto en comun
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
