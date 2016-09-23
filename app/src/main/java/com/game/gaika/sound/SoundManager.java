package com.game.gaika.sound;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.ID;
import com.game.gaika.db.DatabaseManager;
import com.game.gaika.debug.DebugManager;
import com.game.gaika.main.MainActivity;
import com.game.gaika.data.GameSetup;

public class SoundManager {

    private static final String FILE_EXC = ".wav";
   // private static final String FILE_EXC = ".mp3";

    private Map<String, Sound> sounds = null;

    private Handler handler;

    private static SoundManager _nistance = null;

    public static SoundManager getInstance() {
        if (_nistance == null) {
            _nistance = new SoundManager();
//            _nistance.init();
        }
        return _nistance;
    }

    private SoundManager() {
    }


    public void setHandler(Handler pHandler) {
        handler = pHandler;
    }

    public void init() {

        SQLiteDatabase dbData = DatabaseManager.getSqLiteDatabase("data.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "data.db", null, SQLiteDatabase.OPEN_READWRITE);

        sounds = new HashMap<>();

        Cursor cursor = dbData.query("SOUND", new String[]{"name"}, null, null, null, null, null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            String name = cursor.getString(cursor.getColumnIndex("name"));


           // if (GameSetup.isDebug_read_SD_res == true) {
            if (DebugManager.getAppRunModel() == ID.RUN_MODELE.DEBUG) {
                File resFile = new File(GameSetup.sdcartdPahtSound + name + FILE_EXC);
//                File resFile = new File(GameSetup.sdcartdPahtSound + "explosion.ogg");
                Sound sound = SoundFactory.createSoundFromFile(MainActivity.mGameActiviy.getEngine().getSoundManager(), resFile);

                sounds.put(name, sound);
            } else {
                try {
                    Sound sound = SoundFactory.createSoundFromAsset(MainActivity.mGameActiviy.getEngine().getSoundManager(), MainActivity.mGameActiviy, "sound/" + name + FILE_EXC);
                    sounds.put(name, sound);
                } catch (IOException e) {
                    throw new IllegalArgumentException("createSoundFromAsset filed : " + name + FILE_EXC);
                }

            }

            cursor.moveToNext();
        }
        cursor.close();
        dbData.close();

//		if(GameSetup.isDebug_test_Sound_list == true){
//			for(String soundKey: this.sounds.keySet()){
//				this.playSound(soundKey);
//			}
//		}
    }

    public void playSound(final String key) {

        if (sounds.containsKey(key) == false) {
            throw new IllegalArgumentException("Illegal src.sound key : " + key);
        }


        if (GameSetup.settingOpenSound == true) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    sounds.get(key).stop();

                    sounds.get(key).setVolume(GameSetup.settingSoundVolume);
                    sounds.get(key).play();
                }
            }, 0);
        }
    }

    public void stopSound(final String key) {

        if (sounds.containsKey(key) == false) {
            throw new IllegalArgumentException("Illegal src.sound key : " + key);
        }


        if (GameSetup.settingOpenSound == true) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    sounds.get(key).stop();
                }
            }, 0);
        }
    }
}
