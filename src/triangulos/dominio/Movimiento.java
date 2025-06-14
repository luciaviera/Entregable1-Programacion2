package triangulos.dominio;

//Crea jugadas que el programa pueda entender (no implica que se pueda colocar la banda en el tablero)
public class Movimiento {

         private Punto origen;
         private char dir;
         private int largo;
         
         //Constructor privado
         private Movimiento(Punto origen, char dir, int largo) {
                  this.origen = origen;
                  this.dir = dir;
                  this.largo = largo;
         }
         
         //Creacion de movimiento
         public static Movimiento crear(String entrada, Tablero tablero) {
                  int largoEntrada = entrada.length();
                  if (largoEntrada  < 3 || largoEntrada > 4) throw new IllegalArgumentException("No ingreso la jugada correctamente");

                  //Creo punto
                  int fila = entrada.charAt(1)  - '0'; 
                  char col = entrada.charAt(0);
                  if (col < 'A' || col > 'M') throw new IllegalArgumentException("Columna debe ser A-M");
                  if (fila < 1 || fila > 7) throw new IllegalArgumentException("Fila debe ser 1-7");
                  Punto punto = Punto.crear(col, fila);
                  
                  //Valido la direccion
                  char dir   = entrada.charAt(2);
                  if ("QEDCZA".indexOf(dir) == -1) throw new IllegalArgumentException("Dirección inválida (Q,E,D,C,Z,A)");
                  
                  int largo = 4; //largo por defecto
                  //Valido el largo de haberse indicado
                  
                  if (largoEntrada == 4) {
                           largo = entrada.charAt(3) - '0';
                           if (largo < 1 || largo > 4) throw new IllegalArgumentException("Largo debe ser 1-4");
                  }
                  
                  return new Movimiento(punto, dir, largo);
         }

         
         //Getters
         public Punto getOrigen(){ 
                  return this.origen; 
         }
         public char getDir() { 
                  return this.dir; }
         public int  getLargo() { 
                  return this.largo; 
         }
         
         @Override
         public String toString() {
                  return this.origen.toString() + dir + largo;
         }
        
}
