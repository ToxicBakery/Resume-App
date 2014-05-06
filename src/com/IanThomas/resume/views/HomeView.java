package com.IanThomas.resume.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.IanThomas.resume.R;
import com.IanThomas.resume.utils.ResumeMath;

public class HomeView extends AAnimatedRelativeLayout implements
		OnClickListener {

	private ImageView mImageViewProfileImage;
	private TextView mTextViewTitle;
	private ImageView mImageViewEmail;
	private ImageView mImageViewLocation;
	private ImageView mImageViewLinkedIn;
	private TextView mTextViewAboutMe;

	public HomeView(Context context) {
		super(context);
	}

	public HomeView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HomeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onPositionChanged(float position) {
		// final float positionAsPercentToZero = 1f - Math.abs(clamp(position,
		// -1f, 1f));
		final int height = getHeight();
		final int width = getWidth();

		mTextViewTitle.setTranslationY(position * -height);
		mTextViewTitle.setTranslationX(position * width);
		mImageViewEmail.setTranslationY(position * height);
		mImageViewLinkedIn.setTranslationY(position * -height);
		mTextViewAboutMe.setTranslationY(position * height * -2);

		/*
		 * clamp profile pic position to prevent flipping back into view on
		 * large screen devices
		 */
		final float imageViewPosition = ResumeMath.clamp(position, -0.25f,
				0.25f);
		mImageViewProfileImage.setTranslationY(imageViewPosition * -height);
		mImageViewProfileImage.setTranslationX(imageViewPosition * -width);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		mImageViewProfileImage = (ImageView) findViewById(R.id.fragment_home_imageview_profile_pic);
		mTextViewTitle = (TextView) findViewById(R.id.fragment_home_textview_title);
		mImageViewEmail = (ImageView) findViewById(R.id.fragment_home_imageview_mail);
		mImageViewLocation = (ImageView) findViewById(R.id.fragment_home_imageview_location);
		mImageViewLinkedIn = (ImageView) findViewById(R.id.fragment_home_imageview_linked_in);
		mTextViewAboutMe = (TextView) findViewById(R.id.fragment_home_textview_about_me);

		if (isInEditMode())
			return;

		mImageViewEmail.setOnClickListener(this);
		mImageViewLocation.setOnClickListener(this);
		mImageViewLinkedIn.setOnClickListener(this);
		mImageViewProfileImage.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.fragment_home_imageview_profile_pic:
			intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("https://plus.google.com/+IanThomasShaun"));
			break;
		case R.id.fragment_home_imageview_location:
			intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("geo:0,0?q=Orland, FL"));
			break;
		case R.id.fragment_home_imageview_mail:
			intent = new Intent(Intent.ACTION_SENDTO);
			intent.setData(Uri.parse("mailto:"));
			intent.putExtra(Intent.EXTRA_EMAIL,
					"ian.shaun.thomas+resume@gmail.com");
			intent.putExtra(Intent.EXTRA_SUBJECT, "I would like to hire you!");
			break;
		case R.id.fragment_home_imageview_linked_in:
			intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri
					.parse("https://www.linkedin.com/profile/view?id=51923912"));
			break;
		}

		try {
			if (intent != null)
				getContext().startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getContext(), R.string.error_failed_to_open,
					Toast.LENGTH_LONG).show();
		}
	}

	protected static final float clamp(float val, float min, float max) {
		return val < min ? min : val > max ? max : val;
	}

}
