package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import game.Board;
import game.Cell;
import geometry.Bounds;
import geometry.GridLayout;
import geometry.Layout;
import geometry.Position;

public class GraphicsRenderer {

	/**
	 * The Java 2D graphic context.
	 */
	final private Graphics2D graphics;

	/**
	 * The frame dimension.
	 */
	final private Dimension dimension;

	/**
	 * Constructs and initializes GraphicsRenderer from a java graphic context.
	 * 
	 * @param graphics the java graphic context.
	 */
	public GraphicsRenderer(Graphics graphics, Dimension dimension) {
		this.graphics = (Graphics2D) graphics;
		this.dimension = dimension;
	}

	public void render(Image image, Bounds bounds) {
		this.graphics.drawImage(image.asBufferedImage(), bounds.x(), bounds.y(), bounds.width(), bounds.height(), null);
		this.graphics.drawRect(bounds.x(), bounds.y(), bounds.width(), bounds.height());
	}

	public void render(Cell cell, Bounds bounds) {
		this.render(cell.image(), bounds);
		cell.components().stream().forEach(component -> {
			if (component.hasProperty("image")) {
				this.render(new Image("ground.png"), bounds);
			} 
			else {
				this.render(new Image("wko.png"), bounds);
			}
		});
	}

	public void render(Board board, Layout layout) {
		Integer index = 0;
		for (int row = 0; row < board.rows(); row++) {
			for (int column = 0; column < board.columns(); column++) {
				this.render(board.getCell(new Position(column, row)), layout.bounds(index));
				index++;
			}
		}
	}

	public void render(Board board) {
		this.render(board, new GridLayout(this.dimension, board.rows(), board.columns(), true));
	}

}