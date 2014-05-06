package com.IanThomas.resume.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.IanThomas.resume.R;
import com.IanThomas.resume.data.DataProject;

public class ProjectItemView extends RelativeLayout implements OnClickListener {

	private ImageView mImageViewProjectImage;
	private TextView mTextViewTitle;
	private TextView mTextViewDate;
	private TextView mTextViewDescription;

	private DataProject mDataProject;

	public ProjectItemView(Context context) {
		super(context);
	}

	public ProjectItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ProjectItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		if (isInEditMode())
			return;

		LayoutInflater.from(getContext()).inflate(R.layout.project_item_view,
				this, true);

		mImageViewProjectImage = (ImageView) findViewById(R.id.view_project_item_image);
		mTextViewTitle = (TextView) findViewById(R.id.view_project_item_title);
		mTextViewDate = (TextView) findViewById(R.id.view_project_item_date);
		mTextViewDescription = (TextView) findViewById(R.id.view_project_item_desciption);

		setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == this) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(mDataProject.getUrl()));
			getContext().startActivity(intent);
		}
	}

	public void updateView(DataProject data) {
		mDataProject = data;
		mTextViewTitle.setText(data.getTitle());
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

		// Set the image resource if one exists
		if (data.hasImageResource()) {
			data.applyImage(mImageViewProjectImage);
		} else {
			mImageViewProjectImage.setVisibility(View.INVISIBLE);
			mImageViewProjectImage.getLayoutParams().width = 0;
		}
	}

}
