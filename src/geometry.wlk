class Point {

	const property x
	const property y

	method translate(_x, _y) {
		return new Point(x = self.x() + _x, y = self.y() + _y)
	}

	method translate(destiny) {
		return self.translate(destiny.x(), destiny.y())
	}

	method isInPosition(_x, _y) {
		return self.x().equals(_x) and self.y().equals(_y)
	}

}

class Position inherits Point {

}

class Dimension {

	const property width
	const property height

}

