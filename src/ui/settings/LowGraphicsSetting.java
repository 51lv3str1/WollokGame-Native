package ui.settings;

import static java.awt.RenderingHints.*;

public class LowGraphicsSetting implements GraphicSettings {

	@Override
	public Object alphaInterpolation() {
		return VALUE_ALPHA_INTERPOLATION_SPEED;
	}

	@Override
	public Object antiliasing() {
		return VALUE_ANTIALIAS_OFF;
	}

	@Override
	public Object interpolation() {
		return VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
	}

	@Override
	public Object renderingQuality() {
		return VALUE_RENDER_SPEED;
	}

	@Override
	public Object textAntiLiasing() {
		return VALUE_TEXT_ANTIALIAS_OFF;
	}

}
