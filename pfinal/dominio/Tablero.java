package pfinal.dominio;

import java.awt.Graphics;

import pfinal.ui.Lienzo;


public class Tablero {

	private int x; // posición de la esquina superior izquierda del tablero (mínímo x/y)
	private int y;
    private int[][] mapa;
	private Casilla[][] casillas;

	public Tablero(int x, int y, int[][] mapa, Casilla[][] casillas){
		this.x=x;
		this.y=y;
		this.mapa=mapa;
		this.casillas=casillas;
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

	public int[][] getMapa() {
		return mapa;
	}

	public void setMapa(int[][] mapa) {
		this.mapa = mapa;
	}

	public Casilla[][] getCasillas() {
		return casillas;
	}

	public void setCasillas(Casilla[][] casillas) {
		this.casillas = casillas;
	}

	public void pintar(Graphics g, Lienzo lienzo){
		for(int row = y; row < y+8; row++) {
			for(int col = x; col < x+8; col++) {                
				int rc = mapa[row][col];
				int r = rc / 20;
				int c = rc % 20;
				g.drawImage(casillas[r][c].getImagen(),(col-x)*48,(row-y)*48,48,48,lienzo);
			}
			
		}
	}

	public int isAndable(int row, int col){      
		int rc = mapa[row][col];
		int r = rc / 20;
		int c = rc % 20;
		return casillas[r][c].getTipo();
	}


	/**
		Método que gestiona el cambio de la casilla "puerta" cuando el jugador
		utiliza la llave para abrirla. Cambiará esa posición del tablero por un
		"azulejo" para que el jugador pueda caminar por encima.
	*/
	public void actualizarPuerta(Lienzo lienzo, Jugador jugador){
		int row = jugador.getY();
		int col = jugador.getX();
		if(mapa[row+1][col]==21)
			mapa[row+1][col] = 1;
		if(mapa[row-1][col]==21)
			mapa[row-1][col] = 1;
		if(mapa[row][col+1]==21)
			mapa[row][col+1] = 1;
		if(mapa[row][col-1]==21)
			mapa[row][col-1] = 1;
		lienzo.repaint();
	}

	public void actualizarCovid(){
		casillas[1][2].setTipo(Casilla.CAMINO);
	}
}