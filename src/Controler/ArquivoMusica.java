package Controler;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
 
public class ArquivoMusica {
        MP3Musica musica = new MP3Musica();
        String path = "msc/musica.mp3";
        public void setPath (String a) {
            path = a;
        }
	public  void ArquivoMusica() {
            File mp3File = new File(path);
            musica.tocar(mp3File);
            musica.start();
	}
 
	public static class MP3Musica extends Thread {
		private File mp3;

		private Player player;
 
		public void tocar(File mp3) {
			this.mp3 = mp3;
		}
 
		public void run() {        
			try {
				FileInputStream fis = new FileInputStream(mp3);
				BufferedInputStream bis = new BufferedInputStream(fis);
				this.player = new Player(bis); 
				this.player.play();

			} catch (FileNotFoundException e) {
				System.out.println("Problema ao tocar Musica" + mp3);
			} catch (JavaLayerException e) {
                            System.out.println("Problema ao tocar Musica" + mp3);
                    }
		}
	}
 
}