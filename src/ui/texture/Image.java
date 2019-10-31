package ui.texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A image representing a renderizable image.
 * 
 * @since 1.0
 * @version 1.0
 * @author 51lv3str1 - <a href="https://github.com/51lv3str1">https://github.com/51lv3str1</a>
 */
public class Image implements Texture {

	/**
	 * The path of this image file.
	 */
	private String path;
	
	/**
	 * The BufferedImage representation of this image.
	 */
	private BufferedImage bufferedImage;
	
	/**
	 * The image opacity.
	 */
	private Float opacity;
	
	/**
	 * Constructs and initializes a Image from a specific file path.
	 * 
	 * @param route the image file route.
	 */
	public Image(BufferedImage bufferedImage) {
		this.path = "";
		this.opacity = 1f;
		this.bufferedImage = bufferedImage;
	}

	/**
	 * Constructs and initializes a Image from a specific file path.
	 * 
	 * @param route the image file route.
	 */
	public Image(String path) {
		this.path = path;
		this.opacity = 1f;
		this.createImage();
	}
	
	/**
	 * Constructs and initializes a Image from a specific file path, with a custom opacity.
	 * 
	 * @param route the image file route.
	 * @param opacity the desired opacity level for this image.
	 */
	public Image(String path, Float opacity) {
		this.path = path;
		this.opacity = opacity;
		this.createImage();
	}

	/**
	 * Creates a buffered image. 
	 */
	private void createImage() {
		try {
			bufferedImage = ImageIO.read(new File(path));
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the Image file path.
	 * 
	 * @return the file path of this image.
	 */
	public String getPath() {
		return this.path;
	}
	
	/**
	 * Gets the opacity level.
	 * 
	 * @return the opacity level for this image.
	 */
	public Float getOpacity() {
		return opacity;
	}

	/**
	 * Sets the opacity level.
	 * 
	 * @param opacity the desired opacity level for this image.
	 */
	public void setOpacity(Float opacity) {
		this.opacity = opacity;
	}

	@Override
	public BufferedImage asBufferedImage() {
		return this.bufferedImage;
	}
	
	public Image getSubimage(Integer x, Integer y, Integer width, Integer height) {
		return new Image(this.asBufferedImage().getSubimage(x, y, width, height));
	}
	
	/**
	 * Updates this component.
	 * 
	 * @param time the elapsed time.
	 */
	@Override
	public void update(Double time) {
		
	}
	
}
