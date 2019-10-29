package wollokGame;

import static ui.Color.*;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import geometry.Point;
import ui.Color;
import ui.graphics.GraphicsRenderer;

@SuppressWarnings("serial")
public class Balloon extends JComponent {

	private final WollokObject component;
	private final Message message;
	
	public Balloon(WollokObject component, WollokObject message) {
	this.component = component;
	this.message = new Message(message.toString());
}
	
//	public Balloon(VisualComponent component, String message) {
//		this.component = component;
//		this.message = new Message(message, new Point());
//	}
//
//	public Balloon(VisualComponent component, String message, Color color) {
//		this.component = component;
//		this.message = new Message(message, new Point(), color);
//	}
//
//	public Balloon(VisualComponent component, String message, Integer fontSize) {
//		this.component = component;
//		this.message = new Message(message, new Point(), fontSize);
//	}
//	
//	public Balloon(VisualComponent component, String message, Integer fontSize, Integer fontStyle) {
//		this.component = component;
//		this.message = new Message(message, new Point(), BLACK, fontSize, fontStyle);
//	}
//
//	public Balloon(VisualComponent component, String message, Color color, Integer fontSize, Integer fontStyle) {
//		this.component = component;
//		this.message = new Message(message, new Point(), color, fontSize, fontStyle);
//	}

	public WollokObject getSpeecher() {
		return component;
	}

	public Message getMessage() {
		return message;
	}

	public String getMessageText() {
		return this.message.getText();
	}

	public void setMessageText(String text) {
		this.message.setText(text);
	}

	public Point getPoint() {
		return this.message.getPoint();
	}

	public void setPoint(Point point) {
		this.message.setPoint(point);
	}

	public Color getColor() {
		return this.message.getColor();
	}

	public void setColor(Color color) {
		this.message.setColor(color);
	}

	@Override
	public Font getFont() {
		return this.message.getFont();
	}

	public void setFont(Integer fontSize, Integer fontStyle) {
		this.message.setFont(fontSize, fontStyle);
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
