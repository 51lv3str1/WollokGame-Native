package component;

import java.awt.Font;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import component.actor.Actor;
import geometry.Point;
import ui.Color;
import ui.GraphicsRenderer;

public class Balloon extends GameComponent {

	private final Actor actor;
	private final Message message;

	public Balloon(Actor actor, String message) {
		this.actor = actor;
		this.message = new Message(message);
	}

	public Actor getSpeecher() {
		return this.actor;
	}

	public Message getMessage() {
		return this.message;
	}

	public String getMessageText() {
		return this.message.getText();
	}

	public void setMessageText(String text) {
		this.message.setText(text);
	}

	public Point getPosition() {
		return this.message.getPosition();
	}

	public void setPosition(Point point) {
		this.message.setPosition(point);
	}

	public Color getColor() {
		return this.message.getColor();
	}

	public void setColor(Color color) {
		this.message.setColor(color);
	}

	public Font getFont() {
		return this.message.getFont();
	}

	public void setFont(Integer fontSize, Integer fontStyle) {
		this.message.setFont(fontSize, fontStyle);
	}

	@Override
	public void render(GraphicsRenderer graphicsRenderer) {
		graphicsRenderer.render(this);
	}

	@Override
	public void update(Double time) {
		// TODO Apéndice de método generado automáticamente

	}

	public void setPosition(WollokObject position) {
		// TODO Apéndice de método generado automáticamente
		
	}

}
