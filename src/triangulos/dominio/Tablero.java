
package triangulos.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tablero {
        
         private Partida partida;
         private ArrayList<Banda> bandas = new ArrayList<>();
         private Set<Triangulo> triangulos = new HashSet<>();
         private Map<Punto, Set<Punto>> ptosConectados = new HashMap<>(); // Me guarda un punto y a que puntos esta conectado mediante bandas (como mucho 4)

         //Constructor
         public Tablero (Partida partida)  {
                  this.partida = partida;
         }

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

         public boolean agregarBanda(Movimiento mov) {
                  
                  char turno = this.partida.getTurno();
                  ConfiguracionDePartida config = this.partida.getConfig();
                  
                  //Se crea la banda (solo si es valdia)
                  Banda banda = Banda.crear(mov, turno);
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
                           if (!comparte) throw new IllegalArgumentException("Jugada no realizable. Debe compartir algun punto con una banda previa");
                           //De hacerlo la agrega
                           if (comparte){
                                    bandas.add(banda);
                                    this.procesarBanda(banda);
                                    agregada = true;
                           }
                  } else {
                           bandas.add(banda);
                           this.procesarBanda(banda);
                           agregada = true;
                  }
                  return agregada;
                  
         }
         
         private void procesarBanda(Banda banda){
                  ArrayList<Punto> ptsBanda = banda.getPuntosInternos();
                  //Recorro segmentos de largo 1 de la banda
                  for (int i = 0; i < ptsBanda.size() -1; i++) {
                           Punto p1 = ptsBanda.get(i);
                           Punto p2 = ptsBanda.get(i+1);
                           
                           //Registro que comparten banda
                           this.mapPuntos(p1,p2);
                           this.mapPuntos(p2, p1);
                           
                           this.trazarSegmentoBanda(p1, p2, banda.getDir());
                           
                           this.detectarTriangulos(p1,p2);
                  }
         }
         
         private void mapPuntos(Punto punto, Punto ptoVecino){
                  Set<Punto> vecinos = this.ptosConectados.get(punto); //Me devuelve las adyacencias registradas del punto
                  //De no haber ninguna registrada, creo el set
                  if (vecinos == null) {
                           vecinos = new HashSet<>(); 
                           this.ptosConectados.put(punto, vecinos);
                  }
                  //Agrego pto a sus ayacentes (de no estarlo)
                  vecinos.add(ptoVecino);
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
         
         private void detectarTriangulos(Punto p1, Punto p2) {
                  
                           for (Punto pAdy : this.ptosConectados.get(p1)){ //Recorro los puntos adyacentes a p1
                      
                           if (pAdy.equals(p2)) continue;
                          
                           if (this.ptosConectados.get(p2).contains(pAdy)) { //Veo si los puntos adyacentes a pd tienen al punto adyacente a p1
                                    Triangulo triangulo = Triangulo.crear(p1, p2, pAdy, this.partida.getTurno());
                                    if (!triangulos.contains(triangulo)) { //No se actualice la puntuacion si ya se habia formado el triangulo antes
                                             this.actualizarPuntajes(triangulo);
                                    }
                                    triangulos.add(triangulo);
                           }
                  }
         }
         
         private void actualizarPuntajes(Triangulo triangulo) {
                  int puntajeActual;
                  String trazo;
                  if (this.partida.getTurno() == 'B') {
                           puntajeActual = this.partida.getPuntajeBlanco();
                           this.partida.setPuntajeBlanco(puntajeActual + 1);
                           trazo = "□";
                  } else {
                           puntajeActual = this.partida.getPuntajeNegro();
                           this.partida.setPuntajeNegro(puntajeActual +1);
                           trazo = "■";
                  }
                  this.trazarTriangulo(triangulo, trazo);
         }
         
         private void trazarTriangulo(Triangulo triangulo, String trazo){
                  Punto p1 = triangulo.getPuntos()[0];
                  Punto p2 = triangulo.getPuntos()[1];
                  Punto p3 = triangulo.getPuntos()[2];
                  int col = (p1.obtenerIndiceColumna() + p2.obtenerIndiceColumna() + p3.obtenerIndiceColumna())/3;
                  int fila = Math.min(p1.obtenerIndiceFila(), Math.min(p2.obtenerIndiceFila(), p3.obtenerIndiceFila()))+1;
                  this.repTablero[fila][col] = trazo;
         }
         
}

