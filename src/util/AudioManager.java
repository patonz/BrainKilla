package util;

import brainkilla.Skeleton.AudioManagerInterface;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.bean.playerbean.MediaPlayer;
import javax.sound.sampled.*;

/**
 *
 * @author Leonardo Montecchiari
 */
/*questa classe gestisce l'audio del gioco*/
public class AudioManager implements AudioManagerInterface {


    private Clip wavClip= null;    
    private MediaPlayer mp3MediaPlayer = null;

    public AudioManager(String song) {

        URL songUrl = Resource.GetAudio(song);
        
        if (songUrl != null) {     
        
            if (song.indexOf(".mp3") > 0)
                loadMP3(songUrl);           //se l'audio è un mp3 viene gestito con loadMP3 oppure con loadwav
            else
                loadWAV(songUrl);
            
        }
    }
    //metodo che gestisce i file .mp3
    private void loadMP3(URL songUrl) {
        
         mp3MediaPlayer = new MediaPlayer();
        
        try {

            mp3MediaPlayer.setPlayer(Manager.createPlayer(songUrl));

        } catch (IOException | NoPlayerException ex) {
            
            System.out.println("MP3 load error");
            Logger.getLogger(AudioManager.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    //metodo che gestisce i file .wav
    private void loadWAV(URL songUrl) {

        try {

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(songUrl);

            AudioFormat format = inputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat(), ((int) inputStream.getFrameLength() * format.getFrameSize()));
            wavClip = (Clip) AudioSystem.getLine(info);
            wavClip.open(inputStream);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {

            System.out.println("WAV load error");
            Logger.getLogger(AudioManager.class.getName()).log(Level.SEVERE, null, ex);

        }        
    }
    
    @Override //metodo che implementa la funzione play
    public void play() {
        
        if (mp3MediaPlayer != null) {
        
            mp3MediaPlayer.setPlaybackLoop(false);
            mp3MediaPlayer.start();
        }
        else if (wavClip != null) {
            
            wavClip.start();
        }
     
    }

    @Override //metodo che implementa la funzione loop
    public void loop() {
        
        if (mp3MediaPlayer != null) { 
            
            mp3MediaPlayer.setPlaybackLoop(true);
            mp3MediaPlayer.start();
        }
        else if (wavClip != null) {
            
            wavClip.loop(10000);
        }
    }

    @Override //metodo che implementa la funzione pausa
    public void pause() {
        
        if (mp3MediaPlayer != null)
            mp3MediaPlayer.stop();

        else if (wavClip != null)
            wavClip.stop();
    }

    @Override //metodo che implementa la funzione stop
    public void stop() {

        if (mp3MediaPlayer != null) {
         
            mp3MediaPlayer.stop();
            mp3MediaPlayer.close();
        }
        else if (wavClip != null) {
            
            wavClip.setFramePosition(0);
            wavClip.stop();
        }
        
    }
}
