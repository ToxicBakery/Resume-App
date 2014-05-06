package com.IanThomas.resume.adapters;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.IanThomas.resume.fragments.AResumeFragment;
import com.IanThomas.resume.fragments.ExperienceFragment;
import com.IanThomas.resume.fragments.ProjectsFragment;
import com.IanThomas.resume.fragments.HomeFragment;

public class ResumeFragmentPagerAdapter extends FragmentStatePagerAdapter {

	private final ArrayList<Class<? extends AResumeFragment>> mFragments;

	public ResumeFragmentPagerAdapter(FragmentManager fm) {
		super(fm);

		mFragments = new ArrayList<>();
		mFragments.add(HomeFragment.class);
		mFragments.add(ProjectsFragment.class);
		mFragments.add(ExperienceFragment.class);
	}

	@Override
	public Fragment getItem(int position) {
		try {
			final Bundle bundle = new Bundle();
			bundle.putBoolean(AResumeFragment.EXTRA_HAS_NEXT_PAGE,
					position + 1 < getCount());
			bundle.putBoolean(AResumeFragment.EXTRA_HAS_PREVIOUS_PAGE,
					position != 0);

			final Fragment fragment = mFragments.get(position).newInstance();
			fragment.setArguments(bundle);

			return fragment;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}

}