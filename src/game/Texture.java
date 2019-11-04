package game;

import java.awt.image.BufferedImage;

public interface Texture extends Updatable {

	public BufferedImage asBufferedImage();

	public Float getOpacity();
	
	public String getPath();

}
