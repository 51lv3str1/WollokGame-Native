package game;

public class GridLayout implements Layout {

	private Dimensionable dimensionable;
	private Integer columns;
	private Integer rows;
	private Bounds[] boundary;

	public GridLayout(Dimensionable dimensionable, Integer columns, Integer rows) {
		this.dimensionable = dimensionable;
		this.columns = columns;
		this.rows = rows;
		this.boundary = new Bounds[columns * rows];
		this.validate();
	}

	public GridLayout(Integer columns, Integer rows) {
		this(Window.getInstance(), columns, rows);
	}

	public void validate() {
		final Integer width = this.dimensionable.getDimension().getWidth() / this.getColumns();
		final Integer height = this.dimensionable.getDimension().getHeight() / this.getRows();
		Integer index = 0;
		Integer x = 0;
		Integer y = 0;

		for (int row = 0; row < this.getRows(); row++) {
			x = 0;
			for (Integer column = 0; column < this.getColumns(); column++) {
				this.boundary[index++] = new Bounds(x, y, width, height);
				x = x + width;
			}
			y = y + height;
		}
	}

	public Integer getColumns() {
		return columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Bounds getBounds(Integer index) {
		return this.boundary[index];
	}

}
