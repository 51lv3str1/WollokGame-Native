package geometry;

public class Pair<X, Y> {

	final private X first;
	final private Y second;

	public Pair(X first, Y second) {
		this.first = first;
		this.second = second;
	}

	public X first() {
		return first;
	}

	public Y second() {
		return second;
	}

}