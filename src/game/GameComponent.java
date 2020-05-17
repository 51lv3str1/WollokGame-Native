package game;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import geometry.Position;
import ui.Image;
import utils.WollokMetaUtils;

public class GameComponent {

	public final static Image DEFAULT_IMAGE = new Image("wko.png");

	private final WollokObject wrapped;

	private final WollokMetaUtils wkoUtils = WollokMetaUtils.getInstance();

	private Position position;

	public GameComponent(WollokObject wrapped) {
		this.wrapped = wrapped;
		this.position = null;
	}

	public GameComponent(WollokObject wrapped, Position position) {
		this.wrapped = wrapped;
		this.position = position;
	}

	public GameComponent(WollokObject wrapped, WollokObject position) {
		this.wrapped = wrapped;
		this.position = new Position(position);
	}

	public WollokObject asWollokObject() {
		return this.wrapped;
	}

	public Boolean hasImageMethodDefined() {
		return this.wkoUtils.hasMethodOrPropertyDefined(this.wrapped, "image");
	}

	public Boolean hasPositionMethodDefined() {
		return this.wkoUtils.hasMethodOrPropertyDefined(this.wrapped, "position");
	}

	public Position position() {
		return this.hasPositionMethodDefined() ? new Position(this.wrapped.call("position")) : this.position;
	}

	public void position(WollokObject position) {
		if (this.hasPositionMethodDefined()) {
			this.wrapped.call("position", position);
		}

		else {
			this.position = new Position(position);
		}
	}

	public void position(Position position) {
		this.position = position;
	}

	public Image image() {
		return this.hasImageMethodDefined() ? new Image(this.wrapped.call("image")) : GameComponent.DEFAULT_IMAGE;
	}

	public boolean same(GameComponent another) {
		return this.wrapped.equals(another.asWollokObject());
	}

}