package game;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import geometry.Position;
import ui.Image;

public class Cell {

	private static final Image DEFAULT_TEXTURE = new Image("ground.png");
	private final List<WollokObject> components;
	private final Position position;

	public Cell(Position position) {
		this.components = new ArrayList<WollokObject>();
		this.position = position;
	}

	public List<WollokObject> components() {
		return this.components;
	}

	public Position position() {
		return this.position;
	}

	public void addComponent(WollokObject component) {
		this.components().add(component);
	}

	public void removeComponent(WollokObject component) {
		this.components().remove(component);
	}

	public Boolean hasComponent(WollokObject component) {
		return this.components().contains(component);
	}

	public Image image() {
		return DEFAULT_TEXTURE;
	}
	
}