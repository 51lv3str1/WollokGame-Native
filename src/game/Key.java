package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import org.uqbar.project.wollok.interpreter.core.WollokObject;
import org.uqbar.project.wollok.interpreter.nativeobj.WollokJavaConversions;

public class Key implements KeyListener {

	private final WollokObject wrapped;
	private final Set<WollokObject> clousures = new HashSet<WollokObject>();
	private Boolean isPressed = false;
	
	public Key(WollokObject wrapped) {
		this.wrapped = wrapped;
		GameObject.game.listenKey(this);
	}

	public void onPressDo(WollokObject clousure) {
		this.clousures.add(clousure);
	}

	public void clear() {
		this.clousures.clear();
	}

	public WollokObject isPressed() {
		return WollokJavaConversions.convertJavaToWollok(this.isPressed);
	}

	public Integer keycode() {
		return Integer.valueOf(this.wrapped.call("key").toString());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!this.isPressed && e.getKeyCode() == this.keycode()) {
			isPressed = true;
			this.clousures.stream().forEach(clousure -> clousure.call("apply"));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == this.keycode()) {
			this.isPressed = false;
		}
	}

}