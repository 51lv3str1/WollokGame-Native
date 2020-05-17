package ui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import geometry.Bounds;

/**
 * A image representing a renderizable image.
 * 
 * @since 1.0
 * @version 1.0
 * @author 51lv3str1 - <a href= "https://github.com/51lv3str1">https://github.com/51lv3str1</a>
 */
public class Image {

	/**
	 * The name of this image file.
	 */
	private String name;

	/**
	 * The BufferedImage representation of this image.
	 */
	private BufferedImage bufferedImage;

	/**
	 * The image opacity.
	 */
	private Float opacity;

	/**
	 * Constructs and initializes a Image by name.
	 * 
	 */
	public Image(BufferedImage bufferedImage) {
		this.name = "";
		this.opacity = 1f;
		this.bufferedImage = bufferedImage;
	}

	/**
	 *  Constructs and initializes a Image by name.
	 * 
	 * @param image name.
	 */
	public Image(String name) {
		this.name = name;
		this.opacity = 1f;
		this.createImage();
	}
	
	/**
	 * Constructs and initializes a Image from a specific file path.
	 * 
	 * @param @param image name.
	 */
	public Image(WollokObject name) {
		this.name = name.toString();
		this.opacity = 1f;
		this.createImage();
	}

	/**
	 * Constructs and initializes a Image from a specific file path, with a
	 * custom opacity.
	 * 
	 * @param route the image file route.
	 * @param opacity  the desired opacity level for this image.
	 */
	public Image(String path, Float opacity) {
		this.name = path;
		this.opacity = opacity;
		this.createImage();
	}

	/**
	 * Creates a buffered image.
	 */
	private void createImage() {
		try {
			bufferedImage = ImageIO.read(new File(this.getClass().getClassLoader().getResource(name).getFile()));
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
	public String path() {
		return this.name;
	}

	/**
	 * Gets the opacity level.
	 * 
	 * @return the opacity level for this image.
	 */
	public Float opacity() {
		return opacity;
	}

	/**
	 * Sets the opacity level.
	 * 
	 * @param opacity the desired opacity level for this image.
	 */
	public void opacity(Float opacity) {
		this.opacity = opacity;
	}

	public BufferedImage asBufferedImage() {
		return this.bufferedImage;
	}
	
	public Image subimage(Bounds bounds) {
		return new Image(this.bufferedImage.getSubimage(bounds.x(), bounds.y(), bounds.width(), bounds.height()));
	}

	public Image subimage(Integer x, Integer y, Integer width, Integer height) {
		return new Image(this.bufferedImage.getSubimage(x, y, width, height));
	}
	
	public Dimension dimension() {
		return new Dimension(this.bufferedImage.getWidth(), this.bufferedImage.getHeight());
	}

	public Integer width() {
		return Double.valueOf(this.dimension().getWidth()).intValue();
	}

	public Integer height() {
		return Double.valueOf(this.dimension().getHeight()).intValue();
	}
	
}