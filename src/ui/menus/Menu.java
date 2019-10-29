package ui.menus;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenu;

public class Menu {

	final private JMenu menu;
	private Map<String, MenuItem> menuItems = new HashMap<String, MenuItem>();

	public Menu(String name) {
		this.menu = new JMenu(name);
	}

	public String getName() {
		return this.menu.getName();
	}

	public void setName(String name) {
		this.menu.setName(name);
	}

	public MenuItem getItem(String name) {
		return this.menuItems.get(name);
	}

	public void createItem(String name, String text, Runnable action) {
		final MenuItem item = new MenuItem(name, text, action);
		this.menuItems.put(name, item);
		this.menu.add(item.asAWT());
	}

	public void addItem(MenuItem item) {
		this.menuItems.put(item.getName(), item);
		this.menu.add(item.asAWT());
	}

	public JMenu asAWT() {
		return this.menu;
	}

}
