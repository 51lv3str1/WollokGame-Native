package ui.texture;

import ui.GraphicsRenderer;

public interface Renderable {

	/**
	 * Render this component.
	 * 
	 * @param fps the current FPS count.
	 */
	public void render(Integer fps, GraphicsRenderer graphicsRenderer);
	
}
