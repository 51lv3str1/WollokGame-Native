package ui.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAction implements ActionListener {

	private final Runnable action;

	public MenuAction(Runnable action) {
		this.action = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.action.run();
	}

}
