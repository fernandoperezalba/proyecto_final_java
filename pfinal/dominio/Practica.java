package pfinal.dominio;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pfinal.ui.Lienzo;

public class Practica extends Elemento{
   
    private static BufferedImage[][] ICONOS;
    private int animacion=0;

    public Practica(int x, int y, Tablero tablero) {
        super(x,y,tablero);
    }
    
    public static BufferedImage[][] getICONOS() {
        return ICONOS;
    }

    public static void setICONOS(BufferedImage[][] iCONOS) {
        ICONOS = iCONOS;
    }
    
    public void cambiarAnimacion(){
        if(animacion==0)
            animacion=1;
        else if(animacion==1)
            animacion=2;
        else if(animacion==2)
            animacion=3;
        else if(animacion==3)
            animacion=0;
    }

    @Override
    public void pintar(Graphics g, Lienzo lienzo){
        if(visible){
            g.drawImage(ICONOS[0][animacion], (x-tablero.getX())*48, (y-tablero.getY())*48, 48, 48, lienzo);
        }
    }
    
}
