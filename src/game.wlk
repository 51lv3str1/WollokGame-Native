import geometry.Position.*

object game {
	
	/**
	 * Adds an object to the board for drawing it.
	 * Object should understand a position property 
	 * (implemented by a reference or getter method).
	 * 
	 * @param component the visual component.
	 * 
	 * Example:
	 *     game.addVisual(pepita) ==> pepita should have a position property
	 */
	method addVisual(component) native
	
	/**
	 * Returns all visual objects added to the board into a List.
	 * 
	 * Example:
	 *     game.allVisuals()
	 */
	method allVisuals() native
	
	/**
	 * Verifies if an object is currently in the board.
	 * 
	 * @param component a visual component.
	 * 
	 * Example:
	 *     game.hasVisual(pepita)
	 */
	method hasVisual(component) native
	
	/**
	 * Adds an object to the board for drawing it on a specific position.
	 * 
	 * @param component a visual component.
	 * @param position a position.
	 * 
	 * Example:
	 *     game.addVisualIn(pepita, game.origin()) ==> no need for pepita to have a position property
	 *     game.addVisualIn(pepita, game.at(2, 2))
	 */
	method addVisualIn(component, position) native
	
	/**
	 * Removes an object from the board for stop drawing it.
	 * 
	 * @param component a visual component.
	 * 
	 * Example:
	 *     game.removeVisual(pepita)
	 */
	method removeVisual(component) native

	/** Gets a position for given coordinates.
	 * 
	 * @param column a column
	 * @param row a row
	 */
	method at(column, row) = new Position(x = column, y = row)

	/**
	 * Ends the game, and close the window.
	 */
	method stop() native

	/**
	 * Starts the game.
	 */
	method start() native

	/**
	 * Returns the game title.
	 */
	method title() native

	/**
	 * Sets a game title.
	 */
	method title(title) native

	/**
	 * Returns board width (in cells).
	 */
	method width() native

	/**
	 * Sets board width (in cells).
	 */
	method width(width) native

	/**
	 * Sets board height (in cells).
	 */
	method height(height) native

	/**
	 * Returns board height (in cells).
	 */
	method height() native

	/**
	 * Returns the center board position (rounded down).
	 */
	method center() = self.at(self.width() / 2, self.height() / 2)

}

