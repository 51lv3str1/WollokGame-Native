package wollokGame;

import java.lang.reflect.Array;

import org.uqbar.project.wollok.interpreter.WollokInterpreter;
import org.uqbar.project.wollok.interpreter.WollokInterpreterEvaluator;
import org.uqbar.project.wollok.interpreter.core.WollokObject;
import org.uqbar.project.wollok.interpreter.nativeobj.WollokJavaConversions;

import geometry.Point;
import ui.Animation;
import ui.Image;
import ui.ImageBasedGraphics;
import ui.SpriteSheet;

public class VisualComponent {

	private static final ImageBasedGraphics DEFAULT_IMAGE = new Image("assets/wko.png");

	/** Wollok instance for this game. */
	private final WollokObject wrapped;

	/** Wollok interpreter. */
	private final WollokInterpreter interpreter;

	/** Wollok evaluator. */
	private final WollokInterpreterEvaluator evaluator;

	private SpriteSheet spritesheet;
	private Animation animation;

	public VisualComponent(WollokObject wrapped) {
		this.wrapped = wrapped;
		this.interpreter = WollokInterpreter.getInstance();
		this.evaluator = (WollokInterpreterEvaluator) interpreter.getEvaluator();
	}

	public WollokObject asWollokObject() {
		return this.wrapped;
	}

	public ImageBasedGraphics getGraphics() {
		if (this.wrapped.hasProperty("image")) {
			
			if (this.wrapped.hasProperty("opacity")) {
				return new Image(this.wrapped.call("image").toString(), Float.valueOf(this.wrapped.call("opacity").toString()));
			}

			else {
				return new Image(this.wrapped.call("image").toString());
			}
			
		}
		
		if(this.wrapped.hasProperty("animation") && this.animation == null){
			final String path = this.wrapped.call("animation").call("spritesheet").call("path").toString();
			final Integer rows = Integer.valueOf(this.wrapped.call("animation").call("spritesheet").call("rows").toString());
			final Integer columns = Integer.valueOf(this.wrapped.call("animation").call("spritesheet").call("columns").toString());
			
			final SpriteSheet spritesheet = new SpriteSheet(path, rows, columns);
			final Boolean loop = Boolean.valueOf(this.wrapped.call("animation").call("loop").toString());
			final Integer ratio = Integer.valueOf(this.wrapped.call("animation").call("ratio").toString());
			final WollokObject wFrames = this.wrapped.call("animation").call("indexes");
			final Integer[] indexes = new Integer[Integer.valueOf(wFrames.call("size").toString())];
			for (int i = 0; i < indexes.length; i++) {
				indexes[i] = Integer.valueOf(wFrames.call("get", WollokJavaConversions.javaToWollok(i)).toString());
			}
			this.animation =  spritesheet.getAnimation(loop, ratio, indexes);
		}
		
		if(this.wrapped.hasProperty("animation")) {
			return this.animation;
		}

		return DEFAULT_IMAGE;
	}

	public Point position() {
		final Integer x = Integer.valueOf(this.wrapped.call("position").call("x").toString());
		final Integer y = Integer.valueOf(this.wrapped.call("position").call("y").toString());
		return new Point(x, y);
	}

	public Point left(int n) {
		return new Point(this.position().getX() - n, this.position().getY());
	}

	public Point up(int n) {
		return new Point(this.position().getX(), this.position().getY() - n);
	}

	public Point right(int n) {
		return new Point(this.position().getX() + n, this.position().getY());
	}

	public Point down(int n) {
		return new Point(this.position().getX(), this.position().getY() + n);
	}

	public void update(Double time) {
		this.getGraphics().update(time);
	}

}
