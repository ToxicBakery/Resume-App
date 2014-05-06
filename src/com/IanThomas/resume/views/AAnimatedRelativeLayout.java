package com.IanThomas.resume.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.IanThomas.resume.transformers.CubeTransformer.OnPositionChangedListener;

public abstract class AAnimatedRelativeLayout extends RelativeLayout implements
		OnPositionChangedListener {

	public AAnimatedRelativeLayout(Context context) {
		super(context);
	}

	public AAnimatedRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AAnimatedRelativeLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

}
