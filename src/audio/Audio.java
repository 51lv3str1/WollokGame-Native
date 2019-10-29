package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * <p>An audio represents a sound file that can be played, paused, resume and can be stopped.</p>
 * 
 * @since 1.0
 * @version 1.0
 * @author 51lv3str1 - <a href="https://github.com/51lv3str1">https://github.com/51lv3str1</a>
 * @see Sound
 * @see BGM
 */
public abstract class Audio {

	/**
	 * The File associated with this Audio.
	 */
	private File file;
	
	/**
	 * The path of this audio file.
	 */
	private String path;
	
	/**
	 * The clip of this input stream.
	 */
	private Clip clip;

	/**
	 * Constructs and initializes a Audio from a specific file path.
	 * 
	 * @param route the audio file route.
	 */
	public Audio(String route) {
		this.path = route;
		this.createAudioClip();
	}

	/**
	 * Creates a audio clip. 
	 */
	private void createAudioClip() {
		try {
			this.file = new File(this.path);
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
	 * @return the file path of this image.
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Plays the audio.
	 * 
	 * @see Sound
	 * @see BGM
	 */
	public abstract void play();

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
