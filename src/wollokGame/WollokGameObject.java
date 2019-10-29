package wollokGame;

import static java.awt.Font.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import org.uqbar.project.wollok.interpreter.WollokInterpreter;
import org.uqbar.project.wollok.interpreter.WollokInterpreterEvaluator;
import org.uqbar.project.wollok.interpreter.core.WollokObject;
import org.uqbar.project.wollok.interpreter.nativeobj.WollokJavaConversions;

import audio.Audio;
import audio.BGM;
import audio.Sound;
import geometry.Point;
import input.Keyboard;
import ui.Window;

@SuppressWarnings({ "unused" })
public class WollokGameObject {

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

	private Map<String, Timer> ticks = new HashMap<String, Timer>();
	private Map<String, Audio> bgm = new HashMap<String, Audio>();

	public WollokGameObject(WollokObject wrapped) {
		this.wrapped = wrapped;
		this.interpreter = WollokInterpreter.getInstance();
		this.evaluator = (WollokInterpreterEvaluator) interpreter.getEvaluator();
		this.gameloop = new GameLoop(this);
		this.board = new Board(8, 8);
		this.window = new Window(this.board, 800, 600);
	}

	/**
	 * Adds an component to the current board for drawing on it. Object should
	 * understand a position property (implemented by a reference or getter
	 * method).
	 * 
	 * @param component the visual component.
	 */
	public void addVisual(WollokObject wcomponent) {
		this.board.addVisualComponent(new VisualComponent(wcomponent));
	}
	
	/**
	 * Adds an object to the board for drawing it on a specific position.
	 * 
	 * @param component a visual component.
	 * @param position a position.
	 */
	public void addVisualIn(WollokObject component, WollokObject position) {
		component.call("position",position);
		this.addVisual(component);
	}

	/**
	 * Returns all visual objects added to the board.
	 */
	public WollokObject allVisuals() {
		final WollokObject wcomponents = this.evaluator.newInstance("wollok.lang.List");
		this.board.getVisualComponets().forEach(component -> wcomponents.call("add", component.asWollokObject()));
		return wcomponents;
	}

	/**
	 * Returns a position for given coordinates.
	 * 
	 * @param x
	 * @param y
	 */
	public WollokObject at(WollokObject x, WollokObject y) {
		final WollokObject position = this.evaluator.newInstance("geometry.Position");
		position.addReference("x", x);
		position.addReference("y", y);
		return position;
	}

	/**
	 * Returns the center board position (rounded down).
	 */
	public WollokObject center() {
		final Integer width = Integer.valueOf(this.getWidth().toString());
		final Integer height = Integer.valueOf(this.getHeight().toString());
		final WollokObject wWidth = WollokJavaConversions.convertJavaToWollok(width / 2);
		final WollokObject wHeight = WollokJavaConversions.convertJavaToWollok(height / 2);
		return this.at(wWidth, wHeight);
	}

	/**
	 * Returns board height (in cells).
	 */
	public WollokObject getHeight() {
		return WollokJavaConversions.convertJavaToWollok(this.board.getColumns());
	}

	/**
	 * Returns board width (in cells).
	 */
	public WollokObject getWidth() {
		return WollokJavaConversions.convertJavaToWollok(this.board.getRows());
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
	public void background(WollokObject path) {
		this.board.setBackgroundImage(path.toString());
	}

	/**
	 * Gets the background image.
	 */
	public WollokObject background() {
		return WollokJavaConversions.convertJavaToWollok(this.board.getBackgroundImage().getPath());
	}

	/**
	 * Sets the cell background image.
	 */
	public void ground(WollokObject path) {
		this.board.setCellsBackground(path.toString());
	}

	/**
	 * Gets the cell background image.
	 */
	public WollokObject ground() {
		return WollokJavaConversions.convertJavaToWollok(this.board.getCellsBackground().getPath());
	}

	/**
	 * Pause the background music associated with the given name.
	 * 
	 * @param name
	 */
	public void pauseBGM(String name) {
		bgm.get(name).pause();
	}

	/**
	 * Set and play a background music.
	 * 
	 * @param name
	 * @param soundPath
	 */
	public void playBGM(String name, String soundPath) {
		bgm.put(name, new BGM(soundPath));
		bgm.get(name).play();
	}

	/**
	 * Play a sound.
	 * 
	 * @param soundPath
	 */
	public void playSound(String soundPath) {
		new Sound(soundPath).play();
	}

	/**
	 * Resume the background music associated with the given name.
	 * 
	 * @param name
	 */
	public void resumeBGM(String name) {
		bgm.get(name).resume();
	}

	/**
	 * Stop the background music associated with the given name.
	 * 
	 * @param name
	 */
	public void stopBGM(String name) {
		bgm.get(name).stop();
	}

	/**
	 * Adds a block that will be executed in n milliseconds. Block expects no
	 * argument.
	 * 
	 * @param milliseconds
	 * @param gameAction
	 */
	public void schedule(int milliseconds, WollokObject closure) {
		Timer timer = new Timer(milliseconds, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				closure.call("apply");
			}

		});
		timer.setRepeats(false);
		timer.setCoalesce(true);
		timer.start();
	}

	/**
	 * Adds a block with a specific name that will be executed every n
	 * milliseconds. Block expects no argument. Be careful not to set it too
	 * often :)
	 * 
	 * @param milliseconds
	 * @param name
	 * @param gameAction
	 */
	public void onTick(int milliseconds, String name, WollokObject closure) {
		Timer timer = new Timer(milliseconds, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closure.call("apply");
			}
		});
		this.ticks.put(name, timer);
		timer.setCoalesce(true);
		timer.start();
	}
	
	/**
	 * Draws a dialog balloon with a message in given visual object position.
	 * 
	 * @param component
	 * @param message
	 */
	public void say(WollokObject component, WollokObject message) {
		window.add(new Balloon(component, message));
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
	public void end() {
		this.gameloop.end();
		this.window.close();
	}

	/**
	 * Render hook.
	 * 
	 * @param fps the current fps count.
	 */
	public void render(Integer fps) {
		this.window.render(fps);
	}

	/**
	 * Update hook
	 * 
	 * @param time the current time.
	 */
	public void update(Double time) {
		this.board.update(time);
	}

}