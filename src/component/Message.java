package component;

import static java.awt.Font.*;
import static ui.Color.*;

import java.awt.Font;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import geometry.Point;
import geometry.Scale;
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
	public void render(Integer fps, GraphicsRenderer graphicsRenderer) {
		graphicsRenderer.render(this);
	}

	@Override
	public void update(Double time) {
		// TODO Apéndice de método generado automáticamente

	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	public void setPosition(Point position) {
		this.position = position;
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
