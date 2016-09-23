package com.game.gaika.data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.game.gaika.data.ID.TEAM_COLOR.*;

/**
 * Created by fangxg on 2015/6/20.
 */
public class GameChapter {

    public int no;
    public String mapName;
    public String cName;

    private GameMap gameMap;

    public int maxTurn;
    public ID.COUNTRY area;

    public boolean finished;
    // public int finishedOrder;

    public int pointX;
    public int pointY;
    public int pointX2;
    public int pointY2;
    public String victoryTerm;
    public String getWeaponIds;

    public int addMoney;
    public Set<Integer> nextChapterNos = new HashSet<Integer>();

    public Set<ID.TEAM_COLOR> side1 = new HashSet<>();
    public Set<ID.TEAM_COLOR> side2 = new HashSet<>();

    public String battleInfo;

   /* public int endTurnCount = 0;
    public int myLossCount = 0;
    public int enemyLossCount = 0;
    public int score = 0;
*/
    public GameChapter(int pNo, String pMapName, String pCName, int pMaxTurn, ID.COUNTRY pArea, int pPointX, int pPointY, int pPointX2, int pPointY2, boolean pFinished, String pVictoryTerm,
                       String pGetWeaponIds, int pAddMoney, String pNextChapterNos, String pSide1s, String pSide2s, String pBattleInfo) {
        no = pNo;
        mapName = pMapName;
        cName = pCName;
        maxTurn = pMaxTurn;
        area = pArea;
        pointX = pPointX;
        pointY = pPointY;
        pointX2 = pPointX2;
        pointY2 = pPointY2;
        finished = pFinished;
        victoryTerm = pVictoryTerm;
        getWeaponIds = pGetWeaponIds;
        addMoney = pAddMoney;

        if (pNextChapterNos != null) {

            nextChapterNos.clear();
            String[] ss = pNextChapterNos.split("\\|");
            for (String s : ss) {
                nextChapterNos.add(Integer.valueOf(s));
            }
        }

        String[] ss1 = pSide1s.split("\\|");
        for (String s : ss1) {
            if (s.equals("B")) {
                side1.add(BLUE);
            }
            if (s.equals("R")) {
                side1.add(RED);
            }
            if (s.equals("Y")) {
                side1.add(YELLOW);
            }
            if (s.equals("G")) {
                side1.add(GREEN);
            }
        }

        String[] ss2 = pSide2s.split("\\|");
        for (String s : ss2) {
            if (s.equals("B")) {
                side2.add(BLUE);
            }
            if (s.equals("R")) {
                side2.add(RED);
            }
            if (s.equals("Y")) {
                side2.add(YELLOW);
            }
            if (s.equals("G")) {
                side2.add(GREEN);
            }
        }
        battleInfo = pBattleInfo;
    }

    public void reset() {
        gameMap = null;
        finished = false;
        /*endTurnCount = 0;
        myLossCount = 0;
        enemyLossCount = 0;
        score = 0;*/
    }

    public GameMap getGameMap() {
        if (gameMap == null) {
            gameMap = new GameMap(no);
        }
        return gameMap;
    }

    public boolean canSelect() {
        GameDataManager gdm = GameDataManager.getInstance();
        for (GameChapter chapter : gdm.chapters.values()) {
            if (chapter.finished == true) {
                if (chapter.nextChapterNos.contains(this.no)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getAddMoney() {
        return addMoney;
    }

    public Collection<ID.TEAM_COLOR> getEnemyTeamColors(ID.TEAM_COLOR pMyTeamColor) {
        if (side1.contains(pMyTeamColor)) {
            return side2;
        } else {
            return side1;
        }
    }

    public Collection<ID.TEAM_COLOR> getAllianceTeamColors(ID.TEAM_COLOR pMyTeamColor) {
        if (side1.contains(pMyTeamColor)) {
            return side1;
        } else {
            return side2;
        }
    }


}
