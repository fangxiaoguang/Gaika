package com.game.gaika.data;

import com.game.gaika.data.weapon.BaseWeapon;

import static com.game.gaika.data.ID.MAP_NODE_TYPE.*;
import static com.game.gaika.data.ID.WEAPON_TYPE_EX.*;

/**
 * Created by fangxg on 2015/6/21.
 */
public class MapNode extends IMapPoint {
    public int id;
    public int x;
    public int y;
    public ID.MAP_NODE_TYPE type;

    public boolean isDaoLu;
    public MapNode[] neighbours;

    //计算路径 缓冲用数据字段


    public float costFromStart;
    public float groundStep;
//    public boolean isPath;
    boolean isCanMovePathAir;
    boolean isCanMovePathGround;
    boolean isCanMovePathShip;
    public BaseWeapon weapon;


    //--------------------------

    //    public float costToGoal;
//    public float totalCost;
//    public MapNode parent;
    //---------------------------


    public MapNode(int pX, int pY) {
        x = pX;
        y = pY;
        id = x * 100  + y;
        type = PING_DI;
        isDaoLu = false;
        neighbours = new MapNode[6];

//        isPath = false;
        costFromStart = GameMap.F1000_STEP;
    }

    public MapNode getNeighbour(int pIdx) {
        if (pIdx < 0 || pIdx > 5) {
            return null;
        }

        return neighbours[pIdx];
    }

    public float getGroundStep(ID.WEAPON_TYPE_EX pWeapTypeEx) {

        if (pWeapTypeEx == ID.WEAPON_TYPE_EX.GROUND) {
            return groundStep;
        } else {//AIR, SHIP,SUBMARINE
            return 1.0f;
        }
    }
    public float getGroundStepEx3(ID.WEAPON_TYPE_EX pWeapTypeEx) {

        if (pWeapTypeEx == ID.WEAPON_TYPE_EX.GROUND) {
                return groundStep;
        } else if (pWeapTypeEx == ID.WEAPON_TYPE_EX.SHIP ||pWeapTypeEx == ID.WEAPON_TYPE_EX.SUBMARINE) {
            if(type == HAI_HU || type == QIAN_TAN || type == QIAO_LIANG){
                return 1.0f;
            }else {
                return GameMap.F1000_STEP;
            }
        }else {//AIR,
            return 1.0f;
        }
    }


    public boolean isCanMovePath( ID.TEAM_COLOR pTeamColor, ID.WEAPON_TYPE_EX pWeapTypeEx) {

        GameDataManager gdm = GameDataManager.getInstance();
//        ID.WEAPON_TYPE_EX pWeapTypeEx = pWeapon.info.getTypeEx();

        if (weapon == null || gdm.getCurrentChapter().getAllianceTeamColors(pTeamColor).contains(weapon.teamColor)) {

          /*  if (pWeapTypeEx == AIR) {
                return true;
            } else if (pWeapTypeEx == SHIP || pWeapTypeEx == SUBMARINE) {
                if (this.type == HAI_HU || this.type == QIAN_TAN || this.type == QIAO_LIANG) {
                    return true;
                }
            } else if (pWeapTypeEx == GROUND) {
                if (type != HAI_HU && type != SHAN_DI) {
                    return true;
                }
            }*/
            if (pWeapTypeEx == GROUND) {
                return isCanMovePathGround;
            } else if (pWeapTypeEx == AIR) {
                return isCanMovePathAir;
            } else if (pWeapTypeEx == SHIP || pWeapTypeEx == SUBMARINE) {
                return isCanMovePathShip;
            }
        }
        return false;
    }
   /* public boolean isCanMovePathEx3( ID.TEAM_COLOR pTeamColor, ID.WEAPON_TYPE_EX pWeapTypeEx) {

        GameDataManager gdm = GameDataManager.getInstance();

        if (weapon == null || gdm.getCurrentChapter().getAllianceTeamColors(pTeamColor).contains(weapon.teamColor)) {

         return true;
        }
        return false;
    }*/

    @Override
    public int getMapX() {
        return x;
    }

    @Override
    public int getMapY() {
        return y;
    }

    public MapNode getMiniPathNeighbour(IMapPoint pDesMapNode) {
        GameDataManager gdm = GameDataManager.getInstance();
        MapNode retNode = null;

        for (int i = 0; i < 6; i++) {
            if (this.neighbours[i] == gdm.getCurrentChapter().getGameMap().getMapNodeFromXY(pDesMapNode)) {
                return this.neighbours[i];
            }
        }

        for (int i = 0; i < 6; i++) {
            if (this.neighbours[i] != null && this.neighbours[i].costFromStart < GameMap.F1000_STEP) {
                if (retNode == null) {
                    retNode = this.neighbours[i];
                } else if (this.neighbours[i].costFromStart < retNode.costFromStart) {
                    retNode = this.neighbours[i];
                }
            }
        }

        return retNode;
    }

    @Override
    public String toString() {
        return "   X = " + x + "  Y = " + y;
    }


}
