package pfinal.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import pfinal.dominio.Practica;
import pfinal.ui.JVentana;
import pfinal.ui.Lienzo;

public class Util{
    public static void iniciarAnimacion(Lienzo lienzo){
        // Me crea un hilo, las líneas de código dentro del run se estarán recorriendo
        // cada 0,2 segundos para cambiar la animación de los elementos
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                lienzo.getJugador().cambiarAnimacion();
                for(Practica p:lienzo.getPracticas()){
                    p.cambiarAnimacion();
                }          
                lienzo.repaint();
            }
        }, 0, 200, TimeUnit.MILLISECONDS);
    }

    public static void contarTiempo(JVentana ventana){
        // Cada segundo
        ScheduledExecutorService exec2 = Executors.newSingleThreadScheduledExecutor();
        exec2.scheduleAtFixedRate(new Runnable() {
            private int inicio = 0;
            @Override
            public void run() {
                if(inicio!=0 && !ventana.getFin() && !ventana.getPausa())
                    ventana.addSegundo();
                inicio=1;
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
