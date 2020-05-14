class Position {

	const property x
	const property y

	method translate(_x, _y) {
		return new Position(x = self.x() + _x, y = self.y() + _y)
	}

	method translate(position) {
		return self.translate(position.x(), position.y())
	}

	method same(position) {
		return self.x().equals(position.x()) and self.y().equals(position.y())
	}

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

