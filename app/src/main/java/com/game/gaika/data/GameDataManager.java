package com.game.gaika.data;

import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.data.weapon.WeaponInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game.gaika.data.ID.*;

import com.game.gaika.data.ID.GAME_DIFF;
import com.game.gaika.debug.DebugManager;

import static com.game.gaika.data.ID.COUNTRY.*;

/**
 * Created by fangxg on 2015/6/20.
 */
public class GameDataManager {


    public GAME_DIFF gameBeginDiff = GAME_DIFF.NON;
    public COUNTRY gameBeginLocal = COUNTRY.USA;
    public COUNTRY gameBeginCounty = COUNTRY.USA;

    //-------------------------------------
    public Map<Integer, WeaponInfo> weapInfos;
    public Map<Integer, BaseWeapon> weapons = new HashMap<>();

    public Map<Integer, GameChapter> chapters;
    private int currentChapterID;

    private Map<COUNTRY, Integer> diplomacys = new HashMap<>();
    private Map<TEAM_COLOR, Integer> supplys = new HashMap<>();
    public int money;
    public int scoreCount = 0;

    public GameTimer gameTimer = new GameTimer(1, 0);
    public TEAM_COLOR currentAiTeamColor = TEAM_COLOR.BLUE;
    public List<EffectNode> effectNodes = new ArrayList<EffectNode>();

//    public void loadInitDate() {
//        SaveManager.loadWeaponInfos();
//        SaveManager.reInitChapters();
//    }

    public void setCurrentChapter(int pCurrentChapterID) {
        currentChapterID = pCurrentChapterID;
    }

    public GameChapter getCurrentChapter() {
        return chapters.get(currentChapterID);
    }

    private GameDataManager() {


    }

    private static GameDataManager _nistance = null;

    public static GameDataManager getInstance() {
        if (_nistance == null) {
            _nistance = new GameDataManager();
//            _nistance.loadInitDate();
            _nistance.gameBeginDiff = GAME_DIFF.NON;
            _nistance.gameBeginLocal = COUNTRY.NON;
            _nistance.gameBeginCounty = COUNTRY.NON;
            SaveManager.loadWeaponInfos();
            _nistance.weapons = new HashMap<>();
            DebugManager.initFirstSaveNo();
//        SaveManager.reInitChapters();
        }
        return _nistance;
    }

    public List<BaseWeapon> getWeapons(WeaponSelectFilter pFilter) {
        return pFilter.getWeapons();
    }

    public List<BaseWeapon> getCanBuyWeapnos() {
        List<BaseWeapon> retNodes = new ArrayList<>();

        for (WeaponInfo weaponInfo : weapInfos.values()) {
            if (weaponInfo.diplomacy <= getDiplomacy(weaponInfo.country)
                    && (weaponInfo.airArmsInfoId == 0 || weaponInfo.airArmsInfoId == weaponInfo.id)) {

                retNodes.add(new BaseWeapon(weaponInfo));
            }
        }
        return retNodes;
    }

    public void setDiplomacy(COUNTRY pCountry, int pLv) {
        if (pCountry.ordinal() >= USA.ordinal() && pCountry.ordinal() <= JAPAN.ordinal()) {
            diplomacys.put(pCountry, pLv);
        }
    }

    public int getDiplomacy(COUNTRY pCountry) {
        if (diplomacys.containsKey(pCountry) == false) {
            setDiplomacy(pCountry, 0);
        }
        if (pCountry == ALL) {
            int lv = 0;
            List<COUNTRY> countrys = new ArrayList<>();
            countrys.add(USA);
            countrys.add(USN);
            countrys.add(RUSSIA);
            countrys.add(GERMANY);
            countrys.add(ENGLAND);
            countrys.add(JAPAN);
            for (COUNTRY country : countrys) {
                if (getDiplomacy(country) > lv) {
                    lv = getDiplomacy(country);
                }
            }
            return lv;
        } else {
            return diplomacys.get(pCountry);
        }

    }

    public void computeEnemyWeaponAdvantage(BaseWeapon pWeapon) {

        String methodName = ".computeEnemyWeaponAdvantage()";
        GameDataManager gdm = GameDataManager.getInstance();

        cleanAdvantageFlag();

        Collection<TEAM_COLOR> enemyColorMark = gdm.getCurrentChapter().getEnemyTeamColors(pWeapon.teamColor);

        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setSetOutInBattlefield();
        filter.addTeamColor(enemyColorMark);
        List<BaseWeapon> enemys = gdm.getWeapons(filter);

        for (BaseWeapon enemy : enemys) {

            Pair<Integer> damages = pWeapon.getFightDamage(enemy);

            int myDamag = damages.getX();
            ;
            if (myDamag >= enemy.life) {
                myDamag = (int) enemy.life;
            }

            int enemyDamag = damages.getY();
            if (enemyDamag >= pWeapon.life) {
                enemyDamag = (int) pWeapon.life;
            }

            if (myDamag > enemyDamag * 2) {
                enemy.advantage = ADVANTAGE.EASY;
            } else if (myDamag * 2 < enemyDamag) {
                enemy.advantage = ADVANTAGE.HARD;
            } else {
                enemy.advantage = ADVANTAGE.NORMAL;
            }

            int range = pWeapon.getMapDistance(enemy);
            int myRange = pWeapon.info.getRange(enemy.info.getTypeEx()).getY();

            WeaponSelectFilter filter2 = new WeaponSelectFilter();
            filter2.setSetOutInBattlefield();
            filter2.setMapXY(pWeapon.x, pWeapon.y);
            filter2.addTeamColor(TEAM_COLOR.BLUE);
            filter2.setIsTransporter(true);
            List<BaseWeapon> transporters = gdm.getWeapons(filter2);

            boolean inRange = true;
            if (transporters.size() > 0 && pWeapon.id != transporters.get(0).id) {
                inRange = false;
            }

            if (myRange < range) {
                inRange = false;
            }

            if (GameSetup.open_attack_neighbour == true) {

                if (pWeapon.info.attackNeighbour == false && pWeapon.getMapDistance(enemy) == 1) {
                    inRange = false;
                }
            }
            if (inRange == false) {

                if (enemy.advantage == ADVANTAGE.EASY) {
                    enemy.advantage = ADVANTAGE.EASY_GREY;
                }
                if (enemy.advantage == ADVANTAGE.NORMAL) {
                    enemy.advantage = ADVANTAGE.NORMAL_GREY;
                }
                if (enemy.advantage == ADVANTAGE.HARD) {
                    enemy.advantage = ADVANTAGE.HARD_GREY;
                }
            }

            if (myDamag == 0) {
                enemy.advantage = ADVANTAGE.NON;
            }
        }
    }

    public void cleanAdvantageFlag() {
        for (BaseWeapon weapon : weapons.values()) {
            weapon.advantage = ADVANTAGE.NON;
        }
    }

    public BaseWeapon getSelectedWeapon() {
        GameDataManager gdm = GameDataManager.getInstance();

        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setSelected(true);
        List<BaseWeapon> weapons = gdm.getWeapons(filter);

        if (weapons.size() != 1) {
//            throw new IllegalArgumentException("Selected weapon count is must 1.");
            return null;
        }

        BaseWeapon selectWeapon = weapons.get(0);
        return selectWeapon;
    }

    public void computeTransportsFlag(BaseWeapon pWeapon) {

        GameDataManager gdm = GameDataManager.getInstance();

        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setSetOutInBattlefield();
        filter.addTeamColor(TEAM_COLOR.BLUE);
        List<BaseWeapon> myWeapons = gdm.getWeapons(filter);

        for (BaseWeapon myWeapon : myWeapons) {

            if (myWeapon.canTransport(pWeapon) == true) {
                myWeapon.setTransportFlag();
            } else {
                myWeapon.clearTransportFlag();
            }
        }
    }

    public void cleanTransportsFlag() {
        for (BaseWeapon weapon : weapons.values()) {
            weapon.clearTransportFlag();
        }
    }

    public WIN_TYPE checkWinOrLose() {
        GameDataManager gdm = GameDataManager.getInstance();

        if (gdm.gameTimer.getCurrentTurn() <= 3) {
            return WIN_TYPE.NON;
        }

        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setSetOutInBattlefield();
        filter.addTeamColor(gdm.getCurrentChapter().side2);
        List<BaseWeapon> enemys = gdm.getWeapons(filter);
        if (enemys.size() == 0) {
            return WIN_TYPE.WIN;
        }

        boolean haveRedPoint = false;
        boolean haveYelloPoint = false;
        for (City city : gdm.getCurrentChapter().getGameMap().citys.values()) {
            if (city.isPoint && city.teamColor == TEAM_COLOR.RED) {
                haveRedPoint = true;
            }
            if (city.isPoint && city.teamColor == TEAM_COLOR.YELLOW) {
                haveYelloPoint = true;
            }
        }
        if (haveRedPoint == false && haveYelloPoint == false) {
            return WIN_TYPE.WIN;
        }

        WeaponSelectFilter filter2 = new WeaponSelectFilter();
        filter2.setSetOutInBattlefield();
        filter2.addTeamColor(gdm.getCurrentChapter().side1);
        List<BaseWeapon> myWeapons = gdm.getWeapons(filter2);
        if (myWeapons.size() == 0) {
            return WIN_TYPE.LOSE;
        }

        boolean haveBluePoint = false;
        for (City city : gdm.getCurrentChapter().getGameMap().citys.values()) {
            if (city.isPoint && city.teamColor == TEAM_COLOR.BLUE) {
                haveBluePoint = true;
            }
        }
        if (haveBluePoint == false) {
            return WIN_TYPE.LOSE;
        }
        return WIN_TYPE.NON;
    }

    public void setSupply(TEAM_COLOR pTeamColor, int pSupply) {
        supplys.put(pTeamColor, pSupply);
    }

    public void addSupply(TEAM_COLOR pTeamColor, int pSupply) {
        int supply = getSupply(pTeamColor);
        supply += pSupply;
        supplys.put(pTeamColor, supply);
    }

    public int getSupply(TEAM_COLOR pTeamColor) {
        if (supplys.keySet().contains(pTeamColor) == false) {
            supplys.put(pTeamColor, 0);
        }
        return supplys.get(pTeamColor);
    }

    public TEAM_COLOR getCurrentAiTeamColor() {
        return currentAiTeamColor;
    }

    public void nextAiTeamColor() {
        List<TEAM_COLOR> aiTeamColors = new ArrayList<>();
        aiTeamColors.add(TEAM_COLOR.BLUE);
        if (getCurrentChapter().side1.contains(TEAM_COLOR.GREEN) || getCurrentChapter().side2.contains(TEAM_COLOR.GREEN)) {
            aiTeamColors.add(TEAM_COLOR.GREEN);
        }
        if (getCurrentChapter().side1.contains(TEAM_COLOR.YELLOW) || getCurrentChapter().side2.contains(TEAM_COLOR.YELLOW)) {
            aiTeamColors.add(TEAM_COLOR.YELLOW);
        }
        if (getCurrentChapter().side1.contains(TEAM_COLOR.RED) || getCurrentChapter().side2.contains(TEAM_COLOR.RED)) {
            aiTeamColors.add(TEAM_COLOR.RED);
        }

        int index = aiTeamColors.indexOf(currentAiTeamColor);

        if (index != (aiTeamColors.size() - 1)) {
            currentAiTeamColor = aiTeamColors.get(index + 1);
        } else {
            currentAiTeamColor = aiTeamColors.get(0);
        }
    }

    public int getNewWeaponId() {
        int newId = 0;
        if (weapons == null) {
            weapons = new HashMap<Integer, BaseWeapon>();
        }
        for (Integer id : weapons.keySet()) {
            if (id > newId) {
                newId = id;
            }
        }
        newId++;
        return newId;
    }

    public void aiTrunBegin(TEAM_COLOR pAiTeamColor) {
        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setSetOutInBattlefield();
        filter.addTeamColor(pAiTeamColor);
        List<BaseWeapon> weapons = filter.getWeapons();

        for (BaseWeapon weapon : weapons) {
            weapon.moveToDescFinished = false;  // 做成  doTurn（）函数   连续攻击第二次以后，为啥没有判断输赢
            if (weapon.canSupply() == true) {
                weapon.doSupply();
            }
            if (weapon.getStrategic() != null) {
                weapon.getStrategic().updateSelf();
            }
        }
        WeaponSelectFilter filter2 = new WeaponSelectFilter();
        filter2.setSetOutInBattlefield();
        List<BaseWeapon> weapons2 = filter2.getWeapons();

        for (BaseWeapon weapon : weapons2) {
            weapon.moveEnding = false;
            if (weapon.fuel >= weapon.info.move) {
                weapon.move = weapon.info.move;
            } else {
                weapon.move = weapon.fuel;
            }
        }

        GameDataManager gdm = GameDataManager.getInstance();
        for (City city : gdm.getCurrentChapter().getGameMap().citys.values()) {
            if (city.teamColor == pAiTeamColor) {
                city.capture += 10;
                if (city.capture > 100) {
                    city.capture = 100;
                }
            }
        }

        for (City city : gdm.getCurrentChapter().getGameMap().citys.values()) {
            if (city.teamColor == pAiTeamColor) {
                gdm.addSupply(city.teamColor, city.getAddSupply());
            }
        }

        if (pAiTeamColor == TEAM_COLOR.BLUE) {
            gdm.gameTimer.tick();
        }


    }

    public City getPointCity(TEAM_COLOR pTeamColor) {
        for (City city : getCurrentChapter().getGameMap().citys.values()) {
            if (city.teamColor == pTeamColor && city.isPoint == true) {
                return city;
            }
        }
        return null;
    }
}
