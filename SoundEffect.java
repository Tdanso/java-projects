package View;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect {

    File audioBackground;
    File playerAudioShot;
    File audioExpl;
    File enemyAudioShot;
    Clip audioClipBack;
    Clip playerClipShot;
    Clip enemyClipShot;
    Clip audioClipExpl;
    AudioInputStream audioStreamBackground;
    AudioInputStream playerAudioStreamShot;
    AudioInputStream enemyAudioStreamShot;
    AudioInputStream audioStreamExpl;

    public SoundEffect() {
        this.audioBackground = new File("data/background.wav");
        this.playerAudioShot = new File("data/playerShotSound.wav");
        this.audioExpl = new File("data/expl.wav");
        this.enemyAudioShot = new File("data/enemyShotSound.wav");
    }

    public void playerShotSound() {
        try {
            this.playerAudioStreamShot = AudioSystem.getAudioInputStream(this.playerAudioShot);
            this.playerClipShot = AudioSystem.getClip();
            this.playerClipShot.open(this.playerAudioStreamShot);
            this.playerClipShot.start();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (LineUnavailableException | UnsupportedAudioFileException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enemyShotSound() {
        try {
            this.enemyAudioStreamShot = AudioSystem.getAudioInputStream(this.enemyAudioShot);
            this.enemyClipShot = AudioSystem.getClip();
            this.enemyClipShot.open(this.enemyAudioStreamShot);
            this.enemyClipShot.start();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (LineUnavailableException | UnsupportedAudioFileException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void backgroundMusic() {
        try {

            this.audioStreamBackground = AudioSystem.getAudioInputStream(this.audioBackground);

            this.audioClipBack = AudioSystem.getClip();
            this.audioClipBack.open(this.audioStreamBackground);
            this.audioClipBack.start();
            this.audioClipBack.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (LineUnavailableException | UnsupportedAudioFileException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void explosionSound() {
        try {

            this.audioStreamExpl = AudioSystem.getAudioInputStream(this.audioExpl);

            this.audioClipExpl = AudioSystem.getClip();
            this.audioClipExpl.open(this.audioStreamExpl);
            this.audioClipExpl.start();

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (LineUnavailableException | UnsupportedAudioFileException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopBackgroundMusic() {
        this.audioClipBack.stop();
        this.audioClipBack.close();
    }

}
