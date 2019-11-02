package component.scene;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import component.actor.Actor;
import game.Collisions;
import geometry.Dimension;
import geometry.Point;
import ui.GraphicsRenderer;
import ui.SpriteSheet;
import ui.layout.GridLayout;
import ui.texture.Image;

public class Board extends Scene {

	private static final Image TEXTURE = new Image("assets/ground.png");

	private final Cell[] cells;
	private Integer rows;
	private Integer columns;
	private final List<Actor> components;
	private Image ground;
	private SpriteSheet background;

	public Board(Integer columns, Integer rows) {
		this.columns = columns;
		this.rows = rows;
		this.setLayout(new GridLayout(this.columns, this.rows));
		this.cells = new Cell[this.columns * this.rows];
		this.components = new ArrayList<Actor>();
		this.createRowsAndColumns();
	}

	private void createRowsAndColumns() {
		Integer index = 0;
		for (int row = 0; row < this.getRows(); row++) {
			for (int column = 0; column < this.getColumns(); column++) {
				this.cells[index] = new Cell(this, index, new Point(column, row));
				index++;
			}
		}
	}
	
	public List<Actor> getComponents() {
		return this.components;
	}
	
	public Integer getColumns() {
		return this.columns;
	}
	
	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	public Integer getRows() {
		return this.rows;
	}
	
	public void setRows(Integer rows) {
		this.rows = rows;
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
	
	public List<Actor> getComponentsInPoint(Point point){
		return this.getComponents().stream().
				filter(component -> component.getBoardPosition().equals(point)).
				collect(Collectors.toList());
	}

	public void remove(Actor actor) {
		this.getComponents().remove(actor);
	}

	@Override
	public void render(GraphicsRenderer graphicsRenderer) {

		for (int index = 0; index < cells.length; index++) {
			final Rectangle bounds = this.getLayout().getBounds(index);
			final Point position = new Point(bounds.x, bounds.y);
			final Dimension dimension = new Dimension(bounds.width, bounds.height);
			graphicsRenderer.render(this.cells[index], position, dimension);
		}

		for (int index = 0; index < components.size(); index++) {
			final Actor component = this.components.get(index);
			final Rectangle bounds = this.getLayout().getBounds(this.getCellWith(component).getIndex());
			final Point position = new Point(bounds.x, bounds.y);
			final Dimension dimension = new Dimension(bounds.width, bounds.height);
			graphicsRenderer.render(component, position, dimension);
		}

	}

	@Override
	public void update(Double time) {
		final Collisions collisions = Collisions.getInstance();
		final Actor[] components = new Actor[this.components.size()];
		
		for (int index = 0; index < this.components.size(); index++) {
			components[index] = this.components.get(index);
			components[index].update(time);
		}
		
		for (int index = 0; index < components.length; index++) {
			collisions.collides(components[index], components);
		}
		
	}

}
