import ui.*
import scheduler.*

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

class Point {

	const property x
	const property y

	method translate(_x, _y) {
		return new Point(x = self.x() + _x, y = self.y() + _y)
	}

	method translate(destiny) {
		return self.translate(destiny.x(), destiny.y())
	}

	method samePosition(point) {
		return self.x().equals(point.x()) and self.y().equals(point.y())
	}

}

class Position inherits Point {

	method up(n) {
		return new Position(x = x, y = y - n)
	}

	method down(n) {
		return new Position(x = x, y = y + n)
	}

	method left(n) {
		return new Position(x = x - n, y = y)
	}

	method right(n) {
		return new Position(x = x + n, y = y)
	}

}

class Dimension {

	const property width
	const property height

}

class Sound {

	const property path

	method play() native

	method loop() native

	method pause() native

	method resume() native

	method stop() native

}

class KeyInput {

	const property code

	method onKeyPressedDo(clousure) native

	method clear() native

}

object keyboard {

	const property cancel = new KeyInput(code = 3)

	const property backspace = new KeyInput(code = 8)

	const property tab = new KeyInput(code = 9)

	const property enter = new KeyInput(code = 10)

	const property clear = new KeyInput(code = 12)

	const property shift = new KeyInput(code = 16)

	const property ctrl = new KeyInput(code = 17)

	const property alt = new KeyInput(code = 18)

	const property pause = new KeyInput(code = 19)

	const property capsLock = new KeyInput(code = 20)

	const property kana = new KeyInput(code = 21)

	const property final = new KeyInput(code = 24)

	const property kanji = new KeyInput(code = 25)

	const property escape = new KeyInput(code = 27)

	const property convert = new KeyInput(code = 28)

	const property noConvert = new KeyInput(code = 29)

	const property accept = new KeyInput(code = 30)

	const property modeChange = new KeyInput(code = 31)

	const property space = new KeyInput(code = 32)

	const property pageUp = new KeyInput(code = 33)

	const property pageDown = new KeyInput(code = 34)

	const property end = new KeyInput(code = 35)

	const property home = new KeyInput(code = 36)

	const property left = new KeyInput(code = 37)

	const property up = new KeyInput(code = 38)

	const property right = new KeyInput(code = 39)

	const property down = new KeyInput(code = 40)

	const property comma = new KeyInput(code = 44)

	const property minus = new KeyInput(code = 45)

	const property period = new KeyInput(code = 46)

	const property slash = new KeyInput(code = 47)

	const property semicolon = new KeyInput(code = 59)

	const property a = new KeyInput(code = 65)

	const property b = new KeyInput(code = 66)

	const property c = new KeyInput(code = 67)

	const property d = new KeyInput(code = 68)

	const property e = new KeyInput(code = 69)

	const property f = new KeyInput(code = 70)

	const property g = new KeyInput(code = 71)

	const property h = new KeyInput(code = 72)

	const property i = new KeyInput(code = 73)

	const property j = new KeyInput(code = 74)

	const property k = new KeyInput(code = 75)

	const property m = new KeyInput(code = 76)

	const property n = new KeyInput(code = 77)

	const property l = new KeyInput(code = 78)

	const property o = new KeyInput(code = 79)

	const property p = new KeyInput(code = 80)

	const property q = new KeyInput(code = 81)

	const property r = new KeyInput(code = 82)

	const property s = new KeyInput(code = 83)

	const property t = new KeyInput(code = 84)

	const property u = new KeyInput(code = 85)

	const property v = new KeyInput(code = 86)

	const property w = new KeyInput(code = 87)

	const property x = new KeyInput(code = 88)

	const property y = new KeyInput(code = 89)

	const property z = new KeyInput(code = 90)

	const property openBracked = new KeyInput(code = 91)

	const property backSlash = new KeyInput(code = 92)

	const property closeBracket = new KeyInput(code = 93)

	const property numpad0 = new KeyInput(code = 96)

	const property numpad1 = new KeyInput(code = 97)

	const property numpad2 = new KeyInput(code = 98)

	const property numpad3 = new KeyInput(code = 99)

	const property numpad4 = new KeyInput(code = 100)

	const property numpad5 = new KeyInput(code = 101)

	const property numpad6 = new KeyInput(code = 102)

	const property numpad7 = new KeyInput(code = 103)

	const property numpad8 = new KeyInput(code = 104)

	const property numpad9 = new KeyInput(code = 105)

	const property numpadAsterisk = new KeyInput(code = 106)

	const property numpadPlus = new KeyInput(code = 107)

	const property numpadComa = new KeyInput(code = 108)

	const property numpadMinus = new KeyInput(code = 109)

	const property numpadDot = new KeyInput(code = 110)

	const property numpadSlash = new KeyInput(code = 111)

	const property f1 = new KeyInput(code = 112)

	const property f2 = new KeyInput(code = 113)

	const property f3 = new KeyInput(code = 114)

	const property f4 = new KeyInput(code = 115)

	const property f5 = new KeyInput(code = 116)

	const property f6 = new KeyInput(code = 117)

	const property f7 = new KeyInput(code = 118)

	const property f8 = new KeyInput(code = 119)

	const property f9 = new KeyInput(code = 120)

	const property f10 = new KeyInput(code = 121)

	const property f11 = new KeyInput(code = 122)

	const property f12 = new KeyInput(code = 123)

	const property delete = new KeyInput(code = 127)

	const property deadGrave = new KeyInput(code = 128)

	const property deadAcute = new KeyInput(code = 129)

	const property deadCircumflex = new KeyInput(code = 130)

	const property deadTilde = new KeyInput(code = 131)

	const property deadMacron = new KeyInput(code = 132)

	const property deadBreve = new KeyInput(code = 133)

	const property deadAboveDot = new KeyInput(code = 134)

	const property deadDieresis = new KeyInput(code = 135)

	const property deadAboveRing = new KeyInput(code = 136)

	const property deadDoubleAcute = new KeyInput(code = 137)

	const property deadCaron = new KeyInput(code = 138)

	const property deadCedilla = new KeyInput(code = 139)

	const property deadOgonek = new KeyInput(code = 140)

	const property deadIota = new KeyInput(code = 141)

	const property deadVoicedSound = new KeyInput(code = 142)

	const property deadSemivoicedSound = new KeyInput(code = 143)

	const property numLock = new KeyInput(code = 144)

	const property scrollLock = new KeyInput(code = 145)

	const property ampersand = new KeyInput(code = 150)

	const property asterisk = new KeyInput(code = 151)

	const property doubleQuote = new KeyInput(code = 152)

	const property less = new KeyInput(code = 153)

	const property  propertyprintScreen = new KeyInput(code = 154)

	const property insert = new KeyInput(code = 155)

	const property help = new KeyInput(code = 156)

	const property meta = new KeyInput(code = 157)

	const property greater = new KeyInput(code = 160)

	const property leftBrace = new KeyInput(code = 161)

	const property rightBrace = new KeyInput(code = 162)

	const property backQuote = new KeyInput(code = 192)

	const property quote = new KeyInput(code = 222)

	const property alphanumeric = new KeyInput(code = 240)

	const property katakana = new KeyInput(code = 241)

	const property hiragana = new KeyInput(code = 242)

	const property fullWidth = new KeyInput(code = 243)

	const property halfWidth = new KeyInput(code = 244)

	const property romanCharacters = new KeyInput(code = 245)

	const property allCandidates = new KeyInput(code = 256)

	const property previousCandidates = new KeyInput(code = 257)

	const property codeInput = new KeyInput(code = 258)

	const property japaneseKatakana = new KeyInput(code = 259)

	const property japaneseHiragana = new KeyInput(code = 260)

	const property japaneseRoman = new KeyInput(code = 261)

	const property kanaLock = new KeyInput(code = 262)

	const property imputconstOnOrOff = new KeyInput(code = 263)

	const property at = new KeyInput(code = 512)

	const property colon = new KeyInput(code = 513)

	const property circunflex = new KeyInput(code = 514)

	const property dollar = new KeyInput(code = 515)

	const property euro = new KeyInput(code = 516)

	const property exclamationMark = new KeyInput(code = 517)

	const property jinvertedExclamationMark = new KeyInput(code = 518)

	const property leftParenthesis = new KeyInput(code = 519)

	const property numberSign = new KeyInput(code = 520)

	const property plus = new KeyInput(code = 521)

	const property rightParenthesis = new KeyInput(code = 522)

	const property underscore = new KeyInput(code = 523)

	const property windows = new KeyInput(code = 524)

	const property contextMenu = new KeyInput(code = 525)
	
	const property keys = [cancel, backspace, tab, enter, clear, shift, ctrl, alt, pause, capsLock,
		kana, final, kanji, escape, convert, noConvert, accept, modeChange, space, pageUp, pageDown,
		end, home, left, up, right, down, comma, minus, period, slash, semicolon,
		a, b, c, d, e, f, g, h, i, j, k, m, n, l, o, p, q, r, s, t, u, v, w, x, y, z,
		openBracked, backSlash, closeBracket, numpad0, numpad1, numpad2, numpad3, numpad4, numpad5,
		numpad6, numpad7, numpad8, numpad9, numpadAsterisk, numpadPlus, numpadComa, numpadMinus,
		numpadDot, numpadSlash, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, delete, deadGrave,
		deadAcute, deadCircumflex, deadTilde, deadMacron, deadBreve, deadAboveDot, deadDieresis,
		deadAboveRing, deadDoubleAcute, deadCaron, deadCedilla, deadOgonek, deadIota, deadVoicedSound,
		deadSemivoicedSound, numLock, scrollLock, ampersand, asterisk, doubleQuote, less, propertyprintScreen,
		insert, help, meta, greater, leftBrace, rightBrace, backQuote, quote, alphanumeric, katakana,
		hiragana, fullWidth, halfWidth, romanCharacters, allCandidates, previousCandidates, codeInput,
		japaneseKatakana, japaneseHiragana, japaneseRoman, kanaLock, imputconstOnOrOff, at, colon,
		circunflex, dollar, euro, exclamationMark, jinvertedExclamationMark, leftParenthesis, numberSign,
		plus, rightParenthesis, underscore, windows, contextMenu]

	method clearEvents() {
		keys.forEach({key => key.clear()})
	}
}





