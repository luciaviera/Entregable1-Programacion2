package triangulos.dominio;

public class ConfiguracionDePartida {
         private int largo;
         private boolean largoVariable;
         private int cantMaxBandas;
         private boolean reglaDeContacto;

         //Getters
         public int getLargo() {
                  return largo;
          }
         public boolean esLargoVariable() {
                  return this.largoVariable;
         }
         public int getCantMaxBandas() {
                  return this.cantMaxBandas;
         }
         public boolean hayReglaDeContacto() {
                  return this.reglaDeContacto;
         }

         
         
         public void confiugracionPorDefecto() {
                  this.largo = 4;
                  this.cantMaxBandas = 10;
                  this.largoVariable = false;
                  this.reglaDeContacto = false;
         }
}
