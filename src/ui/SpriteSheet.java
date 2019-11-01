package ui;

import ui.texture.Animation;
import ui.texture.Image;
import ui.texture.Sprite;

public class SpriteSheet {
	private final Image image;
	private final Image[] chunks;

	public SpriteSheet(String path, Integer columns, Integer rows) {
		this.image = new Image(path);
		this.chunks = this.createChunks(rows, columns);
	}

	private Image[] createChunks(Integer columns, Integer rows) {
		final Integer size = rows * columns;
		final Image chunks[] = new Image[size];
		Integer x = 0;
		Integer y = 0;
		final Integer width = this.image.asBufferedImage().getWidth() / columns;
		final Integer height = this.image.asBufferedImage().getHeight() / rows;
		Integer index = 0;

		for (int row = 0; row < rows; row++) {
			x = 0;
			for (int column = 0; column < columns; column++) {
				chunks[index++] = this.image.getSubimage(x, y, width, height);
				x = x + width;
			}
			y = y + height;
		}
		return chunks;
	}

	public Image getImage() {
		return this.image;
	}

	public Image getImage(Integer index) {
		return this.chunks[index];
	}

	public Sprite getSprite(Integer index) {
		return new Sprite(this, index);
	}

	public Animation getAnimation(Integer ratio, Integer... indexes) {
		return new Animation(this, ratio, indexes);
	}

	public Animation getAnimation(Boolean loop, Integer ratio, Integer... indexes) {
		return new Animation(this, loop, ratio, indexes);
	}

	public String getPath() {
		return this.image.getPath();
	}

}
