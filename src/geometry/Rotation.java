package geometry;

public class Rotation {

	public static final Rotation NO_ROTATION = new Rotation(0.0, new Point(0, 0));
	private final Double theta;
	private final Point rotation;

	public Rotation(Double theta, Point rotation) {
		this.theta = theta;
		this.rotation = rotation;
	}

	public Rotation(Double theta, Integer x, Integer y) {
		this(theta, new Point(x, y));
	}

	public Double getTheta() {
		return theta;
	}

	public Point getRotation() {
		return rotation;
	}

	public Integer getX() {
		return rotation.getX();
	}

	public Integer getY() {
		return rotation.getY();
	}

	public Rotation rotate(Double theta, Integer x, Integer y) {
		return new Rotation(this.getTheta() + theta, this.getX() + x, this.getY() + y);
	}

	public Rotation rotate(Double theta, Point rotation) {
		return this.rotate(theta, rotation.getX(), rotation.getY());
	}

}
