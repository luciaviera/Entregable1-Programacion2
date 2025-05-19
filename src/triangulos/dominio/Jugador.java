package triangulos.dominio;

public class Jugador {

    
         private String nombre;
         private int edad;
         private char color;
         private int racha = 0;
         private int victorias = 0;
         
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
         public int getRacha() {
                  return this.racha;
         }
         public int getVictorias() {
                  return this.victorias;
         }
         
         
         //Setters
         public void setColor(char color) {
                  this.color = color;
         }
         public void setRacha(int racha) {
                  this.racha = racha;
         }
         public void setVictorias(int victorias) {
                  this.victorias = victorias;
         }
         
         
         //Constructor
         public Jugador(String nombre, int edad) {
                    this.nombre = nombre;
                    if (this.nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede estar vacío");
                    if (edad < 1 || edad > 120)    throw new IllegalArgumentException("Edad fuera de rango");
                    this.edad = edad;
        }
         
         @Override
         public boolean equals(Object o) {
                  boolean iguales = false;
                  Jugador otro = (Jugador) o;
                  if (this.nombre.equalsIgnoreCase(otro.getNombre())) iguales = true;
                  return iguales;
         }

         @Override
         public String toString() {
                  String str = this.getNombre() + " (" + this.getEdad() +" AÑOS) ";
                  return str;
         }
}
         
