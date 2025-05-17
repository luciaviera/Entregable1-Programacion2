package triangulos.dominio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RegistroDeJugadores {
         private ArrayList<Jugador> jugadores = new ArrayList<>();
         
         //Getters
         public ArrayList<Jugador> getJugadores(){
             return this.jugadores;
         }
         
         /** Agrega un jugador al registro; lanza si ya existía */
         public void registrar(Jugador j) {
                  if (jugadores.contains(j)) {
                        throw new IllegalArgumentException("El jugador ya estaba registrado");
                  }
                  jugadores.add(j);
                  jugadores.sort(Comparator.comparing(Jugador::getNombre, String.CASE_INSENSITIVE_ORDER));
         }
         
         public boolean hayMinimo() {
                  return jugadores.size() >= 2;
         }
         
         public Jugador[] seleccionarJugadores(int indiceJ1, int indiceJ2){
                  if (indiceJ1 == indiceJ2) throw new IllegalArgumentException("Los jugadores no pueden ser el mismo");
                  Jugador jugador1 = jugadores.get(indiceJ1 - 1);
                  Jugador jugador2 = jugadores.get(indiceJ2 - 1);
                  
                  Jugador[] seleccionados = {jugador1, jugador2};
                  return seleccionados;
         }

}
