package game;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import geometry.Position;
import ui.GraphicsRenderer;
import ui.Image;
import ui.Layout;

public class Board {

	public final static Image DEFAULT_IMAGE = new Image("ground.png");
	private Image ground;
	private Image boardGround;
	private final List<Cell> cells;
	private final List<GameComponent> components;
	private Integer columns;
	private Integer rows;

	public Board() {
		this(5, 5);
	}

	public Board(Integer columns, Integer rows) {
		this.cells = new ArrayList<Cell>();
		this.components = new ArrayList<GameComponent>();
		this.columns(columns);
		this.rows(rows);
	}

	/**
	 * Returns board height (in cells).
	 */
	public Integer rows() {
		return this.rows;
	}

	/**
	 * Sets board height (in cells).
	 */
	public void rows(Integer rows) {
		this.rows = rows;
	}

	/**
	 * Sets board height (in cells).
	 */
	public void rows(Double rows) {
		this.rows(rows.intValue());
	}

	/**
	 * Sets board height (in cells).
	 */
	public void rows(WollokObject rows) {
		this.rows(Integer.valueOf(rows.toString()));
	}

	/**
	 * Returns board width (in cells).
	 */
	public Integer columns() {
		return this.columns;
	}

	/**
	 * Sets board width (in cells).
	 */
	public void columns(Integer columns) {
		this.columns = columns;
	}

	/**
	 * Sets board width (in cells).
	 */
	public void columns(Double columns) {
		this.columns(columns.intValue());
	}

	/**
	 * Sets board width (in cells).
	 */
	public void columns(WollokObject columns) {
		this.columns(Integer.valueOf(columns.toString()));
	}

	public List<Cell> cells() {
		return this.cells;
	}

	/**
	 * Create a new cell at the given position.
	 */
	private Cell createCellAt(Position position) {
		final Cell cell = new Cell(this, position);
		this.cells().add(cell);
		return cell;
	}

	public Boolean existCellAt(Position position) {
		return this.cells().stream().anyMatch(cell -> cell.position().equals(position));
	}

	public Cell getCell(Position position) {
		return this.existCellAt(position)
				? this.cells().stream().filter(cell -> cell.position().equals(position)).findFirst().get()
				: this.createCellAt(position);
	}

	public List<GameComponent> components() {
		return this.components;
	}

	public List<GameComponent> componentsIn(Position position) {
		return this.components().stream().filter(component -> component.position().equals(position))
				.collect(Collectors.toList());
	}
	
	public void addComponent(GameComponent component) {
		this.components().add(component);
	}
	
	public Boolean hasComponent(GameComponent component) {
		return this.components.stream().anyMatch(another -> component.same(another));
	}

	public void removeComponent(GameComponent component) {
		this.components().removeIf(another -> component.same(another));
	}

	public Image ground() {
		return this.ground;
	}

	public void ground(WollokObject resource) {
		this.ground = new Image(resource.toString());
	}

	public Boolean hasGround() {
		return this.ground() != null;
	}

	public Image boardGround() {
		return this.boardGround;
	}

	public void boardGround(WollokObject resource) {
		this.boardGround = new Image(resource.toString());
	}

	public Boolean hasBoardGround() {
		return this.boardGround() != null;
	}

	public void forEach(BiConsumer<Cell, Integer> closure) {
		Integer index = 0;
		for (int row = 0; row < this.rows(); row++) {
			for (int column = 0; column < this.columns(); column++) {
				closure.accept(this.getCell(new Position(column, row)), index++);
			}
		}
	}

	public void update(Double time) {

	}

	public void render(GraphicsRenderer graphicsRenderer, Layout layout) {
		graphicsRenderer.render(this, layout);
	}

}