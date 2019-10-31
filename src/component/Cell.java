package component;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import geometry.Dimension;
import geometry.Point;
import geometry.Scale;
import ui.GraphicsRenderer;
import ui.texture.Image;

public class Cell extends GameComponent implements Positionable {

	private final Board board;
	private final Integer index;
	private final Point boardPosition;
	private Point position;
	private Dimension dimension;

	public Cell(Board board, Integer index, Point position) {
		this.board = board;
		this.index = index;
		this.boardPosition = position;
	}

	public Board getBoard() {
		return this.board;
	}

	public Integer getIndex() {
		return this.index;
	}

	public Dimension getDimension() {
		return this.dimension;
	}
	
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public void add(Actor component) {
		this.board.getComponents().add(component);
		component.setBoard(this.board);
	}

	public Image getImage() {
		return this.getBoard().getGround();
	}

	public Boolean hasComponent(Actor component) {
		return this.board.getComponents().contains(component);
	}

	public Cell onNorth() {
		return this.getBoard().getCellAt(this.getBoardPosition().translate(0, -1));
	}

	public Cell onNortheast() {
		return this.getBoard().getCellAt(this.getBoardPosition().translate(1, -1));
	}

	public Cell onEast() {
		return this.getBoard().getCellAt(this.getBoardPosition().translate(1, 0));
	}

	public Cell onSoutheast() {
		return this.getBoard().getCellAt(this.getBoardPosition().translate(1, 1));
	}

	public Cell onSouth() {
		return this.getBoard().getCellAt(this.getBoardPosition().translate(0, 1));
	}

	public Cell onSouthwest() {
		return this.getBoard().getCellAt(this.getBoardPosition().translate(-1, 1));
	}

	public Cell onWest() {
		return this.getBoard().getCellAt(this.getBoardPosition().translate(-1, 0));
	}

	public Cell onNorthwest() {
		return this.getBoard().getCellAt(this.getBoardPosition().translate(-1, -1));
	}
	
	public Point getBoardPosition() {
		return boardPosition;
	}
	
	@Override
	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}
	
	public void setPosition(WollokObject position) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void translate(Integer x, Integer y) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void translate(Point position) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void translateTo(Integer x, Integer y) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void translateTo(Point position) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void translateTo(WollokObject position) {
		// TODO Apéndice de método generado automáticamente
		
	}
	
	@Override
	public void render(Integer fps, GraphicsRenderer graphicsRenderer) {
		graphicsRenderer.render(this);
	}

	@Override
	public void update(Double time) {
		
	}

	@Override
	public void rotate(Double theta) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void rotate(Double theta, Point vector) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void rotate(Double theta, Integer x, Integer y) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void rotateAboutCenter(Double theta) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void scale(Integer x, Integer y) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void scale(Scale scale) {
		// TODO Apéndice de método generado automáticamente
		
	}

}