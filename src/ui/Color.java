package ui;

public class Color {

	public final static Color BLACK = new Color("#000000");
	public final static Color BLUE = new Color("#0000FF");
	public final static Color CYAN = new Color("#00FFFF");
	public final static Color DARKGRAY = new Color("#A9A9A9");
	public final static Color GRAY = new Color("#808080");
	public final static Color GREEN = new Color("#008000");
	public final static Color LIGHTGRAY = new Color("#D3D3D3");
	public final static Color MAGENTA = new Color("#FF00FF");
	public final static Color ORANGE = new Color("#FFA500");
	public final static Color PINK = new Color("#FFC0CB");
	public final static Color RED = new Color("#FF0000");
	public final static Color WHITE = new Color("#FFFFFF");
	public final static Color YELLOW = new Color("#FFFF00");

	private final String hexcode;

	public Color(String hexcode) {
		this.hexcode = hexcode;
	}

	public String hexCode() {
		return this.hexcode;
	}

	public java.awt.Color asAWT() {
		return java.awt.Color.decode(this.hexCode());
	}

	@Override
	public boolean equals(Object obj) {
		return this.hexCode() == ((Color) obj).hexCode();
	}

}
