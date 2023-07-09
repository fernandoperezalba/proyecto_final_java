package pfinal.ui;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;
import java.util.ArrayList;

import pfinal.dominio.Casilla;
import pfinal.dominio.Herramienta;
import pfinal.dominio.Practica;
import pfinal.dominio.Jugador;
import pfinal.dominio.Tablero;
import pfinal.io.IOJuego;
import pfinal.util.Audio;
import pfinal.util.Util;

public class Lienzo extends javax.swing.JPanel implements Runnable {

    private static final long serialVersionUID = 1L;

    private JVentana ventana;
    private Tablero tablero;
    private Jugador jugador;
    private Herramienta vacuna;
    private Herramienta llave;
    private ArrayList<Practica> practicas;

    private Thread hilo;
    private int xJugador=17;
	private int yJugador=17;

    private String sentido="abajo";

    private boolean pausa = false;

    public Lienzo(JVentana ventana){
        this.ventana=ventana;
        Jugador.setICONOS(IOJuego.importarIconos("imagenes/jugador.gif"));
        Practica.setICONOS(IOJuego.importarIconos("imagenes/practica2.png"));
        Herramienta.setICONOS(IOJuego.importarIconos("imagenes/herramientas2.png"));

        hilo = new Thread(this);
        this.setPreferredSize(new Dimension(8*48, 8*48));
        this.setBackground(Color.WHITE);
        
        tablero = new Tablero(16,16,IOJuego.importarTablero(),IOJuego.importarCasillas());

        jugador = new Jugador(17,17,tablero);
        vacuna = new Herramienta(4,12,tablero,true,false); //4,12
        llave = new Herramienta(37,26,tablero,false,true); //37,26
        practicas = new ArrayList<Practica>();
        cargarPracticas();
        
        Util.iniciarAnimacion(this);
        Audio.reproducirSonido();

        this.repaint();
    }

    @Override
    public void run() {
        if(!pausa) {
            try{
                ventana.desactivarFocus();
                if(xJugador>jugador.getX()) {
                    for(int i=(jugador.getX()-tablero.getX())*48; i<=(xJugador-tablero.getX())*48; i++){
                        jugador.setFluidoX(i);
                        Thread.sleep(5);
                        this.repaint();
                    }
                }
                else if(yJugador>jugador.getY()) {
                    for(int i=(jugador.getY()-tablero.getY())*48; i<=(yJugador-tablero.getY())*48; i++){
                        jugador.setFluidoY(i);
                        Thread.sleep(5);
                        this.repaint();
                    }
                }
                else if(xJugador<jugador.getX()) {
                    for(int i=(jugador.getX()-tablero.getX())*48; i>=(xJugador-tablero.getX())*48; i--){
                        jugador.setFluidoX(i);
                        Thread.sleep(5);
                        this.repaint();
                    }
                }
                else if(yJugador<jugador.getY()) {
                    for(int i=(jugador.getY()-tablero.getY())*48; i>=(yJugador-tablero.getY())*48; i--){
                        jugador.setFluidoY(i);
                        Thread.sleep(5);
                        this.repaint();
                    }
                }
                jugador.setX(xJugador);
                jugador.setY(yJugador);

                Thread.sleep(3);
                ventana.activarFocus();

                if(xJugador>=tablero.getX()+8){ 
                    ventana.desactivarFocus();
                    for(int i=0; i<8; i++){
                        tablero.setX(tablero.getX()+1);
                        jugador.setFluidoX((xJugador-tablero.getX())*48);
                        Thread.sleep(50);
                        this.repaint();
                    }
                    ventana.activarFocus();
                }else
                    this.repaint();

                if(yJugador>=tablero.getY()+8){
                    ventana.desactivarFocus();
                    for(int i=0; i<8; i++){
                        tablero.setY(tablero.getY()+1);
                        jugador.setFluidoY((yJugador-tablero.getY())*48);
                        Thread.sleep(50);
                        this.repaint();
                    }
                    ventana.activarFocus();
                }else
                    this.repaint();

                if(xJugador<tablero.getX()){
                    ventana.desactivarFocus();
                    for(int i=0; i<8; i++){
                        tablero.setX(tablero.getX()-1);
                        jugador.setFluidoX((xJugador-tablero.getX())*48);
                        Thread.sleep(50);
                        this.repaint();
                    }
                    ventana.activarFocus();
                }else
                    this.repaint();

                if(yJugador<tablero.getY()){
                    ventana.desactivarFocus();
                    for(int i=0; i<8; i++){
                        tablero.setY(tablero.getY()-1);
                        jugador.setFluidoY((yJugador-tablero.getY())*48);
                        Thread.sleep(50);
                        this.repaint();
                    }
                    ventana.activarFocus();
                }else
                    this.repaint();
                
                ponerVacuna();
            }catch(InterruptedException ie){
                    ie.printStackTrace();
            }
            
            if(jugador.getNumPracticas()==15){
                Audio.pararSonido();
                ventana.setFin(true);
                ventana.finJuego();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){    
        tablero.pintar(g, this);
        jugador.pintar(g, this);

        for(Practica practica:practicas)
            if(practica.getX()>=tablero.getX() && practica.getX()<=tablero.getX()+8 && practica.getY()>=tablero.getY() && practica.getY()<=tablero.getY()+8)
                practica.pintar(g,this);
        
        if(vacuna.getX()>=tablero.getX() && vacuna.getX()<=tablero.getX()+8 && vacuna.getY()>=tablero.getY() && vacuna.getY()<=tablero.getY()+8)
            vacuna.pintar(g, this);
        if(llave.getX()>=tablero.getX() && llave.getX()<=tablero.getX()+8 && llave.getY()>=tablero.getY() && llave.getY()<=tablero.getY()+8)
            llave.pintar(g,this);
    }

    public void iniciar(){
        try{
            hilo = new Thread(this);
            hilo.start();
        }catch(IllegalThreadStateException itse){}
    }

    public void moverJugador(String sentido)
    {
        if(sentido.equals("derecha")){
            if(tablero.isAndable(yJugador, xJugador+1)==Casilla.CAMINO){
                comprobarChoque(xJugador+1, yJugador);
                xJugador++;
            }
        }else if(sentido.equals("izquierda")){
            if(tablero.isAndable(yJugador, xJugador-1)==Casilla.CAMINO){
                comprobarChoque(xJugador-1, yJugador);
                xJugador--;
            }
        }else if(sentido.equals("arriba")){
            if(tablero.isAndable(yJugador-1, xJugador)==Casilla.CAMINO){
                comprobarChoque(xJugador, yJugador-1);
                yJugador--;
            }     
        }else if(sentido.equals("abajo")){
            if(tablero.isAndable(yJugador+1,xJugador)==Casilla.CAMINO){
                comprobarChoque(xJugador, yJugador+1);
                yJugador++;
            }
        }
        this.sentido = sentido;
        iniciar();
    }

    public void comprobarChoque(int x, int y){
        if(x==llave.getX() && y==llave.getY()){
            llave.setVisible(false);
            ventana.setLlave();
            jugador.setLlave(true);
        }else if(x==vacuna.getX() && y==vacuna.getY()){
            vacuna.setVisible(false);
            ventana.setVacuna();
            jugador.setVacuna(true);
        }
        for(Practica practica:practicas)
            if(x==practica.getX() && y==practica.getY()){
                if(practica.isVisible()){
                    jugador.addPractica();
                    ventana.addPractica(jugador);
                }
                practica.setVisible(false);
            }
    }

    public void abrirPuerta(){
        if(jugador.puertaCerca() && jugador.isLlave())
            tablero.actualizarPuerta(this, jugador); 
    }

    public void ponerVacuna(){
        if(jugador.covidCerca() && jugador.isVacuna())
            tablero.actualizarCovid();
    }

    public void cargarPracticas(){
		practicas.add(new Practica(20, 20, tablero));
        practicas.add(new Practica(36, 12, tablero));
        practicas.add(new Practica(4, 28, tablero));
        practicas.add(new Practica(34, 4, tablero));
        practicas.add(new Practica(19, 28, tablero));
        practicas.add(new Practica(26, 35, tablero));
        practicas.add(new Practica(36, 38, tablero));
        practicas.add(new Practica(28, 27, tablero));
        practicas.add(new Practica(25, 14, tablero));
        practicas.add(new Practica(30, 20, tablero));
        practicas.add(new Practica(21, 4, tablero));
        practicas.add(new Practica(14, 9, tablero));
        practicas.add(new Practica(3, 4, tablero));
        practicas.add(new Practica(14, 20, tablero));
        practicas.add(new Practica(20, 13, tablero));
    }

    public Jugador getJugador(){
        return jugador;
    }

    public String getSentido(){
        return sentido;
    }

    public ArrayList<Practica> getPracticas(){
        return practicas;
    }

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    public boolean getPausa() {
        return pausa;
    }
}