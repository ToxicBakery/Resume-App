package com.IanThomas.resume.views;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.IanThomas.resume.fragments.AResumeFragment;

public class NavigatorView extends View {

	private static final float MARGIN = 30;
	private static final float INDICATOR_RADIUS = 10;

	private final ArrayList<String> mFragmentTitles = new ArrayList<>();

	private volatile int mPosition;
	private volatile float mPositionOffset;

	private float mDensity;
	private Paint mPaintNavBar;
	private Paint mPaintNavBarSelected;
	private Paint mPaintText;
	private Paint mPaintBackground;
	private Paint mPaintBackgroundBorder;
	private RectF mRectBackground;

	public NavigatorView(Context context) {
		super(context);
	}

	public NavigatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NavigatorView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		Resources res = getResources();
		mDensity = res.getDisplayMetrics().density;

		mPaintNavBar = new Paint();
		mRectBackground = new RectF();

		mRectBackground.top = -MARGIN * 4f;
		mRectBackground.bottom = MARGIN * 5f;
		mRectBackground.left = -50 * mDensity;

		final Typeface tf = Typeface.create("Roboto", Typeface.NORMAL);

		mPaintNavBar.setColor(res.getColor(android.R.color.holo_orange_dark));
		mPaintNavBar.setStyle(Style.STROKE);
		mPaintNavBar.setStrokeCap(Cap.BUTT);
		mPaintNavBar.setStrokeWidth(mDensity * 4);
		mPaintNavBar.setTextSize(20 * res.getDisplayMetrics().scaledDensity);
		mPaintNavBar.setAntiAlias(true);
		mPaintNavBar.setTypeface(tf);

		mPaintNavBarSelected = new Paint(mPaintNavBar);
		mPaintNavBarSelected.setColor(res
				.getColor(android.R.color.holo_red_dark));

		mPaintText = new Paint(mPaintNavBarSelected);
		mPaintText.setStyle(Style.FILL);

		mPaintBackground = new Paint();
		mPaintBackground.setStyle(Style.FILL);
		mPaintBackground.setColor(res.getColor(android.R.color.white));

		mPaintBackgroundBorder = new Paint();
		mPaintBackgroundBorder.setStyle(Style.STROKE);
		mPaintBackgroundBorder.setStrokeWidth(1f);
		mPaintBackgroundBorder.setAntiAlias(true);
		mPaintBackgroundBorder.setColor(0xFFCCCCCC);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// Update the background dimensions
		mRectBackground.right = getWidth() + (50 * mDensity);

		// Use 90% of the width
		final float width = getWidth() * 0.9f;
		final float start = (getWidth() - width) * 0.5f;
		final float segmentLength = width / (mFragmentTitles.size() - 1);

		// Determine the position for coloring
		int position = 0;
		if (mPosition != 0 && mPositionOffset < 0.5f) {
			position = mPosition;
		} else if (mPosition + 1 < mFragmentTitles.size()
				&& mPositionOffset > 0.5f) {
			position = mPosition + 1;
		}

		// Determine the xOffset for the segments
		final float center = start + (width / 2f);
		final float xOffset = center
				+ (segmentLength - ((mPosition + 1) * segmentLength))
				- (mPositionOffset * segmentLength);

		// Draw a background
		canvas.drawOval(mRectBackground, mPaintBackground);
		canvas.drawOval(mRectBackground, mPaintBackgroundBorder);

		// Write text
		final String text = mFragmentTitles.get(position);
		final float textWidth = mPaintText.measureText(text);
		mPaintText.setAlpha((int) (255 * Math.abs((mPositionOffset * 2) - 1)));
		canvas.drawText(text, center - textWidth / 2, MARGIN * 4f, mPaintText);

		for (int i = 0, j = mFragmentTitles.size(); i < j; ++i) {

			final float segmentOffset = segmentLength * i;
			final Paint drawPaint = i == position ? mPaintNavBarSelected
					: mPaintNavBar;

			// Draw the segments
			if (i + 1 != j) {
				final float offsetLength = xOffset + segmentOffset;
				canvas.drawLine(offsetLength + (INDICATOR_RADIUS * 1.5f),
						MARGIN, offsetLength + segmentLength
								- (INDICATOR_RADIUS * 1.5f), MARGIN,
						mPaintNavBar);
			}

			// Draw the indicators
			canvas.drawCircle(xOffset + (segmentLength * i), MARGIN,
					INDICATOR_RADIUS, drawPaint);
		}
	}

	public void setFragments(
			ArrayList<Class<? extends AResumeFragment>> fragments) {

		Resources res = getResources();
		mFragmentTitles.clear();

		for (Class<? extends AResumeFragment> fragment : fragments) {
			try {
				mFragmentTitles.add(res.getString(fragment.newInstance()
						.getTitleResId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		postInvalidate();
	}

	public void onPageScrolled(int position, float positionOffset) {
		mPosition = position;
		mPositionOffset = positionOffset;
		postInvalidate();
	}

}
