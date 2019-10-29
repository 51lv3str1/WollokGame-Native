package wollokGame;

import java.awt.Graphics;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComponent;

import geometry.Dimension;
import geometry.Point;
import ui.Image;
import ui.graphics.GraphicsRenderer;

@SuppressWarnings({ "serial" })
public class Cell extends JComponent {

	private final Board board;
	private final Integer index;
	private final Point position;

	public Cell(Board board, Integer index, Point position) {
		this.board = board;
		this.index = index;
		this.position = position;
	}

	public Board getBoard() {
		return this.board;
	}

	public Integer getIndex() {
		return this.index;
	}

	public Point getPosition() {
		return position;
	}

	public Dimension getDimension() {
		return new Dimension(this.getWidth(), this.getHeight());
	}

	public List<VisualComponent> getVisualComponents() {
		return this.getBoard().getVisualComponets().stream().filter(c -> c.position().equals(this.getPosition()))
				.collect(Collectors.toList());
	}

	public Image getImage() {
		return this.getBoard().getCellsBackground();
	}

	public Boolean hasVisualComponent(VisualComponent component) {
		return this.getVisualComponents().contains(component);
	}

	public Cell onNorth() {
		return this.getBoard().getCellAt(position.translate(0, -1));
	}

	public Cell onNortheast() {
		return this.getBoard().getCellAt(position.translate(1, -1));
	}

	public Cell onEast() {
		return this.getBoard().getCellAt(position.translate(1, 0));
	}

	public Cell onSoutheast() {
		return this.getBoard().getCellAt(position.translate(1, 1));
	}

	public Cell onSouth() {
		return this.getBoard().getCellAt(position.translate(0, 1));
	}

	public Cell onSouthwest() {
		return this.getBoard().getCellAt(position.translate(-1, 1));
	}

	public Cell onWest() {
		return this.getBoard().getCellAt(position.translate(-1, 0));
	}

	public Cell onNorthwest() {
		return this.getBoard().getCellAt(position.translate(-1, -1));
	}

	@Override
	public void paintComponent(Graphics graphics) {
		final GraphicsRenderer renderer = new GraphicsRenderer(graphics);
		renderer.render(this);
		super.paintComponent(graphics);
	}

}