package com.IanThomas.resume.gles;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import rajawali.Object3D;
import rajawali.math.vector.Vector3;

public class CubeField extends Object3D {

	static final float[] B_VERTICES = { 0f, 0f, 0f, 0f, 0f, 1f, 1f, 0f, 1f, 1f,
			0f, 0f, 0f, 1f, 0f, 0f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 0f, 0f, 0f, 0f,
			0f, 1f, 0f, 1f, 1f, 0f, 1f, 0f, 0f, 0f, 0f, 1f, 0f, 1f, 1f, 1f, 1f,
			1f, 1f, 0f, 1f, 0f, 0f, 0f, 0f, 0f, 1f, 0f, 1f, 1f, 0f, 1f, 0f, 1f,
			0f, 0f, 1f, 0f, 1f, 1f, 1f, 1f, 1f, 1f, 0f };

	static final float[] B_UV = { 0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f, 1f, 0f, 1f,
			1f, 0f, 1f, 0f, 0f, 0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f, 0f, 0f, 0f, 1f,
			1f, 1f, 1f, 0f, 0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f, 0f, 0f, 0f, 1f, 1f,
			1f, 1f, 0f };

	static final float[] B_NORMALS = { 0f, -1f, 0f, 0f, -1f, 0f, 0f, -1f, 0f,
			0f, -1f, 0f, 0f, 1f, 0f, 0f, 1f, 0f, 0f, 1f, 0f, 0f, 1f, 0f, 0f,
			0f, -1f, 0f, 0f, -1f, 0f, 0f, -1f, 0f, 0f, -1f, 0f, 0f, 1f, 0f, 0f,
			1f, 0f, 0f, 1f, 0f, 0f, 1f, -1f, 0f, 0f, -1f, 0f, 0f, -1f, 0f, 0f,
			-1f, 0f, 0f, 1f, 0f, 0f, 1f, 0f, 0f, 1f, 0f, 0f, 1f, 0f, 0f };

	static final int[] B_INDICES = { 0, 2, 1, 0, 3, 2, 4, 5, 6, 4, 6, 7, 8, 9,
			10, 8, 10, 11, 12, 15, 14, 12, 14, 13, 16, 17, 18, 16, 18, 19, 20,
			23, 22, 20, 22, 21 };

	private final float[] mVertices;
	private final float[] mUv;
	private final float[] mNormals;
	private final int[] mIndices;
	private final float[] mColor;

	public CubeField(CubeFieldManager mgr) {

		final int cubeCount = mgr.getCubeCount();
		final int cubeSize = mgr.getCubeSize();
		final float cubeScale = mgr.getCubeScale();

		mVertices = new float[B_VERTICES.length * cubeCount];
		mUv = new float[B_UV.length * cubeCount];
		mNormals = new float[B_NORMALS.length * cubeCount];
		mIndices = new int[B_INDICES.length * cubeCount];
		mColor = new float[mVertices.length];

		// Fill the color array
		Arrays.fill(mColor, 1f);

		final CubePos cubePos = new CubePos();
		final HashSet<String> usedPositions = new HashSet<>();

		// Create the cubes
		for (int i = 0; i < cubeCount; ++i) {

			do {
				cubePos.randomize(cubeSize);
			} while (usedPositions.contains(cubePos.toString()));
			usedPositions.add(cubePos.toString());

			// Copy vertices
			for (int j = 0, k = B_VERTICES.length; j < k; j += 3) {
				int offset = i * k;
				mVertices[offset + j] = (float) (B_VERTICES[j] + (cubePos.x * cubeScale));
				mVertices[offset + j + 1] = (float) (B_VERTICES[j + 1] + (cubePos.y * cubeScale));
				mVertices[offset + j + 2] = (float) (B_VERTICES[j + 2] + (cubePos.z * cubeScale));
			}

			// Copy uv coords
			System.arraycopy(B_UV, 0, mUv, B_UV.length * i, B_UV.length);

			// Copy normals
			System.arraycopy(B_NORMALS, 0, mNormals, B_NORMALS.length * i,
					B_NORMALS.length);

			// Calculate indices
			for (int j = 0, k = B_INDICES.length; j < k; ++j)
				mIndices[(k * i) + j] = B_INDICES[j] + (i * 24);
		}

		setData(mVertices, mNormals, mUv, mColor, mIndices);
	}

	public static final class CubeFieldManager {

		private final int mCubeSize;
		private final int mCubeCount;
		private final float mCubeScale;

		private CubeFieldManager(Builder builder) {
			mCubeSize = builder.mCubeSize;
			mCubeCount = builder.mCubeCount;
			mCubeScale = builder.mCubeScale;
		}

		public int getCubeCount() {
			return mCubeCount;
		}

		public int getCubeSize() {
			return mCubeSize;
		}

		public float getCubeScale() {
			return mCubeScale;
		}

		public static final class Builder {

			private int mCubeSize;
			private int mCubeCount;
			private float mCubeScale;

			public Builder() {
				mCubeCount = 70;
				mCubeSize = 5;
				mCubeScale = 2f;
			}

			public CubeFieldManager build() {
				return new CubeFieldManager(this);
			}

			public void setCubeSize(int cubeSize) {
				mCubeSize = cubeSize;
			}

			public void setCubeCount(int cubeCount) {
				mCubeCount = cubeCount;
			}

			public void setCubeScale(float scale) {
				mCubeScale = scale;
			}

		}

	}

	private static final class CubePos extends Vector3 {

		final Random r = new Random();

		public CubePos() {
			super();
		}

		@Override
		public String toString() {
			return x + " " + y + " " + z;
		}

		public void randomize(int size) {
			x = r.nextInt(size);
			y = r.nextInt(size);
			z = r.nextInt(size);
		}

	}

}
