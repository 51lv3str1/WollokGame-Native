package component;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.project.wollok.interpreter.WollokInterpreter;
import org.uqbar.project.wollok.interpreter.WollokInterpreterEvaluator;
import org.uqbar.project.wollok.interpreter.core.WollokObject;
import org.uqbar.project.wollok.interpreter.nativeobj.WollokJavaConversions;

import geometry.Point;
import geometry.Scale;
import ui.GraphicsRenderer;
import ui.SpriteSheet;
import ui.texture.Image;
import ui.texture.Texture;

public class Actor extends GameComponent implements Positionable {

	private static final Texture DEFAULT_TEXTURE = new Image("assets/wko.png");

	private final WollokObject wollokObject;
	private final WollokInterpreter interpreter;
	private final WollokInterpreterEvaluator evaluator;
	private Texture texture;
	private Board board;
	private List<GameComponent> components;

	public Actor(WollokObject wollokObject) {
		this.wollokObject = wollokObject;
		this.interpreter = WollokInterpreter.getInstance();
		this.evaluator = (WollokInterpreterEvaluator) interpreter.getEvaluator();
		this.components = new ArrayList<GameComponent>();
		this.createTexture();
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
	
	public Cell getCell() {
		return this.board.getCellAt(this.getBoardPosition());
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public List<GameComponent> getComponents() {
		return this.components;
	}
	
	public void add(GameComponent component){
		this.getComponents().add(component);
	}
	
	@Override
	public Point getPosition() {
		return this.board.getCellAt(this.getBoardPosition()).getPosition();
	}

	@Override
	public Point getBoardPosition() {
		final Integer x = Integer.valueOf(this.wrapper().call("position").call("x").toString());
		final Integer y = Integer.valueOf(this.wrapper().call("position").call("y").toString());
		return new Point(x, y);
	}

	public void setBoardPosition(Point position) {
		final WollokObject wposition = this.getEvaluator().newInstance("geometry.Position");
		final WollokObject x = WollokJavaConversions.convertJavaToWollok(position.getX());
		final WollokObject y = WollokJavaConversions.convertJavaToWollok(position.getY());
		wposition.setReference("x", x);
		wposition.setReference("y", y);
		this.wrapper().call("position", wposition);
	}

	public void setBoardPosition(WollokObject position) {
		this.wrapper().call("position", position);
	}

	public void translate(Integer x, Integer y) {
		
	}
	
	@Override
	public void translate(Point position) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void translateTo(Integer x, Integer y) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void translateTo(Point position) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void translateTo(WollokObject position) {
		// TODO Apéndice de método generado automáticamente
		
	}

//	public void translate(Point position) {
//		this.setBoardPosition(this.getBoardPosition().translate(position));
//	}
//
//	public void translate(WollokObject position) {
//		final Integer x = Integer.valueOf(position.call("x").toString());
//		final Integer y = Integer.valueOf(position.call("y").toString());
//		this.translate(new Point(x, y));
//	}
//
//	public void translateTo(Integer x, Integer y) {
//		this.translate(x - this.getBoardPosition().getX(), y - this.getBoardPosition().getY());
//	}
//
//	public void translateTo(Point point) {
//		this.translateTo(point.getX(), point.getY());
//	}
//
//	public void translateTo(WollokObject position) {
//		final Integer x = Integer.valueOf(position.call("x").toString());
//		final Integer y = Integer.valueOf(position.call("y").toString());
//		this.translateTo(x, y);
//	}

	/**
	 * Creates a animation for this actor.
	 */
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

	/**
	 * Creates a image for this actor.
	 * 
	 * @param opacity a opacity value.
	 */
	private void createImage(Float opacity) {
		this.texture = new Image(this.wrapper().call("image").toString(), opacity);
	}

	/**
	 * Creates a texture for this actor.
	 */
	private void createTexture() {
		if (this.wrapper().hasProperty("image")) {
			this.createImage(this.wrapper().hasProperty("opacity") ? Float.valueOf(this.wrapper().call("opacity").toString()) : 1.0F);
		}

		else if (this.wrapper().hasProperty("animation")) {
			this.createAnimation();
		}
	}

	/**
	 * Gets this actor texture.
	 */
	public Texture getTexture() {
		return this.texture.equals(null) ? DEFAULT_TEXTURE : this.texture;
	}
	
	public Boolean canTranslate(Point position) {
		return position.getX() >= 0 && position.getX() <= this.board.getColumns() 
				&& position.getY() >= 0 && position.getY() <= this.board.getRows();
	}

	public Point getAvailableNeighborPosition() {
		
		if (this.canTranslate(new Point(this.getCell().getBoardPosition().getX() + 1,this.getCell().getBoardPosition().getY()))) {
			return this.getCell().onEast().getPosition();
		}
		
		if (this.canTranslate(new Point(this.getCell().getBoardPosition().getX() - 1, this.getCell().getBoardPosition().getY()))) {
			return this.getCell().onWest().getPosition();
		}
		
		if (this.canTranslate(new Point(this.getCell().getBoardPosition().getX(), this.getCell().getBoardPosition().getY() - 1))) {
			return this.getCell().onNorth().getPosition();
		}
		
		if (this.canTranslate(new Point(this.getCell().getBoardPosition().getX(),this.getCell().getBoardPosition().getY() + 1))) {
			return this.getCell().onSouth().getPosition();
		}
		
		return null;
	}

	@Override
	public void render(Integer fps, GraphicsRenderer graphicsRenderer) {
		graphicsRenderer.render(this);
		this.getComponents().forEach(component ->  graphicsRenderer.render(fps, component));
	}

	@Override
	public void update(Double time) {
		this.getTexture().update(time);
	}

	@Override
	public void rotate(Double theta) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void rotate(Double theta, Point vector) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void rotate(Double theta, Integer x, Integer y) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void rotateAboutCenter(Double theta) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void scale(Integer x, Integer y) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void scale(Scale scale) {
		// TODO Apéndice de método generado automáticamente
		
	}

}