package com.IanThomas.resume.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.IanThomas.resume.R;

public abstract class AResumeFragment extends Fragment implements
		OnClickListener {

	public static final String EXTRA_HAS_NEXT_PAGE = AResumeFragment.class
			.getSimpleName() + ".EXTRA_HAS_NEXT_PAGE";
	public static final String EXTRA_HAS_PREVIOUS_PAGE = AResumeFragment.class
			.getSimpleName() + ".EXTRA_HAS_PREVIOUS_PAGE";

	protected boolean mHasNextPage;
	protected boolean mHasPreviousPage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final Bundle bundle = getArguments();
		validateExtra(bundle);

		mHasNextPage = bundle.getBoolean(EXTRA_HAS_NEXT_PAGE);
		mHasPreviousPage = bundle.getBoolean(EXTRA_HAS_PREVIOUS_PAGE);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

//		final LayoutInflater inflater = LayoutInflater.from(getActivity());
//
//		if (mHasNextPage) {
//			final View buttonRight = inflater.inflate(
//					R.layout.page_next_button, (ViewGroup) view, true);
//			buttonRight.setOnClickListener(this);
//			buttonRight.startAnimation(getNewPageIndicatorAnimationInstance());
//		}
//
//		if (mHasPreviousPage) {
//			final View buttonLeft = inflater.inflate(
//					R.layout.page_previous_button, (ViewGroup) view, true);
//			buttonLeft.setOnClickListener(this);
//			buttonLeft.startAnimation(getNewPageIndicatorAnimationInstance());
//		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_page_next:
			break;
		case R.id.button_page_previous:
			break;
		}
	}

	protected List<String> requiredExtras() {
		final List<String> requiredExtras = new ArrayList<>();
		requiredExtras.add(EXTRA_HAS_NEXT_PAGE);
		requiredExtras.add(EXTRA_HAS_PREVIOUS_PAGE);
		return requiredExtras;
	}

	protected void validateExtra(Bundle bundle) {
		if (bundle == null)
			throw new IllegalArgumentException("Missing required extras.");

		for (String extra : requiredExtras()) {
			if (!bundle.containsKey(extra))
				throw new IllegalArgumentException("Missing required extra "
						+ extra);
		}
	}

	protected Animation getNewPageIndicatorAnimationInstance() {
		final AlphaAnimation anim = new AlphaAnimation(0f, 1f);
		anim.setRepeatCount(Animation.INFINITE);
		anim.setRepeatMode(Animation.REVERSE);
		anim.setDuration(2000);

		return anim;
	}

}
