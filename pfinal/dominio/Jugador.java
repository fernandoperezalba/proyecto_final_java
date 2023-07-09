package pfinal.dominio;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pfinal.ui.Lienzo;

/** 
    Esta clase define el comportamiento de objetos Jugador.
    @author Fernando Pérez Alba
*/

public class Jugador extends Elemento {

    private static BufferedImage[][] ICONOS;
    private boolean llave=false;
    private boolean vacuna=false;
    private int numPracticas=0;

    private int animacion=0;

    private int fluidoX=0;
    private int fluidoY=0;

    public Jugador(int x, int y, Tablero tablero) {
        super(x,y,tablero);
        fluidoX=(x-tablero.getX())*48;
        fluidoY=(y-tablero.getY())*48;
    }

    public static BufferedImage[][] getICONOS() {
        return ICONOS;
    }

    public static void setICONOS(BufferedImage[][] iCONOS) {
        ICONOS = iCONOS;
    }

    public boolean isLlave() {
        return llave;
    }

    public void setLlave(boolean llave) {
        this.llave = llave;
    }

    public boolean isVacuna() {
        return vacuna;
    }

    public void setVacuna(boolean vacuna) {
        this.vacuna = vacuna;
    }

    @Override
    public void pintar(Graphics g, Lienzo lienzo) {
        int conVacuna = 0;
        if(isVacuna() && tablero.getMapa()[y][x]==22)
            conVacuna = 4;
        if(lienzo.getSentido().equals("abajo")){
            g.drawImage(ICONOS[0+conVacuna][animacion], fluidoX, fluidoY, 48, 48, lienzo);
        }else if(lienzo.getSentido().equals("izquierda")){
            g.drawImage(ICONOS[1+conVacuna][animacion], fluidoX, fluidoY, 48, 48, lienzo);
        }else if(lienzo.getSentido().equals("derecha")){
            g.drawImage(ICONOS[2+conVacuna][animacion], fluidoX, fluidoY, 48, 48, lienzo);
        }else if(lienzo.getSentido().equals("arriba")){
            g.drawImage(ICONOS[3+conVacuna][animacion], fluidoX, fluidoY, 48, 48, lienzo);
        }
    }

    public void cambiarAnimacion() {
        if(animacion==0)
            animacion=1;
        else if(animacion==1)
            animacion=0;
    }

    public boolean puertaCerca() {
        if(tablero.getMapa()[y][x+1]==21 || tablero.getMapa()[y+1][x]==21 || tablero.getMapa()[y][x-1]==21 || tablero.getMapa()[y-1][x]==21)
            return true;
        else
            return false;
    }

    public boolean covidCerca() {
        if(tablero.getMapa()[y][x+1]==22 || tablero.getMapa()[y+1][x]==22 || tablero.getMapa()[y][x-1]==22 || tablero.getMapa()[y-1][x]==22)
            return true;
        else
            return false;
    }

    public void addPractica() {
        numPracticas++;
    }

    public int getNumPracticas() {
        return numPracticas;
    }

    public int getFluidoX() {
        return fluidoX;
    }

    public void setFluidoX(int fluidoX) {
        this.fluidoX = fluidoX;
    }

    public int getFluidoY() {
        return fluidoY;
    }

    public void setFluidoY(int fluidoY) {
        this.fluidoY = fluidoY;
    }
    
}