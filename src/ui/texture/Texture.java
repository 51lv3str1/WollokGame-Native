package ui.texture;

import java.awt.image.BufferedImage;

import component.Updatable;

public interface Texture extends Updatable {

	public BufferedImage asBufferedImage();

	public Float getOpacity();
	
	public String getPath();

}
