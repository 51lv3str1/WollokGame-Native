package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import static java.awt.RenderingHints.*;
import game.Board;
import game.Cell;
import geometry.Bounds;

public class GraphicsRenderer {
	
	public static final GraphicsSettings LOW_QUALITY_RENDER = new LowGraphicsSetting();
	public static final GraphicsSettings MEDIUM_QUALITY_RENDER = new MediumGraphicsSetting();
	public static final GraphicsSettings HIGH_QUALITY_RENDER = new HighGraphicsSetting();
	private static GraphicsSettings graphicSettings = MEDIUM_QUALITY_RENDER;

	/**
	 * The Java 2D graphic context.
	 */
	final private Graphics2D graphics;

	/**
	 * Constructs and initializes GraphicsRenderer from a java graphic context.
	 * 
	 * @param graphics the java graphic context.
	 */
	public GraphicsRenderer(Graphics graphics) {
		this.graphics = (Graphics2D) graphics;
		this.setSettings(GraphicsRenderer.graphicSettings);
	}
	
	/**
	 * Sets the graphic renderer quality setting.
	 * 
	 * @param setting the graphic renderer quality setting.
	 */
	private static void setGraphicsSetting(GraphicsSettings setting){
		graphicSettings = setting;
	}
	
	/**
	 * Sets the graphic renderer quality setting as low.
	 */ 
	public static void setLowGraphicsSetting(){
		GraphicsRenderer.setGraphicsSetting(LOW_QUALITY_RENDER);
	}
	
	/**
	 * Sets the graphic renderer quality setting as medium.
	 */ 
	public static void setMediumGraphicsSetting(){
		GraphicsRenderer.setGraphicsSetting(MEDIUM_QUALITY_RENDER);
	}
	
	/**
	 * Sets the graphic renderer quality setting as high.
	 */ 
	public static void setHighGraphicsSetting(){
		GraphicsRenderer.setGraphicsSetting(HIGH_QUALITY_RENDER);
	}
	
	/**
	 * Sets a graphical configuration that is used during component rendering.
	 * 
	 * @param settings The graphic configuration that will be used during component rendering.
	 */
	private void setSettings(GraphicsSettings settings) {
		this.graphics.setRenderingHint(KEY_ALPHA_INTERPOLATION, settings.alphaInterpolation());
		this.graphics.setRenderingHint(KEY_ANTIALIASING, settings.antiliasing());
		this.graphics.setRenderingHint(KEY_INTERPOLATION, settings.interpolation());
		this.graphics.setRenderingHint(KEY_RENDERING, settings.renderingQuality());
		this.graphics.setRenderingHint(KEY_TEXT_ANTIALIASING, settings.textAntiLiasing());
	}

	public void render(Image image, Integer x, Integer y, Integer width, Integer height) {
		this.graphics.drawImage(image.asBufferedImage(), x, y, width, height, null);
//		this.graphics.drawRect(x, y, width, height);
	}

	public void render(Image image, Bounds bounds) {
		this.render(image, bounds.x(), bounds.y(), bounds.width(), bounds.height());
	}

	public void render(Cell cell, Bounds bounds) {
		cell.components().stream().forEach(component -> {
			this.render(component.image(), bounds);
		});
	}

	public void render(Board board, Layout layout) {
		board.forEach((cell, index) -> {
			final Bounds bounds = layout.bounds(index);

			if (board.hasBoardGround()) {
				final Layout boardLayout = new GridLayout(board.boardGround().dimension(), board.rows(), board.columns());
				this.render(board.boardGround().subimage(boardLayout.bounds(index)), bounds);
			}

			else {
				final Image ground = board.hasGround() ? board.ground() : Board.DEFAULT_IMAGE;
//				this.render(ground, new Bounds(bounds.x(), bounds.y(), ground.width(), ground.height()));
				this.render(ground, new Bounds(bounds.x(), bounds.y(), bounds.width(), bounds.height()));
			}
		});

		board.forEach((cell, index) -> {
			this.render(cell, layout.bounds(index));
		});
	}

}