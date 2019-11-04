package game;

/**
 * <p>The Dimension class encapsulates the width and height of a component (in
 * integer precision) in a single object.</p>
 * 
 * <p>Normally the values of width and height are non-negative integers. The
 * constructors that allow you to create a dimension do not prevent you from
 * setting a negative value for these properties. If the value of width or
 * height is negative, the behavior of some methods defined by other objects is
 * undefined.</p>
 * 
 * @since 1.0
 * @version 1.0
 * @author 51lv3str1 - <a href="https://github.com/51lv3str1">https://github.com/51lv3str1</a>
 */
public class Dimension {

	/**
	 * The width dimension; negative values can be used.
	 */
	private final Integer width;
	
	/**
	 * The height dimension; negative values can be used.
	 */
	private final Integer height;

	/**
	 * Creates an instance of Dimension with a width of zero and a height of zero.
	 */
	public Dimension() {
		this.width = 0;
		this.height = 0;
	}
	
	/**
	 * Creates an instance of Dimension whose width and height are the same as for
	 * the specified java.awt.Dimension.
	 * 
	 * @param dimension  the specified dimension for the width and height values.
	 */
	public Dimension(java.awt.Dimension dimension) {
		this.width = dimension.width;
		this.height = dimension.height;
	}
	
	/**
	 * Constructs a Dimension and initializes it to the specified width and specified height.
	 * 
	 * @param width the specified width.
	 * @param height the specified height.
	 */
	public Dimension(Integer width, Integer height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Returns the width of this Dimension in Integer precision.
	 * 
	 * @return the width of this Dimension.
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * Returns the height of this Dimension in Integer precision.
	 * 
	 * @return the height of this Dimension.
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * Create instance of java.awt.Dimension with a width and height of this Dimension.
	 * 
	 * @return this as java.awt.Dimension
	 */
	public java.awt.Dimension asAWT() {
		return new java.awt.Dimension(this.getWidth(), this.getHeight());
	}

	/**
	 * Indicates whether some other dimension is "equal to" this one.
	 * 
	 * @param obj an object to be compared with this Dimension.
	 * @return true if this dimension is the same as the another argument; false otherwise.
	 */
	public boolean equals(Dimension another) {
		return this.getWidth() == another.getWidth() && this.getHeight() == another.getHeight();
	}

}
