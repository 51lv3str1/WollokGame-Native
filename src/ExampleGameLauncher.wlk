import game.*

class WKO {

}

object hero {

	var property position = game.at(2, 2)

}

object launcher {

	override method initialize() {
		game.addVisualCharacter(hero)
//		game.whenCollideDo(hero, { some => console.println("ouch")})
//		game.onTick(1000, "movesDownInfinite", { hero.position(hero.position().down(1))})
//		game.schedule(10000, { hero.position(hero.position().down(1))})
		game.addVisualIn(new WKO(), game.at(0, 0))
		game.start()
	}

}

