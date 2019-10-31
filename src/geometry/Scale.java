package geometry;

public class Scale {

	public static final Scale DEFAULT = new Scale(1, 1);
	public static final Scale INVERTED = new Scale(-1, -1);
	public static final Scale INVERTED_X = new Scale(-1, 1);
	public static final Scale INVERTED_Y = new Scale(1, -1);
	private final Pair<Integer, Integer> pair;

	public Scale(Integer x, Integer y) {
		pair = new Pair<Integer, Integer>(x, y);
	}

	public Integer getX() {
		return pair.first();
	}

	public Integer getY() {
		return pair.second();
	}

	public Scale scale(Integer x, Integer y) {
		return new Scale(x, y);
	}

	public Scale scale(Scale scale) {
		return this.scale(scale.getX(), scale.getY());
	}

	@Override
	public boolean equals(Object obj) {
		Scale scale = (Scale) obj;
		return this.getX() == scale.getX() && this.getY() == scale.getY();
	}

}
