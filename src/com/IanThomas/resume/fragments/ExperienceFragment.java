package com.IanThomas.resume.fragments;

import com.IanThomas.resume.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExperienceFragment extends AResumeFragment {

	@Override
	public int getTitleResId() {
		return R.string.fragment_experience_title;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_experience, container, false);
	}

}
