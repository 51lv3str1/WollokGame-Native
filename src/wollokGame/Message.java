package wollokGame;

import static java.awt.Font.*;
import static ui.Color.*;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

import geometry.Point;
import ui.Color;
import ui.graphics.GraphicsRenderer;

@SuppressWarnings("serial")
public class Message extends JComponent {

	private String text;
	private Point point;
	private Color color;
	private Font font;
	
	public Message(String text) {
		this.text = text;
		this.point = new Point();
		this.color = BLACK;
		this.font = new Font("Dialog", PLAIN, 15);
	}
	
	public Message(String text, Integer fontSize) {
		this.text = text;
		this.point = new Point();;
		this.color = BLACK;
		this.font = new Font("Dialog", PLAIN, fontSize);
	}

	public Message(String text, Point point) {
		this.text = text;
		this.point = point;
		this.color = BLACK;
		this.font = new Font("Dialog", PLAIN, 15);
	}

	public Message(String text, Point point, Integer fontSize) {
		this.text = text;
		this.point = point;
		this.color = BLACK;
		this.font = new Font("Dialog", PLAIN, fontSize);
	}

	public Message(String text, Point point, Color color) {
		this.text = text;
		this.point = point;
		this.color = color;
		this.font = new Font("Dialog", PLAIN, 15);
	}

	public Message(String text, Point point, Color color, Integer fontSize) {
		this.text = text;
		this.point = point;
		this.color = color;
		this.font = new Font("Dialog", PLAIN, fontSize);
	}
	
	public Message(String text, Point point, Integer fontSize, Integer fontStyle) {
		this.text = text;
		this.point = point;
		this.color = BLACK;
		this.font = new Font("Dialog", fontStyle, fontSize);
	}

	public Message(String text, Point point, Color color, Integer fontSize, Integer fontStyle) {
		this.text = text;
		this.point = point;
		this.color = color;
		this.font = new Font("Dialog", fontStyle, fontSize);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
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

	/**
	 * Calls the UI delegate's paint method, if the UI delegate is non-null. We
	 * pass the delegate a copy of the Graphics object to protect the rest of
	 * the paint code from irrevocable changes (for example,
	 * Graphics.translate). If you override this in a subclass you should not
	 * make permanent changes to the passed in Graphics. For example, you should
	 * not alter the clip Rectangle or modify the transform. If you need to do
	 * these operations you may find it easier to create a new Graphics from the
	 * passed in Graphics and manipulate it. Further, if you do not invoker
	 * super's implementation you must honor the opaque property, that is if
	 * this component is opaque, you must completely fill in the background in a
	 * non-opaque color. If you do not honor the opaque property you will likely
	 * see visual artifacts. The passed in Graphics object might have a
	 * transform other than the identify transform installed on it. In this
	 * case, you might get unexpected results if you cumulatively apply another
	 * transform.
	 * 
	 * @param graphics the Graphics object to protect
	 */
	@Override
	public void paintComponent(Graphics graphics) {
		final GraphicsRenderer renderer = new GraphicsRenderer(graphics);
		renderer.render(this);
		super.paintComponent(graphics);
	}

}
