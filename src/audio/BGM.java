package audio;

import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;

/**
 * <p>A BGM represents a background music file that can be played in a loop,
 * its play looping again at the end of its playback.</p>
 * 
 * @since 1.0
 * @version 1.0
 * @author 51lv3str1 - <a href="https://github.com/51lv3str1">https://github.com/51lv3str1</a>
 * @see Audio
 */
public class BGM extends Audio {

	/**
	 * Constructs and initializes a BGM from a specific file path.
	 * 
	 * @param route the background music file route.
	 */
	public BGM(String route) {
		super(route);
	}

	/**
	 * Plays the audio in a continuously loop.
	 * 
	 * @see Audio
	 */
	@Override
	public void play() {
		this.getAudioClip().loop(LOOP_CONTINUOUSLY);
	}
	
}
