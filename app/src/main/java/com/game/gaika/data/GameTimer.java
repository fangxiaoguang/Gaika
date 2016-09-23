package com.game.gaika.data;

import java.util.Date;

/**
 * Created by fangxg on 2015/7/20.
 */
public class GameTimer {
    private int maxTunt;
    private int currentTurn;

    public GameTimer(int pMaxTunt) {
       this(pMaxTunt, 1);
    }

    public GameTimer(int pMaxTunt, int pCurrentTurn) {
        maxTunt = pMaxTunt;
        currentTurn = pCurrentTurn;
    }
    public void reset(){
        maxTunt = 0;
        currentTurn = 0;
    }

    public void tick(){
        currentTurn ++;
    }


    public  int getDayFromTurn() {
        return (currentTurn + 8) / 8;
    }

    public  String getAmPmFromTurn() {
        if (currentTurn % 8 == 1) {
            return "上午";
        }
        if (currentTurn % 8 == 2) {
            return "上午";
        }
        if (currentTurn % 8 == 3) {
            return "上午";
        }
        if (currentTurn % 8 == 4) {
            return "下午";
        }
        if (currentTurn % 8 == 5) {
            return "下午";
        }
        if (currentTurn % 8 == 6) {
            return "下午";
        }
        if (currentTurn % 8 == 7) {
            return "下午";
        }
        if (currentTurn % 8 == 0) {
            return "上午";
        }
        return "";

    }
    public  int getHourFromTurn() {
        if (currentTurn % 8 == 1) {
            return 7;
        }
        if (currentTurn % 8 == 2) {
            return 9;
        }
        if (currentTurn % 8 == 3) {
            return 11;
        }
        if (currentTurn % 8 == 4) {
            return 13;
        }
        if (currentTurn % 8 == 5) {
            return 15;
        }
        if (currentTurn % 8 == 6) {
            return 17;
        }
        if (currentTurn % 8 == 7) {
            return 21;
        }
        if (currentTurn % 8 == 0) {
            return 2;
        }
        return 0;
    }

    public int getBackDay(){
        return (maxTunt - currentTurn - 1) / 8 ;
    }

    public void setCurrentTurn(int pCurrentTurn){
        currentTurn = pCurrentTurn;
    }
    public int getCurrentTurn(){
        return currentTurn;
    }

    public static String getDateTimeNow() {
        String ret = "";
        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        ret = format1.format(new Date());
        return ret;
    }
}
