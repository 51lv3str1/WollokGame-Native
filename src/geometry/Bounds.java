package geometry;

import java.awt.Dimension;

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

	public Bounds(final Integer x, final Integer y, final Double width, final Double height) {
		this(x, y, width.intValue(), height.intValue());
	}

	public Bounds(Position position, Dimension dimension) {
		this(position.x(), position.y(), dimension.getWidth(), dimension.getHeight());
	}

	public Integer x() {
		return x;
	}

	public Integer y() {
		return y;
	}

	public Integer width() {
		return width;
	}

	public Integer height() {
		return height;
	}

}