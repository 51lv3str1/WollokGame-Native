package component;

import java.util.ArrayList;
import java.util.List;

import geometry.Dimension;
import geometry.Point;
import ui.GraphicsRenderer;
import ui.SpriteSheet;
import ui.Window;
import ui.texture.Image;
import ui.texture.Renderable;

public class Board implements Renderable, Updatable {

	private static final Image TEXTURE = new Image("assets/ground.png");

	private final Integer rows;
	private final Integer columns;
	private final Cell[][] cells;
	private Image ground;
	private SpriteSheet background;
	private Window window;
	private final List<Actor> components;

	public Board(Integer rows, Integer columns) {
		this.rows = rows;
		this.columns = columns;
		this.cells = new Cell[rows][columns];
		this.window = Window.getInstance();
		this.createRowsAndColumns();
		this.components = new ArrayList<Actor>();
	}

	private void createRowsAndColumns() {
		Integer index = 0;
		for (int column = 0; column < columns; column++) {
			for (int row = 0; row < rows; row++) {
				this.cells[row][column] = new Cell(this, index++, new Point(row, column));
			}
		}
	}

	public List<Cell> getCells() {
		List<Cell> cells = new ArrayList<Cell>();
		for (int column = 0; column < columns; column++) {
			for (int row = 0; row < rows; row++) {
				cells.add(this.cells[row][column]);
			}
		}
		return cells;
	}
	
	public List<Actor> getComponents() {
		return this.components;
	}

	public Integer getRows() {
		return rows;
	}

	public Integer getColumns() {
		return columns;
	}

	public Dimension getDimension() {
		return this.window.getSize();
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
		this.background = new SpriteSheet(path, this.getRows(), this.getColumns());
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
	
	public Boolean hasComponent(Actor component) {
		return this.getCells().stream().anyMatch(cell -> cell.hasComponent(component));
	}

	public Cell getCellAt(Point position) {
		return this.getCells().stream().filter(cell -> cell.getBoardPosition().equals(position)).findFirst().get();
	}

	public Cell getCellWith(Actor component) {
		return this.getCells().stream().filter(cell -> cell.hasComponent(component)).findFirst().get();
	}
	
	public void addComponent(Actor component, Point position) {
		this.cells[position.getX()][position.getY()].add(component);
	}
	
	public void remove(Actor actor) {
		
	}

	@Override
	public void render(Integer fps, GraphicsRenderer graphicsRenderer) {
		this.getCells().stream().forEach(cell -> cell.render(fps, graphicsRenderer));
		this.getComponents().forEach(actor -> actor.render(fps, graphicsRenderer));
	}

	@Override
	public void update(Double time) {
		Integer x = 0;
		Integer y = 0;
		final Integer width = this.getDimension().getWidth() / this.columns;
		final Integer height = this.getDimension().getHeight() / this.rows;
		Integer index = 0;

		for (int row = 0; row < this.rows; row++) {
			x = 0;
			for (int column = 0; column < this.columns; column++) {
				final Point position = new Point(x, y);
				final Dimension dimension = new Dimension(width, height);
				final Cell cell = this.getCells().get(index++);
				cell.setPosition(position);
				cell.setDimension(dimension);
				cell.update(time);
				x = x + width;
			}
			y = y + height;
		}
		this.getComponents().forEach(actor -> actor.update(time));
	}

}
