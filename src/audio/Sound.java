package audio;

/**
 * <p>A sound represents a sound file that can be played once, its stops at the end of its playback.</p>
 * 
 * @since 1.0
 * @version 1.0
 * @author 51lv3str1 - <a href="https://github.com/51lv3str1">https://github.com/51lv3str1</a>
 * @see Audio
 */
public class Sound extends Audio {

	/**
	 * Constructs and initializes a Sound from a specific file path.
	 * 
	 * @param route the sound file route.
	 */
	public Sound(String route) {
		super(route);
	}

	/**
	 * Plays the audio once.
	 * 
	 * @see Audio
	 */
	@Override
	public void play() {
		this.getAudioClip().start();
	}

}
