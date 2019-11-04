package game;

public class SpriteSheet {
	private final Image image;
	private final Image[] chunks;
	private final GridLayout layout;

	public SpriteSheet(String path, Integer columns, Integer rows) {
		this.image = new Image(path);
		this.layout = new GridLayout(this.image, columns, rows);
		this.chunks = this.createChunks(rows, columns);
	}

	private Image[] createChunks(Integer columns, Integer rows) {
		final Image chunks[] = new Image[columns * rows];
		Integer index = 0;

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				final Bounds bounds = this.layout.getBounds(index);
				chunks[index++] = this.image.getSubimage(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
			}
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
