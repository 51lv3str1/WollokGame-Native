import game.*

object wko {

	var property position = game.at(2, 2)

}

object anotherWko {

	var property position = game.at(4, 4)

}

object heavyWko {

	var property position = game.at(0, 4)

}

object launcher {

	override method initialize() {
		game.addVisualCharacter(wko)
		game.addVisual(anotherWko)
		game.addVisual(heavyWko)
		game.whenCollideDo(wko, {_ => console.println("i collide with another object.")})
		game.schedule(500, {anotherWko.position(anotherWko.position().down(1))})
		game.onTick(500, "followGravity", {heavyWko.position(heavyWko.position().down(1))})
		game.start()
	}

}

