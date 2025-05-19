
package triangulos.dominio;

import java.util.ArrayList;

public class Tablero {
         
         private ArrayList<Banda> bandas; 

         //Representacion del tablero

         
         private String[][] repTablero = {
                   // A        B         C         D         E         F        G        H          I          J          K        M        N
                   { " ", " ", " ", " ", " ", " ", "*", " ", " ", " ", "*", " ", " " ," ", "*", " ", " ", " ", "*", " ", " ", " ", " ", " ", " "}, // fila 1
                   { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ," ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                   { " ", " ", " ", " ", "*", " ", " ", " ", "*", " ", " ", " ", "*" ," ", " ", " ", "*", " ", " ", " ", "*", " ", " ", " ", " "}, // fila 2
                   { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ," ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "}, 
                   { " ", " ", "*", " ", " ", " ", "*", " ", " ", " ", "*", " ", " " ," ", "*", " ", " ", " ", "*", " ", " ", " ", "*", " ", " "}, // fila 3
                   { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ," ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                   { "*", " ", " ", " ", "*", " ", " ", " ", "*", " ", " ", " ", "*" ," ", " ", " ", "*", " ", " ", " ", "*", " ", " ", " ", "*"}, // fila 4
                   { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ," ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                   { " ", " ", "*", " ", " ", " ", "*", " ", " ", " ", "*", " ", " " ," ", "*", " ", " ", " ", "*", " ", " ", " ", "*", " ", " "}, // fila 5
                   { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ," ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                   { " ", " ", " ", " ", "*", " ", " ", " ", "*", " ", " ", " ", "*" ," ", " ", " ", "*", " ", " ", " ", "*", " ", " ", " ", " "}, // fila 6
                   { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ," ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                    { " ", " ", " ", " ", " ", " ", "*", " ", " ", " ", "*", " ", " " ," ", "*", " ", " ", " ", "*", " ", " ", " ", " ", " ", " "}, // fila 7
         };

          
         public String[][] getRepTablero() {
                  return this.repTablero;
         }
         
         public ArrayList<Banda>  getBandas() {
                  return bandas;
         }

         public boolean agregarBanda(Movimiento mov, char turno, ConfiguracionDePartida config) {
                  
                  //Se crea la banda (solo si es valdia)
                  Banda banda = Banda.crear(mov, turno);
                  boolean agregada = false;
                  
                  //Si no hay bandas colocadas en el tablero se coloca directamente
                  if (this.bandas.isEmpty()) {
                           this.bandas.add(banda);
                           agregada = true;
                  }
                  
                  //De haber bandas verifica si la reglade contacto esta activada para colocarla
                  if (config.isReglaDeContacto()) {
                           for (Banda b : this.bandas) {
                                    if (b.compartePuntosCon(banda)) {
                                             bandas.add(banda);
                                             agregada = true;
                                    }
                           }
                  } else {
                           bandas.add(banda);
                           agregada = true;
                  }
                  
                  return agregada;
         }
         
         private void trazarSegmentoBanda(Punto pInicial, Punto pFinal, char dir){
                  int filaPI = pInicial.obtenerIndiceFila();
                  int colPI = pInicial.obtenerIndiceColumna();
                  int filaPF = pFinal.obtenerIndiceFila();
                  int colPF = pFinal.obtenerIndiceColumna();
                  
                  int posFila = (filaPI + filaPF)/2; //FUNCIONA
                  int posCol = (colPI + colPF)/2; //A VERIFICAR
                  
                  String trazo;
                  
                  switch (dir) {
                           case 'D','A':
                                    trazo = "___";
                           case 'E','Z':
                                    trazo = "/";
                           case 'Q','C': 
                                    trazo = "\\";
                           default: 
                                    throw new IllegalArgumentException("No se pudo realziar el trazado en el tablero");
                  }
                  
                  
         }
}

