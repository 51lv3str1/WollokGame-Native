package game;

public interface Rotatable {

	public abstract void rotate(Double theta);

	public abstract void rotate(Double theta, Point vector);

	public abstract void rotate(Double theta, Integer x, Integer y);

	public abstract void rotateAboutCenter(Double theta);

}
