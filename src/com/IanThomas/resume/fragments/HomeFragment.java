package com.IanThomas.resume.fragments;

import com.IanThomas.resume.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends AResumeFragment {
	
	@Override
	public int getTitleResId() {
		return R.string.fragment_home_title;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

}
