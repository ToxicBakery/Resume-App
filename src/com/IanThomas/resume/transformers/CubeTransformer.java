package com.IanThomas.resume.transformers;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class CubeTransformer implements PageTransformer {
	private static final float MIN_SCALE = 0.85f;
	private static final float MIN_ALPHA = 0.8f;

	@Override
	public void transformPage(View view, float position) {
		if (position < -1) {
			// View is offscreen
		} else if (position <= 1) {
			final int pageHeight = view.getHeight();
			final int pageWidth = view.getWidth();

			// Rotate the fragment on the left or right edge
			view.setPivotX(position < 0 ? pageWidth : 0);
			view.setPivotY(pageHeight * -0.2f);
			view.setRotationY(-90f * position);

			// Fade the page relative to its size.
			float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
			view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
					/ (1 - MIN_SCALE) * (1 - MIN_ALPHA));

			if (view instanceof OnPositionChangedListener)
				((OnPositionChangedListener) view).onPositionChanged(position);
		} else {
			// View is offscreen
		}
	}

	public interface OnPositionChangedListener {

		public void onPositionChanged(float position);

	}

}
