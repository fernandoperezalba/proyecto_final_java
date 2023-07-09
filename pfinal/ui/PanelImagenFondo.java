package pfinal.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;

import javax.swing.JPanel;

import pfinal.io.IOJuego;

public class PanelImagenFondo extends JPanel{

    private static final String imagenFondo = "imagenes/fondo2.png";
    private static final String imagenInicio = "imagenes/ICAI_Studios.png";

    private BufferedImage imagen;
    private boolean fondo;
    private float alpha = 1f;

    public PanelImagenFondo(boolean fondo)
    {
        this.fondo = fondo;
        if(fondo)
            imagen = IOJuego.importarImagen(imagenFondo);
        else
            imagen = IOJuego.importarImagen(imagenInicio);
        this.setPreferredSize(new Dimension(384,384));
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        if(fondo)
            g.drawImage(imagen,0,0,384,384,this);
        else {
            Graphics2D g2d = (Graphics2D) g.create();
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
            g2d.setComposite(ac);
            g2d.drawImage(imagen,0,0,384,100,this);
        }
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

}