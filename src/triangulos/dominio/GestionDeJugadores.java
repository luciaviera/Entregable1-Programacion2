package triangulos.dominio;

import java.util.ArrayList;
import java.util.Comparator;

public class GestionDeJugadores {
        
         private ArrayList<Jugador> jugadores = new ArrayList<>();
         private ArrayList<Jugador> ranking = jugadores;

         //Getters
         public ArrayList<Jugador> getJugadores(){
             return this.jugadores;
         }
         public ArrayList<Jugador> getRanking() {
                  return this.ranking;
         }
         

         public void registrarJugador(Jugador j) {
                  if (jugadores.contains(j)) {
                        throw new IllegalArgumentException("El jugador ya estaba registrado");
                  }
                  jugadores.add(j);
                  jugadores.sort(Comparator.comparing(Jugador::getNombre, String.CASE_INSENSITIVE_ORDER));
         }
         
         public void hayMinimoDeJugadores() {
                   if (this.jugadores.size() < 2) throw new IllegalArgumentException("Es necesario tener al menos dos jugadores registrados para poder jugar");
         }
         
         public Jugador[] seleccionarJugadores(int indiceJ1, int indiceJ2){
                  if (indiceJ1 == 0 || indiceJ1 > this.jugadores.size() ) throw new IllegalArgumentException("Numero indicado para el primero jugador no admisible");
                  if (indiceJ2 == 0 || indiceJ2 > this.jugadores.size() )  throw new IllegalArgumentException("Numero indicado para el segundo jugador no admisible");
                  if (indiceJ1 == indiceJ2) throw new IllegalArgumentException("Los jugadores no pueden ser el mismo");
                  Jugador jugador1 = this.jugadores.get(indiceJ1 - 1);
                  Jugador jugador2 = this.jugadores.get(indiceJ2 - 1);
                  
                  Jugador[] seleccionados = {jugador1, jugador2};
                  return seleccionados;
         }
         
         public void actualizarRanking(){
                  this.ranking.sort((j1, j2) -> Integer.compare(j2.getVictorias(), j1.getVictorias()));
         }
         
         public ArrayList<Jugador> jugadoresDeMayorRacha(){
                  ArrayList<Jugador> resultado = new ArrayList<>();
                  int maxRacha = 1;
                  
                  // Buscar la mayor racha
                  for (Jugador jugador : this.jugadores) {
                           if (jugador.getRacha() >= maxRacha) {
                                    maxRacha = jugador.getRacha();
                           }
                  }
                  
                  //Guardo los jugadores con esa racha en el array
                  for (Jugador j : this.jugadores) {
                           if (j.getRacha() == maxRacha) {
                                    resultado.add(j);
                            }
                  }
                  return resultado;
         }

}
