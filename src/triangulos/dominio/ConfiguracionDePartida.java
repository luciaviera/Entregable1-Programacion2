package triangulos.dominio;

public class ConfiguracionDePartida {
         private int largo;
         private boolean largoVariable;
         private int cantMaxBandas;
         private boolean reglaDeContacto;
         private int historialDeTableros;

         //Getters
         public int getLargo() {
                  return largo;
          }
         public boolean isLargoVariable() {
                  return this.largoVariable;
         }
         public int getCantMaxBandas() {
                  return this.cantMaxBandas;
         }
         public boolean isReglaDeContacto() {
                  return this.reglaDeContacto;
         }
         public int getHistorialDeTableros() {
                  return historialDeTableros;
         }

         //Setters
         public void setLargoVariable(boolean largoVariable) {
                  this.largoVariable = largoVariable;
         }
         public void setCantMaxBandas(int cantMaxBandas) {
                  if (cantMaxBandas < 1) throw new IllegalArgumentException("Necesita poder colocarse al menos 1 banda");
                  this.cantMaxBandas = cantMaxBandas;
         }
         public void setReglaDeContacto(boolean reglaDeContacto) {
                  this.reglaDeContacto = reglaDeContacto;
         }
         public void setHistorialDeTableros(int historialDeTableros) {
                  if (historialDeTableros < 1) throw new IllegalArgumentException("Debe mostrarse al mneos un tablero");
                   if (historialDeTableros > 4) throw new IllegalArgumentException("Limite excedido. Se pueden mostrar hasta 4 tableros ");
                  this.historialDeTableros = historialDeTableros;
         }
         

         public void confiugracionPorDefecto() {
                  this.largo = 4;
                  this.cantMaxBandas = 10;
                  this.largoVariable = false;
                  this.reglaDeContacto = false;
                  this.historialDeTableros = 1;
         }
}
