package com.IanThomas.resume.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.IanThomas.resume.R;
import com.IanThomas.resume.data.DataExperience;

public class ExperienceItemView extends RelativeLayout {

	private TextView mTextViewTitle;
	private TextView mTextViewCompany;
	private TextView mTextViewLocation;
	private TextView mTextViewDescription;
	private TextView mTextViewDate;

	public ExperienceItemView(Context context) {
		super(context);
	}

	public ExperienceItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ExperienceItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		mTextViewTitle = (TextView) findViewById(R.id.view_experience_item_title);
		mTextViewCompany = (TextView) findViewById(R.id.view_experience_item_company);
		mTextViewLocation = (TextView) findViewById(R.id.view_experience_item_location);
		mTextViewDescription = (TextView) findViewById(R.id.view_experience_item_desciption);
		mTextViewDate = (TextView) findViewById(R.id.view_experience_item_date);
	}

	public void updateView(DataExperience data) {
		mTextViewTitle.setText(data.getTitle());
		mTextViewCompany.setText(data.getCompany());
		mTextViewLocation.setText(data.getLocation());
		mTextViewDescription.setText(data.getDescription());

		// Set the date
		final String dateTo = data.getDateTo();
		final String dateFrom = data.getDateFrom();

		if (dateFrom.isEmpty()) {
			mTextViewDate.setText(dateTo);
		} else if (dateTo.isEmpty()) {
			mTextViewDate.setText(dateFrom
					+ " - "
					+ getResources().getString(
							R.string.fragment_projects_date_present));
		} else {
			mTextViewDate.setText(dateFrom + " - " + dateTo);
		}
	}

}
