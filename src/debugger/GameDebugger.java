package debugger;

/**
 * <p>An GameDebugger represents a debugging system for wollok-game testing purpose.</p>
 * 
 * @since 1.0
 * @version 1.0
 * @author 51lv3str1 - <a href="https://github.com/51lv3str1">https://github.com/51lv3str1</a>
 */
public class GameDebugger {

	/**
	 * The GameDebugger instance.
	 */
	private static GameDebugger instance;

	/**
	 * Is Showing grid?.
	 */
	private Boolean showingGrid;

	/**
	 * Constructs and initializes a GameDebugger.
	 */
	private GameDebugger() {
		this.showingGrid = false;
	}

	/**
	 * Gets the GameDebugger instance.
	 */
	public static GameDebugger getInstance() {
		if (instance == null) {
			instance = new GameDebugger();
		}

		return instance;
	}

	/**
	 * is Showing grid?.
	 * 
	 * @return is showing grid system is enable.
	 */
	public Boolean isShowingGrid() {
		return showingGrid;
	}

	/**
	 * Sets showing grid flag with specific boolean flag.
	 * 
	 * @param flag the showing grid system flag.
	 */
	private void setShowingGrid(Boolean flag) {
		this.showingGrid = flag;
	}

	/**
	 * Sets showing grid system flag to true;
	 */
	public void showGrid() {
		this.setShowingGrid(true);
	}

	/**
	 * Sets showing grid system flag to false;
	 */
	public void hideGrid() {
		this.setShowingGrid(false);
	}

}
