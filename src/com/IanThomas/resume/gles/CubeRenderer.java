package com.IanThomas.resume.gles;

import rajawali.Object3D;
import rajawali.animation.Animation.RepeatMode;
import rajawali.animation.RotateOnAxisAnimation;
import rajawali.materials.Material;
import rajawali.materials.methods.DiffuseMethod;
import rajawali.materials.textures.Texture;
import rajawali.math.vector.Vector3;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;
import android.graphics.Bitmap.Config;
import android.opengl.GLES20;

import com.IanThomas.resume.R;
import com.IanThomas.resume.gles.CubeField.CubeFieldManager;

public class CubeRenderer extends RajawaliRenderer {

	private static final int CUBE_COUNT = 70;
	private static final int CUBE_SIZE = 5;
	private static final float CUBE_SCALE = 2f;
	private static final RandomColorGen RANDOM_COLOR_GEN = new RandomColorGen(
			10);

	private Object3D mCubeField;

	public CubeRenderer(Context context) {
		super(context);
		setFrameRate(60);
	}

	@Override
	protected void onRender(double delta) {
		super.onRender(delta);
		RANDOM_COLOR_GEN.updateColor();
		mCubeField.setColor(RANDOM_COLOR_GEN.color);
	}

	@Override
	protected void initScene() {

		final CubeFieldManager.Builder cubeFieldBuilder = new CubeFieldManager.Builder();
		cubeFieldBuilder.setCubeCount(CUBE_COUNT);
		cubeFieldBuilder.setCubeSize(CUBE_SIZE);
		cubeFieldBuilder.setCubeScale(CUBE_SCALE);

		final Material material = new Material();
		material.setDiffuseMethod(new DiffuseMethod.Lambert());
		material.setColorInfluence(0.3f);
		material.setColor(0x11771111);
		try {
			Texture texture = new Texture("cubetex", R.drawable.boxtexthin);
			texture.setBitmapConfig(Config.ARGB_8888);
			texture.setInfluence(0.7f);
			material.addTexture(texture);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		mCubeField = new CubeField(cubeFieldBuilder.build());
		mCubeField.setMaterial(material);
		mCubeField.setDoubleSided(true);
		mCubeField.setBlendingEnabled(true);
		mCubeField.setDepthTestEnabled(false);
		mCubeField.setBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE);
		mCubeField.setDepthMaskEnabled(false);
		mCubeField.setPosition(0, 0, 0);

		final Object3D container = new Object3D();
		container.isContainer(true);
		container.addChild(mCubeField);
		getCurrentScene().addChild(container);

		final RotateOnAxisAnimation anim = new RotateOnAxisAnimation(
				new Vector3(0.8d, 0.6d, 0.4d), 359.9d);
		anim.setDurationMilliseconds(30000);
		anim.setTransformable3D(mCubeField);
		anim.setRepeatMode(RepeatMode.INFINITE);
		anim.play();

		getCurrentScene().setBackgroundColor(0x11111111);
		getCurrentScene().registerAnimation(anim);
		getCurrentCamera().setPosition(
				new Vector3(CUBE_SIZE * CUBE_SCALE * 0.5d));
		getCurrentCamera().setLookAt(container.getPosition());

	}

}
