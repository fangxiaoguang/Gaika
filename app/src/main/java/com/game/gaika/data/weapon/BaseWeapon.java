package com.game.gaika.data.weapon;

import com.game.gaika.ai.strategy.BaseStrategic;
import com.game.gaika.data.City;
import com.game.gaika.data.EffectNode;
import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameMap;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.GameTimer;
import com.game.gaika.data.ID;
import com.game.gaika.data.IMapPoint;
import com.game.gaika.data.MapNode;
import com.game.gaika.data.Pair;
import com.game.gaika.data.WeaponSelectFilter;

//import org.andengine.audio.src.sound.SoundManager;

import java.util.ArrayList;
import java.util.List;

import static com.game.gaika.data.ID.WEAPON_TYPE.*;
import static com.game.gaika.data.ID.TEAM_COLOR.*;

/**
 * Created by fangxg on 2015/6/23.
 */
public class BaseWeapon extends IMapPoint {
    public static int LIFE_PER_LIFE_EX = 100;
    public static int EXPERINCE_PER_LV = 100;
    private static float[] expReat = new float[]{0.0f, 10.0f / 9.0f, 10.0f / 9.0f, 10.0f / 18.0f, 10.0f / 36.0f, 10.0f / 48.0f, 10.0f / 120.0f,
            10.0f / 120.0f, 10.0f / 200.0f, 10.0f / 200.0f,};

    public int id;
    public WeaponInfo info;

    public ID.TEAM_COLOR teamColor;

    public int x;
    public int y;
    public int preX;
    public int preY;

    public int life;

    public int exp;
    public int preExp;

    public int move;
    public int preMove;

    public int fuel;
    public int preFuel;

    public int ammoOnAir;
    public int ammoOnGround;
    public int ammoOnShip;
    public int ammoOnSubmarine;

    public BaseWeapon transporter = null;

    public boolean teamOut;
    public boolean setOut;

    public boolean moveEnding;
    public boolean selected;

    public boolean moveToDescFinished;

    public List<MapNode> path = null;
    public ID.ADVANTAGE advantage;

    private BaseStrategic strategic = null;


    public BaseWeapon(WeaponInfo pWeapInfo) {
        GameDataManager gdm = GameDataManager.getInstance();
        id = gdm.getNewWeaponId();
        info = pWeapInfo;
        teamColor = BLUE;
        life = LIFE_PER_LIFE_EX * 10;
        move = pWeapInfo.move;
        fuel = pWeapInfo.fuel;
        ammoOnAir = pWeapInfo.ammoOnAir;
        ammoOnGround = pWeapInfo.ammoOnGround;
        ammoOnShip = pWeapInfo.ammoOnShip;
        ammoOnSubmarine = pWeapInfo.ammoOnSubmarine;
        teamOut = false;
        setOut = false;
        moveEnding = false;
        selected = false;
        moveToDescFinished = false;
        advantage = ID.ADVANTAGE.NON;
    }


    public BaseWeapon(/*int pId,*/ int pX, int pY, int pExp, int pInfoId, boolean pTeamOut, boolean pSetOut, ID.TEAM_COLOR pTeamColor, boolean pMoveEnding) {
        GameDataManager gdm = GameDataManager.getInstance();
        id = gdm.getNewWeaponId();
        info = gdm.weapInfos.get(pInfoId);
        teamColor = pTeamColor;
        x = pX;
        y = pY;
        life = LIFE_PER_LIFE_EX * 10;
        exp = pExp;
        preExp = pExp;
        move = gdm.weapInfos.get(pInfoId).move;
        fuel = gdm.weapInfos.get(pInfoId).fuel;
        ammoOnAir = gdm.weapInfos.get(pInfoId).ammoOnAir;
        ammoOnGround = gdm.weapInfos.get(pInfoId).ammoOnGround;
        ammoOnShip = gdm.weapInfos.get(pInfoId).ammoOnShip;
        ammoOnSubmarine = gdm.weapInfos.get(pInfoId).ammoOnSubmarine;
        teamOut = pTeamOut;
        setOut = pSetOut;
        moveEnding = pMoveEnding;
        selected = false;
        moveToDescFinished = false;
        advantage = ID.ADVANTAGE.NON;
    }

    public BaseWeapon(int pId, int pInfoId, int pX, int pY, boolean pTeamout, boolean pSetout, ID.TEAM_COLOR pTemaColor, int pLife, boolean pMoveEnding, int pExp,
                      int pFuel, int pAmmoOnAir, int pAmmoOnGround, int pAmmoOnShip, int pAmmoOnSubmarine, int pMove) {
        GameDataManager gdm = GameDataManager.getInstance();
        id = pId;
        info = gdm.weapInfos.get(pInfoId);
        teamColor = pTemaColor;
        x = pX;
        y = pY;
        life = pLife;
        exp = pExp;
        preExp = pExp;
        move = pMove;
        fuel = pFuel;
        ammoOnAir = pAmmoOnAir;
        ammoOnGround = pAmmoOnGround;
        ammoOnShip = pAmmoOnShip;
        ammoOnSubmarine = pAmmoOnSubmarine;
        teamOut = pTeamout;
        setOut = pSetout;
        moveEnding = pMoveEnding;
        selected = false;
        moveToDescFinished = false;
        advantage = ID.ADVANTAGE.NON;
    }

    private static int expToLv(int pExp) {
        int lv = 1 + pExp / EXPERINCE_PER_LV;
        if (lv > 10) {
            lv = 10;
        }
        return lv;
    }

    public int getLv() {
        return expToLv(exp);
    }

    public int getPreLv() {
        return expToLv(preExp);
    }

    public int getLifeEx() {
        return (life + 99) / LIFE_PER_LIFE_EX;
    }

    public void setAmmo(ID.WEAPON_TYPE_EX pTypeEx, int pAmmo) {
        if (pTypeEx == ID.WEAPON_TYPE_EX.AIR) {
            this.ammoOnAir = pAmmo;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.GROUND) {
            this.ammoOnGround = pAmmo;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.SHIP) {
            this.ammoOnShip = pAmmo;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.SUBMARINE) {
            this.ammoOnSubmarine = pAmmo;
        } else {
            throw new IllegalArgumentException("Not supprt WeaponTypeEx: " + pTypeEx);
        }
    }

    public int getAmmo(ID.WEAPON_TYPE_EX pTypeEx) {
        if (pTypeEx == ID.WEAPON_TYPE_EX.AIR) {
            return ammoOnAir;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.GROUND) {
            return ammoOnGround;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.SHIP) {
            return ammoOnShip;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.SUBMARINE) {
            return ammoOnSubmarine;
        } else {
            throw new IllegalArgumentException("Not supprt WeaponTypeEx: " + pTypeEx);
        }
    }

    public int getAttack(ID.WEAPON_TYPE_EX pTypeEx) {
        if (pTypeEx == ID.WEAPON_TYPE_EX.AIR) {
            return info.lvDataFromType.get(getLv()).attack_air;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.GROUND) {
            return info.lvDataFromType.get(getLv()).attack_ground;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.SHIP) {
            return info.lvDataFromType.get(getLv()).attack_ship;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.SUBMARINE) {
            return info.lvDataFromType.get(getLv()).attack_submarine;
        } else {
            throw new IllegalArgumentException("Not supprt WeaponTypeEx: " + pTypeEx);
        }
    }

    public int getDefense(ID.WEAPON_TYPE_EX pTypeEx) {
        if (pTypeEx == ID.WEAPON_TYPE_EX.AIR) {
            return info.lvDataFromType.get(getLv()).defense_air;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.GROUND) {
            return info.lvDataFromType.get(getLv()).defense_ground;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.SHIP) {
            return info.lvDataFromType.get(getLv()).defense_ship;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.SUBMARINE) {
            return info.lvDataFromType.get(getLv()).defense_submarine;
        } else {
            throw new IllegalArgumentException("Not supprt WeaponTypeEx: " + pTypeEx);
        }
    }

    public int getPreAttack(ID.WEAPON_TYPE_EX pTypeEx) {
        if (pTypeEx == ID.WEAPON_TYPE_EX.AIR) {
            return info.lvDataFromType.get(getPreLv()).attack_air;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.GROUND) {
            return info.lvDataFromType.get(getPreLv()).attack_ground;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.SHIP) {
            return info.lvDataFromType.get(getPreLv()).attack_ship;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.SUBMARINE) {
            return info.lvDataFromType.get(getPreLv()).attack_submarine;
        } else {
            throw new IllegalArgumentException("Not supprt WeaponTypeEx: " + pTypeEx);
        }
    }

    public int getPreDefense(ID.WEAPON_TYPE_EX pTypeEx) {
        if (pTypeEx == ID.WEAPON_TYPE_EX.AIR) {
            return info.lvDataFromType.get(getPreLv()).defense_air;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.GROUND) {
            return info.lvDataFromType.get(getPreLv()).defense_ground;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.SHIP) {
            return info.lvDataFromType.get(getPreLv()).defense_ship;
        } else if (pTypeEx == ID.WEAPON_TYPE_EX.SUBMARINE) {
            return info.lvDataFromType.get(getPreLv()).defense_submarine;
        } else {
            throw new IllegalArgumentException("Not supprt WeaponTypeEx: " + pTypeEx);
        }
    }

    //Choose arms begin......
    public boolean canChooseArms() {
        return false;
    }

    public void chooseArms(ID.ARMS_TYPE pArmsType) {
        return;
    }
    //Choose arms end......

    public boolean isBeBeset() {
        return (haveDanger(0) == true && haveDanger(3) == true)
                || (haveDanger(1) == true && haveDanger(4) == true)
                || (haveDanger(2) == true && haveDanger(5) == true);
    }

    public void doNew() {
        this.fuel = this.info.fuel;
        this.move = this.info.move;
        if(life > 0){
            this.life = LIFE_PER_LIFE_EX * 10;
        }
        this.ammoOnAir = this.info.ammoOnAir;
        this.ammoOnGround = this.info.ammoOnGround;
        this.ammoOnShip = this.info.ammoOnShip;
        this.ammoOnSubmarine = this.info.ammoOnSubmarine;

    }

    public void doSetOut(City pCity) {

//        this.doNew();
        this.x = pCity.getMapX();
        this.y = pCity.getMapY();
        this.setOut = true;
        this.moveEnding = true;

    }

    public void doSelect() {
        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();

        selected = true;

        map.cleanGreenBox(teamColor);
        map.fullGreenBox(this);

        gdm.computeEnemyWeaponAdvantage(this);
        gdm.computeTransportsFlag(this);
    }

    public void doBackSelected() {
        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();

        selected = false;
        map.cleanGreenBox(teamColor);
        gdm.cleanAdvantageFlag();
        gdm.cleanTransportsFlag();
    }

    public void doBackToTransporter(BaseWeapon pTransporter) {
        transporter = pTransporter;
        x = transporter.x;
        y = transporter.y;
        transporter.getPassengers().add(this);
    }

    public void doMoveToDesc(IMapPoint pDesc) {
        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();

        this.path = gdm.getCurrentChapter().getGameMap().getGreenBoxPath(this, pDesc);

        this.preX = this.x;
        this.preY = this.y;
        this.x = pDesc.getMapX();
        this.y = pDesc.getMapY();

        this.preMove = this.move;
        this.move -= gdm.getCurrentChapter().getGameMap().nodes[x][y].costFromStart;

        this.preFuel = this.fuel;
        if (this.info.halfMoveLoss == true) {
            if ((map.nodes[pDesc.getMapX()][pDesc.getMapY()].costFromStart) >= (this.info.move + 1) / 2) {
                this.fuel -= map.nodes[pDesc.getMapX()][pDesc.getMapY()].costFromStart;
            } else {
                this.fuel -= (this.info.move + 1) / 2;
            }
        } else {
//            this.fuel -= map.nodes[x][y].costFromStart;
            this.fuel -= map.nodes[pDesc.getMapX()][pDesc.getMapY()].costFromStart;
        }
        if (this.fuel <= 0) {
            this.fuel = 0;
        }

        this.moveToDescFinished = true;
        gdm.computeEnemyWeaponAdvantage(this);
    }

    public void doBackMoveToSrc() {
        GameDataManager gdm = GameDataManager.getInstance();
        this.x = this.preX;
        this.y = this.preY;
        this.move = this.preMove;
        this.fuel = this.preFuel;
        this.moveToDescFinished = false;

        gdm.computeEnemyWeaponAdvantage(this);
    }

    public void doMoveEnd() {
        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();

        this.selected = false;
        this.moveEnding = true;

        map.cleanGreenBox(teamColor);
        gdm.cleanAdvantageFlag();
        gdm.cleanTransportsFlag();
    }

    public void doFight(BaseWeapon pEnempWeapon) {
        GameDataManager gdm = GameDataManager.getInstance();
        GameMap map = gdm.getCurrentChapter().getGameMap();

        //myWeapon process
        selected = false;
        map.cleanGreenBox(teamColor);
        gdm.cleanAdvantageFlag();
        gdm.cleanTransportsFlag();

        Pair<Integer> damages = getFightDamage(pEnempWeapon);

        int myDamag = damages.getX();
        if (myDamag > pEnempWeapon.life) {
            myDamag = pEnempWeapon.life;
        }
        pEnempWeapon.life -= myDamag;

        if (pEnempWeapon.life == 0) {
            pEnempWeapon.doDie();
        }

        if (this.getAmmo(pEnempWeapon.info.getTypeEx()) > 0) {
            this.setAmmo(pEnempWeapon.info.getTypeEx(), this.getAmmo(pEnempWeapon.info.getTypeEx()) - 1);
        }

        this.moveEnding = !(this.info.continueAttack == true && pEnempWeapon.life == 0);
        //enemyWeapon process
        int enemyDamag = 0;
        if (pEnempWeapon.canAttack(this)) {
            enemyDamag = damages.getY();
            if (enemyDamag >= this.life) {
                enemyDamag = this.life;
            }

            this.life -= enemyDamag;
            if (this.life == 0) {
                this.doDie();
            }
            if (pEnempWeapon.getAmmo(this.info.getTypeEx()) > 0) {
                pEnempWeapon.setAmmo(this.info.getTypeEx(), pEnempWeapon.getAmmo(this.info.getTypeEx()) - 1);
            }

        }

        // ///////////////////////////
        if (this.life <= 0 || pEnempWeapon.life <= 0) {
//            SoundManager.getInstance().playSound("m_effe01");
        } else {
//            SoundManager.getInstance().playSound("m_effe02");
        }

        gdm.effectNodes = new ArrayList<>();
        if (myDamag > 0) {
            if (pEnempWeapon.life == 0) {
                gdm.effectNodes.add(new EffectNode(pEnempWeapon.x, pEnempWeapon.y, EffectNode.EEffectID.EFFECT_ID_MARKER04));
            } else {
                gdm.effectNodes.add(new EffectNode(pEnempWeapon.x, pEnempWeapon.y, EffectNode.EEffectID.EFFECT_ID_MARKER04));
            }
        }

        if (enemyDamag > 0) {
            if (this.life == 0) {
                gdm.effectNodes.add(new EffectNode(this.x, this.y, EffectNode.EEffectID.EFFECT_ID_MARKER04));
            } else {
                gdm.effectNodes.add(new EffectNode(this.x, this.y, EffectNode.EEffectID.EFFECT_ID_MARKER04));
            }
        }

        float myExp = (float) myDamag / 100.0f * 10.0f;
        if (pEnempWeapon.life <= 0) {
            myExp += 20.0f;
        }
        this.exp += myExp * expReat[this.getLv()];

        float enemyExp = (float) enemyDamag / 100.0f * 10.0f;
        if (this.life <= 0) {
            enemyExp += 20.0f;
        }
        pEnempWeapon.exp += enemyExp * expReat[pEnempWeapon.getLv()];
        // //////////////////////////
        if (GameSetup.open_group_attack == true && this.info.groupAttack == true) {
            MapNode mapNode = gdm.getCurrentChapter().getGameMap().getMapNodeFromXY(this);
            for (int i = 0; i < 6; i++) {
                MapNode mapNeighbour = mapNode.getNeighbour(i);
                if (mapNeighbour != null) {

                    WeaponSelectFilter filter = new WeaponSelectFilter();
                    filter.setSetOutInBattlefield();
                    filter.setMapXY(mapNeighbour.getMapX(), mapNeighbour.getMapY());
                    filter.addTeamColor(gdm.getCurrentChapter().getEnemyTeamColors(this.teamColor));
                    filter.addTypeEx(ID.WEAPON_TYPE_EX.GROUND);
                    List<BaseWeapon> enemys = gdm.getWeapons(filter);

                    if (enemys.size() > 0) {
                        BaseWeapon enemyNeighbour = enemys.get(0);
                        int att1 = this.getAttack(enemyNeighbour.info.getTypeEx());
                        int def2 = enemyNeighbour.getDefense(this.info.getTypeEx());

                        float damage1 = 0;
                        if (att1 > def2) {
                            damage1 = (3.0f + att1 - def2) * 10 * this.getLifeEx();

                        } else if (att1 < def2) {
                            damage1 = (3.0f + (att1 - def2) / 3.0f) * 10 * this.getLifeEx();
                            if (damage1 <= 0) {
                                damage1 = 10;
                            }
                        } else {
                            damage1 = 3.0f * 10 * this.getLifeEx();
                        }
                        damage1 = damage1 * GameSetup.GROUP_ATTACK_RATE;
//                        Log.d("Gaika", "BaseWeapon.doFight() Neibhbour = " + enemyNeighbour.info.name + "    damage = " + damage1);

                        if (damage1 > enemyNeighbour.life) {
                            damage1 = enemyNeighbour.life;
                        }
                        enemyNeighbour.life -= damage1;
                        if (enemyNeighbour.life <= 0) {
                            enemyNeighbour.doDie();
                        }

                        float exp = damage1 / 100.0f * 10.0f;
                        if (enemyNeighbour.life <= 0) {
                            exp += 20.0f;
                        }
                        this.exp += exp * expReat[this.getLv()];

                        gdm.effectNodes.add(new EffectNode(enemyNeighbour.x, enemyNeighbour.y, EffectNode.EEffectID.EFFECT_ID_MARKER04));

                    }
                }
            }
        }
    }

    public void doTurnFinish() {
        if (moveEnding == false) {
            if (this.info.halfMoveLoss == true) {
                fuel -= (info.move + 1) / 2;
                if (fuel < 0) {
                    fuel = 0;
                }
            }
        }
        moveEnding = false;
    }

    public void doDie() {
        life = 0;
    }

    public Pair getFightDamage(BaseWeapon pEnemy) {

        GameDataManager gd = GameDataManager.getInstance();

        float att1 = 0;
        if (this.getAmmo(pEnemy.info.getTypeEx()) == 0) {
            att1 = 0;
        } else {
            att1 = this.getAttack(pEnemy.info.getTypeEx());
        }

        float att2 = 0;
        if (pEnemy.getAmmo(this.info.getTypeEx()) == 0) {
            att2 = 0;
        } else {
            att2 = pEnemy.getAttack(this.info.getTypeEx());
        }

        float def1 = this.getDefense(pEnemy.info.getTypeEx());
        ID.DEF_ADD_TYPE defAdd1 = this.getDefenseAdd();
        if (defAdd1 == ID.DEF_ADD_TYPE.MIDDLE) {
            def1 += 3;
        } else if (defAdd1 == ID.DEF_ADD_TYPE.HIGH) {
            def1 += 5;
        }

        float def2 = pEnemy.getDefense(this.info.getTypeEx());
        ID.DEF_ADD_TYPE defAdd2 = pEnemy.getDefenseAdd();
        if (defAdd2 == ID.DEF_ADD_TYPE.MIDDLE) {
            def2 += 3;
        } else if (defAdd2 == ID.DEF_ADD_TYPE.HIGH) {
            def2 += 5;
        }

        float damage1 = 0;
        if (att1 == 0) {
            damage1 = 0;
        } else if (att1 > def2) {
            damage1 = (3.0f + att1 - def2) * 10 * this.getLifeEx();

        } else if (att1 < def2) {
            damage1 = (3.0f + (att1 - def2) / 3.0f) * 10 * this.getLifeEx();
            if (damage1 <= 0) {
                damage1 = 1;
            }
        } else {
            damage1 = 3.0f * 10 * this.getLifeEx();
        }

        int lifeEx2 = pEnemy.getLifeEx();

        if (GameSetup.open_first_attack == true) {

            if (this.info.fristAttack == true) {
                lifeEx2 = pEnemy.getLifeEx() - ((int) damage1) / 100;
            }

            if (lifeEx2 < 0) {
                lifeEx2 = 0;
            }
        }

        float damage2 = 0;
        if (att2 == 0) {
            damage2 = 0;
        } else if (att2 > def1) {
            damage2 = (3.0f + att2 - def1) * 10 * lifeEx2;

        } else if (att2 < def1) {
            damage2 = (3.0f + (att2 - def1) / 3.0f) * 10 * lifeEx2;
            if (damage2 <= 0) {
                damage2 = 1;
            }
        } else {
            damage2 = 3.0f * 10 * lifeEx2;
        }

        if (damage1 < 0) {
            damage1 = 0;
        }
        if (damage2 < 0) {
            damage2 = 0;
        }

        if (GameSetup.open_attack_tank_up == true) {
            // proc weapon 1
            if (this.info.attackTankUp == true && pEnemy.info.type == TANK) {
                damage1 = damage1 * GameSetup.TANK_ATTACK_UP;
            }

            // proc weapon 2
            if (pEnemy.info.attackTankUp == true && this.info.type == TANK) {
                damage2 = damage2 * GameSetup.TANK_ATTACK_UP;
            }

        }
        if (GameSetup.open_attack_helicopter_up == true) {
            // proc weapon 1
            if (this.info.attackHelicopterUp == true && (pEnemy.info.type == ATTACK_HELICOPTER || pEnemy.info.type == UTILITY_HELICOPTER)) {
                damage1 = damage1 * GameSetup.TANK_ATTACK_UP;
            }

            // proc weapon 2
            if (pEnemy.info.attackHelicopterUp == true && (this.info.type == ATTACK_HELICOPTER || this.info.type == UTILITY_HELICOPTER)) {

                damage2 = damage2 * GameSetup.TANK_ATTACK_UP;
            }

        }

        if (GameSetup.open_night_attack_low == true) {

            boolean isNight = false;
//            int currentTurn = gd.currentTurn;

            GameTimer timer = GameDataManager.getInstance().gameTimer;
            if (timer.getHourFromTurn() == 21 || timer.getHourFromTurn() == 2) {
                isNight = true;
            }

            // proc weapon 1
            if (this.info.nightAttackLow == true && isNight == true) {
                damage1 = damage1 * GameSetup.NIGHT_ATTACK_LOW;
            }

            // proc weapon 2
            if (pEnemy.info.nightAttackLow == true && isNight == true) {

                damage2 = damage2 * GameSetup.NIGHT_ATTACK_LOW;
            }
        }

        if (gd.getCurrentChapter().side1.contains(this.teamColor) == true) {

            if (gd.gameBeginDiff == ID.GAME_DIFF.EASE) {
                damage1 = damage1 * GameSetup.DIFF_EASE;
            } else if (gd.gameBeginDiff == ID.GAME_DIFF.HARD) {
                damage1 = damage1 * GameSetup.DIFF_HARD;
            } else if (gd.gameBeginDiff == ID.GAME_DIFF.VERY_HARD) {
                damage1 = damage1 * GameSetup.DIFF_VERY_HARD;
            }
        }
        return new Pair((int) damage1, (int) damage2);
    }

    private boolean haveDanger(int pNeighbourNo) {
        GameDataManager gdm = GameDataManager.getInstance();
        if (pNeighbourNo < 0 || pNeighbourNo > 5) {
            return false;
        }

        MapNode neighbourMap = gdm.getCurrentChapter().getGameMap().getMapNodeFromXY(this).getNeighbour(pNeighbourNo);
        if (neighbourMap != null) {
            WeaponSelectFilter filter = new WeaponSelectFilter();
            filter.setMapXY(neighbourMap.x, neighbourMap.y);
            filter.addTeamColor(gdm.getCurrentChapter().getEnemyTeamColors(this.teamColor));
            filter.setSetOut(true);
            filter.setLifeNotZero();
            filter.setAmmoNotZero(info.getTypeEx());

            List<BaseWeapon> weapons = gdm.getWeapons(filter);

            if (weapons.size() > 0) {
                return true;
            }
        }
        return false;
    }

    private ID.DEF_ADD_TYPE getDefenseAdd() {
        GameDataManager gdm = GameDataManager.getInstance();
        ID.MAP_NODE_TYPE mapNodeType = gdm.getCurrentChapter().getGameMap().getMapNodeFromXY(this).type;

        if (this.info.type == ID.WEAPON_TYPE.TANK || this.info.type == ID.WEAPON_TYPE.ARMORED_CAR || this.info.type == ID.WEAPON_TYPE.SCOUT_CAR
                || this.info.type == ID.WEAPON_TYPE.SELF_MECHANIZED_GUN || this.info.type == ID.WEAPON_TYPE.ANTIAIRCRAFT_GUN
                || this.info.type == ID.WEAPON_TYPE.ANTIAIRCRAFT_MISSILE || this.info.type == ID.WEAPON_TYPE.INFANTRY_FIGHTING_VEHICLE) {

            if (mapNodeType == ID.MAP_NODE_TYPE.SHEN_LIN || mapNodeType == ID.MAP_NODE_TYPE.JIE_DAO || mapNodeType == ID.MAP_NODE_TYPE.SHI_QU
                    || mapNodeType == ID.MAP_NODE_TYPE.CITY_CITY || mapNodeType == ID.MAP_NODE_TYPE.CITY_HARBOUR) {
                return ID.DEF_ADD_TYPE.MIDDLE;
            }

        }

        if (this.info.type == ID.WEAPON_TYPE.INFANTRY) {
            if (mapNodeType == ID.MAP_NODE_TYPE.SHEN_LIN || mapNodeType == ID.MAP_NODE_TYPE.JIE_DAO) {
                return ID.DEF_ADD_TYPE.MIDDLE;
            }
            if (mapNodeType == ID.MAP_NODE_TYPE.SHI_QU || mapNodeType == ID.MAP_NODE_TYPE.CITY_CITY || mapNodeType == ID.MAP_NODE_TYPE.CITY_HARBOUR) {
                return ID.DEF_ADD_TYPE.HIGH;
            }
        }
        return ID.DEF_ADD_TYPE.NONE;
    }

    public boolean canAttack(BaseWeapon pEnemy) {
        GameDataManager gd = GameDataManager.getInstance();

        int mapDistance = this.getMapDistance(pEnemy);
        Pair<Integer> attackRange = this.info.getRange(pEnemy.info.getTypeEx());

        if (this.getAmmo(pEnemy.info.getTypeEx()) == 0) {
            return false;
        }

        if (mapDistance < attackRange.getNear() || mapDistance > attackRange.getFar()) {
            return false;
        }

        if (GameSetup.open_move_finished_attack == true) {
            if (this.info.moveFinishedAttack == false && this.moveToDescFinished == true) {
                return false;
            }
        }

       /* WeaponSelectFilter filter2 = new WeaponSelectFilter();
        filter2.setSetOutInBattlefield();
        filter2.setMapXY(x, y);
        filter2.addTeamColor(ID.TEAM_COLOR.BLUE);
        filter2.setIsTransporter(true);
        List<BaseWeapon> transporters = filter2.getWeapons();//getWeaponFromSetoutAndLifeTransporter(pWeapon, TEAM_COLOR.BLUE);

        if (transporters.size() > 0 && this.id != transporters.get(0).id) {

            return false;
        }*/

        return true;//this.getAttack(pEnemy.info.getTypeEx()) != 0;

    }

    public void doRelive() {
        this.life = 10 * BaseWeapon.LIFE_PER_LIFE_EX;
    }

    public boolean canRepair(City pCity) {

        if (canSupply(pCity) == true && pCity.country == this.info.country) {
            return true;
        }
        return false;
    }

    public boolean canRepair() {
        GameDataManager gd = GameDataManager.getInstance();
        if (transporter != null) {

            if (canSupply() == true && transporter.info.country == info.country) {
                return true;
            }
        } else {

            City city = gd.getCurrentChapter().getGameMap().citys.get(this.x * 100 + y);
            return canRepair(city);
        }
        return false;
    }


    public void doRepair(int pToLifeEx) {
        this.life = pToLifeEx * BaseWeapon.LIFE_PER_LIFE_EX;
    }
    private boolean canSupply(City pCity) {
        if (pCity != null && pCity.teamColor == teamColor) {

            if (pCity.type == ID.CITY_TYPE.AIRPORT) {
                if (info.type == BATTLE_PLANE || info.type == ATTACK_PLANE
                        || info.type == ATTACK_HELICOPTER || info.type == UTILITY_HELICOPTER
                        || info.type == TRANSPORT_PLANE) {
                    return true;
                }
            }
            if (pCity.type == ID.CITY_TYPE.CITY) {
                if (info.type == ATTACK_HELICOPTER || info.type == UTILITY_HELICOPTER
                        || info.type == TANK || info.type == ARMORED_CAR
                        || info.type == SCOUT_CAR || info.type == SELF_MECHANIZED_GUN
                        || info.type == ANTIAIRCRAFT_GUN || info.type == ANTIAIRCRAFT_MISSILE
                        || info.type == INFANTRY_FIGHTING_VEHICLE || info.type == INFANTRY
                        || (info.type == ATTACK_PLANE && info.canVTAL == true)) {
                    return true;
                }
            }
            if (pCity.type == ID.CITY_TYPE.HARBOUR) {
                if (info.type == BATTLE_SHIP || info.type == TRANSPORT_SHIP
                        || info.type == AIRCRAFT_CARRIER || info.type == SUBMARINE
                        || info.type == LANDING_SHIP) {
                    return true;
                }
            }
        } else if (pCity != null && pCity.teamColor == teamColor) {
            if (pCity.type == ID.CITY_TYPE.AIRPORT) {
                if (info.type == TRANSPORT_PLANE) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canSupply() {
        GameDataManager gd = GameDataManager.getInstance();
        if (transporter != null) {

            if (transporter.info.type == ID.WEAPON_TYPE.AIRCRAFT_CARRIER) {
                return true;
            }
        } else {

            City city = gd.getCurrentChapter().getGameMap().citys.get(this.x * 100 + y);
            return canSupply(city);
        }
        return false;
    }

    public void doSupply() {
        this.ammoOnAir = this.info.ammoOnAir;
        this.ammoOnGround = this.info.ammoOnGround;
        this.ammoOnShip = this.info.ammoOnShip;
        this.ammoOnSubmarine = this.info.ammoOnSubmarine;

        this.fuel = this.info.fuel;
    }

    //AI  begin......
    public void setStrategic(BaseStrategic pStrategic) {
        strategic = pStrategic;
        if(strategic != null){
            strategic.setWeapon(this);
        }
    }

    public BaseStrategic getStrategic() {
        return strategic;
    }

    public int getMainAmmon() {
        int ammo = 0;
        int maxAttac = 0;
        for (ID.WEAPON_TYPE_EX type : ID.WEAPON_TYPE_EX.values()) {
            if (getAttack(type) > maxAttac) {
                ammo = getAmmo(type);
            }
        }
        return ammo;
    }
    //AI  end.....

    //Transport  begin......
    public boolean isTransporter() {

        return info.type == TRANSPORT_PLANE || info.type == TRANSPORT_SHIP
                || info.type == AIRCRAFT_CARRIER || info.type == LANDING_SHIP;
    }

    public void setTransportFlag() {

    }

    public boolean getTransportFlag() {
        return false;
    }

    public void clearTransportFlag() {

    }

    public Pair getTransportFlagTexIndex() {
        return null;
    }

    public void transport(BaseWeapon pWeapon) {

    }

    public List<BaseWeapon> getPassengers() {
        return new ArrayList<BaseWeapon>();
    }

    public boolean canTransportFromMapNode() {

        return false;
    }

    public boolean canTransport(BaseWeapon pWeapon) {
        return false;
    }

    public void intoTransporter(BaseWeapon pTransporter) {
        pTransporter.transport(this);
        this.transporter = pTransporter;

        this.doMoveEnd();
    }

    //Transport  end......

    @Override
    public int getMapX() {
        return this.x;
    }

    @Override
    public int getMapY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        return this.id == ((BaseWeapon) o).id;
    }

    @Override
    public String toString() {
        return "BaseWeapon:" + info.name + " ID:" + id + " color:" + teamColor + " x:" + x + ",y:" + y + " life:" + life + " exp:" + exp + " ammo:" + ammoOnAir
                + "," + ammoOnGround + "," + ammoOnShip + "," + ammoOnSubmarine + " move:" + move + " fuel:" + fuel + " setout:" + setOut + " teamOut:"
                + teamOut + " moveEnding:" + moveEnding + " selected:" + selected + " PassengersCount:" + getPassengers().size();

    }

    public void doEnterBattlefield() {
        if (this.teamOut == true) {
            if (life > 0) {
                life = LIFE_PER_LIFE_EX * 10;
            }
            setOut = false;
            move = info.move;
            fuel = info.fuel;
            ammoOnAir = info.ammoOnAir;
            ammoOnGround = info.ammoOnGround;
            ammoOnShip = info.ammoOnShip;
            ammoOnSubmarine = info.ammoOnSubmarine;
        }
    }

}