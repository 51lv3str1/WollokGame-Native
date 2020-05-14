package geometry;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

/**
 * A position representing a location in (x,y) coordinate space, specified in
 * Integer precision.
 * 
 * @author 51lv3str1 - <a href="https://github.com/51lv3str1">https://github.com/51lv3str1</a>
 */
public class Position {

	private final Integer x;
	private final Integer y;
	
	/**
	 * Constructs and initializes a position at (0,0) location in the
	 * coordinate space.
	 */
	public Position() {
		this(0, 0);
	}

	/**
	 * Constructs and initializes a position at the specified (x,y) location in the
	 * coordinate space.
	 * 
	 * @param x the X coordinate of the newly constructed Position.
	 * @param y the Y coordinate of the newly constructed Position.
	 */
	public Position(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructs and initializes a position at the specified (x,y) location in the
	 * coordinate space.
	 * 
	 * @param x the X coordinate of the newly constructed Position.
	 * @param y the Y coordinate of the newly constructed Position.
	 */
	public Position(Double x, Double y) {
		this(x.intValue(), y.intValue());
	}

	/**
	 * Constructs and initializes a point at the same location in the
	 * coordinate space from a specific position.
	 * 
	 * @param an position in the coordinate space.
	 */
	public Position(WollokObject position) {
		this(Integer.valueOf(position.call("x").toString()), Integer.valueOf(position.call("y").toString()));
	}

	/**
	 * Returns the X coordinate of this Position in Integer precision.
	 * 
	 * @return the X coordinate of this Position.
	 */
	public Integer x() {
		return this.x;
	}

	/**
	 * Returns the Y coordinate of this Position in Integer precision.
	 * 
	 * @return the X coordinate of this Position.
	 */
	public Integer y() {
		return this.y;
	}
	
	/**
	 * Translates this position, at location (x,y), by destiny.x along the x axis
	 * and destiny.y along the y axis so that it now represents the position (x +
	 * destiny.x, y + destiny.y).
	 * 
	 * @param destiny the position to move this point along the X and Y axis.
	 * @return a translated instance of position.
	 */
	public Position translate(WollokObject destiny) {
		return new Position(Integer.valueOf(destiny.call("x").toString()),
				Integer.valueOf(destiny.call("y").toString()));
	}

	/**
	 * Translates this position, at location (x,y), by destiny.x along the x axis
	 * and destiny.y along the y axis so that it now represents the position (x +
	 * destiny.x, y + destiny.y).
	 * 
	 * @param destiny the position to move this point along the X and Y axis.
	 * @return a translated instance of position.
	 */
	public Position translate(Position destiny) {
		return new Position(this.x() + destiny.x(), this.y() + destiny.y());
	}
	
	/**
	 * Translates this position, at location (x,y), by dx along the x axis and dy
	 * along the y axis so that it now represents the position (x + dx, y + dy).
	 * 
	 * @param dx the distance to move this position along the X axis.
	 * @param dy the distance to move this position along the Y axis.
	 * @return a translated instance of Position.
	 */
	public Position translate(Integer dx, Integer dy) {
		return this.translate(new Position(dx, dy));
	}
	
	/**
	 * Translates this position, at location (x,y), by dx along the x axis and dy
	 * along the y axis so that it now represents the position (x + dx, y + dy).
	 * 
	 * @param dx the distance to move this position along the X axis.
	 * @param dy the distance to move this position along the Y axis.
	 * @return a translated instance of Position.
	 */
	public Position translate(Double dx, Double dy) {
		return this.translate(new Position(dx, dy));
	}
	
	/**
	 * Indicates whether some other position is "equal to" this one.
	 * 
	 * @param another an position to be compared with this position.
	 * @return true if this position is the same as the position argument; false otherwise.
	 */
	public boolean equals(Position another) {
		return this.x().equals(another.x()) && this.y().equals(another.y());
	}
	
	/**
	 * Indicates whether some other position is "equal to" this one.
	 * 
	 * @param another an position to be compared with this position.
	 * @return true if this position is the same as the position argument; false otherwise.
	 */
	public boolean equals(WollokObject another) {
		return this.x().equals(Integer.valueOf(another.call("x").toString()))
				&& this.y().equals(Integer.valueOf(another.call("y").toString()));
	}
	
	@Override
	public String toString() {
		return "(" + this.x() + "," + this.y() + ")";
	}

}