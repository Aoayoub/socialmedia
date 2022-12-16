package com.example.socialmedia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;

public class Utility {
    public static Bitmap convertBLOB2Bitmap(byte[] blob) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap tmp = BitmapFactory.decodeByteArray(blob, 0, blob.length, options);
        return tmp;
    }
    public static byte[] convertBitmap2BLOB(@NonNull Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , bos);
        return bos.toByteArray();
    }
}
