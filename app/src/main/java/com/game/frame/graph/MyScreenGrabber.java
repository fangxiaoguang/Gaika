package com.game.frame.graph;

import java.nio.IntBuffer;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.Entity;
import org.andengine.opengl.util.GLState;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.opengl.GLES20;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 *
 * @author Nicolas Gramlich
 * @since 15:27:22 - 10.01.2011
 */
public class MyScreenGrabber extends Entity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================


    private int mGrabX;
    private int mGrabY;
    private int mGrabWidth;
    private int mGrabHeight;

    private boolean mScreenGrabPending = false;
    private IMyScreenGrabberCallback mScreenGrabCallback;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onManagedDraw(final GLState pGLState, final Camera pCamera) {
        if (this.mScreenGrabPending) {
            try {
                final Bitmap screenGrab = MyScreenGrabber.grab(this.mGrabX, this.mGrabY, this.mGrabWidth, this.mGrabHeight);

                this.mScreenGrabCallback.onScreenGrabbed(screenGrab);
            } catch (final Exception e) {
                this.mScreenGrabCallback.onScreenGrabFailed(e);
            }

            this.mScreenGrabPending = false;
        }
    }

    @Override
    protected void onManagedUpdate(final float pSecondsElapsed) {
        /* Nothing */
    }

    @Override
    public void reset() {
		/* Nothing */
    }

    // ===========================================================
    // Methods
    // ===========================================================

    public void grab(final int pGrabWidth, final int pGrabHeight, final IMyScreenGrabberCallback pScreenGrabCallback) {
        this.grab(0, 0, pGrabWidth, pGrabHeight, pScreenGrabCallback);
    }

    public void grab(final int pGrabX, final int pGrabY, final int pGrabWidth, final int pGrabHeight, final IMyScreenGrabberCallback pScreenGrabCallback) {
        this.mGrabX = pGrabX;
        this.mGrabY = pGrabY;
        this.mGrabWidth = pGrabWidth;
        this.mGrabHeight = pGrabHeight;
        this.mScreenGrabCallback = pScreenGrabCallback;

        this.mScreenGrabPending = true;
    }

    @SuppressLint("NewApi")
    private static Bitmap grab(final int pGrabX, final int pGrabY, final int pGrabWidth, final int pGrabHeight) {
        final int[] source = new int[pGrabWidth * (pGrabY + pGrabHeight)];
        final IntBuffer sourceBuffer = IntBuffer.wrap(source);
        sourceBuffer.position(0);

        // TODO Check availability of OpenGL and GLES20.GL_RGBA combinations that require less conversion operations.
        // Note: There is (said to be) a bug with glReadPixels when 'y != 0', so we simply read starting from 'y == 0'.
        // TODO Does that bug still exist?
        GLES20.glReadPixels(pGrabX, 0, pGrabWidth, pGrabY + pGrabHeight, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, sourceBuffer);

        final int[] pixels = new int[130 * 97];

        // Convert from RGBA_8888 (Which is actually ABGR as the whole buffer seems to be inverted) --> ARGB_8888
		/*for (int y = 0; y < pGrabHeight; y++) {
			for (int x = 0; x < pGrabWidth; x++) {
				final int pixel = source[x + ((pGrabY + y) * pGrabWidth)];

				final int blue = (pixel & 0x00FF0000) >> 16;
				final int red = (pixel  & 0x000000FF) << 16;
				final int greenAlpha = pixel & 0xFF00FF00;

				pixels[x + ((pGrabHeight - y - 1) * pGrabWidth)] = greenAlpha | red | blue;
			}
		}

		return Bitmap.createBitmap(pixels, pGrabWidth, pGrabHeight, Config.ARGB_8888);*/
        for (int y = 0; y < 97; y++) {
            for (int x = 0; x < 130; x++) {
                float rx = (float) pGrabWidth / 130.0f;
                float ry = (float) pGrabHeight / 97.0f;
                int ox = (int) (x * rx);
                int oy = (int) (y * ry);
                final int pixel = source[ox + ((pGrabY + oy) * pGrabWidth)];

                final int blue = (pixel & 0x00FF0000) >> 16;
                final int red = (pixel & 0x000000FF) << 16;
                final int greenAlpha = pixel & 0xFF00FF00;

                pixels[x + ((97 - y - 1) * 130)] = greenAlpha | red | blue;
            }
        }

        return Bitmap.createBitmap(pixels, 130, 97, Config.ARGB_8888);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    public static interface IMyScreenGrabberCallback {
        // ===========================================================
        // Constants
        // ===========================================================

        // ===========================================================
        // Methods
        // ===========================================================

        public void onScreenGrabbed(final Bitmap pBitmap);

        public void onScreenGrabFailed(final Exception pException);
    }
}