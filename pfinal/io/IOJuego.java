package pfinal.io;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import pfinal.dominio.Casilla;
import pfinal.dominio.Usuario;

public class IOJuego {
    
    public static BufferedImage[][] importarIconos(String s) {
        try {
            BufferedImage imagen = ImageIO.read(new File(s));
            int ancho = imagen.getWidth() / 16; // cada icono dentro de la imagen es de 16x16 píxeles
			int altura = imagen.getHeight() / 16;
            BufferedImage[][] img = new BufferedImage[altura][ancho]; // matriz de imágenes
            for(int i=0; i<altura; i++){
                for(int j=0; j<ancho; j++){
                    img[i][j] = imagen.getSubimage(j*16, i*16, 16, 16);
                }
            }
            return img;
        } catch (IOException e) {
            System.out.println("Ha habido un error al abrir el fichero");
			e.printStackTrace();
            return null;
        }
    }

    /**
	Método que contiene la lógica para importar las posiciones del tablero.
	El tablero tiene 40 casillas por fila y 40 casillas por columna en total.
    El método devuelve una matriz de números enteros con los tipos de casilla
    que hay en cada posición del mapa.
    Los números corresponden a:
    - 1: azulejo
    - 20: muro
    - 21: puerta
    - 22: zona con coronavirus 
	*/
    public static int[][] importarTablero() {
		try {
			FileReader fr = new FileReader("datos/mapa.csv");
            BufferedReader br = new BufferedReader(fr);
            String linea = null;
			    
			// El mapa tiene 40 casillas por fila y 40 casillas por columna
			int cols = 40;
			int filas = 40;
			int[][] mapa = new int[filas][cols];

            // recorro el fichero y guardo los valores en mapa
            for(int i=0; i<filas; i++){
                linea = br.readLine();
                String s[] = linea.split(", ");
                for(int j=0; j<cols; j++)
                    mapa[i][j]=Integer.parseInt(s[j]);
            }
            br.close();
            fr.close();
            return mapa;
		}
		catch(IOException e) {
            System.out.println("Ha habido un error al abrir el fichero");
			e.printStackTrace();
            return null;
        }
    }

    public static Casilla[][] importarCasillas() {        
        try {
			BufferedImage casillasInput = ImageIO.read(new File("imagenes/elementosTablero.gif"));
			int numCasillas = casillasInput.getWidth() / 16;
			Casilla[][] casillas = new Casilla[2][numCasillas]; // matriz de casillas: primera fila=camino, segunda fila=bordes
			
			BufferedImage subImagen;
			for(int col=0; col<numCasillas; col++) {
				subImagen = casillasInput.getSubimage(col*16, 0, 16, 16);
				casillas[0][col] = new Casilla(subImagen, Casilla.CAMINO);
				
                subImagen = casillasInput.getSubimage(col*16, 16, 16, 16);
				casillas[1][col] = new Casilla(subImagen, Casilla.BORDE);
			}
            return casillas;
		}
		catch(IOException e) {
            System.out.println("Ha habido un error al abrir el fichero");
			e.printStackTrace();
            return null;
        }
    }
    
    public static BufferedImage importarImagen(String file) {
        try {
            BufferedImage imagen = ImageIO.read(new File(file));
            return imagen;
        } catch (IOException e) {
            System.out.println("Ha habido un error al abrir el fichero");
			e.printStackTrace();
            return null;
        }
    }

    public static void exportarUsuarios(List<Usuario> usuarios) {
        try
        {
            FileWriter fw = new FileWriter("datos/usuarios.csv");
            PrintWriter pw = new PrintWriter(fw);
            for(Usuario u:usuarios)          
                pw.println(u.getNombre()+", "+u.getMinutos()+", "+u.getSegundos());
            pw.close();
            fw.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    /**
	Método que contiene la lógica para importar la información de los usuarios.
	En el fichero se guarda el nombre del usuario y los minutos y segundos de su puntuación máxima.
    Devuelve una lista de usuarios.
	*/
    public static List<Usuario> importarUsuarios() {
        try
        {
            List<Usuario> usuarios = new ArrayList<Usuario>();
            FileReader fr = new FileReader("datos/usuarios.csv");
            BufferedReader br = new BufferedReader(fr);
            String linea = null;
            while((linea = br.readLine()) != null)
            {
                String s[] = linea.split(", ");
                String nombre = s[0];
                int minutos = Integer.parseInt(s[1].trim());
                int segundos = Integer.parseInt(s[2].trim());
                
                usuarios.add(new Usuario(nombre,minutos,segundos));
            }
            br.close();
            fr.close();
            return usuarios;
        }
        catch(FileNotFoundException ioe)
        {
            return null;
        }   
        catch(IOException ioe)
        {
            ioe.printStackTrace();
            return null;
        }
    }

}
