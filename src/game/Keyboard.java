package game;

import java.util.ArrayList;
import java.util.List;

public class Keyboard implements Input {

	private static Keyboard instance;

	private List<InputListener> listeners = new ArrayList<InputListener>();

	private Keyboard() {

	}

	public static Keyboard getInstance() {
		if (instance == null) {
			instance = new Keyboard();
		}

		return instance;
	}

	public void addListener(InputListener listener) {
		this.listeners.add(listener);
	}

	public void addKey(KeyInput key) {
		this.listeners.stream().forEach(listener -> listener.listenKeyInput(key));
	}

}
