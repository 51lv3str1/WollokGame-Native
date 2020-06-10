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
	 * Adds an object to the board for drawing it. It can be moved with arrow keys.
	 * That object should understand a position property 
	 * (implemented by a reference or getter method).
	 * 
	 * @param component
	 * 
	 * Example:
	 *     game.addVisualCharacter(pepita) ==> pepita should have a position property
	 */
	method addVisualCharacter(component) native

	/**
	 * Adds an object to the board for drawing it on a specific position. It can be moved with arrow keys.
	 * 
	 * @param component a visual component.
	 * @param position a position.
	 * 
	 * Example:
	 *     game.addVisualCharacterIn(pepita, game.origin()) ==> no need for pepita to have a position property
	 */ 
	method addVisualCharacterIn(component, position) native

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
	 * Verifies if an object is currently in the board.
	 * 
	 * @param component a visual component.
	 * 
	 * Example:
	 *     game.hasVisual(pepita)
	 */
	method hasVisual(component) native
	
	/**
	 * Adds a block that will be executed each time a specific key is pressed
	 * @see keyboard.onPressDo()
	 */
	method whenKeyPressedDo(key, action) {
		key.onPressDo(action)
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
	method onTick(milliseconds, name, closure) native

	/**
	 * Adds a block that will be executed in n milliseconds. Block expects no
	 * argument.
	 * 
	 * @param milliseconds
	 * @param gameAction
	 */
	 method schedule(milliseconds, closure) native
	 
	 /**
	 * Remove a tick event created with onTick message
	 * 
	 * Example:
	 *      game.removeTickEvent("pepitaMoving")
	 */
	method removeTickEvent(name) native
	
	/**
	 * Removes all visual objects on board and configurations (colliders, keys, etc).
	 */
	method clear() native
	
	/**
	 * Returns all objects that are in same position of given object.
	 */
	method colliders(visual) {
		return self.getObjectsIn(visual.position())
	}

	/**
	 * Returns the unique object that is in same position of given object.
	 */
	method uniqueCollider(visual) = self.colliders(visual).uniqueElement()

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
	 * Returns the position (0,0).
	 */
	method origin() = self.at(0, 0)
	
	/**
	 * Returns the center board position (rounded down).
	 */
	method center() = self.at(self.width() / 2, self.height() / 2)
	
	/**
	 * Sets cells background image.
	 */
	method ground(resource) native

	/**
	 * Sets full background image.
	 */
	method boardGround(resource) native
	
	/**
	 * Returns all objects in given position.
	 * 
	 * Example:
	 *     game.getObjectsIn(game.origin())
	 */
	method getObjectsIn(position) native

}

/**
 * Position implementation.
 */
class Position {

	const property x
	const property y

	method translate(_x, _y) {
		return new Position(x = self.x() + _x, y = self.y() + _y)
	}

	method translate(position) {
		return self.translate(position.x(), position.y())
	}

	method same(position) {
		return self.x().equals(position.x()) and self.y().equals(position.y())
	}

	method up(n) {
		return new Position(x = x, y = y + n)
	}

	method down(n) {
		return new Position(x = x, y = y - n)
	}

	method left(n) {
		return new Position(x = x - n, y = y)
	}

	method right(n) {
		return new Position(x = x + n, y = y)
	}

}

class Key {
	
	const property key

	method onPressDo(clousure) native
	
	method clear() native
	
	method isPressed() native

}

/** Keyboard Implementation.
 *  All keys availables on game.
 */
object keyboard {
	
	const property cancel = new game.Key(key = 3)

	const property backspace = new game.Key(key = 8)

	const property tab = new game.Key(key = 9)

	const property enter = new game.Key(key = 10)

	const property clear = new game.Key(key = 12)

	const property shift = new game.Key(key = 16)

	const property ctrl = new game.Key(key = 17)

	const property alt = new game.Key(key = 18)

	const property pause = new game.Key(key = 19)

	const property capsLock = new game.Key(key = 20)

	const property kana = new game.Key(key = 21)

	const property final = new game.Key(key = 24)

	const property kanji = new game.Key(key = 25)

	const property escape = new game.Key(key = 27)

	const property convert = new game.Key(key = 28)

	const property noConvert = new game.Key(key = 29)

	const property accept = new game.Key(key = 30)

	const property modeChange = new game.Key(key = 31)

	const property space = new game.Key(key = 32)

	const property pageUp = new game.Key(key = 33)

	const property pageDown = new game.Key(key = 34)

	const property end = new game.Key(key = 35)

	const property home = new game.Key(key = 36)

	const property left = new game.Key(key = 37)

	const property up = new game.Key(key = 38)

	const property right = new game.Key(key = 39)

	const property down = new game.Key(key = 40)

	const property comma = new game.Key(key = 44)

	const property minus = new game.Key(key = 45)

	const property period = new game.Key(key = 46)

	const property slash = new game.Key(key = 47)

	const property semicolon = new game.Key(key = 59)

	const property a = new game.Key(key = 65)

	const property b = new game.Key(key = 66)

	const property c = new game.Key(key = 67)

	const property d = new game.Key(key = 68)

	const property e = new game.Key(key = 69)

	const property f = new game.Key(key = 70)

	const property g = new game.Key(key = 71)

	const property h = new game.Key(key = 72)

	const property i = new game.Key(key = 73)

	const property j = new game.Key(key = 74)

	const property k = new game.Key(key = 75)

	const property m = new game.Key(key = 76)

	const property n = new game.Key(key = 77)

	const property l = new game.Key(key = 78)

	const property o = new game.Key(key = 79)

	const property p = new game.Key(key = 80)

	const property q = new game.Key(key = 81)

	const property r = new game.Key(key = 82)

	const property s = new game.Key(key = 83)

	const property t = new game.Key(key = 84)

	const property u = new game.Key(key = 85)

	const property v = new game.Key(key = 86)

	const property w = new game.Key(key = 87)

	const property x = new game.Key(key = 88)

	const property y = new game.Key(key = 89)

	const property z = new game.Key(key = 90)

	const property openBracked = new game.Key(key = 91)

	const property backSlash = new game.Key(key = 92)

	const property closeBracket = new game.Key(key = 93)

	const property numpad0 = new game.Key(key = 96)

	const property numpad1 = new game.Key(key = 97)

	const property numpad2 = new game.Key(key = 98)

	const property numpad3 = new game.Key(key = 99)

	const property numpad4 = new game.Key(key = 100)

	const property numpad5 = new game.Key(key = 101)

	const property numpad6 = new game.Key(key = 102)

	const property numpad7 = new game.Key(key = 103)

	const property numpad8 = new game.Key(key = 104)

	const property numpad9 = new game.Key(key = 105)

	const property numpadAsterisk = new game.Key(key = 106)

	const property numpadPlus = new game.Key(key = 107)

	const property numpadComa = new game.Key(key = 108)

	const property numpadMinus = new game.Key(key = 109)

	const property numpadDot = new game.Key(key = 110)

	const property numpadSlash = new game.Key(key = 111)

	const property f1 = new game.Key(key = 112)

	const property f2 = new game.Key(key = 113)

	const property f3 = new game.Key(key = 114)

	const property f4 = new game.Key(key = 115)

	const property f5 = new game.Key(key = 116)

	const property f6 = new game.Key(key = 117)

	const property f7 = new game.Key(key = 118)

	const property f8 = new game.Key(key = 119)

	const property f9 = new game.Key(key = 120)

	const property f10 = new game.Key(key = 121)

	const property f11 = new game.Key(key = 122)

	const property f12 = new game.Key(key = 123)

	const property delete = new game.Key(key = 127)

	const property deadGrave = new game.Key(key = 128)

	const property deadAcute = new game.Key(key = 129)

	const property deadCircumflex = new game.Key(key = 130)

	const property deadTilde = new game.Key(key = 131)

	const property deadMacron = new game.Key(key = 132)

	const property deadBreve = new game.Key(key = 133)

	const property deadAboveDot = new game.Key(key = 134)

	const property deadDieresis = new game.Key(key = 135)

	const property deadAboveRing = new game.Key(key = 136)

	const property deadDoubleAcute = new game.Key(key = 137)

	const property deadCaron = new game.Key(key = 138)

	const property deadCedilla = new game.Key(key = 139)

	const property deadOgonek = new game.Key(key = 140)

	const property deadIota = new game.Key(key = 141)

	const property deadVoicedSound = new game.Key(key = 142)

	const property deadSemivoicedSound = new game.Key(key = 143)

	const property numLock = new game.Key(key = 144)

	const property scrollLock = new game.Key(key = 145)

	const property ampersand = new game.Key(key = 150)

	const property asterisk = new game.Key(key = 151)

	const property doubleQuote = new game.Key(key = 152)

	const property less = new game.Key(key = 153)

	const property  propertyprintScreen = new game.Key(key = 154)

	const property insert = new game.Key(key = 155)

	const property help = new game.Key(key = 156)

	const property meta = new game.Key(key = 157)

	const property greater = new game.Key(key = 160)

	const property leftBrace = new game.Key(key = 161)

	const property rightBrace = new game.Key(key = 162)

	const property backQuote = new game.Key(key = 192)

	const property quote = new game.Key(key = 222)

	const property alphanumeric = new game.Key(key = 240)

	const property katakana = new game.Key(key = 241)

	const property hiragana = new game.Key(key = 242)

	const property fullWidth = new game.Key(key = 243)

	const property halfWidth = new game.Key(key = 244)

	const property romanCharacters = new game.Key(key = 245)

	const property allCandidates = new game.Key(key = 256)

	const property previousCandidates = new game.Key(key = 257)

	const property codeInput = new game.Key(key = 258)

	const property japaneseKatakana = new game.Key(key = 259)

	const property japaneseHiragana = new game.Key(key = 260)

	const property japaneseRoman = new game.Key(key = 261)

	const property kanaLock = new game.Key(key = 262)

	const property imputconstOnOrOff = new game.Key(key = 263)

	const property at = new game.Key(key = 512)

	const property colon = new game.Key(key = 513)

	const property circunflex = new game.Key(key = 514)

	const property dollar = new game.Key(key = 515)

	const property euro = new game.Key(key = 516)

	const property exclamationMark = new game.Key(key = 517)

	const property jinvertedExclamationMark = new game.Key(key = 518)

	const property leftParenthesis = new game.Key(key = 519)

	const property numberSign = new game.Key(key = 520)

	const property plus = new game.Key(key = 521)

	const property rightParenthesis = new game.Key(key = 522)

	const property underscore = new game.Key(key = 523)

	const property windows = new game.Key(key = 524)

	const property contextMenu = new game.Key(key = 525)
		
}


///**
// * Class color implementation.
// * hexcode represents the hexadecimal code for decoding that color.
// */
//class Color {
//
//	const property hexcode
//
//}
//
///** Default Colors */
//
///** Black color */
//object black inherits Color { override method hexcode() = "#000000" }
//
///** Blue color */
//object blue inherits Color { override method hexcode() = "#0000FF" }
//
///** Cyan color */
//object cyan inherits Color { override method hexcode() = "#00FFFF" }
//
///** Dark Gray color */
//object darkGray inherits Color { override method hexcode() = "#A9A9A9" }
//
///** Gray color */
//object gray inherits Color { override method hexcode() = "#808080" }
//
///** Green color */
//object green inherits Color { override method hexcode() = "#008000" }
//
///** Light Gray color */
//object lightGray inherits Color { override method hexcode() = "#D3D3D3" }
//
///** Magenta color */
//object magenta inherits Color { override method hexcode() = "#FF00FF" }
//
///** Orange color */
//object orange inherits Color { override method hexcode() = "#FFA500" }
//
///** Pink color */
//object pink inherits Color { override method hexcode() = "#FFC0CB" }
//
///** Red color */
//object red inherits Color { override method hexcode() = "#FF0000" }
//
///** White color */
//object white inherits Color { override method hexcode() = "#FFFFFF" }
//
///** Yellow color */
//object yellow inherits Color { override method hexcode() = "#FFFF00" }
//
