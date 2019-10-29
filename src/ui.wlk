class Spritesheet {

	var property path
	var property rows
	var property columns

	method animation(loop, ratio, indexes...) {
		return new Animation(spritesheet = self, loop = loop, ratio = ratio, indexes = indexes)
	}

}

class Animation {

	var property spritesheet
	var property loop
	var property ratio
	var property indexes

}

