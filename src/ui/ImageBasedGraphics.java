package ui;

import java.awt.image.BufferedImage;

public interface ImageBasedGraphics extends Renderable {

	public BufferedImage asBufferedImage();

	public Float getOpacity();

}
