import game.*

object wko {

	var property image = "wko.png"
	var property position = game.center()

}

object launch {

	override method initialize() {
		game.title("Wollok Game Example")
		game.height(13)
		game.width(20)
		game.addVisualCharacter(wko)
		game.start()
	}

}

