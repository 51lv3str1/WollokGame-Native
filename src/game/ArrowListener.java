package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArrowListener implements KeyListener {

	private final GameComponent component;
	private Boolean leftIsPressed = false;
	private Boolean upIsPressed = false;
	private Boolean rightIsPressed = false;
	private Boolean downIsPressed = false;

	public ArrowListener(GameComponent component) {
		this.component = component;
	}

	public void clear() {
		GameObject.game.stopListenKey(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!this.leftIsPressed && e.getKeyCode() == 37) {
			leftIsPressed = true;
			this.component.position(this.component.position().translate(-1, 0));
		}

		if (!this.upIsPressed && e.getKeyCode() == 38) {
			upIsPressed = true;
			this.component.position(this.component.position().translate(0, 1));
		}

		if (!this.rightIsPressed && e.getKeyCode() == 39) {
			rightIsPressed = true;
			this.component.position(this.component.position().translate(1, 0));
		}

		if (!this.downIsPressed && e.getKeyCode() == 40) {
			downIsPressed = true;
			this.component.position(this.component.position().translate(0, -1));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			this.leftIsPressed = false;
		}
		if (e.getKeyCode() == 38) {
			this.upIsPressed = false;
		}
		if (e.getKeyCode() == 39) {
			this.rightIsPressed = false;
		}
		if (e.getKeyCode() == 40) {
			this.downIsPressed = false;
		}
	}

}