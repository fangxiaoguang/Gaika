package com.game.gaika.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.db.DatabaseManager;
import com.game.gaika.debug.DebugManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.game.gaika.data.ID.MAP_NODE_TYPE.*;

/**
 * Created by fangxg on 2015/6/20.
 */
public class GameMap {
    public static final float F1000_STEP = 1000.0f;

    public String name;
    public String hqName;
    public int sizeX;
    public int sizeY;
    public MapNode[][] nodes;
    public Map<Integer, City> citys;
    public Map<Integer, ColorBox> boxs = new HashMap<>();//自己所在位置，无box   地方单位所在位置，无Box   己方单位所在位置， 有Box

    public GameMap(int pCharterNo) {
        GameDataManager gdm = GameDataManager.getInstance();
        SQLiteDatabase dbData = DatabaseManager.getSqLiteDatabase("data.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "data.db", null, SQLiteDatabase.OPEN_READWRITE);

        Cursor cursor = dbData.query("CHAPTER_MAIN", new String[]{"chapter_no", "map_name"}, "chapter_no = " + pCharterNo, null, null, null, null);
        cursor.moveToFirst();
        name = cursor.getString(cursor.getColumnIndex("map_name"));
        cursor.close();

        cursor = dbData.query("MAP_MAIN", new String[]{"name", "sizex", "sizey", "hp_name"}, "name = '" + name + "'", null, null, null, null);
        cursor.moveToFirst();
        sizeX = cursor.getInt(cursor.getColumnIndex("sizex"));
        sizeY = cursor.getInt(cursor.getColumnIndex("sizey"));
        hqName = cursor.getString(cursor.getColumnIndex("hp_name"));

        cursor.close();


        nodes = new MapNode[sizeX][sizeY];
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                nodes[x][y] = new MapNode(x, y);
            }
        }


        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                // neighbor 0 set
                if (y == 0) {
                    nodes[x][y].neighbours[0] = null;
                } else {
                    nodes[x][y].neighbours[0] = nodes[x][y - 1];

                }

                // neighbor 1 set
                if (x == sizeX - 1) {
                    nodes[x][y].neighbours[1] = null;
                } else if (x % 2 == 0 && y == 0) {
                    nodes[x][y].neighbours[1] = null;
                } else {
                    if (x % 2 == 1) {
                        nodes[x][y].neighbours[1] = nodes[x + 1][y];
                    } else {
                        nodes[x][y].neighbours[1] = nodes[x + 1][y - 1];
                    }
                }

                // neighbor 2 set
                if (x == sizeX - 1) {
                    nodes[x][y].neighbours[2] = null;
                } else if (x % 2 == 1 && y == sizeY - 1) {
                    nodes[x][y].neighbours[2] = null;
                } else {
                    if (x % 2 == 1) {
                        nodes[x][y].neighbours[2] = nodes[x + 1][y + 1];
                    } else {
                        nodes[x][y].neighbours[2] = nodes[x + 1][y];
                    }
                }

                // neighbor 3 set
                if (y == sizeY - 1) {
                    nodes[x][y].neighbours[3] = null;
                } else {
                    nodes[x][y].neighbours[3] = nodes[x][y + 1];
                }

                // neighbor 4 set
                if (x == 0) {
                    nodes[x][y].neighbours[4] = null;
                } else if (x % 2 == 1 && y == sizeY - 1) {
                    nodes[x][y].neighbours[4] = null;
                } else {
                    if (x % 2 == 1) {
                        nodes[x][y].neighbours[4] = nodes[x - 1][y + 1];
                    } else {
                        nodes[x][y].neighbours[4] = nodes[x - 1][y];
                    }
                }

                // neighbor 5 set
                if (x == 0) {
                    nodes[x][y].neighbours[5] = null;
                } else if (x % 2 == 0 && y == 0) {
                    nodes[x][y].neighbours[5] = null;
                } else {
                    if (x % 2 == 1) {
                        nodes[x][y].neighbours[5] = nodes[x - 1][y];
                    } else {
                        nodes[x][y].neighbours[5] = nodes[x - 1][y - 1];
                    }
                }
            }// end for j
        }// end for i

        cursor = dbData.query("MAP_NODE", new String[]{"name", "x", "y", "type", "dao_lu"}, "name = '" + name + "'", null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int x = cursor.getInt(cursor.getColumnIndex("x"));
            int y = cursor.getInt(cursor.getColumnIndex("y"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            boolean daoLu = cursor.getInt(cursor.getColumnIndex("dao_lu")) > 0 ? true : false;

            nodes[x][y].type = ID.MAP_NODE_TYPE.valueOf(type);
            nodes[x][y].isDaoLu = daoLu;

            if (nodes[x][y].type == HAI_HU) {
                nodes[x][y].groundStep = F1000_STEP;
            } else if (nodes[x][y].type == PING_DI) {
                nodes[x][y].groundStep = 1.0f;
            } else if (nodes[x][y].type == SHEN_LIN) {
                nodes[x][y].groundStep = 2.3f;
            } else if (nodes[x][y].type == QIU_LING) {
                nodes[x][y].groundStep = 2.7f;
            } else if (nodes[x][y].type == QIAN_TAN) {
                nodes[x][y].groundStep = 2.5f;
            } else if (nodes[x][y].type == SHAN_DI) {
                nodes[x][y].groundStep = F1000_STEP;
            } else {
                nodes[x][y].groundStep = 1.0f;
            }

            if (nodes[x][y].isDaoLu == true) {
                nodes[x][y].groundStep = 0.9f;
            }

            nodes[x][y].isCanMovePathAir = true;

            if (nodes[x][y].type == HAI_HU || nodes[x][y].type == QIAN_TAN || nodes[x][y].type == QIAO_LIANG) {
                nodes[x][y].isCanMovePathShip = true;
            } else {
                nodes[x][y].isCanMovePathShip = false;
            }

            if (nodes[x][y].type != HAI_HU && nodes[x][y].type != SHAN_DI) {
                nodes[x][y].isCanMovePathGround = true;
            } else {
                nodes[x][y].isCanMovePathGround = false;
            }


            cursor.moveToNext();
        }

        cursor.close();


       /* for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                nodes[x][y].type = PING_DI;
                nodes[x][y].groundStep = 1.0f;
            }
        }*/


        dbData.close();

        resetCitys();
        return;
    }

    public void resetCitys() {
        GameDataManager gdm = GameDataManager.getInstance();
        if (citys == null) {
            citys = new HashMap<Integer, City>();
        }
        citys.clear();
        SQLiteDatabase dbData = DatabaseManager.getSqLiteDatabase("data.db");//SQLiteDatabase.openDatabase(GameSetup.sdcartdPahtDB + "data.db", null, SQLiteDatabase.OPEN_READWRITE);

        Cursor cursor = dbData.query("CITY_NODE_MAP", new String[]{"map_name", "x", "y", "type", "is_base", "color", "capture", "country", "name", "is_point",
                "get_weapon_info_id", "get_money", "get_supply", "get_msg_id"}, "map_name = '" + name + "'", null, null, null, null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            int x = cursor.getInt(cursor.getColumnIndex("x"));
            int y = cursor.getInt(cursor.getColumnIndex("y"));
            ID.CITY_TYPE type = ID.CITY_TYPE.valueOf(cursor.getString(cursor.getColumnIndex("type")));
            boolean isBase = cursor.getInt(cursor.getColumnIndex("is_base")) == 1 ? true : false;
            ID.TEAM_COLOR teamColor = ID.TEAM_COLOR.valueOf(cursor.getString(cursor.getColumnIndex("color")));
            int capture = cursor.getInt(cursor.getColumnIndex("capture"));

            Enum country = ID.COUNTRY.valueOf(cursor.getString(cursor.getColumnIndex("country")));
            if (teamColor == ID.TEAM_COLOR.BLUE) {
                country = gdm.gameBeginCounty;
            }

            String name = cursor.getString(cursor.getColumnIndex("name"));
            boolean isPoint = cursor.getInt(cursor.getColumnIndex("is_point")) == 1 ? true : false;
            int getWeaponInfoId = cursor.getInt(cursor.getColumnIndex("get_weapon_info_id"));
            int getMoney = cursor.getInt(cursor.getColumnIndex("get_money"));
            int getSupply = cursor.getInt(cursor.getColumnIndex("get_supply"));
            String getMsgId = cursor.getString(cursor.getColumnIndex("get_msg_id"));

            citys.put(x * 100 + y, new City(x * 100 + y, x, y, type, isBase, teamColor, capture, country, name, isPoint, getWeaponInfoId, getMoney, getSupply, getMsgId));


            cursor.moveToNext();
        }
        cursor.close();
        dbData.close();
    }

    public float getMapSizePixelX() {
        return sizeX * 50 + 94;
    }

    public float getMapSizePixelY() {
        return sizeY * 48 + 96;
    }

    public MapNode getMapNodeFromXY(IMapPoint p) {

        return nodes[p.getMapX()][p.getMapY()];
    }

    public City getCityNodeFromXY(IMapPoint p) {
        return citys.get(p.getMapX() * 100 + p.getMapY());
    }


    public void cleanGreenBox(ID.TEAM_COLOR pTeamColor) {


        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {


//                nodes[i][j].isPath = false;
                nodes[i][j].weapon = null;
                nodes[i][j].costFromStart = F1000_STEP;

            }
        }

        GameDataManager gdm = GameDataManager.getInstance();
        WeaponSelectFilter filter = new WeaponSelectFilter();
        filter.setTeamOut(true);
        filter.setSetOut(true);
        filter.setLifeNotZero();
        List<BaseWeapon> weapons = gdm.getWeapons(filter);

        for (BaseWeapon weapon : weapons) {
            nodes[weapon.x][weapon.y].weapon = weapon;
        }
        boxs = new HashMap<>();

    }

    public void fullGreenBox(BaseWeapon pWeapNode) {
//        fullGreenBox(pWeapNode, pWeapNode.move);
        this.boxs = getGreenBoxs(pWeapNode);
    }

    public Map<Integer, ColorBox> getGreenBoxs(BaseWeapon pWeapNode) {
        if (pWeapNode.info.inferiorMove == false && pWeapNode.isBeBeset() == true) {
            return getGreenBoxsShort(pWeapNode);
        }
//        return getGreenBoxsLong(pWeapNode, pWeapNode.move);
        return getGreenBoxsLong(pWeapNode, pWeapNode.move);
    }

    private Map<Integer, ColorBox> getGreenBoxsShort(BaseWeapon pWeapNode) {

        Map<Integer, ColorBox> boxs = new HashMap<>();

        MapNode node = getMapNodeFromXY(pWeapNode);
        node.costFromStart = 0;

        float maxMove = pWeapNode.move;
        ID.WEAPON_TYPE_EX typeEx = pWeapNode.info.getTypeEx();
        ID.TEAM_COLOR tesmcolor = pWeapNode.teamColor;

        for (int i = 0; i < 6; i++) {
            MapNode newNode = node.neighbours[i];
            if (newNode == null || newNode.isCanMovePath(tesmcolor, typeEx) == false) {
                continue;
            }
            float newCost = node.costFromStart + newNode.getGroundStep(typeEx);
            if (newCost >= newNode.costFromStart || newCost > maxMove) {
                continue;
            }
            newNode.costFromStart = newCost;
            ColorBox box = new ColorBox(newNode.x, newNode.y, ID.BOX_COLOR.GREEN);
            box.costFromStart = newNode.costFromStart;
            box.weapon = null;
            boxs.put(box.id, box);
        }
        return boxs;
    }

    public Map<Integer, ColorBox> getGreenBoxsLong(BaseWeapon pWeapNode, float pMaxMove) {

//        Map<Integer, MapNode> openNodes = new HashMap<>();
        LinkedHashMap<Integer,MapNode>  openNodes = new LinkedHashMap<>();



        MapNode startMapNode = getMapNodeFromXY(pWeapNode);
        startMapNode.costFromStart = 0;

        openNodes.put(startMapNode.id, startMapNode);

//        float maxMove = pWeapNode.move;
//        ID.WEAPON_TYPE_EX typeEx = pWeaponTypeEx;
//        ID.TEAM_COLOR tesmcolor = pTeamColor;
//        Long t1 = System.currentTimeMillis();
        int countStep = 0;
        while (openNodes.isEmpty() == false) {
            countStep++;



            MapNode node = openNodes.values().iterator().next();
            openNodes.remove(node.id);

            for (int i = 0; i < 6; i++) {
                MapNode newNode = node.neighbours[i];
                if (newNode == null || newNode.isCanMovePath(pWeapNode.teamColor, pWeapNode.info.getTypeEx()) == false) {
                    continue;
                }
                float newCost = node.costFromStart + newNode.getGroundStep(pWeapNode.info.getTypeEx());
                if (newCost >= newNode.costFromStart || newCost > pMaxMove) {
                    continue;
                }

                newNode.costFromStart = newCost;
                openNodes.put(newNode.id, newNode);

            }
        }
//        Long t2 = System.currentTimeMillis();
//        Long t3 = t2 - t1;
//        DebugManager.getInstance().stateInfo2 = "\n" + "           maxMove = " + pMaxMove + "\n" + "          T3(ms) = " + t3 + "\n" + "           countStep = " + countStep;
//        Log.d("gaika-ai", DebugManager.getInstance().stateInfo2);

        boxs = new HashMap<>();

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (nodes[i][j].costFromStart < F1000_STEP) {
                    ColorBox box = new ColorBox(i, j, ID.BOX_COLOR.GREEN);
                    box.costFromStart = nodes[i][j].costFromStart;
                    box.weapon = nodes[i][j].weapon;
                    boxs.put(box.id, box);
                }
            }
        }

        //----------------------------
        return boxs;

    }

    /*
    *区别于 getGreenBoxsLong，该函数，pWeaponTypeEx即使是地面单位，或者海面单位，也能保证计算全地图，
    * 切不考虑是否有Weapon占用地图节点位置
    * 其中 海面PathStep复制3or4？  F1000_STEP = 3.0f
    * */


    public Map<Integer, ColorBox> getGreenBoxsAllMap(IMapPoint pMapPoint, ID.WEAPON_TYPE_EX pWeaponTypeEx/*, ID.TEAM_COLOR pTeamColor*//*, float pMaxMove2*/) {
//        Map<Integer, MapNode> openNodes = new HashMap<>();
        LinkedHashMap<Integer, MapNode> openNodes = new LinkedHashMap<>();

        MapNode startMapNode = getMapNodeFromXY(pMapPoint);
        startMapNode.costFromStart = 0;
//        startMapNode.isPath = true;

        openNodes.put(startMapNode.x * 100 + startMapNode.y, startMapNode);

//        float maxMove = pWeapNode.move;
//        ID.WEAPON_TYPE_EX typeEx = pWeaponTypeEx;
//        ID.TEAM_COLOR tesmcolor = pTeamColor;
//        Long t1 = System.currentTimeMillis();
        int countStep = 0;
        while (openNodes.isEmpty() == false) {
            countStep++;

            MapNode node = openNodes.values().iterator().next();
            openNodes.remove(node.x * 100 + node.y);

            for (int i = 0; i < 6; i++) {
                MapNode newNode = node.neighbours[i];
                if (newNode == null /*|| newNode.isCanMovePathEx3(pTeamColor, pWeaponTypeEx) == false*/) {
                    continue;
                }
                float newCost = node.costFromStart + newNode.getGroundStepEx3(pWeaponTypeEx);
                if (newCost >= newNode.costFromStart /*|| newCost > pMaxMove*/) {
                    continue;
                }

                newNode.costFromStart = newCost;
//                newNode.isPath = true;
                openNodes.put(newNode.x * 100 + newNode.y, newNode);
            }
        }
//        Long t2 = System.currentTimeMillis();
//        Long t3 = t2 - t1;
//        DebugManager.getInstance().stateInfo2 = "\n" + /*"           maxMove = " + pMaxMove2 + "\n" + */ "          T3(ms) = " + t3 + "\n" + "           countStep = " + countStep;
//        Log.d("gaika", DebugManager.getInstance().stateInfo2);

        boxs = new HashMap<>();

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                ColorBox box = new ColorBox(i, j, ID.BOX_COLOR.GREEN);
                box.costFromStart = nodes[i][j].costFromStart;
                box.weapon = nodes[i][j].weapon;
                boxs.put(box.id, box);
//                }
            }
        }

        //----------------------------
        return boxs;

    }

    public List<MapNode> getGreenBoxPath(IMapPoint p1, IMapPoint p2) {

        List<MapNode> retList = new ArrayList<MapNode>();

        MapNode mapNode = nodes[p2.getMapX()][p2.getMapY()];//  getMapNodeFromXY(new Pair(p2.getMapX(), p2.getMapY()));

        retList.add(mapNode);

        int count = 0;
        while (!(mapNode.equalsMapPoint(p1))) {
            mapNode = mapNode.getMiniPathNeighbour(p1);
            retList.add(mapNode);
            count++;
            if (count > 100) {
                count = 0;
            }
        }


        List<MapNode> retList2 = new ArrayList<MapNode>();

        for (int i = 0; i < retList.size(); i++) {
            retList2.add(retList.get(retList.size() - i - 1));
        }

        return retList2;
    }
}
