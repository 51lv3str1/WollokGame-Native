package ui;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import input.InputListener;
import input.KeyInput;
import input.Keyboard;
import ui.graphics.GraphicsRenderer;
import ui.menus.Menu;
import ui.menus.MenuItem;
import ui.menus.ShowGridMenuItem;
import wollokGame.Board;

public class Window implements WindowListener, ComponentListener, InputListener {

	private final JFrame frame;
	private final JMenuBar menuBar;
	private Map<String, Menu> menus = new HashMap<String, Menu>();
	final Keyboard keyboard;
	private Board board;

	public Window(Board board, Integer width, Integer height) {
		this.menuBar = new JMenuBar();
		this.frame = new JFrame("My Wollok Game");
		this.frame.setJMenuBar(this.menuBar);
		this.frame.setSize(width, height);
		this.createMenus();
		this.board = board;
		this.add(board);
		this.frame.setLocationRelativeTo(null);
		this.frame.addWindowListener(this);
		this.frame.addComponentListener(this);
		this.keyboard = Keyboard.getInstance();
		this.keyboard.addListener(this);
		System.setProperty("sun.java2d.opengl", "false");
		System.setProperty("sun.java2d.d3d", "false");
	}

	public void setSize(Integer width, Integer height) {
		final Dimension dimension = new Dimension(width, height);
		this.frame.setSize(dimension);
		this.frame.setPreferredSize(dimension);
		this.board.setSize(dimension);
		this.board.setPreferredSize(dimension);
	}

	public void add(JComponent component) {
		this.frame.add(component, 0);
		this.frame.revalidate();
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
		this.createMenuItem("Window", "LOW", "Set Medium Graphics setting", GraphicsRenderer::setMediumGraphicsSetting);
		this.createMenuItem("Window", "LOW", "Set High Graphics setting", GraphicsRenderer::setHighGraphicsSetting);
		this.createMenu("Debug");
		this.addMenuItem("Debug", new ShowGridMenuItem());
	}

	public String getTitle() {
		return this.frame.getTitle();
	}

	public void setTitle(String title) {
		this.frame.setTitle(title);
	}

	private void resize() {
		this.board.setSize(this.frame.getContentPane().getSize());
		this.board.setPreferredSize(this.frame.getContentPane().getSize());
		this.frame.revalidate();
	}

	public void open() {
		this.frame.setVisible(true);
	}

	public void close() {
		System.exit(0);
	}

	public void listenKeyInput(KeyInput key) {
		this.frame.addKeyListener(key);
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
		this.resize();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Apéndice de método generado automáticamente

	}

//	public void update(Double time) {
//		this.board.revalidate();
//	}

	public void render(Integer fps) {
		this.frame.repaint();
	}

}
