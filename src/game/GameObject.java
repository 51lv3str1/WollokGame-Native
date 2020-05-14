package game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.util.List;

import javax.swing.JFrame;

import org.uqbar.project.wollok.interpreter.WollokInterpreter;
import org.uqbar.project.wollok.interpreter.WollokInterpreterEvaluator;
import org.uqbar.project.wollok.interpreter.core.WollokObject;
import org.uqbar.project.wollok.interpreter.nativeobj.WollokJavaConversions;

import ui.GraphicsRenderer;

public class GameObject implements ComponentListener, Runnable, WindowListener {

	/** Wollok interpreter. */
	private final WollokInterpreter interpreter;

	/** Wollok evaluator. */
	private final WollokInterpreterEvaluator evaluator;

	/** Game Thread */
	private volatile Thread loopThread = new Thread(this);

	/** Game Frame */
	private final JFrame frame = new JFrame(this.title);

	/** Game Canvas */
	private final Canvas canvas = new Canvas();

	/** Game running state */
	private Boolean isRunning = false;

	/** Current fps count */
	private Integer fpsCount = 0;

	/** FPS target */
	private Integer fps = 60;

	/** UPS target */
	private Integer ups = 60;

	/** Game Title **/
	private String title = "My Game";

	private Dimension dimension;

	private Board board;

	public GameObject() {
		this.interpreter = WollokInterpreter.getInstance();
		this.evaluator = (WollokInterpreterEvaluator) interpreter.getEvaluator();
		this.loopThread.setDaemon(true);
		this.frame.add(this.canvas, 0);
		this.frame.validate();
		this.frame.addWindowListener(this);
		this.frame.addComponentListener(this);
		this.dimension(800, 600);
		this.board = new Board(8, 8);
	}

	private WollokObject toWollokListObject(List<WollokObject> components) {
		final WollokObject wcomponents = this.evaluator.newInstance("wollok.lang.List");
		components.forEach(component -> wcomponents.call("add", component));
		return wcomponents;
	}

	public void addVisual(WollokObject component) {
		this.board.addComponent(component);
	}

	public void addVisualIn(WollokObject component, WollokObject position) {
		this.board.addComponent(component);
	}

	public WollokObject allVisuals() {
		return this.toWollokListObject(this.board.getComponents());
	}

	public Boolean hasVisual(WollokObject component) {
		return this.board.hasComponent(component);
	}

	public void removeVisual(WollokObject component) {
		this.board.removeComponent(component);
	}

	public void stop() {
		System.exit(0);
	}

	public void start() {
		this.run();
	}

	public String title() {
		return this.title;
	}

	public void title(String title) {
		this.title = title;
	}

	public WollokObject width() {
		return WollokJavaConversions.convertJavaToWollok(this.board.columns());
	}

	public void width(WollokObject columns) {
		this.board.columns(columns);
	}

	public WollokObject height() {
		return WollokJavaConversions.convertJavaToWollok(this.board.rows());
	}

	public void height(WollokObject rows) {
		this.board.rows(rows);
	}

	public Dimension dimension() {
		return this.dimension;
	}

	private void dimension(Integer width, Integer height) {
		this.dimension = new Dimension(width, height);
		this.frame.setSize(dimension);
		this.frame.setPreferredSize(dimension);
		this.canvas.setSize(dimension);
		this.canvas.setPreferredSize(dimension);
	}

	public void run() {
		long initialTime = System.nanoTime();
		double timeU = 1000000000.0 / ups;
		double timeF = 1000000000.0 / fps;
		double deltaU = 0.0;
		double deltaF = 0.0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		this.isRunning = true;
		this.open();

		while (this.isRunning) {

			long currentTime = System.nanoTime();
			deltaU += (currentTime - initialTime) / timeU;
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;

			if (deltaU >= 1) {
				// update logic here.
				this.board.update(deltaU);
				deltaU--;
			}

			if (deltaF >= 1) {
				// render logic here.
				this.frame.setTitle(this.title + ": " + fps);
				final GraphicsRenderer graphicsRenderer = new GraphicsRenderer(this.graphics(), this.canvas.getSize());
				this.board.render(graphicsRenderer);
				this.frame.setTitle(this.title() + " : " + this.fpsCount);
				frames++;
				deltaF--;
				this.showGraphics();
				this.clearGraphics();
				this.diposeGraphics();
			}

			if (System.currentTimeMillis() - timer > 1000) {
				this.fpsCount = frames;
				frames = 0;
				timer += 1000;
			}

			this.sleep(initialTime, deltaU);
		}
	}

	public void end() {
		this.isRunning = false;
	}

	private void sleep(Long lastLoopTime, Double OPTIMAL_TIME) {
		try {
			Thread.sleep((long) Math.max(0, ((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000)));
		}

		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Window methods.

	public void open() {
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
		this.canvas.setIgnoreRepaint(true);
		this.canvas.createBufferStrategy(2);
	}
	
	private BufferStrategy bufferStrategy() {
		return this.canvas.getBufferStrategy();
	}

	private Graphics2D graphics() {
		return (Graphics2D) this.bufferStrategy().getDrawGraphics();
	}
	
	private void showGraphics() {
		this.bufferStrategy().show();
	}
	
	private void clearGraphics() {
		this.graphics().clearRect(0, 0, Double.valueOf(this.dimension().getWidth()).intValue(), Double.valueOf(this.dimension().getHeight()).intValue());
	}
	
	private void diposeGraphics() {
		this.graphics().dispose();
	}

	// Window listeners methods.

	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowClosing(WindowEvent arg0) {
		this.stop();
	}

	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

}