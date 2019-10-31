package sound;

import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

/**
 * <p>
 * A sound represents a sound file that can be played once, its stops at the end
 * of its playback.
 * </p>
 * 
 * @since 1.0
 * @version 1.0
 * @author 51lv3str1 - <a href="https://github.com/51lv3str1">https://github.com/51lv3str1</a>
 */
public class Sound {
	
	/** Wollok instance for this game. */
	private final WollokObject wrapped;
	
	/**
	 * The File associated with this sound.
	 */
	private File file;
	
	/**
	 * The clip of this input stream.
	 */
	private Clip clip;

	/**
	 * Constructs and initializes a Sound from a specific file path.
	 * 
	 * @param route the sound file route.
	 */
	public Sound(WollokObject wrapped) {
		this.wrapped = wrapped;
	}
	
	/**
	 * Creates a audio clip. 
	 */
	private void createAudioClip() {
		try {
			this.file = new File(this.getPath());
			final AudioInputStream stream = AudioSystem.getAudioInputStream(this.file.toURI().toURL());
			this.clip = AudioSystem.getClip();
			this.clip.open(stream);
		}

		catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the audio clip associated with this Audio.
	 * 
	 * @return this audio clip.
	 */
	public Clip getAudioClip() {
		return clip;
	}
	
	/**
	 * Gets the Image file path.
	 * 
	 * @return the file path of this sound.
	 */
	public String getPath() {
		return this.wrapped.call("path").toString();
	}

	/**
	 * Plays the audio once.
	 */
	public void play() {
		if (this.getAudioClip() == null) {
			this.createAudioClip();
		}
		
		else {
			this.getAudioClip().stop();
		}

		this.getAudioClip().start();
	}

	/**
	 * Plays the audio in a continuously loop.
	 */
	public void loop() {
		if (this.getAudioClip() == null) {
			this.createAudioClip();
		}
		
		else {
			this.getAudioClip().stop();
		}
		
		this.getAudioClip().loop(LOOP_CONTINUOUSLY);
	}

	/**
	 * Pause the audio.
	 */
	public void pause() {
		if (this.getAudioClip() != null) {
			this.getAudioClip().stop();
		}
	}

	/**
	 * Resumes the audio.
	 */
	public void resume() {
		if (this.getAudioClip() != null) {
			this.getAudioClip().start();
		}
	}

	/**
	 * Stops the audio.
	 */
	public void stop() {
		if (this.getAudioClip() != null) {
			this.getAudioClip().stop();
			this.getAudioClip().close();
		}
	}

}
