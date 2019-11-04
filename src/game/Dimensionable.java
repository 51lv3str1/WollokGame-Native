package game;

public interface Dimensionable {

	public void setDimension(game.Dimension dimension);

	public void setDimension(Integer width, Integer height);

	public game.Dimension getDimension();

}
