package game;

import java.util.ArrayList;
import java.util.List;

public class Board extends Scene {

	private static final Image TEXTURE = new Image("ground.png");

	private Cell[] cells;
	private GridLayout layout;
	private Image ground;
	private SpriteSheet background;
	private final List<Actor> components;

	public Board(Integer columns, Integer rows) {
		this.components = new ArrayList<Actor>();
		this.setLayout(new GridLayout(columns, rows));
		this.validate();
	}

	public void validate() {
		this.cells = new Cell[this.getColumns() * this.getRows()];
		this.setLayout(new GridLayout(this.getColumns(), this.getRows()));

		Integer index = 0;
		for (int row = 0; row < this.getRows(); row++) {
			for (int column = 0; column < this.getColumns(); column++) {
				this.cells[index] = new Cell(this, index++, new Point(column, row));
			}
		}

	}

	public GridLayout getLayout() {
		return layout;
	}

	protected void setLayout(GridLayout layout) {
		this.layout = layout;
	}

	public List<Actor> getComponents() {
		return this.components;
	}

	public Integer getColumns() {
		return this.getLayout().getColumns();
	}

	public void setColumns(Integer columns) {
		this.getLayout().setColumns(columns);
	}

	public Integer getRows() {
		return this.getLayout().getRows();
	}

	public void setRows(Integer rows) {
		this.getLayout().setRows(rows);
	}

	public Boolean hasBackground() {
		return this.background != null;
	}

	public Image getBackground() {
		return this.background.getImage();
	}

	public Image getBackgroundSubimage(Integer index) {
		return this.background.getSprite(index).getImage();
	}

	public void setBackground(String path) {
		this.background = new SpriteSheet(path, this.getColumns(), this.getRows());
	}

	public Boolean hasGround() {
		return this.ground != null;
	}

	public Image getGround() {
		return (this.hasGround()) ? this.getGround() : TEXTURE;
	}

	public void setGround(String path) {
		this.ground = new Image(path);
	}

	public Cell[] getCells() {
		return this.cells;
	}

	public void setCells(Cell... cells) {
		this.cells = cells;
	}

	public Integer size() {
		return this.getCells().length;
	}

	public Integer componentsCount() {
		return this.getComponents().size();
	}

	public Boolean hasComponent(Actor component) {
		Boolean any = false;
		Integer count = 0;
		Cell cell = null;

		while (!any || count == this.cells.length) {
			cell = this.cells[count++];
			any |= cell.hasComponent(component);
		}

		return any;
	}

	public Cell getCellAt(Point position) {
		Boolean finded = false;
		Integer count = 0;
		Cell cell = null;

		while (!finded || count == this.cells.length) {
			cell = this.cells[count++];
			finded = cell.getBoardPosition().equals(position);
		}

		return cell;
	}

	public Cell getCellWith(Actor component) {
		return this.getCellAt(component.getBoardPosition());
	}

	public void addComponent(Actor component) {
		this.getComponents().add(component);
	}

	public List<Actor> getComponentsInPoint(Point point) {
		final List<Actor> components = new ArrayList<Actor>();

		for (int index = 0; index < this.getComponents().size(); index++) {
			final Actor component = this.getComponents().get(index);
			if (component.getBoardPosition().equals(point)) {
				components.add(component);
			}
		}

		return components;
	}

	public void remove(Actor actor) {
		this.getComponents().remove(actor);
	}

	@Override
	public void render(GraphicsRenderer graphicsRenderer) {
		this.validate();

		for (int index = 0; index < this.size(); index++) {
			final Bounds bounds = this.getLayout().getBounds(index);
			final Point position = new Point(bounds.getX(), bounds.getY());
			final Dimension dimension = new Dimension(bounds.getWidth(), bounds.getHeight());
			graphicsRenderer.render(cells[index], position, dimension);
		}

		for (int index = 0; index < components.size(); index++) {
			final Actor component = this.components.get(index);
			final Bounds bounds = this.getLayout().getBounds(this.getCellWith(component).getIndex());
			final Point position = new Point(bounds.getX(), bounds.getY());
			final Dimension dimension = new Dimension(bounds.getWidth(), bounds.getHeight());
			graphicsRenderer.render(component, position, dimension);
		}

	}

	@Override
	public void update(Double time) {
		for (int index = 0; index < this.componentsCount(); index++) {
			final Actor component = this.getComponents().get(index);
			component.update(time);
			Collisions.getInstance().collides(component, this.getComponents());
		}
	}

}
