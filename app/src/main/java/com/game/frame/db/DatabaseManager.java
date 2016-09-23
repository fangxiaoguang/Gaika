package com.game.frame.db;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.game.gaika.data.GameSetup;

/**
 * Created by fangxg on 2015/9/3.
 */
public class DatabaseManager {
    @NonNull
    public static SQLiteDatabase getSqLiteDatabase(String pDBName) {


     //   if (GameSetup.isDebug_read_SD_res == true) {
            return SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + pDBName, null, SQLiteDatabase.OPEN_READWRITE);
     /*   } else {
            GaikaDBOpenHelper dbOpenHelperData = new GaikaDBOpenHelper(MainActivity.mGameActiviy.getBaseContext(), pDBName, null, 1);

            SQLiteDatabase dbData = dbOpenHelperData.getReadableDatabase();
            return dbData;
        }*/
    }
}
