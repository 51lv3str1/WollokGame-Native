package game;

import static java.awt.RenderingHints.*;

public class HighGraphicsSetting implements GraphicSettings {

	@Override
	public Object alphaInterpolation() {
		return VALUE_ALPHA_INTERPOLATION_QUALITY;
	}

	@Override
	public Object antiliasing() {
		return VALUE_ANTIALIAS_ON;
	}

	@Override
	public Object interpolation() {
		return VALUE_INTERPOLATION_BICUBIC;
	}

	@Override
	public Object renderingQuality() {
		return VALUE_RENDER_QUALITY;
	}

	@Override
	public Object textAntiLiasing() {
		return VALUE_TEXT_ANTIALIAS_ON;
	}

}
