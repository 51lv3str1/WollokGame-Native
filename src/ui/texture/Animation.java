package ui.texture;

import ui.SpriteSheet;

public class Animation extends SpritesheetBasedTexture {

	private final Integer[] frameIndexes;
	private final Boolean loop;
	private Integer frameIndexCount = 0;
	private Integer ratio;
	private Double counter = 0.0;

	public Animation(SpriteSheet spriteSheet, Integer ratio, Integer... frameIndexes) {
		super(spriteSheet);
		this.frameIndexes = frameIndexes;
		this.ratio = ratio;
		this.loop = false;
	}

	public Animation(SpriteSheet spriteSheet, Boolean loop, Integer ratio, Integer... frameIndexes) {
		super(spriteSheet);
		this.frameIndexes = frameIndexes;
		this.ratio = ratio;
		this.loop = loop;
	}

	public Integer getRatio() {
		return this.ratio;
	}

	public void restart() {
		this.setIndex(this.frameIndexes[0]);
		this.frameIndexCount = 0;
	}

	@Override
	public void update(Double time) {

		if (counter == (ratio - 1)) {

			if (this.loop) {
				this.setIndex(frameIndexes[(frameIndexCount++) % frameIndexes.length]);
			}

			else {
				if (frameIndexCount < frameIndexes.length) {
					this.setIndex(frameIndexes[(frameIndexCount++)]);
				}

				else {
					this.setIndex(frameIndexes[frameIndexes.length - 1]);
				}

			}
		}

		counter = (counter + 1) % ratio;

	}

}