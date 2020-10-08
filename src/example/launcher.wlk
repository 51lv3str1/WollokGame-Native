import game.*

object wko {

	var property image = "wko.png"
	var property position = game.center()

}

object launch {

	override method initialize() {
		game.title("Wollok Game Example")
		game.height(8)
		game.width(8)
		game.addVisualCharacter(wko)
		game.start()
	}

}

