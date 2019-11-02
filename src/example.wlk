import wollokGame.*

class Obstacle {

	var property collisionable = true

	method collidedBy(another) {
		collisionable = false
		wollokGame.removeCollisionEvent(duba)
		duba.knockAndFall()
	}

}

class Objective {
	
	var property collisionable = true
	
	method collidedBy(another) {
		collisionable = false
		duba.playing(false)
		duba.eat()
		wollokGame.schedule(1000, {wollokGame.removeVisual(duba.playing(true))})
		wollokGame.removeVisual(self)
	}

}

class Decoration {

	const property collisionable = false

}

object obstaculo1 inherits Obstacle {

	var property image = "assets/obstacle-1.png"
	var property position = wollokGame.at(0, 0)

}

object obstaculo2 inherits Obstacle {

	var property image = "assets/obstacle-2.png"
	var property position = wollokGame.at(0, 0)

}

object obstaculo3 inherits Obstacle {

	var property image = "assets/obstacle-1.png"
	var property position = wollokGame.at(0, 0)

}

class Piso inherits Decoration {

	var property image = "assets/duba-ground.png"
	var property position = wollokGame.at(0, 0)
	var property opacity = 0.8

}

object decoracion1 inherits Decoration {

	var property image = "assets/decoration-1.png"
	var property position = wollokGame.at(0, 0)

}

object decoracion2 inherits Decoration {

	var property image = "assets/decoration-2.png"
	var property position = wollokGame.at(0, 0)

}

object decoracion3 inherits Decoration {

	var property image = "assets/decoration-1.png"
	var property position = wollokGame.at(0, 0)

}

object churrasco1 inherits Objective {

	var property image = "assets/churrasco.png"
	var property position = wollokGame.at(0, 0)

}

object churrasco2 inherits Objective {

	var property image = "assets/churrasco.png"
	var property position = wollokGame.at(0, 0)

}

object churrasco3 inherits Objective {

	var property image = "assets/churrasco.png"
	var property position = wollokGame.at(0, 0)

}

object duba {

	var property playing = true
	const bounds =new Range(start=1, end=6)
	const spritesheet = wollokGame.createSpritesheet("assets/duba.png", 10, 15)
	const idleAnimation = spritesheet.animation(true, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 11, 12, 12, 13, 14, 15, 16, 17, 18, 19, 19, 20, 21, 22, 23, 23, 23, 24, 25, 26, 27, 27, 28, 29, 30, 31, 31, 31, 31, 31, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
//	const walkingAnimation = spritesheet.animation(true, 3, 0, 53, 54, 55, 56, 57, 58, 59, 60, 0)
	const eatAnimation = spritesheet.animation(false, 3, 0, 0, 61, 62, 63, 64, 65, 65, 65, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75)
	const fallingAnimation = spritesheet.animation(false, 3, 76, 77, 78, 77, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128)
	var property position = wollokGame.at(1, 1)
	var property animation = idleAnimation

	method moveToLeft() {
		if (playing and bounds.contains(self.position().left(1).x())) {
			position = self.position().left(1)
		}
	}

	method moveToUp() {
		if (playing and bounds.contains(self.position().up(1).y())) {
			position = self.position().up(1)
		}
	}

	method moveToRight() {
		if (playing and bounds.contains(self.position().right(1).x())) {
			position = self.position().right(1)
		}
	}

	method moveToDown() {
		if (playing and bounds.contains(self.position().down(1).y())) {
			position = self.position().down(1)
		}
	}
	
	method idle() {
		animation = idleAnimation
	}

	method knockAndFall() {
		animation = fallingAnimation
		playing = false
		wollokGame.say(self,"Auch!, esquiva los objetivos")
	}
	
	method eat() {
		animation = eatAnimation
		wollokGame.schedule(885, {self.animation(idleAnimation)})
	}

	method initialize() {
		keyboard.left().onKeyPressedDo({ self.moveToLeft()})
		keyboard.up().onKeyPressedDo({ self.moveToUp()})
		keyboard.right().onKeyPressedDo({ self.moveToRight()})
		keyboard.down().onKeyPressedDo({ self.moveToDown()})
		wollokGame.whenCollideDo(self, { actor => actor.collidedBy(self)})
	}

}

object example {

	method initialize() {
		wollokGame.ground("assets/background.png")
		new Range(start=1,end=6).forEach({ x => new Range(start = 1, end = 6).forEach({ y => wollokGame.addVisualIn(new Piso(), wollokGame.at(x, y))})})
		wollokGame.addVisualIn(obstaculo1, wollokGame.at(2, 3))
		wollokGame.addVisualIn(obstaculo2, wollokGame.at(4, 4))
		wollokGame.addVisualIn(obstaculo3, wollokGame.at(5, 3))
		wollokGame.addVisualIn(decoracion1, wollokGame.at(3, 1))
		wollokGame.addVisualIn(decoracion2, wollokGame.at(3, 6))
		wollokGame.addVisualIn(decoracion3, wollokGame.at(4, 3))
		wollokGame.addVisualIn(churrasco1, wollokGame.at(3, 3))
		wollokGame.addVisualIn(churrasco2, wollokGame.at(2, 6))
		wollokGame.addVisualIn(churrasco3, wollokGame.at(6, 6))
		wollokGame.addVisual(duba)
		wollokGame.sound("assets/BGM.wav").loop()
		wollokGame.start()
	}

}