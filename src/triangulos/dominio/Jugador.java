package triangulos.dominio;

import java.util.Objects;

public class Jugador {

    
         private String nombre;
         private int edad;
         
         //Getters y setters
         public String getNombre() {
                  return nombre;
         }
         public int getEdad() {
                 return edad;
         }
         
         //Constructor
         public Jugador(String nombre, int edad) {
                    this.nombre = Objects.requireNonNull(nombre).trim();
                    if (this.nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede estar vacío");
                    if (edad < 1 || edad > 120)    throw new IllegalArgumentException("Edad fuera de rango");
                    this.edad = edad;
        }
         
         
         
}
         
