package game;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.project.wollok.interpreter.WollokInterpreter;
import org.uqbar.project.wollok.interpreter.WollokInterpreterEvaluator;
import org.uqbar.project.wollok.interpreter.core.WollokObject;
import org.uqbar.project.wollok.interpreter.core.WollokObjectListener;
import org.uqbar.project.wollok.interpreter.nativeobj.WollokJavaConversions;

public class Actor extends GameComponent implements Positionable, WollokObjectListener {

	private static final Texture DEFAULT_TEXTURE = new Image("wko.png");

	private final WollokObject wollokObject;
	private final WollokInterpreter interpreter;
	private final WollokInterpreterEvaluator evaluator;
	private Texture texture;
	private final Board board;
	private List<GameComponent> components;
	private Point boardPosition;

	public Actor(WollokObject wollokObject, Board board) {
		this.wollokObject = wollokObject;
		this.interpreter = WollokInterpreter.getInstance();
		this.evaluator = (WollokInterpreterEvaluator) interpreter.getEvaluator();
		this.components = new ArrayList<GameComponent>();
		this.createTexture();
		this.wollokObject.addFieldChangedListener(this);
		this.board = board;
	}

	public Actor(WollokObject wollokObject, Board board, WollokObject position) {
		this(wollokObject, board);
		this.setBoardPosition(position);
	}

	public WollokObject wrapper() {
		return this.wollokObject;
	}

	protected WollokInterpreter getInterpreter() {
		return this.interpreter;
	}

	protected WollokInterpreterEvaluator getEvaluator() {
		return this.evaluator;
	}

	public List<GameComponent> getComponents() {
		return this.components;
	}

	public void add(GameComponent component) {
		this.getComponents().add(component);
	}

	public Cell getCell() {
		return this.board.getCellWith(this);
	}

	public Boolean collisionable() {
		return !this.wrapper().hasProperty("collisionable") || this.wrapper().hasProperty("collisionable")
				&& Boolean.valueOf(this.wrapper().call("collisionable").toString());
	}

	@Override
	public Point getBoardPosition() {
		if(this.wrapper().hasProperty("position")){
			final Integer x = Integer.valueOf(this.wrapper().call("position").call("x").toString());
			final Integer y = Integer.valueOf(this.wrapper().call("position").call("y").toString());
			return new Point(x, y);
		}
		
		else {
			return this.boardPosition;
		}
	}

	public void setBoardPosition(Point position) {
		this.boardPosition = position;
	}

	public void setBoardPosition(WollokObject position) {
		this.boardPosition = new Point(position);
	}

	private void createAnimation() {
		final String path = this.wrapper().call("animation").call("spritesheet").call("path").toString();
		final Integer rows = Integer.valueOf(this.wrapper().call("animation").call("spritesheet").call("rows").toString());
		final Integer columns = Integer.valueOf(this.wrapper().call("animation").call("spritesheet").call("columns").toString());
		final SpriteSheet spritesheet = new SpriteSheet(path, rows, columns);
		final Boolean loop = Boolean.valueOf(this.wrapper().call("animation").call("loop").toString());
		final Integer ratio = Integer.valueOf(this.wrapper().call("animation").call("ratio").toString());
		final WollokObject frames = this.wrapper().call("animation").call("indexes");
		final Integer[] indexes = new Integer[Integer.valueOf(frames.call("size").toString())];

		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = Integer.valueOf(frames.call("get", WollokJavaConversions.javaToWollok(i)).toString());
		}

		this.texture = spritesheet.getAnimation(loop, ratio, indexes);
	}

	private void createImage(Float opacity) {
		this.texture = new Image(this.wrapper().call("image").toString(), opacity);
	}

	private void createTexture() {
		if (this.wrapper().hasProperty("image")) {
			this.createImage(this.wrapper().hasProperty("opacity")
					? Float.valueOf(this.wrapper().call("opacity").toString()) : 1.0F);
		}

		else if (this.wrapper().hasProperty("animation")) {
			this.createAnimation();
		}
	}

	private void updateTexture(Double time) {
		this.getTexture().update(time);
	}

	public Texture getTexture() {
		return this.texture.equals(null) ? DEFAULT_TEXTURE : this.texture;
	}

	@Override
	public void render(GraphicsRenderer graphicsRenderer) {
		this.getComponents().forEach(component -> graphicsRenderer.render(component));
	}

	@Override
	public void update(Double time) {
		this.updateTexture(time);
		this.getComponents().forEach(component -> component.update(time));
	}

	@Override
	public void fieldChanged(String fieldName, Object oldValue, Object newValue) {
		if (fieldName.equals("animation")) {
			this.createTexture();
		}
	}

}