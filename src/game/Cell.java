package game;

import java.util.List;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import geometry.Position;

public class Cell {

	private final Board board;
	private final Position position;

	public Cell(Board board, Position position) {
		this.board = board;
		this.position = position;
	}

	public Position position() {
		return this.position;
	}

	public List<GameComponent> components() {
		return this.board.componentsIn(this.position());
	}

	public Boolean hasComponent(WollokObject component) {
		Position compomentPosition = new Position(
				component.hasProperty("position") ? 
				component.call("position") : 
				component.resolve("position"));
		return compomentPosition.equals(position);
	}
	
}