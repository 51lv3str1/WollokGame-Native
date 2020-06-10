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

	public Position position() {
		return this.wkoUtils.hasMethod(this.wrapped, "position", 0) || this.wkoUtils.hasProperty(this.wrapped, "position")
				? new Position(this.wrapped.call("position")) : this.position;
	}

	public void position(WollokObject position) {
		this.position(new Position(position));
	}

	public void position(Position position) {
		if (this.wkoUtils.hasMethod(this.wrapped, "position", 1) || this.wkoUtils.hasProperty(this.wrapped, "position")) {
			this.wrapped.call("position", GameObject.game.at(position));
		}

		else {
			this.position = position;
		}
	}

	public Image image() {
		return this.wkoUtils.hasMethod(this.wrapped, "image", 0) || this.wkoUtils.hasProperty(this.wrapped, "image")
				? new Image(this.wrapped.call("image")) : GameComponent.DEFAULT_IMAGE;
	}

	public boolean same(GameComponent another) {
		return this.wrapped.equals(another.asWollokObject());
	}

}