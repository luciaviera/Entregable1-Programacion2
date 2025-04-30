package triangulos.interfaz;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


public class Main {
   public static void main(String[] args) throws UnsupportedEncodingException {
        // Ajuste sugerido por la cátedra
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name())); 
        
        
        System.out.println("Triángulos – Obligatorio P2");

    }
}
