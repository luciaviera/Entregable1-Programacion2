package triangulos.dominio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RegistroDeJugadores {
         private final List<Jugador> jugadores = new ArrayList<>();

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
         
        // Getters
         public List<Jugador> getJugadores(){
             return this.jugadores;
         }

}
