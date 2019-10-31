import geometry.*
import wollokGame.*

object duba {

	var property position = wollokGame.at(0,0)
	var spritesheet = wollokGame.createSpritesheet("assets/duba.png", 15, 10)
	var property animation = spritesheet.animation(true, 
		3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 34, 35, 36, 37,
		38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 34,
		35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 11, 12, 12, 13, 14, 15, 16, 17, 18, 19, 19, 20, 21, 22,
		23, 23, 23, 24, 25, 26, 27, 27, 28, 29, 30, 31, 31, 31, 31, 31, 32, 0, 0, 0, 0, 0
	)

}

class Piso {

	var property image = "assets/duba-ground.png"
	var property position = wollokGame.at(0,0)
	var property opacity = 0.8

}

object obstaculo1 {

	var property image = "assets/obstacle-1.png"
	var property position = wollokGame.at(0,0)

}

object obstaculo2 {

	var property image = "assets/obstacle-2.png"
	var property position = wollokGame.at(0,0)

}

object obstaculo3 {

	var property image = "assets/obstacle-3.png"
	var property position = wollokGame.at(0,0)

}

object decoracion1 {

	var property image = "assets/decoration-1.png"
	var property position = wollokGame.at(0,0)

}

object decoracion2 {

	var property image = "assets/decoration-2.png"
	var property position = wollokGame.at(0,0)

}

object decoracion3 {

	var property image = "assets/decoration-3.png"
	var property position = wollokGame.at(0,0)

}

object churrasco {

	var property image = "assets/churrasco.png"
	var property position = wollokGame.at(0,0)

}

object example {

	method initialize() {
		wollokGame.ground("assets/background.png")
		
		new Range(start=1,end=6).forEach({ x => 
			new Range(start=1,end=6).forEach({
				y => wollokGame.addVisualIn(new Piso(), wollokGame.at(x, y))
			})
		})
		
		wollokGame.addVisualIn(obstaculo1, wollokGame.at(2, 3))
		wollokGame.addVisualIn(obstaculo2, wollokGame.at(4, 4))
		wollokGame.addVisualIn(obstaculo3, wollokGame.at(6, 6))
		wollokGame.addVisualIn(decoracion1, wollokGame.at(3, 1))
		wollokGame.addVisualIn(decoracion2, wollokGame.at(3, 6))
		wollokGame.addVisualIn(decoracion3, wollokGame.at(4, 3))
		wollokGame.addVisualIn(churrasco, wollokGame.at(5, 4))
		wollokGame.addVisualCharacter(duba)
		wollokGame.playBGM("music", "assets/BGM.wav")
		wollokGame.say(duba,"estoy feliz :D")
		console.println(wollokGame.hasVisual(duba))
		wollokGame.start()
	}

}

