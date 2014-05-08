package com.IanThomas.resume.views;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.IanThomas.resume.R;
import com.IanThomas.resume.data.DataCreativeWork;
import com.IanThomas.resume.data.DataManager;

public class CreativeWorksView extends AAnimatedRelativeLayout {

	private DataManager mDataManager;
	private LinearLayout mCreativeWorkContainer;

	public CreativeWorksView(Context context) {
		super(context);
	}

	public CreativeWorksView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CreativeWorksView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		LayoutInflater inflater = LayoutInflater.from(getContext());
		mDataManager = DataManager.getInstance();

		mCreativeWorkContainer = (LinearLayout) findViewById(R.id.fragment_creative_work_container);
		ArrayList<DataCreativeWork> projects = mDataManager.getCreativeWorks();
		for (int i = 0, j = projects.size(); i < j; ++i) {
			DataCreativeWork creativeWork = projects.get(i);
			CreativeWorksItemView view = (CreativeWorksItemView) inflater
					.inflate(R.layout.creative_works_item_view,
							mCreativeWorkContainer, false);
			view.updateView(creativeWork);
			mCreativeWorkContainer.addView(view);

			if (i + 1 < j) {
				inflater.inflate(R.layout.divider, mCreativeWorkContainer, true);
			}
		}
	}

	@Override
	public void onPositionChanged(float position) {
		final float height = getHeight();

		// Fly in from bottom
		final float newPosition = Math.abs(position) * 0.125f;
		for (int i = 0, j = mCreativeWorkContainer.getChildCount(); i < j; ++i) {
			final View view = mCreativeWorkContainer.getChildAt(i);
			view.setTranslationY((height * newPosition)
					* (view.getTop() * newPosition));

		}
	}

}
