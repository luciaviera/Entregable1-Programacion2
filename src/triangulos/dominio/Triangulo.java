package triangulos.dominio;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Triangulo {
         private Punto[] puntos = new Punto[3];
         private char color;
            
         private Triangulo(Punto a, Punto b, Punto c, char color) {
                  this.color = color;
                  this.puntos[0] = a;
                  this.puntos[1] = b;
                  this.puntos[2] = c;
         }
         
         public static Triangulo crear(Punto a, Punto b, Punto c, char color) {
                  return new Triangulo(a,b,c,color);       
         }
         
         //Getters
         public char getColor() { 
                  return this.color; 
         }
         public Punto[] getPuntos() {
                  return puntos;
         }
         
         @Override
         public boolean equals(Object o) {
                  boolean iguales = false;
                  if (this == o) iguales = true;
                  if (!(o instanceof Triangulo)) iguales = false;
                  Triangulo otroTriangulo = (Triangulo) o;
                  Set<Punto> t1 = new HashSet<>(Arrays.asList(this.puntos));
                  Set<Punto> t2 = new HashSet<>(Arrays.asList(otroTriangulo.getPuntos()));     
                  
                  return t1.equals(t2);
         }
         
         @Override
        public int hashCode() {
            return new HashSet<>(Arrays.asList(puntos)).hashCode();
        }

}
