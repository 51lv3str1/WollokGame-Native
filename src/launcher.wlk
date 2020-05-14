import game.*

object pepita {

	var property position = game.at(0, 0)

}

object launcher {

	method startGame() {
		game.title("Wollok test game")
		game.addVisual(pepita)
		console.println(game.allVisuals())
		game.start()
	}

}