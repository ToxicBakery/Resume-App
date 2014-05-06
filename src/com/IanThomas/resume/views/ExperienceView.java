package com.IanThomas.resume.views;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.IanThomas.resume.R;
import com.IanThomas.resume.data.DataExperience;
import com.IanThomas.resume.data.DataManager;

public class ExperienceView extends AAnimatedRelativeLayout {

	private DataManager mDataManager;
	private LinearLayout mExperienceContainer;

	public ExperienceView(Context context) {
		super(context);
	}

	public ExperienceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ExperienceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		if (isInEditMode())
			return;

		LayoutInflater inflater = LayoutInflater.from(getContext());
		mDataManager = DataManager.getInstance();

		mExperienceContainer = (LinearLayout) findViewById(R.id.fragment_experience_container);
		ArrayList<DataExperience> experiences = mDataManager.getExperience();
		for (int i = 0, j = experiences.size(); i < j; ++i) {
			DataExperience experience = experiences.get(i);
			ExperienceItemView view = (ExperienceItemView) inflater.inflate(
					R.layout.experience_item, mExperienceContainer, false);
			view.updateView(experience);
			mExperienceContainer.addView(view);

			if (i + 1 < j) {
				inflater.inflate(R.layout.divider, mExperienceContainer, true);
			}
		}
	}

	@Override
	public void onPositionChanged(float position) {
		final int height = getHeight();

		// Slide in each card from opposite directions on the X
		for (int i = 0, j = mExperienceContainer.getChildCount(); i < j; ++i) {
			final View view = mExperienceContainer.getChildAt(i);
			position = i % 2 == 0 ? position : -position;
			view.setTranslationY(height * position * 2f);
		}
	}

}
