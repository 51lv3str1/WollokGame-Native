package game;

import java.util.ArrayList;
import java.util.List;

public class Keyboard implements Input {

	private static Keyboard instance;

	private final List<InputListener> listeners;
	private final List<KeyInput> keys;

	private Keyboard() {
		this.listeners = new ArrayList<InputListener>();
		this.keys = new ArrayList<KeyInput>();
	}

	public static Keyboard getInstance() {
		if (instance == null) {
			instance = new Keyboard();
		}

		return instance;
	}

	public void addListener(InputListener listener) {
		if (!this.listeners.contains(listener)) {
			this.listeners.add(listener);
		}
	}

	public void addKey(KeyInput key) {
		if (!this.keys.contains(key)) {
			for (int index = 0; index < this.listeners.size(); index++) {
				this.listeners.get(index).listenKeyInput(key);
			}
			this.keys.add(key);
		}
	}

	public void removeKey(KeyInput key) {
		if (this.keys.contains(key)) {
			for (int index = 0; index < this.listeners.size(); index++) {
				this.listeners.get(index).stoplisteningKeyInput(key);
			}
			this.keys.remove(key);
		}
	}

	public void clear() {
		for (int lIndex = 0; lIndex < this.listeners.size(); lIndex++) {
			for (int kIndex = 0; kIndex < this.keys.size(); kIndex++) {
				this.listeners.get(lIndex).stoplisteningKeyInput(this.keys.get(kIndex));
			}
		}
		this.listeners.clear();
	}

}
