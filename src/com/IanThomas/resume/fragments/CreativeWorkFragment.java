package com.IanThomas.resume.fragments;

import com.IanThomas.resume.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreativeWorkFragment extends Fragment implements IResumeFragment {

	@Override
	public int getTitleResId() {
		return R.string.fragment_creative_work;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_creative_works, container,
				false);
	}

}
