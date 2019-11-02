import game.*
import overcooked.*
import statusBar.*
import screens.*

object overcooked {
	method initialize(){
		game.width(gameManager.width() + status.width())
		game.height(gameManager.height())
		game.title("Cooking Ralf")
		screenManager.startScreen()	
		game.start()
	}
}
