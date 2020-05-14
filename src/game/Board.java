package game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import geometry.Position;
import ui.GraphicsRenderer;

public class Board {

	private final List<Cell> cells;
	private Integer columns;
	private Integer rows;

	public Board() {
		this(8, 8);
	}

	public Board(Integer columns, Integer rows) {
		this.cells = new ArrayList<Cell>();
		this.columns(columns);
		this.rows(rows);
	}
	
	public Integer rows() {
		return this.rows;
	}

	public void rows(Integer rows) {
		this.rows = rows;
	}

	public void rows(Double rows) {
		this.rows(rows.intValue());
	}

	public void rows(WollokObject rows) {
		this.rows(Integer.valueOf(rows.toString()));
	}

	public Integer columns() {
		return this.columns;
	}

	public void columns(Integer columns) {
		this.columns = columns;
	}

	public void columns(Double columns) {
		this.columns(columns.intValue());
	}

	public void columns(WollokObject columns) {
		this.columns(Integer.valueOf(columns.toString()));
	}

	public Cell getCell(Position position) {
		Cell res = this.cells.stream().filter(cell -> cell.position().equals(position)).findFirst().orElse(null);
		if (res == null) {
			res = new Cell(position);
			this.cells.add(res);
		}
		return res;
	}

	public List<WollokObject> getComponents() {
		return this.cells.stream().flatMap(cell -> cell.components().stream()).collect(Collectors.toList());
	}

	public void addComponent(WollokObject component) {
		this.addComponentIn(component, component.call("position"));
	}

	public void addComponentIn(WollokObject component, WollokObject position) {
		this.getCell(new Position(position)).addComponent(component);
	}

	public void removeComponent(WollokObject component) {
		this.getCell(new Position(component.call("position"))).removeComponent(component);
	}

	public Boolean hasComponent(WollokObject component) {
		return this.cells.stream().anyMatch(cell -> cell.hasComponent(component));
	}
	
	public void update(Double time) {
		
	}

	public void render(GraphicsRenderer graphicsRenderer) {
		graphicsRenderer.render(this);
//		for (int row = 0; row < this.rows(); row++) {
//			for (int column = 0; column < this.columns(); column++) {
//				this.getCell(new Position(column, row)).render(graphicsRenderer);
//			}
//		}
	}

}