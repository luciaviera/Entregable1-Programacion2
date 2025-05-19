
package triangulos.dominio;

import java.util.ArrayList;

public class Tablero {
         
         private ArrayList<Banda> bandas = new ArrayList<>();

         //Representacion del tablero
         private String[][] repTablero = {
                   // A        B         C         D         E         F        G        H          I          J          K        L        M
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
                  System.out.println("banda creada en agreg");
                  boolean agregada = false;
                  
                  //De haber bandas verifica si la reglade contacto esta activada para colocarla
                  if (config.isReglaDeContacto() && !this.bandas.isEmpty() ) {
                           boolean comparte = false;
                           //Verifgico si comparte algun pto con otra banda
                           for (Banda b : this.bandas) {
                                    if (b.compartePuntosCon(banda)) {
                                             comparte = true;
                                    }
                           }
                           //De hacerlo la agrega
                           if (comparte){
                                    bandas.add(banda);
                                    this.trazarBanda(banda);
                                    agregada = true;
                           }
                  } else {
                           bandas.add(banda);
                           this.trazarBanda(banda);
                           agregada = true;
                           System.out.println("se agrego banda");
                  }
                  
                  return agregada;
         }
         
         private void trazarBanda(Banda banda){
                  ArrayList<Punto> ptsBanda = banda.getPuntosInternos();
                  for (int i = 0; i < ptsBanda.size() -1; i++) {
                           Punto p1 = ptsBanda.get(i);
                           Punto p2 = ptsBanda.get(i+1);
                           this.trazarSegmentoBanda(p1, p2, banda.getDir());
                  }
         }
         
         private void trazarSegmentoBanda(Punto pInicial, Punto pFinal, char dir){
                  int filaPI = pInicial.obtenerIndiceFila();
                  int colPI = pInicial.obtenerIndiceColumna();
                  int filaPF = pFinal.obtenerIndiceFila();
                  int colPF = pFinal.obtenerIndiceColumna();
                  
                  int posFila = (filaPI + filaPF)/2;
                  int posCol = (colPI + colPF)/2 ;

                  String trazo;
                  
                  switch (dir) {
                           case 'D','A':
                                    trazo = "-";
                                    break;
                           case 'E','Z':
                                    trazo = "/";
                                    break;
                           case 'Q','C': 
                                    trazo = "\\";
                                    break;
                           default: 
                                    throw new IllegalArgumentException("No se pudo realziar el trazado en el tablero");
                  }

                  if (dir == 'D' || dir == 'A') {
                           this.repTablero[posFila][posCol + 1] = trazo;
                           this.repTablero[posFila][posCol - 1] = trazo;      
                  }
                  
                  this.repTablero[posFila][posCol] = trazo;
         }
         
}

