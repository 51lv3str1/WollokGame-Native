package ui.texture;

import java.awt.image.BufferedImage;

import ui.SpriteSheet;

public abstract class SpritesheetBasedTexture implements Texture {

	private SpriteSheet spriteSheet;
	private Integer index;

	protected SpritesheetBasedTexture(SpriteSheet spriteSheet) {
		this.setSpriteSheet(spriteSheet);
		this.setIndex(0);
	}

	protected SpritesheetBasedTexture(SpriteSheet spriteSheet, Integer index) {
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
