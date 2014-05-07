package com.IanThomas.resume.adapters;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.IanThomas.resume.fragments.ExperienceFragment;
import com.IanThomas.resume.fragments.HomeFragment;
import com.IanThomas.resume.fragments.ProjectsFragment;

public class ResumeFragmentPagerAdapter extends FragmentStatePagerAdapter {

	private final ArrayList<Class<? extends Fragment>> mFragments;

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
			final Fragment fragment = mFragments.get(position).newInstance();

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

	public ArrayList<Class<? extends Fragment>> getFragments() {
		return new ArrayList<>(mFragments);
	}

}