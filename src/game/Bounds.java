package game;

public class Bounds {

	private final Integer x;
	private final Integer y;
	private final Integer width;
	private final Integer height;

	public Bounds(final Integer x, final Integer y, final Integer width, final Integer height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

}