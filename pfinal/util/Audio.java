package pfinal.util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
    private static Clip clip;
    
    /** 
        Método que gestiona la lectura del fichero de audio con la música de fondo.
        Lee el fichero y lo reproduce en bucle.
    */
    public static void reproducirSonido() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("audio/musicaFondo.wav"));
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            clip.setLoopPoints(0,-1);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(UnsupportedAudioFileException ex) {
            System.out.println("UnsupportedAudioFileException");
            ex.printStackTrace();
        } catch(IOException ex) {
            System.out.println("IOException");
        } catch(LineUnavailableException ex) {
            System.out.println("LineUnavailableException");
        }
    }

    public static void pararSonido() {
        clip.stop();
    }

    public static void reiniciarSonido() {
        clip.setFramePosition(0);
    }

    public static void continuarSonido() {
        clip.start();
    }
}
