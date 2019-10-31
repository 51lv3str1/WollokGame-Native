package geometry;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

public interface Translatable {

	public Point getPosition();

	public void translate(Integer x, Integer y);

	public void translate(Point position);

	public void translateTo(Integer x, Integer y);

	public void translateTo(Point position);

	public void translateTo(WollokObject position);

}