package component;

import static java.awt.Font.*;
import static ui.Color.*;

import java.awt.Font;

import geometry.Point;
import ui.Color;
import ui.GraphicsRenderer;

public class Message extends GameComponent {

	private String text;
	private Point position;
	private Color color;
	private Font font;

	public Message(String text) {
		this.text = text;
		this.position = Point.ORIGIN;
		this.color = BLACK;
		this.font = new Font("Dialog", PLAIN, 15);
	}

	public Message(String text, Integer fontSize) {
		this.text = text;
		this.position = Point.ORIGIN;
		this.color = BLACK;
		this.font = new Font("Dialog", PLAIN, fontSize);
	}

	public Message(String text, Point point) {
		this.text = text;
		this.position = point;
		this.color = BLACK;
		this.font = new Font("Dialog", PLAIN, 15);
	}

	public Message(String text, Point point, Integer fontSize) {
		this.text = text;
		this.position = point;
		this.color = BLACK;
		this.font = new Font("Dialog", PLAIN, fontSize);
	}

	public Message(String text, Point point, Color color) {
		this.text = text;
		this.position = point;
		this.color = color;
		this.font = new Font("Dialog", PLAIN, 15);
	}

	public Message(String text, Point point, Color color, Integer fontSize) {
		this.text = text;
		this.position = point;
		this.color = color;
		this.font = new Font("Dialog", PLAIN, fontSize);
	}

	public Message(String text, Point point, Integer fontSize, Integer fontStyle) {
		this.text = text;
		this.position = point;
		this.color = BLACK;
		this.font = new Font("Dialog", fontStyle, fontSize);
	}

	public Message(String text, Point point, Color color, Integer fontSize, Integer fontStyle) {
		this.text = text;
		this.position = point;
		this.color = color;
		this.font = new Font("Dialog", fontStyle, fontSize);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Integer fontSize, Integer fontStyle) {
		this.font = new Font("Dialog", fontStyle, fontSize);
	}

	@Override
	public void render(GraphicsRenderer graphicsRenderer) {
		graphicsRenderer.render(this);
	}

	@Override
	public void update(Double time) {
		// TODO Apéndice de método generado automáticamente

	}

	public Point getPosition() {
		return this.position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
}
