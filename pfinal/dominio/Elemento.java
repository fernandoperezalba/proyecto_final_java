package pfinal.dominio;
import java.awt.Graphics;

import pfinal.ui.Lienzo;

public abstract class Elemento {

    protected int x; // posición del elemento en el tablero
    protected int y;
    protected Tablero tablero;
    protected boolean visible=true;

    public Elemento(int x, int y, Tablero tablero) {
        this.x = x;
        this.y = y;
        this.tablero = tablero;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public abstract void pintar(Graphics g, Lienzo lienzo);

}
