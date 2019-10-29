package ui;

import java.awt.image.BufferedImage;

public abstract class SpritesheetBasedGraphics implements ImageBasedGraphics {

	private SpriteSheet spriteSheet;
	private Integer index;

	protected SpritesheetBasedGraphics(SpriteSheet spriteSheet) {
		this.setSpriteSheet(spriteSheet);
		this.setIndex(0);
	}

	protected SpritesheetBasedGraphics(SpriteSheet spriteSheet, Integer index) {
		this.setSpriteSheet(spriteSheet);
		this.setIndex(index);
	}

	public Integer getIndex() {
		return index;
	}

	protected void setIndex(Integer index) {
		this.index = index;
	}

	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	private void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public String getPath() {
		return this.getSpriteSheet().getPath();
	}

	public Image getImage() {
		return this.getSpriteSheet().getImage(this.getIndex());
	}

	@Override
	public Float getOpacity() {
		return this.getImage().getOpacity();
	}

	@Override
	public BufferedImage asBufferedImage() {
		return this.getImage().asBufferedImage();
	}

}
