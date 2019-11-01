package component.scene;

import component.GameComponent;
import ui.layout.Layout;

public abstract class Scene extends GameComponent {

	private Layout layout;

	public Layout getLayout() {
		return layout;
	}

	protected void setLayout(Layout layout) {
		this.layout = layout;
	}

}