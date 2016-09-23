package com.game.test;

import com.game.gaika.data.ID;

/**
 * Created by devuser1 on 2016/9/22.
 */

public class GameDriver <T extends Testable>
{
    private T mActivity;
    private long timeOutMS = 3000;

    public GameDriver(T pActivityClass) {
        mActivity = pActivityClass;
    }

    public GameElement findElementById(String pId){

        return mActivity.getElement(pId);

    }

    public void waitScenc(ID.SCENE_ID pSceneId){
        while(true){
            ID.SCENE_ID id = null;

            try{
                id = mActivity.getCurrentSceneId();
            }catch (NullPointerException e) {
                try {
                    Thread.sleep(timeOutMS);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                continue;
            }

            if(mActivity.getCurrentSceneId() == pSceneId){
                break;
            }else{
                try {
                    Thread.sleep(timeOutMS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            Thread.sleep(timeOutMS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
