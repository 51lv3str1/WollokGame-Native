package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

public class KeyInput implements KeyListener {

	private final WollokObject wrapped;
	private final Keyboard keyboard = Keyboard.getInstance();
	private final Set<WollokObject> clousures = new HashSet<WollokObject>();
	private Boolean isPressed = false;

	public KeyInput(WollokObject wrapped) {
		this.wrapped = wrapped;
		this.keyboard.addKey(this);
	}

	public void onKeyPressedDo(WollokObject clousure) {
		this.clousures.add(clousure);
	}

	public void clear() {
		this.clousures.clear();
	}

	public Boolean isPressed() {
		return this.isPressed;
	}

	public Integer keycode() {
		return Integer.valueOf(this.wrapped.call("code").toString());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!this.isPressed() && e.getKeyCode() == this.keycode()) {
			isPressed = true;
			this.clousures.stream().forEach(clousure -> clousure.call("apply"));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == this.keycode()) {
			isPressed = false;
		}
	}

}
