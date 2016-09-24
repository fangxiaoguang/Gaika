package com.game.gaika.debug;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.SaveManager;
import com.game.gaika.db.DatabaseManager;

/**
 * Created by fangxg on 2015/7/18.
 */
public class DebugManager {


    private static DebugManager _nistance = null;

    public static DebugManager getInstance() {
        if (_nistance == null) {
            _nistance = new DebugManager();
        }
        return _nistance;
    }

    private static final int DEBUG_ID_OFFSET = 10000;

    private boolean recording = false;
    private int recordId = 0;
    private long beginRecordTimeMS;

    private static int firstSaveNo;

    public static void initFirstSaveNo() {

        SQLiteDatabase dbSave = DatabaseManager.getSqLiteDatabase("save.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "save.db", null, SQLiteDatabase.OPEN_READWRITE);

        Cursor cursor = dbSave.query("SAVE_MAIN", new String[]{"ifnull( max(no), 0)  max_no"}, null, null, null, null, null);

        int saveNo = 0;

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            saveNo = cursor.getInt(cursor.getColumnIndex("max_no"));
            cursor.moveToNext();
        }
//        if (saveNo < 2000) {
//            saveNo = 10000;
//        }
        firstSaveNo = saveNo / 10000 + 1;
        cursor.close();
        dbSave.close();

    }

    public void beginNewRecord() {
        recording = true;
        beginRecordTimeMS = System.currentTimeMillis();

        SQLiteDatabase dbSave = DatabaseManager.getSqLiteDatabase("data.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "save.db", null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = dbSave.query("SAVE_MAIN ", new String[]{"max(no) max_no"}, "no >= " + DEBUG_ID_OFFSET, null, null, null, null);

        cursor.moveToFirst();
        if (cursor.isAfterLast() == false) {
            recordId = cursor.getInt(cursor.getColumnIndex("max_no"));
            recordId = (recordId / DEBUG_ID_OFFSET + 1) * DEBUG_ID_OFFSET;

        } else {
            recordId = 1 * DEBUG_ID_OFFSET;
        }

        int endrecordId = (recordId / DEBUG_ID_OFFSET + 1) * DEBUG_ID_OFFSET;
        dbSave.delete("D_SAVE_MSG", "record_id >= " + recordId + " and record_id < " + (recordId + DEBUG_ID_OFFSET), null);


        cursor.close();
        dbSave.close();
    }

    public void recordMsg(TouchMessage pMsg) {
        if (recording == false) {
            return;
        }

        GameDataManager gdm = GameDataManager.getInstance();
        SaveManager.save(recordId);

        SQLiteDatabase dbSave = DatabaseManager.getSqLiteDatabase("save.db");// SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "save.db", null, SQLiteDatabase.OPEN_READWRITE);

        // table D_SAVE_MSG
        ContentValues v1 = new ContentValues();
        v1.put("timer_ms", System.currentTimeMillis());
        v1.put("record_id", recordId);
        v1.put("msg_id", pMsg.getMessageID().toString());
        v1.put("sender", pMsg.getSender() == null ? null : pMsg.getSender().toString());
        v1.put("handler", pMsg.getHandler().toString());
        v1.put("param1", pMsg.getParam());
//        v1.put("param2", pMsg.getData() == null ? null : pMsg.getData().toString());

        dbSave.beginTransaction();
        long l = dbSave.insert("D_SAVE_MSG", null, v1);
        dbSave.setTransactionSuccessful();
        dbSave.endTransaction();

        dbSave.close();

        recordId++;
    }

    public void endRecord() {
        recording = false;
    }

    public void beginPlayback(int pRecordId) {

    }

    public String stateInfo;

    public String stateInfo2;


    public String getDebugInfoString() {
        StringBuffer info = new StringBuffer();


        //  FSMClass fsm = BaseLogicScene.getFSMClass(ID.SCENE_ID.BATTLEFIELD);
        //  if (fsm != null) {
        //      Enum newState = fsm.getCurrentStateID();
        //    info.append("SceneState: " + newState);
        //   info.append("\n");
        info.append(stateInfo);
        info.append("\n");

        info.append(stateInfo2);
        info.append("\n");
        //  }

        return info.toString();
    }

    public static void onMessageProcess(TouchMessage pTouchMessage) {
        if (GameSetup.isDebug_trackingUserProcess == true) {

        }
    }

   /* public static void onStateChenge(Enum oldState, Enum newState) {
        if (GameSetup.isDebug_trackingUserProcess == true) {
            if (newState == BattlefieldScene.StateID.STATE_ID_SELECTED_NON && oldState != BattlefieldScene.StateID.STATE_ID_SELECTED_NON) {

                debugSave();
            }
        }
    }*/

    public static void debugSave() {
        int secendSaveNo = 0;
        SQLiteDatabase dbSave = DatabaseManager.getSqLiteDatabase("save.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "save.db", null, SQLiteDatabase.OPEN_READWRITE);

        Cursor cursor = dbSave.query("SAVE_MAIN", new String[]{"ifnull( max(no), 0)  max_no"}, " no >= " + firstSaveNo * 10000 + " and " + " no < " + (firstSaveNo + 1) * 10000, null, null, null, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            secendSaveNo = cursor.getInt(cursor.getColumnIndex("max_no")) % 10000;
            cursor.moveToNext();
        }
        secendSaveNo++;
        cursor.close();
        dbSave.close();

        SaveManager.save(firstSaveNo * 10000 + secendSaveNo);
    }

    public static float getDebugButtonScale(ID.SCENE_ID pSceneID) {

        if (pSceneID == ID.SCENE_ID.BATTLEFIELD) {
            return GameSetup.DEBUG_BUTTON_SCALE_BATT;
        } else {
            return GameSetup.DEBUG_BUTTON_SCALE_NOT_BATT;
        }

    }

    public static ID.RUN_MODELE getAppRunModel(){
        return ID.RUN_MODELE.RELESE;
       // return ID.RUN_MODELE.DEBUG;
    }

}
