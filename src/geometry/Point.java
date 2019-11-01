package geometry;

/**
 * A point representing a location in (x,y) coordinate space, specified in
 * Integer precision.
 * 
 * @since 1.0
 * @version 1.0
 * @author 51lv3str1 - <a href="https://github.com/51lv3str1">https://github.com/51lv3str1</a>
 */
public class Point {

	private final Pair<Integer, Integer> pair;
	public static Point ORIGIN = new Point();
	
	/**
	 * Constructs and initializes a point at (0,0) location in the
	 * coordinate space.
	 */
	public Point() {
		this.pair = new Pair<Integer, Integer>(0, 0);
	}

	/**
	 * Constructs and initializes a point at the specified (x,y) location in the
	 * coordinate space.
	 * 
	 * @param x the X coordinate of the newly constructed Point.
	 * @param y the Y coordinate of the newly constructed Point.
	 */
	public Point(Integer x, Integer y) {
		this.pair = new Pair<Integer, Integer>(x, y);
	}

	/**
	 * Returns the X coordinate of this Point in Integer precision.
	 * 
	 * @return the X coordinate of this Point.
	 */
	public Integer getX() {
		return this.pair.first();
	}

	/**
	 * Returns the Y coordinate of this Point in Integer precision.
	 * 
	 * @return the X coordinate of this Point.
	 */
	public Integer getY() {
		return this.pair.second();
	}

	/**
	 * Translates this point, at location (x,y), by destiny.x along the x axis
	 * and destiny.y along the y axis so that it now represents the point (x +
	 * destiny.x, y + destiny.y).
	 * 
	 * @param destiny the Point to move this point along the X and Y axis.
	 * @return a translated instance of Point.
	 */
	public Point translate(Point destiny) {
		return new Point(this.getX() + destiny.getX(), this.getY() + destiny.getY());
	}

	/**
	 * Translates this point, at location (x,y), by dx along the x axis and dy
	 * along the y axis so that it now represents the point (x + dx, y + dy).
	 * 
	 * @param dx the distance to move this point along the X axis.
	 * @param dy the distance to move this point along the Y axis.
	 * @return a translated instance of Point.
	 */
	public Point translate(Integer dx, Integer dy) {
		return this.translate(new Point(dx, dy));
	}

	/**
	 * Indicates whether some other point is "equal to" this one.
	 * 
	 * @param another an point to be compared with this Point.
	 * @return true if this point is the same as the point argument; false otherwise.
	 */
	public boolean equals(Point another) {
		return this.getX().equals(another.getX()) && this.getY().equals(another.getY());
	}

}
