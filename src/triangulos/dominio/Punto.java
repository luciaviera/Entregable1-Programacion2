package triangulos.dominio;

import java.util.Set;

public class Punto {
         private char columna;
         private int fila;
         
         private static final Set<String> coordenadasInvalidas = Set.of(
                "A1", "A2", "A3", "A5", "A6", "A7",
                "B1", "B2", "B4", "B6", "B7",
                "C1", "C3", "C5", "C7",
                "D2", "D4", "D6",
                "E1","E3","E5","E7",
                "F2","F4","F6",
                "G1", "G3","G5","G7",
                "H2", "H4","H6",
                "I1","I3","I5","I7",
                "J2","J4","J6",
                "K1", "K3", "K5", "K7",
                "L1", "L2", "L4", "L6", "L7",
                "M1", "M2", "M3", "M5", "M6", "M7"
        );
    
         //Contructor privado
         private Punto(char columna, int fila) {
                  this.columna = columna;
                  this.fila = fila;
         }

         //Creacion de puntos
         public static Punto crear(char col, int fila) {
                  String coord = "" + col + fila;
                    if (coordenadasInvalidas.contains(coord)) throw new IllegalArgumentException("El punto no existe en el tablerooo.");
                  return new Punto(col, fila);
         }
         
         //Getters
         public char getColumna() {
                  return columna;
         }

         public int getFila() {
                  return fila;
         }
         
         //Calculan los indices correspondientes para la amtriz Tablero
         public int obtenerIndiceFila(){
                  return (this.fila -1)*2;
         }
         public int obtenerIndiceColumna(){
                   return (this.columna - 'A')*2;
         }

        @Override
         public boolean equals(Object o) {
                  if (this == o) return true;
                  if (!(o instanceof Punto otro)) return false;
                  return this.columna == otro.columna && this.fila == otro.fila;
         }
         
         @Override
         public int hashCode() {
                  return 31 * columna + fila;
         }

         @Override
         public String toString() { return "" + this.columna + this.fila; }
}
