package ui;

import java.awt.Dimension;

import geometry.Bounds;

public class GridLayout implements Layout {

	private final Bounds[] boundary;
	
	public GridLayout(Dimension dimension, Integer rows, Integer columns) {
		this(dimension, rows, columns, true);
	}

	public GridLayout(Dimension dimension, Integer rows, Integer columns, Boolean yInverse) {
		this.boundary = new Bounds[rows * columns];
		final Integer width = Double.valueOf(dimension.getWidth() / columns).intValue();
		final Integer height = Double.valueOf(dimension.getHeight() / rows).intValue();
		Integer index = 0;
		Integer x = 0;
		Integer y = yInverse ? height * (rows - 1) : 0;

		for (int row = 0; row < rows; row++) {
			x = 0;
			for (Integer column = 0; column < columns; column++) {
				this.boundary[index++] = new Bounds(x, y, width, height);
				x = x + width;
			}
			y = yInverse ? y - height : y + height;
		}
	}

	public Bounds bounds(Integer index) {
		return this.boundary[index];
	}

}