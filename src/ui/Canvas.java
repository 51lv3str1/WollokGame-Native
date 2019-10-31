package ui;

import static java.awt.Color.decode;

import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class Canvas extends java.awt.Canvas {

	public Canvas() {
		this.setIgnoreRepaint(true);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(true);
	}

	/**
	 * Changes the background color.
	 */
	public void setBackground(Color color) {
		super.setBackground(decode(color.hexCode()));
	}

	/**
	 * Gets graphics.
	 */
	public Graphics2D getGraphics() {
		return (Graphics2D) this.getBufferStrategy().getDrawGraphics();
	}

	/**
	 * Show graphics on screen.
	 */
	@Override
	public void show() {
		this.getBufferStrategy().show();
	}

	/**
	 * Clear all rendered graphics from screen.
	 */
	public void clear() {
		this.getGraphics().clearRect(0, 0, this.getWidth(), this.getHeight());
		this.dispose();
	}

	/**
	 * Release from memory the current buffer strategy graphics.
	 */
	public void dispose() {
		this.getGraphics().dispose();
	}
	
}