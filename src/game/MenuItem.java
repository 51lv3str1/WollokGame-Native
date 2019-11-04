package game;

import javax.swing.JMenuItem;

public class MenuItem {

	private final JMenuItem item;

	public MenuItem(String name) {
		this.item = new JMenuItem();
		this.setName(name);
	}

	public MenuItem(String name, String text) {
		this.item = new JMenuItem();
		this.item.setName(name);
		this.item.setText(text);
	}

	public MenuItem(String name, String text, Runnable action) {
		this.item = new JMenuItem();
		this.item.setName(name);
		this.item.setText(text);
		item.addActionListener(new MenuAction(action::run));
	}

	protected void setAction(Runnable action) {
		item.addActionListener(new MenuAction(action::run));
	}

	public String getName() {
		return this.item.getName();
	}

	public void setName(String name) {
		this.item.setName(name);
		this.item.revalidate();
	}

	public String getText() {
		return this.item.getText();
	}

	public void setText(String name) {
		this.item.setText(name);
		this.item.revalidate();
	}

	public JMenuItem asAWT() {
		return this.item;
	}

}
