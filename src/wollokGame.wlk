import geometry.*
import input.*
import ui.*


object wollokGame {

	/**
	 * Adds an component to the current board for drawing on it. Object should
	 * understand a position property (implemented by a reference or getter
	 * method).
	 * 
	 * @param component the visual component.
	 */
	method addVisual(component) native

	/**
	 * Adds an object to the board for drawing it on a specific position.
	 * 
	 * @param component a visual component.
	 * @param position a position.
	 */
	method addVisualIn(component, position) native

	/**
	 * Adds an object to the board for drawing it. It can be moved with arrow
	 * keys. That object should understand a position property (implemented by a
	 * reference or getter method).
	 * 
	 * @param component
	 */
	method addVisualCharacter(component) {
		self.addVisual(component)
		keyboard.left().onKeyPressedDo({ component.position(self.at(component.position().x() - 1, component.position().y()))})
		keyboard.up().onKeyPressedDo({ component.position(self.at(component.position().x(), component.position().y() - 1))})
		keyboard.right().onKeyPressedDo({ component.position(self.at(component.position().x() + 1, component.position().y()))})
		keyboard.down().onKeyPressedDo({ component.position(self.at(component.position().x(), component.position().y() + 1))})
	}

	/**
	 * Returns all visual objects added to the board.
	 */
	method allVisuals() native

	/**
	 * Gets a position for given coordinates.
	 * 
	 * @param column a column
	 * @param row a row
	 */
	method at(column, row) native

	/**
	 * Returns the center board position (rounded down).
	 */
	method center() native

	/**
	 * Sets full background image.
	 * 
	 * @param background a image path
	 */
	method background(path) native

	/**
	 * Gets the background image.
	 */
	method background() native

	/**
	 * Sets cell background image.
	 * 
	 * @param background a image path.
	 */
	method ground(path) native

	/**
	 * Gets the cell background image.
	 */
	method ground() native

	/**
	 * Returns the game title.
	 */
	method title() native

	/**
	 * Sets a game title.
	 */
	method title(title) native

	/**
	 * Pause the background music associated with the given name.
	 * 
	 * @param name
	 */
	method pauseBGM(name) native

	/**
	 * Set and play a background music.
	 * 
	 * @param name
	 * @param soundPath
	 */
	method playBGM(name, soundPath) native

	/**
	 * Play a sound.
	 * 
	 * @param soundPath
	 */
	method playSound(soundPath) native

	/**
	 * Resume the background music associated with the given name.
	 * 
	 * @param name
	 */
	method resumeBGM(name) native

	/**
	 * Stop the background music associated with the given name.
	 * 
	 * @param name
	 */
	method stopBGM(name) native

	/**
	 * Adds a block that will be executed in n milliseconds. Block expects no
	 * argument.
	 * 
	 * @param milliseconds
	 * @param gameAction
	 */
	method schedule(milliseconds, closure) native

	/**
	 * Adds a block with a specific name that will be executed every n
	 * milliseconds. Block expects no argument. Be careful not to set it too
	 * often :)
	 * 
	 * @param milliseconds
	 * @param name
	 * @param gameAction
	 */
	method onTick(milliseconds, name, closure) native
	
	method createSpritesheet(path, rows, columns) {
		return new Spritesheet(path=path, rows=rows, columns=columns)
	}
	
	/**
	 * Draws a dialog balloon with a message in given visual object position.
	 * 
	 * @param component
	 * @param message
	 */
	method say(component, message) native

	/**
	 * Starts the game.
	 */
	method start() native

	/**
	 * Ends the game, and close the window.
	 */
	method end() native

}

