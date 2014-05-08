package com.IanThomas.resume.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.IanThomas.resume.R;
import com.IanThomas.resume.data.DataCreativeWork;

public class CreativeWorksItemView extends RelativeLayout implements
		OnClickListener {

	private ImageView mImageViewProjectImage;
	private TextView mTextViewTitle;
	private TextView mTextViewDescription;
	private ImageView mImageViewUrl;

	private DataCreativeWork mDataCreativeWork;

	public CreativeWorksItemView(Context context) {
		super(context);
	}

	public CreativeWorksItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CreativeWorksItemView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		mImageViewProjectImage = (ImageView) findViewById(R.id.creative_work_logo);
		mTextViewTitle = (TextView) findViewById(R.id.creative_work_title);
		mTextViewDescription = (TextView) findViewById(R.id.creative_work_description);
		mImageViewUrl = (ImageView) findViewById(R.id.creative_work_url);

		if (!isInEditMode())
			mImageViewUrl.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.creative_work_url:
			final Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(mDataCreativeWork.getUrl()));
			getContext().startActivity(intent);
			break;
		}
	}

	public void updateView(DataCreativeWork data) {
		mDataCreativeWork = data;
		data.applyImage(mImageViewProjectImage);
		mTextViewTitle.setText(data.getTitle());
		mTextViewDescription.setText(data.getDescription());
	}

}
