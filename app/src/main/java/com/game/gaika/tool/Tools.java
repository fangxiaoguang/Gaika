package com.game.gaika.tool;

import java.io.FileOutputStream;
import java.io.InputStream;
import android.content.res.AssetManager;
import android.util.Log;

public class Tools {

    public static void copyAsstsFile(AssetManager asset, String oldPath, String newPath) {
        try {
            int byteread = 0;
            InputStream inStream = asset.open(oldPath);//
            FileOutputStream fs = new FileOutputStream(newPath);
            byte[] buffer = new byte[1444];
            while ((byteread = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
            fs.close();
        } catch (Exception e) {
            Log.d("Gaika", "Copy file error: " + oldPath);
            e.printStackTrace();
        }
    }
}
