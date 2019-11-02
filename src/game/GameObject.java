package game;

import static java.awt.Font.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbar.project.wollok.interpreter.WollokInterpreter;
import org.uqbar.project.wollok.interpreter.WollokInterpreterEvaluator;
import org.uqbar.project.wollok.interpreter.core.WollokObject;
import org.uqbar.project.wollok.interpreter.nativeobj.WollokJavaConversions;

import component.Balloon;
import component.actor.Actor;
import component.scene.Board;
import geometry.Point;
import ui.Window;

@SuppressWarnings({ "unused" })
public class GameObject {

	/** Wollok instance for this game. */
	private final WollokObject wrapped;

	/** Wollok interpreter. */
	private final WollokInterpreter interpreter;

	/** Wollok evaluator. */
	private final WollokInterpreterEvaluator evaluator;

	/** The windows instance for this game. */
	private final Window window;

	/** The GameLoop instance for this game. */
	private final GameLoop gameloop;

	/** The current board. */
	private Board board;

	public GameObject(WollokObject wrapped) {
		this.wrapped = wrapped;
		this.interpreter = WollokInterpreter.getInstance();
		this.evaluator = (WollokInterpreterEvaluator) interpreter.getEvaluator();
		this.gameloop = new GameLoop(this);
		this.board = new Board(8, 8);
		this.window = Window.getInstance();
		this.window.setDimension(800, 600);
	}

	/**
	 * Adds an component to the current board for drawing on it. Object should
	 * understand a position property (implemented by a reference or getter
	 * method).
	 * 
	 * @param component the visual component.
	 */
	public void addVisual(WollokObject wcomponent) {
		this.board.addComponent(new Actor(wcomponent, this.board));
	}
	
	/**
	 * Removes an object from the board for stop drawing it.
	 *
	 * @param component a visual component.
	 *
	 * Example:
	 *     game.removeVisual(pepita)
	 */
	public void removeVisual(WollokObject component) {
		this.board.remove(this.getActor(component));
	}

	/**
	 * Returns all visual objects added to the board.
	 */
	public WollokObject allVisuals() {
		return toWollokListObject(this.board.getComponents());
	}

	private WollokObject toWollokListObject(List<Actor> actors) {
		final WollokObject wcomponents = this.evaluator.newInstance("wollok.lang.List");
		actors.forEach(component -> wcomponents.call("add", component.wrapper()));
		return wcomponents;
	}
	
	/**
	 * Gets the actor for this wollok object.
	 * 
	 * @param component
	 */
	private Actor getActor(WollokObject component){
		return this.board.getComponents().stream().filter(actor -> actor.wrapper().equals(component)).findFirst().orElse(null);
	}
	
	/**
	 * Returns board height (in cells).
	 */
	public WollokObject height() {
		return WollokJavaConversions.convertJavaToWollok(this.board.getRows());
	}
	
	/**
	 * Sets board height (in cells).
	 */
	public void height(WollokObject height) {
		this.board.setRows(Integer.valueOf(height.toString()));
	}

	/**
	 * Returns board width (in cells).
	 */
	public WollokObject width() {
		return WollokJavaConversions.convertJavaToWollok(this.board.getColumns());
	}
	
	/**
	 * Sets board width (in cells).
	 */
	public void width(WollokObject width) {
		this.board.setColumns(Integer.valueOf(width.toString()));
	}
	
	/**
	 * Returns the game title.
	 */
	public WollokObject title() {
		return WollokJavaConversions.convertJavaToWollok(this.window.getTitle());
	}

	/**
	 * Sets a game title.
	 */
	public void title(WollokObject title) {
		window.setTitle(title.toString());
	}

	/**
	 * Sets full background image.
	 * 
	 * @param background
	 */
	public void ground(WollokObject path) {
		this.board.setBackground(path.toString());
	}

	/**
	 * Sets the cell background image.
	 */
	public void boardGround(WollokObject path) {
		this.board.setGround(path.toString());
	}
	
	/**
	 * Returns all objects in given position.
	 * 
	 * Example:
	 *     game.getObjectsIn(game.origin())
	 */
	public WollokObject getObjectsIn(WollokObject position) {
		return toWollokListObject(board.getComponentsInPoint(new Point(position)));
	}

	/**
	 * Draws a dialog balloon with a message in given visual object position.
	 * 
	 * @param component
	 * @param message
	 */
	public void say(WollokObject component, WollokObject message) {
		final Actor actor = this.getActor(component);
		actor.add(new Balloon(actor, message.toString()));
	}
	
	/**
	 * Adds a block that will be executed exactly when the given object collides with other. 
	 * Two objects collide when are in the same position.
	 * 
	 * The block should expect the other object as parameter.
	 * 
	 * Example:
	 *     game.onCollideDo(pepita, { comida => pepita.comer(comida) })
	 */
	public void whenCollideDo(WollokObject visual, WollokObject action) {
		Collisions.getInstance().listenCollisionWithAny(visual, action);
	}
	
	public void removeCollisionEvent(WollokObject visual){
		Collisions.getInstance().stopListeningCollisionWithAny(visual);
	}
	
	/**
	 * Starts the game.
	 */
	public void start() {	
		this.window.open();
		this.gameloop.run();
	}

	/**
	 * Ends the game, and close the window.
	 */
	public void stop() {
		this.gameloop.end();
		this.window.close();
	}

	/**
	 * Updates this component.
	 * 
	 * @param time the elapsed time.
	 */
	public void update(Double time) {
		this.board.update(time);
	}

	/**
	 * Render this component.
	 * 
	 * @param fps the current FPS count.
	 */
	public void render(Integer fps) {
		this.window.render(fps, this.board);
	}

}