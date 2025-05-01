package triangulos.dominio;

import java.util.ArrayList;
import java.util.List;

public class RegistroDeJugadores {
         private final List<Jugador> jugadores = new ArrayList<>();

         /** Agrega un jugador al registro; lanza si ya existía */
         public void registrar(Jugador j) {
                  if (jugadores.contains(j)) {
                        throw new IllegalArgumentException("El jugador ya estaba registrado");
                   }
                   jugadores.add(j);
         }
         
        // Getters
         public List<Jugador> getJugadores(){
             return this.jugadores;
         }
}
