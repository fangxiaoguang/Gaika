package com.game.gaika.scene.dialg;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.game.frame.scene.dialg.DialogScene;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.ID;
import com.game.frame.db.DatabaseManager;
import com.game.gaika.debug.DebugManager;
import com.game.frame.scene.BaseLogicScene;
import com.game.gaika.scene.SceneFactory;
import com.game.gaika.scene.SceneManager;
import com.game.frame.sprite.NormalSprite;
import com.game.frame.sprite.TextButtonSprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fangxg on 2015/8/16.
 */
public class DebugSetupDialogSaveFristScene extends DialogScene {
    private static List<Integer> fristSaveNos = new ArrayList<>();
    private static int currentPage = 1;
    private static final int COUNT_PER_PAGE = 8;

    public DebugSetupDialogSaveFristScene(BaseLogicScene pParentScene, ID.SCENE_INIT_TYPE pInitType) {
        super(ID.SCENE_ID.DEBUG_DIALOG_2_DIALOG, 0.0f, 0.0f, pParentScene.getLogicCamera().getCameraWidth(), pParentScene.getLogicCamera().getCameraHeight(), pParentScene, EPointModel.POINT_MODEL_CENTER);

        float scale = DebugManager.getDebugButtonScale(pParentScene.sceneId);

        SQLiteDatabase dbSave = DatabaseManager.getSqLiteDatabase("save.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "save.db", null, SQLiteDatabase.OPEN_READWRITE);
        if (pInitType == ID.SCENE_INIT_TYPE.INIT) {
            currentPage = 1;


            Cursor cursor = dbSave.query("SAVE_MAIN", new String[]{"no, date"}, " no >= 10000 ", null, null, null, " date desc ");


            Set<Integer> fristNoSet = new HashSet<>();
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {

                int fristSaveNo = cursor.getInt(cursor.getColumnIndex("no")) / 10000;
                fristNoSet.add(fristSaveNo);
                cursor.moveToNext();
            }
            cursor.close();
            //  dbSave.close();


            fristSaveNos = new ArrayList<>();
            fristSaveNos.addAll(fristNoSet);
            Collections.sort(fristSaveNos, new Comparator<Integer>() {
                @Override
                public int compare(Integer lhs, Integer rhs) {
                    if (lhs > rhs) {
                        return -1;
                    } else if (lhs < rhs) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
        }

        if (currentPage > 1) {
            //<-
            NormalSprite liftSprite = new NormalSprite(10, 110, "scroolbt", 0, new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_2___LEFT, null, this), scale);
            addSprite(liftSprite);
        }

        if (fristSaveNos.size() > currentPage * COUNT_PER_PAGE) {
            //->
            NormalSprite liftSprite = new NormalSprite(450, 110, "scroolbt", 0, new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_2___RIGHT, null, this), scale);
            addSprite(liftSprite);

        }

        int x = 50;
        int y = 10;

        int count = 0;

        while (count < fristSaveNos.size()) {
            if (count >= (currentPage - 1) * COUNT_PER_PAGE && count <= currentPage * COUNT_PER_PAGE - 1) {
                int fristSaveNo = fristSaveNos.get(count);


                String date = "";


                Cursor cursor = dbSave.query("SAVE_MAIN", new String[]{"no, date"}, " no >= " + fristSaveNo * 10000 + " and " + " no < " + (fristSaveNo + 1) * 10000, null, null, null, " date desc ");


                cursor.moveToFirst();
                date = cursor.getString(cursor.getColumnIndex("date"));
                cursor.close();


                TextButtonSprite button1 = new TextButtonSprite(x, y, fristSaveNo + "--" + date, new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_2__BUTTON_1, null, this, fristSaveNo), scale);
                addSprite(button1);
                y += 40;
            }


            count++;
        }


//        y += 30;
        TextButtonSprite button8 = new TextButtonSprite(x, y, "Return", new TouchMessage(ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_2__BUTTON_8, null, this), scale);
        addSprite(button8);

        dbSave.close();
    }

    @Override
    public boolean isBacegroundEnabled() {
        return false;
    }

    @Override
    public void buildScene() {

    }

    @Override
    public void onHandlMessage(TouchMessage pTouchMessage) {

        ID.MSG_ID msgID = pTouchMessage.getMessageID();


        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_2___LEFT) {
            currentPage--;

            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                BaseLogicScene topScene = SceneFactory.createScene(parentScene.sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
                topScene.setDialogSecne(new DebugSetupDialogSaveFristScene(topScene, ID.SCENE_INIT_TYPE.NOT_INIT));
                SceneManager.render(topScene);
            }
        }

        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_2___RIGHT) {
            currentPage++;

            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                BaseLogicScene topScene = SceneFactory.createScene(parentScene.sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
                topScene.setDialogSecne(new DebugSetupDialogSaveFristScene(topScene, ID.SCENE_INIT_TYPE.NOT_INIT));
                SceneManager.render(topScene);
            }
        }

        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_2__BUTTON_1) {
            int fristSaveNo = pTouchMessage.getParam();
            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(null);
                BaseLogicScene topScene = SceneFactory.createScene(parentScene.sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
                topScene.setDialogSecne(new DebugSetupDialogSaveSecendScene(topScene, fristSaveNo, ID.SCENE_INIT_TYPE.INIT));
                SceneManager.render(topScene);
            }
        }
        if (msgID == ID.MSG_ID.MSG_SCENE_DEBUG_SETUP_DIALOG_2__BUTTON_8) {


            BaseLogicScene parentScene = SceneManager.getTopBaseLogicScene();
            if (parentScene != null) {
                parentScene.setDialogSecne(new DebugSetupDialogMainScene(parentScene));
              //  BaseLogicScene topScene = SceneFactory.createScene(parentScene.sceneId, ID.SCENE_INIT_TYPE.NOT_INIT);
                SceneManager.render(parentScene);

            }
        }
    }
}
