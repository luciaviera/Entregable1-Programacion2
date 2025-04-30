package triangulos.dominio;

public class Banda {
    private Punto pInicial;
    private Punto pFinal;
    private char dir;      // 'Q','E','D','C','Z','A'
    private int  largo;    // 1..4

    //Constructor
    private Banda(Punto p1, Punto p2, char dir, int largo) {
        this.pInicial = p1;
        this.pFinal   = p2;
        this.dir      = dir;
        this.largo    = largo;
    }
}
