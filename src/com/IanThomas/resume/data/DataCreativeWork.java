package com.IanThomas.resume.data;

import android.view.View;
import android.widget.ImageView;

public class DataCreativeWork {

	private int mImageResource;
	private String mTitle;
	private String mDescription;
	private String mUrl;

	private DataCreativeWork(Builder builder) {
		mImageResource = builder.mImageResource;
		mTitle = builder.mTitle;
		mDescription = builder.mDescription;
		mUrl = builder.mUrl;
	}

	public void applyImage(ImageView imageView) {
		imageView.setImageResource(mImageResource);
	}

	public String getTitle() {
		return mTitle;
	}

	public String getDescription() {
		return mDescription;
	}

	public String getUrl() {
		return mUrl;
	}

	public static final class Builder {

		private int mImageResource;
		private String mTitle;
		private String mDescription;
		private String mUrl;

		public Builder() {
			mImageResource = View.NO_ID;
			mTitle = "";
			mDescription = "";
			mUrl = "";
		}

		public DataCreativeWork build() {
			return new DataCreativeWork(this);
		}

		public void setImageResource(int resource) {
			mImageResource = resource;
		}

		public void setTitle(String title) {
			mTitle = title;
		}

		public void setDescription(String description) {
			mDescription = description;
		}

		public void setUrl(String url) {
			mUrl = url;
		}

	}

}
