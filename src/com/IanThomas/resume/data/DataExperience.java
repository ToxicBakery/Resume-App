package com.IanThomas.resume.data;

public class DataExperience {

	private String mTitle;
	private String mCompany;
	private String mLocation;
	private String mDescription;
	private String mDateFrom;
	private String mDateTo;
	private String mUrl;

	private DataExperience(Builder builder) {
		mTitle = builder.mTitle;
		mCompany = builder.mCompany;
		mLocation = builder.mLocation;
		mDescription = builder.mDescription;
		mDateFrom = builder.mDateFrom;
		mDateTo = builder.mDateTo;
		mUrl = builder.mUrl;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getCompany() {
		return mCompany;
	}

	public String getLocation() {
		return mLocation;
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

	public static final class Builder {

		private String mTitle;
		private String mCompany;
		private String mLocation;
		private String mDescription;
		private String mDateFrom;
		private String mDateTo;
		private String mUrl;

		public Builder() {
			mTitle = "";
			mCompany = "";
			mLocation = "";
			mDescription = "";
			mDateFrom = "";
			mDateTo = "";
			mUrl = "";
		}

		public DataExperience build() {
			return new DataExperience(this);
		}

		public void setTitle(String title) {
			mTitle = title;
		}

		public void setCompany(String company) {
			mCompany = company;
		}

		public void setLocation(String location) {
			mLocation = location;
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
