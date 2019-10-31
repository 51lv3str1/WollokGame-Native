package ui.menus;

import debugger.GameDebugger;

public class ShowGridMenuItem extends MenuItem {

	private static final String SHOW_GRID_MESSAGE = "Show grid";
	private static final String HIDE_GRID_MESSAGE = "Hide grid";

	public ShowGridMenuItem() {
		super("Debug Menu", SHOW_GRID_MESSAGE);
		this.setAction(this::action);
	}

	private void action() {
		final GameDebugger debugger = GameDebugger.getInstance();

		if (debugger.isShowingGrid()) {
			debugger.hideGrid();
			this.setText(SHOW_GRID_MESSAGE);
		}

		else {
			debugger.showGrid();
			this.setText(HIDE_GRID_MESSAGE);
		}

	}

}
