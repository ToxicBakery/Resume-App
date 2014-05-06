package com.IanThomas.resume.views;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.IanThomas.resume.R;
import com.IanThomas.resume.data.DataManager;
import com.IanThomas.resume.data.DataProject;

public class ProjectsView extends AAnimatedRelativeLayout {

	private DataManager mDataManager;
	private LinearLayout mProjectsContainer;

	public ProjectsView(Context context) {
		super(context);
	}

	public ProjectsView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ProjectsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		if (isInEditMode())
			return;

		LayoutInflater inflater = LayoutInflater.from(getContext());
		mDataManager = DataManager.getInstance();

		mProjectsContainer = (LinearLayout) findViewById(R.id.fragment_projects_container);
		ArrayList<DataProject> projects = mDataManager.getProjects();
		for (int i = 0, j = projects.size(); i < j; ++i) {
			DataProject project = projects.get(i);
			ProjectItemView view = (ProjectItemView) inflater.inflate(
					R.layout.project_item, mProjectsContainer, false);
			view.updateView(project);
			mProjectsContainer.addView(view);

			if (i + 1 < j) {
				inflater.inflate(R.layout.divider, mProjectsContainer, true);
			}
		}
	}

	@Override
	public void onPositionChanged(float position) {
		final float height = getHeight();

		// Roll up the view like an accordion
		for (int i = 0, j = mProjectsContainer.getChildCount(); i < j; ++i) {
			final View view = mProjectsContainer.getChildAt(i);
			view.setTranslationY(-Math.abs(position) * 0.25f * height * (i + 1));

		}
	}

}
