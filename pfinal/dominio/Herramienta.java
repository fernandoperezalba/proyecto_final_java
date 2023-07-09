package pfinal.dominio;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pfinal.ui.Lienzo;

public class Herramienta extends Elemento {

    private static BufferedImage[][] ICONOS;

    private boolean isVacuna;
    private boolean isLlave;

    /**
	Constructor de la clase Herramienta que inicializa sus atributos.
    @param isVacuna Atributo de tipo boolean que indice si la herramienta es una vacuna.
    @param isLlave Atributo de tipo boolean que indice si la herramienta es una llave.
    Llama al constructor de la clase Elemento pasándole los atributos que hereda.
    */

    public Herramienta(int x, int y, Tablero tablero, boolean isVacuna, boolean isLlave) {
        super(x,y,tablero);
        this.isVacuna = isVacuna;
        this.isLlave = isLlave;
    }
    
    public static BufferedImage[][] getICONOS() {
        return ICONOS;
    }


    public static void setICONOS(BufferedImage[][] iCONOS) {
        ICONOS = iCONOS;
    }

    public boolean isVacuna() {
        return isVacuna;
    }

    public void setVacuna(boolean isVacuna) {
        this.isVacuna = isVacuna;
    }

    public boolean isLlave() {
        return isLlave;
    }

    public void setLlave(boolean isLlave) {
        this.isLlave = isLlave;
    }

    @Override
    public void pintar(Graphics g, Lienzo lienzo){
        if(visible){
            if(isVacuna)
                g.drawImage(ICONOS[1][0], (x-tablero.getX())*48, (y-tablero.getY())*48, 48, 48, lienzo);
            if(isLlave)
                g.drawImage(ICONOS[1][1], (x-tablero.getX())*48, (y-tablero.getY())*48, 48, 48, lienzo);
        }
    }
    
}