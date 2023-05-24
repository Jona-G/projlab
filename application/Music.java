package application;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/*
 * A zene lejatszasahoz hasznalt osztaly.
 */
public class Music extends Thread {
	// Forras: https://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java
	
	/*
	 * A thread osztaly run metodusanak feluldefinialasa.
	 */
	@Override
	public void run() {
		playMusic();
	}
	
	/*
	 * Lejatszik egy szamot, majd meghivja onmagat.
	 */
	public void playMusic() {
	    int BUFFER_SIZE = 128000;
	    File soundFile = null;
	    AudioInputStream audioStream = null;
	    AudioFormat audioFormat;
	    SourceDataLine sourceLine = null;
	    
        String strFilename = "music.wav";

        try {
            soundFile = new File(strFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e){
            e.printStackTrace();
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
        
        playMusic();
	}
}
