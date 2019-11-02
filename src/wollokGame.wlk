import geometry.*
import input.*
import ui.*
import scheduler.*
import sound.*

object wollokGame {

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
	 * Removes an object from the board for stop drawing it.
	 * 
	 * @param component a visual component.
	 * 
	 * Example:
	 *     game.removeVisual(pepita)
	 */
	method removeVisual(component) native

	/**
	 * Returns all visual objects added to the board into a List.
	 * 
	 * Example:
	 *     game.allVisuals()
	 */
	method allVisuals() native

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
	method addVisualIn(component, position) {
		component.position(position)
		self.addVisual(component)
	}

	/**
	 * Adds an object to the board for drawing it. It can be moved with arrow keys.
	 * That object should understand a position property 
	 * (implemented by a reference or getter method).
	 * 
	 * @param component
	 * 
	 * Example:
	 *     game.addVisualCharacter(pepita) ==> pepita should have a position property
	 */
	method addVisualCharacter(component) {
		self.addVisual(component)
		keyboard.left().onKeyPressedDo({component.position(component.position().left(1))})
		keyboard.up().onKeyPressedDo({component.position(component.position().up(1))})
		keyboard.right().onKeyPressedDo({component.position(component.position().right(1))})
		keyboard.down().onKeyPressedDo({component.position(component.position().down(1))})
	}

	/**
	 * Adds an object to the board for drawing it on a specific position. It can be moved with arrow keys.
	 * 
	 * @param component a visual component.
	 * @param position a position.
	 * 
	 * Example:
	 *     game.addVisualCharacterIn(pepita, game.origin()) ==> no need for pepita to have a position property
	 */
	method addVisualCharacterIn(component, position) {
		component.position(position)
		self.addVisualCharacter(component)
	}

	/**
	 * Verifies if an object is currently in the board.
	 * 
	 * @param component a visual component.
	 * 
	 * Example:
	 *     game.hasVisual(pepita)
	 */
	method hasVisual(component) {
		return self.allVisuals().contains(component)
	}

	/**
	 * Adds a block that will be executed each time a specific key is pressed
	 * @see keyboard.onPressDo()
	 */
	method whenKeyPressedDo(key, action) {
		key.onKeyPressedDo(action)
	}

	/**
	 * Adds a block that will be executed while the given object collides with other. 
	 * Two objects collide when are in the same position.
	 * 
	 * The block should expect the other object as parameter.
	 * 
	 * Example:
	 *     game.whenCollideDo(pepita, { comida => pepita.comer(comida) })
	 */
	method whenCollideDo(visual, action) native

	/**
	 * Adds a block that will be executed exactly when the given object collides with other. 
	 * Two objects collide when are in the same position.
	 * 
	 * The block should expect the other object as parameter.
	 * 
	 * Example:
	 *     game.onCollideDo(pepita, { comida => pepita.comer(comida) })
	 */
	method onCollideDo(visual, action) {
		self.whenCollideDo(visual, action)
	}
	
	method removeCollisionEvent(visual) native

	/**
	 * Adds a block with a specific name that will be executed every n
	 * milliseconds. Block expects no argument. Be careful not to set it too
	 * often :)
	 * 
	 * @param milliseconds
	 * @param name
	 * @param gameAction
	 */
	method onTick(milliseconds, name, closure) {
		scheduler.onTick(milliseconds, name, closure)
	}

	/**
	 * Adds a block that will be executed in n milliseconds. Block expects no
	 * argument.
	 * 
	 * @param milliseconds
	 * @param gameAction
	 */
	method schedule(milliseconds, closure) {
		scheduler.schedule(milliseconds, closure)
	}

	/**
	 * Remove a tick event created with onTick message
	 * 
	 * Example:
	 *      game.removeTickEvent("pepitaMoving")
	 */
	method removeTickEvent(name) {
		scheduler.removeTickEvent(name)
	}

	/**
	 * Returns all objects in given position.
	 * 
	 * Example:
	 *     game.getObjectsIn(game.origin())
	 */
	method getObjectsIn(position) {
	// TODO
	}

	/**
	 * Draws a dialog balloon with a message in given visual object position.
	 * 
	 * @param component
	 * @param message
	 */
	method say(component, message) native

	/**
	 * Removes all visual objects on board and configurations (colliders, keys, etc).
	 */
	method clear() {
		// TODO Collisions
		scheduler.clearEvents()
		keyboard.clearEvents()
	}

	/**
	 * Returns all objects that are in same position of given object.
	 */
	method colliders(visual) {
		// TODO
		return []
	}

	/**
	 * Returns the unique object that is in same position of given object.
	 */
	method uniqueCollider(visual) = self.colliders(visual).uniqueElement()

	/**
	 * Ends the game, and close the window.
	 */
	method stop() native

	/**
	 * Starts the game.
	 */
	method start() native

	/**
	 * Gets a position for given coordinates.
	 * 
	 * @param column a column
	 * @param row a row
	 */
	method at(column, row) {
		return new Position(x = column, y = row)
	}

	/**
	 * Returns the position (0,0).
	 */
	method origin() = self.at(0, 0)

	/**
	 * Returns the center board position (rounded down).
	 */
	method center() {
		return self.at(self.width() / 2, self.height() / 2)
	}

	/**
	 * Returns the game title.
	 */
	method title() native

	/**
	 * Sets a game title.
	 */
	method title(title) native

	/**
	 * Sets board width (in cells).
	 */
	method width(width) native

	/**
	 * Returns board width (in cells).
	 */
	method width() native

	/**
	 * Sets board height (in cells).
	 */
	method height(height) native

	/**
	 * Returns board height (in cells).
	 */
	method height() native

	/**
	 * Sets cells background image.
	 */
	method ground(path) native

	/**
	 * Sets full background image.
	 */
	method boardGround(path) native

	/**
	 * Play a sound.
	 * 
	 * @param soundPath
	 */
	method sound(soundPath) {
		return new Sound(path = soundPath)
	}

	/**
	 * Creates a spritesh. A spritesheet can create Sprites and Animation.
	 * 
	 * @param path the image path.
	 * @param rows the rows that conform the spritesheet.
	 * @param columns the columns that conforms the spritesheet.
	 */
	method createSpritesheet(path, columns, rows) {
		return new Spritesheet(path = path, columns = columns, rows = rows)
	}

}

