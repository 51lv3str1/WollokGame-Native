package wollokGame;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import geometry.Point;
import ui.Image;
import ui.Sprite;
import ui.SpriteSheet;

@SuppressWarnings({ "serial" })
public class Board extends JPanel {

	private static final Image CELL_GROUND_IMAGE = new Image("assets/ground.png");

	private final List<VisualComponent> components = new ArrayList<VisualComponent>();
	private final Integer rows;
	private final Integer columns;
	private Image cellsBackground;
	private SpriteSheet background;

	public Board(Integer rows, Integer columns) {
		this.rows = rows;
		this.columns = columns;
		this.setLayout(new GridLayout(columns, rows));
		this.initialize();
	}

	private void initialize() {
		Integer index = 0;
		for (int column = 0; column < columns; column++) {
			for (int row = 0; row < rows; row++) {
				this.add(new Cell(this, index++, new Point(row, column)));
			}
		}
	}

	public void setCellsBackground(String path) {
		this.cellsBackground = new Image(path);
	}

	public Image getCellsBackground() {
		return (this.hasCellsBackground()) ? this.getCellsBackground() : CELL_GROUND_IMAGE;
	}

	public Boolean hasCellsBackground() {
		return this.cellsBackground != null;
	}

	public void setBackgroundImage(String path) {
		this.background = new SpriteSheet(path, this.getRows(), this.getColumns());
	}

	public Image getBackgroundImage() {
		return this.background.getImage();
	}

	public Sprite getBackgroundSprite(Integer index) {
		return this.background.getSprite(index);
	}

	public Boolean hasBackground() {
		return this.background != null;
	}

	public Integer getRows() {
		return rows;
	}

	public Integer getColumns() {
		return columns;
	}

	public void addVisualComponent(VisualComponent component) {
		this.components.add(component);
	}

	public List<VisualComponent> getVisualComponets() {
		return this.components;
	}

	public List<Cell> getCells() {
		return Arrays.asList(this.getComponents()).stream().map(component -> (Cell) component)
				.collect(Collectors.toList());
	}

	public Cell getCellAt(Point position) {
		return this.getCells().stream().filter(cell -> cell.getPosition().equals(position)).findFirst().get();
	}

	public Cell getCellWith(VisualComponent component) {
		return this.getCells().stream().filter(cell -> cell.hasVisualComponent(component)).findFirst().get();
	}
	
	public void update(Double time){
		this.components.stream().forEach(component -> component.update(time));
	}

}
