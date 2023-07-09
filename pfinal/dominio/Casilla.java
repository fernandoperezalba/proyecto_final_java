package pfinal.dominio;

import java.awt.image.BufferedImage;

/** 
    Esta clase define el comportamiento de objetos Casilla.
    El tablero estará formado por casillas.
    @author Fernando Pérez Alba
*/

public class Casilla {
    
    private BufferedImage imagen;
    private int tipo;
	
	// tipos de casilla
	public static final int CAMINO = 0; // el jugador puede pasar por encima
	public static final int BORDE = 1; // paredes y otros elementos que no puede atravesar el jugador
	
    /**
    Inicializa los atributos del objeto Casilla.
    @param imagen Este atributo corresponde a la imagen que tendrá de fondo la casilla
    @param tipo Este atributo corresponde al tipo de casilla que podrá ser caminable (camino) o no caminable (borde/pared)
    */
    
	public Casilla(BufferedImage imagen, int tipo) {
		this.imagen = imagen;
		this.tipo = tipo;
	}

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
