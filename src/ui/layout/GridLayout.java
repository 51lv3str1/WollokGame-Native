package ui.layout;

import java.awt.Rectangle;

import geometry.Dimensionable;
import ui.Window;

public class GridLayout implements Layout {

	private final Dimensionable dimensionable;
	private final Integer rows;
	private final Integer columns;
	private final Rectangle[][] bounds;

	public GridLayout(Integer columns, Integer rows) {
		this.dimensionable = Window.getInstance();
		this.columns = columns;
		this.rows = rows;
		this.bounds = new Rectangle[columns][rows];
	}
	
	public GridLayout(Dimensionable dimensionable, Integer columns, Integer rows) {
		this.dimensionable = dimensionable;
		this.columns = columns;
		this.rows = rows;
		this.bounds = new Rectangle[columns][rows];
	}

	public void validate() {
		Integer x = 0;
		Integer y = 0;
		final Integer width = this.dimensionable.getDimension().getWidth() / this.columns;
		final Integer height = this.dimensionable.getDimension().getHeight() / this.rows;

		for (int row = 0; row < this.rows; row++) {
			x = 0;
			for (Integer column = 0; column < this.columns; column++) {
				this.bounds[column][row] = new Rectangle(x, y, width, height);
				x = x + width;
			}
			y = y + height;
		}

	}

	public Integer getRows() {
		return rows;
	}

	public Integer getColumns() {
		return columns;
	}

	public Rectangle getBounds(Integer index) {
		this.validate();
		Integer count = 0;
		Integer x = 0;
		Integer y = 0;

		for (int row = 0; row < this.rows; row++) {
			for (int column = 0; column < this.columns; column++) {
				if (count == index) {
					return this.bounds[x][y];
				}
				count++;
				x++;
			}
			x = 0;
			y++;
		}
		return null;
	}

}
