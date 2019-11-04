package game;

public class Cell implements Positionable {

	private final Board board;
	private final Integer index;
	private final Point boardPosition;

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

	public Image getImage() {
		return this.getBoard().getGround();
	}

	public Boolean hasComponent(Actor component) {
		return component.getBoardPosition().equals(this.getBoardPosition());
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

	public Point getPosition() {
		final Bounds bounds = this.board.getLayout().getBounds(this.index);
		return new Point(bounds.getX(), bounds.getY());
	}

	@Override
	public Point getBoardPosition() {
		return boardPosition;
	}

}