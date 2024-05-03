package controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class AudioController {
    private static AudioController instance;
    private Clip clip;
    private final AtomicBoolean isPlaying = new AtomicBoolean(false);



    public static synchronized AudioController getInstance() {
        if (instance == null) {
            instance = new AudioController();
        }
        return instance;
    }

    public synchronized void audioInGame(String path) {
        if (isPlaying.get()) {
            stopAudio();
        }
        Thread audioThread = new Thread(() -> {
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path))) {
                AudioFormat format = audioInputStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(audioInputStream);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                isPlaying.set(true);
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
        });
        audioThread.start();
    }

    public synchronized void stopAudio() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.flush();
            clip.close();
            isPlaying.set(false);
            System.out.println("stopAudio");
        }
    }

    public static void main(String[] args) {
        AudioController audioController = AudioController.getInstance();
        audioController.audioInGame("src/main/java/controller/audio/audioPoint.wav");
        // Do something here, or simply wait for a while
        try {
            Thread.sleep(5000); // Wait for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Stop audio manually
        audioController.stopAudio();
    }
}
