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
import com.IanThomas.resume.utils.ResumeMath;

public class NavigatorView extends View {

	private static final float FADE_DURATION_MILLI = 1000;
	private static final int MARGIN = 30;
	private static final int HEIGHT_PER_FRAGMENT = 150;
	private static final int FRAGMENT_INDICATOR_RADIUS = 10;

	private final ArrayList<String> mFragmentTitles = new ArrayList<>();

	private volatile int mPosition;
	private volatile float mPositionOffset;
	private volatile long mLastChange;

	private float mDensity;
	private Paint mPaintNavBar;
	private Paint mPaintNavBarSelected;
	private Paint mPaintText;
	private Paint mPaintBackground;
	private RectF mRectBar;

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

		final Typeface tf = Typeface.create("Roboto", Typeface.NORMAL);

		mPaintNavBar.setColor(res.getColor(android.R.color.holo_orange_dark));
		mPaintNavBar.setStyle(Style.FILL_AND_STROKE);
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

		mRectBar = new RectF();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		final int alpha = (int) (255 * ResumeMath
				.clamp((FADE_DURATION_MILLI - (System.currentTimeMillis() - mLastChange))
						/ FADE_DURATION_MILLI, 0f, 1f));

		mPaintNavBar.setAlpha(alpha);
		mPaintNavBarSelected.setAlpha(alpha);
		mPaintText.setAlpha(alpha);
		mPaintBackground.setAlpha((int) (alpha / 3.5f));

		float yOffset = (mPosition * HEIGHT_PER_FRAGMENT)
				+ (HEIGHT_PER_FRAGMENT * mPositionOffset);

		canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(),
				mPaintBackground);

		for (int i = 0, j = mFragmentTitles.size(); i < j; ++i) {
			int position = 0;
			if (mPosition != 0 && mPositionOffset < 0.5f) {
				position = mPosition;
			} else if (mPosition + 1 < j && mPositionOffset > 0.5f) {
				position = mPosition + 1;
			}

			final Paint drawPaint = i == position ? mPaintNavBarSelected
					: mPaintNavBar;

			mPaintText.setColor(drawPaint.getColor());

			// Draw Indicators
			canvas.drawCircle(mRectBar.left, mRectBar.top - yOffset
					+ (i * HEIGHT_PER_FRAGMENT), FRAGMENT_INDICATOR_RADIUS,
					drawPaint);

			final float yTop = mRectBar.top - yOffset
					+ (i * HEIGHT_PER_FRAGMENT) + FRAGMENT_INDICATOR_RADIUS;

			// Draw text
			canvas.drawText(mFragmentTitles.get(i), mRectBar.left
					+ (mDensity * MARGIN), yTop, mPaintText);

			// Draw bars except for the last fragment
			if (i + 1 == j)
				break;

			canvas.drawLine(mRectBar.left, yTop + mDensity * 2, mRectBar.left,
					yTop + HEIGHT_PER_FRAGMENT
							- (FRAGMENT_INDICATOR_RADIUS * 2) - (mDensity * 2),
					mPaintNavBar);
		}

		if (alpha != 0f)
			postInvalidate();
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

		mRectBar.top = MARGIN * mDensity;
		mRectBar.bottom = mRectBar.top + (fragments.size() - 1)
				* HEIGHT_PER_FRAGMENT;
		mRectBar.left = mRectBar.top;
		mRectBar.right = mRectBar.left;

		postInvalidate();
	}

	public void onPageScrolled(int position, float positionOffset) {
		mPosition = position;
		mPositionOffset = positionOffset;
		mLastChange = System.currentTimeMillis();
		postInvalidate();
	}

}
