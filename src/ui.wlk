class Spritesheet {

	const property path
	const property columns
	const property rows

	method animation(loop, ratio, indexes...) {
		return new Animation(spritesheet = self, path = path, loop = loop, ratio = ratio, indexes = indexes)
	}

}

/**
 * 
 */
class Sprite {

	const property spritesheet
	const property path

}

/**
 * 
 */
class Animation {

	const property spritesheet
	const property path
	const property loop
	const property ratio
	const property indexes

}

package color {

	/**
	 * Class color implementation.
	 * hexcode represents the hexadecimal code for decoding that color.
	 */
	class Color {

		const property hexcode

	}
	
	/** Black color */
	object black inherits Color { override method hexcode() = "#000000" }
	
	/** Blue color */
	object blue inherits Color { override method hexcode() = "#0000FF" }
	
	/** Cyan color */
	object cyan inherits Color { override method hexcode() = "#00FFFF" }
	
	/** Dark Gray color */
	object darkGray inherits Color { override method hexcode() = "#A9A9A9" }
	
	/** Gray color */
	object gray inherits Color { override method hexcode() = "#808080" }
	
	/** Green color */
	object green inherits Color { override method hexcode() = "#008000" }
	
	/** Light Gray color */
	object lightGray inherits Color { override method hexcode() = "#D3D3D3" }
	
	/** Magenta color */
	object magenta inherits Color { override method hexcode() = "#FF00FF" }
	
	/** Orange color */
	object orange inherits Color { override method hexcode() = "#FFA500" }
	
	/** Pink color */
	object pink inherits Color { override method hexcode() = "#FFC0CB" }
	
	/** Red color */
	object red inherits Color { override method hexcode() = "#FF0000" }
	
	/** White color */
	object white inherits Color { override method hexcode() = "#FFFFFF" }
	
	/** Yellow color */
	object yellow inherits Color { override method hexcode() = "#FFFF00" }
	
}

