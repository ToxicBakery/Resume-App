package com.IanThomas.resume.data;

import android.view.View;
import android.widget.ImageView;

public class DataProject {

	private int mImageResource;
	private String mTitle;
	private String mDescription;
	private String mDateFrom;
	private String mDateTo;
	private String mUrl;

	private DataProject(Builder builder) {
		mImageResource = builder.mImageResource;
		mTitle = builder.mTitle;
		mDescription = builder.mDescription;
		mDateFrom = builder.mDateFrom;
		mDateTo = builder.mDateTo;
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

	public String getDateFrom() {
		return mDateFrom;
	}

	public String getDateTo() {
		return mDateTo;
	}

	public String getUrl() {
		return mUrl;
	}

	public boolean hasImageResource() {
		return mImageResource != View.NO_ID;
	}

	public static final class Builder {

		private int mImageResource;
		private String mTitle;
		private String mDescription;
		private String mDateFrom;
		private String mDateTo;
		private String mUrl;

		public Builder() {
			mImageResource = View.NO_ID;
			mTitle = "";
			mDescription = "";
			mDateFrom = "";
			mDateTo = "";
			mUrl = "";
		}

		public DataProject build() {
			return new DataProject(this);
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

		public void setDateFrom(String dateFrom) {
			mDateFrom = dateFrom;
		}

		public void setDateTo(String dateTo) {
			mDateTo = dateTo;
		}

		public void setUrl(String url) {
			mUrl = url;
		}

	}

}
