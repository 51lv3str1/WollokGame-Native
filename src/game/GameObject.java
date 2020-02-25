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

	/** the Scheduler instance for this game. */
	private final Scheduler scheduler = Scheduler.getInstance();

	/** the Collisions instance for this game. */
	private final Collisions collisions = Collisions.getInstance();

	/** the Keyboard instance for this game. */
	private final Keyboard keyboard = Keyboard.getInstance();

	
	public GameObject(WollokObject wrapped) {
		this.wrapped = wrapped;
		this.interpreter = WollokInterpreter.getInstance();
		this.evaluator = (WollokInterpreterEvaluator) interpreter.getEvaluator();
		this.gameloop = new GameLoop(this);
		this.board = new Board(8, 8);
		this.window = Window.getInstance();
		this.window.setDimension(800, 600);
	}

	public void addVisual(WollokObject component) {
		this.board.addComponent(new Actor(component, this.board));
	}
	
	public void addVisualIn(WollokObject component, WollokObject position) {
		this.board.addComponent(new Actor(component, this.board, position));
	}
	
	public void addVisualCharacter(WollokObject component) {
		this.addVisual(component);
	}
	
	public void addVisualCharacterIn(WollokObject component, WollokObject position) {
		this.addVisualIn(component, position);
	}

	public void onTick(WollokObject milliseconds, WollokObject name, WollokObject closure) {
		this.scheduler.onTick(milliseconds, name, closure);
	}

	public void removeTickEvent(WollokObject name) {
		this.scheduler.removeTickEvent(name);
	}

	public void schedule(WollokObject milliseconds, WollokObject closure) {
		this.scheduler.schedule(milliseconds, closure);
	}

	public void removeVisual(WollokObject component) {
		this.board.remove(this.getActor(component));
	}

	public WollokObject allVisuals() {
		return this.toWollokListObject(this.board.getComponents());
	}

	private WollokObject toWollokListObject(List<Actor> actors) {
		final WollokObject wcomponents = this.evaluator.newInstance("wollok.lang.List");
		actors.forEach(component -> wcomponents.call("add", component.wrapper()));
		return wcomponents;
	}

	private Actor getActor(WollokObject component) {
		return this.board.getComponents().stream().filter(actor -> actor.wrapper().equals(component)).findFirst().orElse(null);
	}

	public WollokObject height() {
		return WollokJavaConversions.convertJavaToWollok(this.board.getRows());
	}

	public void height(WollokObject height) {
		this.board.setRows(Integer.valueOf(height.toString()));
	}

	public WollokObject width() {
		return WollokJavaConversions.convertJavaToWollok(this.board.getColumns());
	}

	public void width(WollokObject width) {
		this.board.setColumns(Integer.valueOf(width.toString()));
	}

	public WollokObject title() {
		return WollokJavaConversions.convertJavaToWollok(this.window.getTitle());
	}

	public void title(WollokObject title) {
		window.setTitle(title.toString());
	}

	public void ground(WollokObject path) {
		this.board.setBackground(path.toString());
	}

	public void boardGround(WollokObject path) {
		this.board.setGround(path.toString());
	}

	public WollokObject getObjectsIn(WollokObject position) {
		return this.toWollokListObject(this.board.getComponentsInPoint(new Point(position)));
	}

	public void say(WollokObject component, WollokObject message) {
		final Actor actor = this.getActor(component);
		actor.add(new Balloon(actor, message.toString()));
	}
	
	public void clear() {
		this.keyboard.clear();
		this.collisions.clear();
		this.scheduler.clear();
	}

	public void whenCollideDo(WollokObject visual, WollokObject action) {
		this.collisions.listenCollisionWithAny(visual, action);
	}

	public void removeCollisionEvent(WollokObject visual) {
		this.collisions.stopListeningCollisionWithAny(visual);
	}

	public void start() {
		this.window.open();
		this.gameloop.run();
	}

	public void stop() {
		this.gameloop.end();
		this.window.close();
	}

	synchronized public void update(Double time) {
		this.board.update(time);
	}

	synchronized public void render(Integer fps) {
		this.window.render(fps, this.board);
	}
	
	

}