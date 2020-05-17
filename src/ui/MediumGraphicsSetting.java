package ui;

import static java.awt.RenderingHints.*;

public class MediumGraphicsSetting implements GraphicsSettings {

	@Override
	public Object alphaInterpolation() {
		return VALUE_ALPHA_INTERPOLATION_QUALITY;
	}

	@Override
	public Object antiliasing() {
		return VALUE_ANTIALIAS_OFF;
	}

	@Override
	public Object interpolation() {
		return VALUE_INTERPOLATION_BILINEAR;
	}

	@Override
	public Object renderingQuality() {
		return VALUE_RENDER_SPEED;
	}

	@Override
	public Object textAntiLiasing() {
		return VALUE_TEXT_ANTIALIAS_ON;
	}

}