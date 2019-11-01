package geometry;

public interface Dimensionable {

	public void setDimension(geometry.Dimension dimension);

	public void setDimension(Integer width, Integer height);

	public geometry.Dimension getDimension();

}
