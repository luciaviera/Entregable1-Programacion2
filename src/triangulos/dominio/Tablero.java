
package triangulos.dominio;

import java.util.ArrayList;

public class Tablero {
         
         private ArrayList<Banda> bandas; 

         //Representacion del tablero
         private char[][] tableroInicial= {
            //A  B   C  D  E   F  G  H   I    J  K  M  N
            { ' ', ' ', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', ' ', ' ' }, // fila 0
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, // fila 1
            { ' ', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', ' ' }, // fila 2
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, // fila 3
            { ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ' }, // fila 4
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, // fila 5
            { '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*' }, // fila 6
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, // fila 7
            { ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ' }, // fila 8
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, // fila 9
            { ' ', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', ' ' }, // fila 10
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, // fila 11
            { ' ', ' ', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', ' ', ' ' }  // fila 12
         };
          
         public char[][] getTableroInicial() {
                  return this.tableroInicial;
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
}

