package org.zlibrary.ui.android.image;

import android.graphics.Bitmap;

import org.zlibrary.core.image.ZLImageData;

public final class ZLAndroidImageData implements ZLImageData {
	private Bitmap myBitmap;

	ZLAndroidImageData(Bitmap bitmap) {
		myBitmap = bitmap;
	}

	public Bitmap getBitmap() {
		return myBitmap;
	}
}
