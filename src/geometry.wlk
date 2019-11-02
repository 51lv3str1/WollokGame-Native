class Point {

	const property x
	const property y

	method translate(_x, _y) {
		return new Point(x = self.x() + _x, y = self.y() + _y)
	}

	method translate(destiny) {
		return self.translate(destiny.x(), destiny.y())
	}

	method samePosition(point) {
		return self.x().equals(point.x()) and self.y().equals(point.y())
	}

}

class Position inherits Point {

	method up(n) {
		return new Position(x = x, y = y - n)
	}

	method down(n) {
		return new Position(x = x, y = y + n)
	}

	method left(n) {
		return new Position(x = x - n, y = y)
	}

	method right(n) {
		return new Position(x = x + n, y = y)
	}

}

class Dimension {

	const property width
	const property height

}

