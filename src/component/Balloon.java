package component;

import java.awt.Font;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import geometry.Point;
import geometry.Scale;
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

	@Override
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
	public void render(Integer fps, GraphicsRenderer graphicsRenderer) {
		graphicsRenderer.render(this);
	}

	@Override
	public void update(Double time) {
		// TODO Apéndice de método generado automáticamente

	}

	public void setPosition(WollokObject position) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void translate(Integer x, Integer y) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void translate(Point position) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void translateTo(Integer x, Integer y) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void translateTo(Point position) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void translateTo(WollokObject position) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void rotate(Double theta) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void rotate(Double theta, Point vector) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void rotate(Double theta, Integer x, Integer y) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void rotateAboutCenter(Double theta) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void scale(Integer x, Integer y) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void scale(Scale scale) {
		// TODO Apéndice de método generado automáticamente
		
	}

}
