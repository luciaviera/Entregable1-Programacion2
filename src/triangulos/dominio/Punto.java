package triangulos.dominio;

import java.util.Set;

public class Punto {
         private char columna;
         private int fila;
         
         private static final Set<String> coordenadasInvalidas = Set.of(
                "A1", "A2", "A3", "A5", "A6", "A7",
                "B1", "B2", "B6", "B7",
                "C1", "C7",
                "K1", "K7",
                "L1", "L2", "L6", "L7",
                "M1", "M2", "M3", "M5", "M6", "M7"
        );
    
         //Contructor privado
         private Punto(char columna, int fila) {
                  this.columna = columna;
                  this.fila = fila;
         }
         
         //Creacion de puntos
         public static Punto crear(char col, int fila) {
                  //Valido coordenadas
                  if (col < 'A' || col > 'M') throw new IllegalArgumentException("Columna debe ser A-M");
                  if (fila < 1 || fila > 7) throw new IllegalArgumentException("Fila debe ser 1-7");
                  //Verifico que este en la representacion del tablero
                  if (coordenadasInvalidas.contains("" + fila + col)) throw new IllegalArgumentException("El punto no existe en el tablero.");
                  return new Punto(col, fila);
         }
         
         //Getters
         public char getColumna() {
                  return columna;
         }

         public int getFila() {
                  return fila;
         }

         @Override
         public boolean equals(Object o) {
                  boolean iguales = false;
                  if (o != null) {
                           Punto punto = (Punto) o;
                           iguales = (punto.fila == this.fila  &&  this.columna == punto.columna);
                  }
                  return iguales;
         }
         
         @Override
         public String toString() { return "" + this.columna + this.fila; }
}
