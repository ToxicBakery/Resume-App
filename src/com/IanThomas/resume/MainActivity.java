package com.IanThomas.resume;

import rajawali.RajawaliActivity;
import android.annotation.SuppressLint;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.IanThomas.resume.adapters.ResumeFragmentPagerAdapter;
import com.IanThomas.resume.gles.CubeRenderer;
import com.IanThomas.resume.transformers.CubeTransformer;
import com.IanThomas.resume.views.NavigatorView;

public class MainActivity extends RajawaliActivity {

	public MainActivity() {
		deferGLSurfaceViewCreation(true);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		// Init the 3D engine for the background
		mLayout = (FrameLayout) LayoutInflater.from(this).inflate(
				R.layout.activity_main, null, false);
		setContentView(mLayout);
		mSurfaceView = (GLSurfaceView) findViewById(R.id.surface_view);
		mSurfaceView.setEGLContextClientVersion(2);

		// Init the 3D renderer
		final CubeRenderer renderer = new CubeRenderer(this);
		renderer.setSurfaceView(mSurfaceView);
		setRenderer(renderer);

		final ResumeFragmentPagerAdapter mSectionsPagerAdapter = new ResumeFragmentPagerAdapter(
				getFragmentManager());

		final ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setPageTransformer(true, new CubeTransformer());

		final NavigatorView navView = (NavigatorView) findViewById(R.id.navigator);
		navView.setFragments(mSectionsPagerAdapter.getFragments());

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				navView.onPageScrolled(position, positionOffset);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				/*
				 * TODO If the GPU is "meh" pause background rendering while the
				 * foreground updates. Looking at you Galaxy Nexus.
				 */
				// if (state == 0) {
				// mSurfaceView.onResume();
				// } else {
				// mSurfaceView.onPause();
				// }
			}
		});
	}

	@SuppressLint("InlinedApi")
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE
							| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
							| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_FULLSCREEN
							| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}

}
