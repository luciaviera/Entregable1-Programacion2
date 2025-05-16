package triangulos.dominio;

import java.util.Objects;

public class Jugador {

    
         private String nombre;
         private int edad;
         private char color;
         
         //Getters y setters
         public String getNombre() {
                  return nombre;
         }
         public int getEdad() {
                 return edad;
         }
         
         public int getColor() {
                 return color;
         }
         
         public void setColor(char color) {
                  this.color = color;
         }
         
         //Constructor
         public Jugador(String nombre, int edad) {
                    this.nombre = Objects.requireNonNull(nombre).trim();
                    if (this.nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede estar vacío");
                    if (edad < 1 || edad > 120)    throw new IllegalArgumentException("Edad fuera de rango");
                    this.edad = edad;
        }
         
         @Override
         public boolean equals(Object o) {
                   if (this == o) return true;
                   if (!(o instanceof Jugador)) return false;
                   Jugador otro = (Jugador) o;
                   return nombre.equalsIgnoreCase(otro.nombre);
         }

         @Override
         public int hashCode() {
                  // Debe usarse la misma base que equals:
                  return nombre.toLowerCase().hashCode();
         }

         @Override
         public String toString() {
                  return "%s (%d años)".formatted(nombre, edad);
         }
}
         
