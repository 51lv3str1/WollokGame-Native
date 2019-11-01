package ui;

import static java.awt.RenderingHints.*;
import static ui.Color.*;
import static java.awt.AlphaComposite.*;

import java.awt.AlphaComposite;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import component.Balloon;
import component.GameComponent;
import component.Message;
import component.actor.Actor;
import component.scene.Cell;
import debugger.GameDebugger;
import geometry.Dimension;
import geometry.Point;
import ui.settings.GraphicSettings;
import ui.settings.HighGraphicsSetting;
import ui.settings.LowGraphicsSetting;
import ui.settings.MediumGraphicsSetting;
import ui.texture.Texture;

/**
 * A GraphicsRenderer representing a graphics context wrapper
 * that allow an application to render game components on screen.
 * 
 * @since 1.0
 * @version 1.0
 * @author 51lv3str1 - <a href="https://github.com/51lv3str1">https://github.com/51lv3str1</a>
 */
public class GraphicsRenderer {

	
	public static final GraphicSettings LOW_QUALITY_RENDER = new LowGraphicsSetting();
	public static final GraphicSettings MEDIUM_QUALITY_RENDER = new MediumGraphicsSetting();
	public static final GraphicSettings HIGH_QUALITY_RENDER = new HighGraphicsSetting();
	private static GraphicSettings graphicSettings = LOW_QUALITY_RENDER;
			
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
		this.setSettings(graphicSettings);
	}
	
	/**
	 * Sets the graphic renderer quality setting.
	 * 
	 * @param setting the graphic renderer quality setting.
	 */
	private static void setGraphicsSetting(GraphicSettings setting){
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
	private void setSettings(GraphicSettings settings) {
		this.graphics.setRenderingHint(KEY_ALPHA_INTERPOLATION, settings.alphaInterpolation());
		this.graphics.setRenderingHint(KEY_ANTIALIASING, settings.antiliasing());
		this.graphics.setRenderingHint(KEY_INTERPOLATION, settings.interpolation());
		this.graphics.setRenderingHint(KEY_RENDERING, settings.renderingQuality());
		this.graphics.setRenderingHint(KEY_TEXT_ANTIALIASING, settings.textAntiLiasing());
	}
	
	/**
	 * Render an image on screen with a given dimension.
	 * 
	 * @param image the image to be rendered.
	 * @param dimension the desired image dimension.
	 */
	public void render(Texture image, Point position, Dimension dimension) {
		this.graphics.drawImage(image.asBufferedImage(), position.getX(), position.getY(), dimension.getWidth(), dimension.getHeight(), null);
	}

	/**
	 * Render a actor on screen.
	 * 
	 * @param actor the actor to be rendered.
	 */
	public void render(Actor actor, Point position, Dimension dimension) {
		this.graphics.setComposite(AlphaComposite.getInstance(SRC_OVER, actor.getTexture().getOpacity()));
		this.render(actor.getTexture(), position, dimension);
		actor.render(this);
	}

	/**
	 * Render a cell on screen.
	 * 
	 * @param cell the cell to be rendered.
	 */
	public void render(Cell cell, Point position, Dimension dimension) {
		final GameDebugger debugger = GameDebugger.getInstance();

		if (cell.getBoard().hasBackground()) {
			this.render(cell.getBoard().getBackgroundSubimage(cell.getIndex()), position, dimension);
		}
		
		else {
			this.render(cell.getImage(), position, dimension);
		}

		if (debugger.isShowingGrid()) {
			this.graphics.drawRect(position.getX(), position.getY(), dimension.getWidth(), dimension.getHeight());
		}
		
	}
	
	/**
	 * Render a message on screen.
	 * 
	 * @param message the message to be rendered.
	 */
	public void render(Message message) {
//		this.graphics.setColor(message.getColor().asAWT());
//		this.graphics.setFont(message.getFont());
//		this.graphics.drawString(message.getText(), message.getPosition().getX(), message.getPosition().getY());
	}

	/**
	 * Render a balloon on screen.
	 * 
	 * @param balloon the balloon to be rendered.
	 */
	public void render(Balloon balloon) {
//		final FontMetrics metrics = this.graphics.getFontMetrics(balloon.getFont());
//		final Point position = balloon.getSpeecher().getAvailableNeighborPosition();
//		final Rectangle bounds = new Rectangle(position.getX(), position.getY(), metrics.stringWidth(balloon.getMessageText()) + 10, metrics.getHeight() * 2);
//		final int boundsRoundBorder = 20;
//		final int messageX = bounds.x + (bounds.width - metrics.stringWidth(balloon.getMessageText())) / 2;
//		final int messageY = bounds.y + ((bounds.height - metrics.getHeight()) / 2) + metrics.getAscent();
//		this.graphics.setColor(WHITE.asAWT());
//		this.graphics.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, boundsRoundBorder, boundsRoundBorder);
//		balloon.setPosition(new Point(messageX, messageY));
//		this.render(balloon.getMessage());
	}

	public void render(GameComponent component) {
		component.render(this);
	}

}
