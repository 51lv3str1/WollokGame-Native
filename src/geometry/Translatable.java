package geometry;

public interface Translatable {

	public Point getPosition();

	public void translate(Integer x, Integer y);

	public void translate(Point position);

	public void translateTo(Integer x, Integer y);

	public void translateTo(Point position);

}