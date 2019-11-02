package ui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import component.scene.Board;
import geometry.Dimensionable;
import ui.menus.Menu;
import ui.menus.MenuItem;
import ui.menus.ShowGridMenuItem;
import wollokGame.InputListener;
import wollokGame.KeyInput;
import wollokGame.Keyboard;

public class Window implements Dimensionable, WindowListener, ComponentListener, InputListener {

	/**
	 * The Window instance.
	 */
	private static Window instance;

	private final JFrame frame;
	private final Canvas canvas;
	private final JMenuBar menuBar;
	private final Map<String, Menu> menus = new HashMap<String, Menu>();
	private final Keyboard keyboard;
	private String title = "My Wollok Game";

	private Window() {
		this.menuBar = new JMenuBar();
		this.frame = new JFrame("My Wollok Game");
		this.canvas = new Canvas();
		this.canvas.setIgnoreRepaint(true);
		this.frame.setJMenuBar(this.menuBar);
		this.frame.add(this.canvas,0);
		this.frame.validate();
		this.createMenus();
		this.frame.addWindowListener(this);
		this.frame.addComponentListener(this);
		this.keyboard = Keyboard.getInstance();
		this.keyboard.addListener(this);
	}

	/**
	 * Gets the GameDebugger instance.
	 */
	public static Window getInstance() {
		if (instance == null) {
			instance = new Window();
		}

		return instance;
	}

	/**
	 * Changes the background color.
	 */
	public void setBackground(Color color) {
		this.canvas.setBackground(color);
	}
	
	public void setDimension(geometry.Dimension dimension) {
		this.frame.setSize(dimension.asAWT());
		this.frame.setPreferredSize(dimension.asAWT());
		this.canvas.setSize(dimension.asAWT());
		this.canvas.setPreferredSize(dimension.asAWT());
	}

	public void setDimension(Integer width, Integer height) {
		final Dimension dimension = new Dimension(width, height);
		this.frame.setSize(dimension);
		this.frame.setPreferredSize(dimension);
		this.canvas.setSize(dimension);
		this.canvas.setPreferredSize(dimension);
	}

	public geometry.Dimension getDimension() {
		return new geometry.Dimension(this.canvas.getSize());
	}

	public MenuItem getMenuItem(String menuName, String itemName) {
		return this.menus.get(menuName).getItem(itemName);
	}

	public void createMenu(String name) {
		final Menu menu = new Menu(name);
		this.menus.put(name, menu);
		this.menuBar.add(menu.asAWT());
	}

	public void createMenuItem(String menuName, String itemName, String itemText, Runnable action) {
		this.menus.get(menuName).createItem(itemName, itemText, action);
	}

	public void addMenuItem(String menuName, MenuItem item) {
		this.menus.get(menuName).addItem(item);
	}

	private void createMenus() {
		this.createMenu("File");
		this.createMenuItem("File", "Exit", "Exit", this::close);
		this.createMenu("Window");
		this.createMenuItem("Window", "LOW", "Set Low Graphics setting", GraphicsRenderer::setLowGraphicsSetting);
		this.createMenuItem("Window", "Medium", "Set Medium Graphics setting", GraphicsRenderer::setMediumGraphicsSetting);
		this.createMenuItem("Window", "High", "Set High Graphics setting", GraphicsRenderer::setHighGraphicsSetting);
		this.createMenu("Debug");
		this.addMenuItem("Debug", new ShowGridMenuItem());
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void open() {
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
		this.canvas.createBufferStrategy(2);
	}

	public void close() {
		System.exit(0);
	}

	public void listenKeyInput(KeyInput key) {
		this.canvas.addKeyListener(key);
	}

	public void listenMouseInput() {

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Apéndice de método generado automáticamente

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Apéndice de método generado automáticamente

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		this.close();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Apéndice de método generado automáticamente

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Apéndice de método generado automáticamente

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Apéndice de método generado automáticamente

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Apéndice de método generado automáticamente

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Apéndice de método generado automáticamente

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Apéndice de método generado automáticamente

	}

	@Override
	public void componentResized(ComponentEvent arg0) {

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Apéndice de método generado automáticamente

	}

	/**
	 * Render this component.
	 * 
	 * @param fps the current FPS count.
	 */
	public void render(Integer fps, Board board) {
		
		final Graphics2D graphics = this.canvas.getGraphics();
		this.frame.setTitle(this.title + ": " + fps);
		final GraphicsRenderer graphicsRenderer = new GraphicsRenderer(graphics);
		board.render(graphicsRenderer);
		this.canvas.show();
		this.canvas.clear();
	}

}
