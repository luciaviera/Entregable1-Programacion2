package triangulos.interfaz;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Consola {
         private static final Scanner ENTRADA = new Scanner(System.in, StandardCharsets.UTF_8);
         private static final PrintStream SALIDA = crearSalidaUTF8();

         //Para que la salida en consola sea UTF-8
         private static PrintStream crearSalidaUTF8() {
                  PrintStream salidaUTF8 = new PrintStream(System.out, true, StandardCharsets.UTF_8);
                  System.setOut( new PrintStream(System.out, true, StandardCharsets.UTF_8));
                  return salidaUTF8;
         }

         // Muestra un mensaje con salto de linea
         public static void println(String mensaje) {
                  SALIDA.println(mensaje);
         }
         // Muestra un mensaje
         public static void print(String mensaje) {
                  SALIDA.print(mensaje);
         }
         // Muestra mensaje de error
         public static void error(String mensaje) {
                  SALIDA.println("\n⚠ " + mensaje + "\n");
         }
         
         // Solicita algo por consola y lee lo que ingresen
         public static String readln(String prompt) {
            SALIDA.print(prompt);
            String linea;
            do {
                linea = ENTRADA.nextLine().trim().toUpperCase();
            } while (linea.isEmpty());
            return linea;
        }
}
