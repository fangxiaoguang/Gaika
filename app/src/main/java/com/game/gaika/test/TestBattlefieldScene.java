package com.game.gaika.test;

import com.game.gaika.data.GameDataManager;
import com.game.gaika.data.GameMap;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.SaveManager;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.gaika.data.weapon.WeaponFactory;
import com.game.gaika.scene.BattlefieldScene;
import com.game.gaika.scene.DiplomacyScene;
import com.game.gaika.scene.Logo1Scene;
import com.game.gaika.scene.SceneManager;
import com.game.gaika.scene.SelectTargetScene;
import com.game.gaika.scene.TeamBuildScene;
import com.game.gaika.scene.WeaponBuyScene;

/**
 * Created by fangxg on 2015/7/24.
 */

/*id	name
0	M60A3
1	M60A3
2	M1A1
3	M1A1
4	T-72
5	T-80
6	豹式
7	豹2
8	百夫长
9	挑战者
10	74式
11	90式
12	小橡树
13	小橡树
14	复仇者
15	LAV-AD
16	SA-13
17	2S6
18	Gepard
19	轻剑2000
20	81式
21	87式
22	M113
23	M113
24	M2
25	AAV7
26	LAV-25
27	BMP-2
28	BMP-3
29	黄鼠狼
30	FV432
31	武士
32	96式
33	89式
34	UH-1
35	UH-1
36	UH-1
37	UH-60
38	UH-60
39	CH-53
40	V-22
41	Mi-8
42	Mi-17
43	山猫Mk1
44	AH-1
45	AH-1
46	AH-1
47	AH-64
48	AH-64
49	AH-64
50	AH-1W
51	Mi-24
52	Ka-50
53	PAH-1
54	PAH-2
55	山猫Mk9
56	F-15
57	F-15
58	F-22
59	F-16
60	F-4
61	F-4
62	F-4
63	F-14
64	F-18
65	Su-27
66	Su-37
67	EF2000
68	EF2000
69	狂风ADV
70	F-111
71	F-117
72	A-6
73	AV-8
74	AV-8
75	Su-24
76	Su-34
77	阿尔发
78	狂风IDS
79	狂风IDS
80	美洲虎
81	F-1
82	F-2
83	RAH-66
84	BRDM2
85	勒克斯
86	OH-1
87	87式
88	霍克
89	霍克
90	霍克
91	霍克
92	爱国者
93	爱国者
94	SA-6
95	S-300
96	M109
97	M109
98	M109
99	M109A6
100	BM-21
101	龙卷风
102	PzH2000
103	AS90
104	75式
105	运输舰
106	运输舰
107	尼米兹
108	库兹涅佐夫
109	无敌级
110	宙斯盾
111	现代级
112	曼彻斯特
113	金刚级
114	海狼
115	阿库拉级
116	212型
117	特拉法尔加级
118	亲潮级
119	登陆舰
120	登陆舰
121	登陆舰
122	登陆舰
123	2S3
124	MLRS
125	MLRS
126	MLRS
127	MLRS
128	ADATS
129	F-35
130	爱德华
131	DD-21
132	美国步兵
133	美海步兵
134	俄国步兵
135	德国步兵
136	英国步兵
137	日本步兵
138	A-10
139	M247约克中士
140	大和
141	F-3
142	Yak-141
143	黄鼠狼2
144	什么玩意
145	运输机
146	梅卡瓦2
147	班德卡农
148	B-2
149	MiG-31
150	NH90
151	Su-25
152	S型
153	阿奇扎里特
154	奥托
155	半人马座
156	Tu-160
157	捷豹
158	狮式
160	M1A1HA
161	梅卡瓦3
162	T-72B
163	豹2A5
164	挑战者2
165	74式改
166	狮2
167	什么玩意B
168	F-3改
170	F-15
171	F-15
172	F-22
173	F-16
174	F-4
175	F-4
176	F-4
177	F-18
178	Su-27
179	Su-37
180	EF2000
181	EF2000
182	F-2
183	F-3
184	F-3改
*/
public class TestBattlefieldScene {
    public static void test_1() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);
        SaveManager.enterChapter(101);
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);
    }

    public static void test_2() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);


        BaseWeapon w1 = gdm.weapons.get(6);
        w1.x = 15;
        w1.y = 28;
        w1.setOut = true;
        w1.moveEnding = false;

        BaseWeapon w2 = gdm.weapons.get(7);
        w2.x = 16;
        w2.y = 29;
        w2.setOut = true;
        w2.moveEnding = false;
        w2.teamColor = ID.TEAM_COLOR.RED;

        BaseWeapon w3 = gdm.weapons.get(8);
        w3.x = 14;
        w3.y = 29;
        w3.setOut = true;
        w3.moveEnding = false;

        BaseWeapon w4 = gdm.weapons.get(9);
        w4.x = 16;
        w4.y = 28;
        w4.setOut = true;
        w4.moveEnding = false;
        w4.teamColor = ID.TEAM_COLOR.RED;

        BaseWeapon w5 = gdm.weapons.get(10);
        w5.x = 16;
        w5.y = 30;
        w5.setOut = true;
        w5.moveEnding = false;
        w5.teamColor = ID.TEAM_COLOR.RED;


        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);
    }

    /*
    * 测试  战斗机可选择武器
    * */
    public static void test_4() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();


        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(56));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 13;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(56));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 13;
        w.y = 29;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(56));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 16;
        w.y = 28;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 14;
        w.y = 29;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 12;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);

    }

    public static void test_3() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();


        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 15;
        w.y = 28;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 13;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 16;
        w.y = 28;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 14;
        w.y = 29;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 12;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);

    }

    /*
    * 测试运输机
    * */
    public static void test_5() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();


        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(145));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 13;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(145));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 13;
        w.y = 29;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 16;
        w.y = 28;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 14;
        w.y = 29;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 12;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(56));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 16;
        w.y = 30;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);

    }

    /*
    * 测试交战后，单位升级—— LV_UP
    * */
    public static void test_6() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();


        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(47));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 15;
        w.y = 28;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 16;
        w.y = 28;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 14;
        w.y = 29;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 12;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);

    }

    /*
    * 测试交战后，或者结束行动  后  占领城市
    * */
    public static void test_7() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();


        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(37));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 13;
        w.y = 22;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(37));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 13;
        w.y = 24;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(37));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 15;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 14;
        w.y = 23;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);
    }

    /*
        * 测试交战后，或者结束行动   占领城市后   STATE_ID_GET_SOME_THING  1,2,3,4   得到兵器  得到物资  得到金钱
        * */
    public static void test_7_2() {

        GameSetup.isDebug_delay_to_touch = false;

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();


        gdm.getCurrentChapter().getGameMap().citys.get(1322).getWeaponInfoId = 1;  //得到兵器  M60A1
        gdm.getCurrentChapter().getGameMap().citys.get(719).getSupply = 256;  // 得到物资   256
        gdm.getCurrentChapter().getGameMap().citys.get(914).getMoney = 1024; // 得到金钱    1024

        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(37));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 10;
        w.y = 22;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 5;
        w.y = 19;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(37));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 8;
        w.y = 10;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 14;
        w.y = 23;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);
    }

    /*
      * 测试交战后，或者结束行动   占领城市后   战斗胜利
      * */
    public static void test_7_3() {

//        GameSetup.isDebug_delay_to_touch = false;

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();


        gdm.getCurrentChapter().getGameMap().citys.get(2403).capture = 50;  //敌方  处点

        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(37));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 23;
        w.y = 1;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 5;
        w.y = 19;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(47)); //Ah-64
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 14;
        w.y = 20;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 14;
        w.y = 23;
        w.life = 120;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 14;
        w.y = 24;
        w.life = 120;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);
    }

    /*
      * 测试交战后，或者结束行动   占领城市后   战斗失败
      * */
    public static void test_7_4() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();


        gdm.getCurrentChapter().getGameMap().citys.get(1322).getWeaponInfoId = 1;  //得到兵器  M60A1
        gdm.getCurrentChapter().getGameMap().citys.get(719).getSupply = 256;  // 得到物资   256
        gdm.getCurrentChapter().getGameMap().citys.get(914).getMoney = 1024; // 得到金钱    1024

        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(37));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 10;
        w.y = 22;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 5;
        w.y = 19;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(37));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 8;
        w.y = 10;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 14;
        w.y = 23;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);
    }

    /*
   * 最简单的Scene  例如  Logo1Scene
   * */
    public static void test_8() {


        Logo1Scene s = new Logo1Scene();
        SceneManager.render(s);
    }

    /*
   * 测试  寻径函数性能
   * 1#  670-710 ms  23398
   * 2#
   * 3#
   * 4#
   * 5#
   *
   * */
    public static void test_9() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();


        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(56));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 16;
        w.y = 16;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(37));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 13;
        w.y = 24;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(37));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 15;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 14;
        w.y = 23;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);
    }

    /**
     * 测试  飞机 无燃油的情况下坠毁
     */
    public static void test_10() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();


        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(59)); //F-16
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 11;
        w.y = 22;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(59)); //F-16
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 11;
        w.y = 23;
        w.fuel = 2;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(59)); //F-16
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 11;
        w.y = 24;
        w.fuel = 2;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = true;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(59)); //F-16
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 5;
        w.y = 29;
        w.fuel = 2;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(132));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 15;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);
    }

    /**
     * 测试  AI  占领AI
     */
    public static void test_100() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.reInitChapters();

        SaveManager.enterChapter(402);

        gdm.weapons.clear();


        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(22));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 13;
        w.y = 24;
        w.teamOut = true;
//        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(132));
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 14;
        w.y = 27;
        w.teamOut = true;
//        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 14;
        w.y = 23;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        s.getLogicCamera().setCente(w.getPixelX(), w.getPixelY());
        SceneManager.render(s);
    }

    /**
     * 测试  AI  攻击AI
     */
    public static void test_101() {

    }

    /**
     * 测试  AI    修理  补充单位    一个单位坦克  一个单位飞机  一个单位坦克    supply仅够修理头两个单位
     */
    public static void test_11() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美


        SaveManager.enterChapter(101);

        gdm.weapons.clear();

        gdm.setSupply(ID.TEAM_COLOR.RED, 252 + 48 + 10);
        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(133)); //133	美海步兵
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 14;
        w.y = 23;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(38));//38	UH-60
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 19;
        w.y = 22;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));//M1A1
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 27;
        w.y = 6;
        w.life = 210;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);
    }

    /*
    * 测试 save and load   包括  运输机  功能
    * */
    public static void test_12() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();


        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(145));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 13;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(145));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 13;
        w.y = 29;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 16;
        w.y = 28;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 14;
        w.y = 29;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 12;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(56));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 16;
        w.y = 30;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);

    }

    /*
    * 测试 HUD 显示
    *
    * */
    public static void test_13() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.JAPAN;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();

//        gdm.gameTimer = new GameTimer(30, 6);//傍晚
//        gdm.gameTimer = new GameTimer(30, 7);//夜晚
//        gdm.gameTimer = new GameTimer(30, 8);//夜晚

        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(145));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 13;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(145));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 13;
        w.y = 29;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 16;
        w.y = 28;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 14;
        w.y = 29;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 12;
        w.y = 27;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(56));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 16;
        w.y = 30;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);

    }

    /*
   * 测试  路径 内存溢出
   *
   * */
    public static void test_14() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.RUSSIA;//美海
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();

//        gdm.gameTimer = new GameTimer(30, 6);//傍晚
//        gdm.gameTimer = new GameTimer(30, 7);//夜晚
//        gdm.gameTimer = new GameTimer(30, 8);//夜晚

        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(16));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 25;
        w.y = 3;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        SceneManager.render(s);

    }

    /*
  * 测试  SelectTargetScene
  *
  * */
    public static void test_15() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.RUSSIA;//俄国
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();

        gdm.setCurrentChapter(101);
        gdm.chapters.get(101).finished = true;
//        gdm.chapters.get(102).finished = true;
//        gdm.chapters.get(103).finished = true;
//        gdm.chapters.get(104).finished = true;
//        gdm.chapters.get(105).finished = true;
//        gdm.chapters.get(106).finished = true;
//        gdm.chapters.get(107).finished = true;

        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(16));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 25;
        w.y = 3;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        gdm.getCurrentChapter().getGameMap();
        SelectTargetScene s = new SelectTargetScene(true);
        SceneManager.render(s);

    }

    /*
* 测试  TeamBuildScene
*
* */
    public static void test_16() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.RUSSIA;//俄国
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();

        gdm.setCurrentChapter(101);
        gdm.chapters.get(101).finished = true;
//        gdm.chapters.get(102).finished = true;
//        gdm.chapters.get(103).finished = true;
//        gdm.chapters.get(104).finished = true;
//        gdm.chapters.get(105).finished = true;
//        gdm.chapters.get(106).finished = true;
//        gdm.chapters.get(107).finished = true;

        int id = 0;
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(16));
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 25;
        w.y = 3;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        gdm.getCurrentChapter().getGameMap();
        TeamBuildScene s = new TeamBuildScene(true);
        SceneManager.render(s);

    }

    /*
* 测试  WeaponBuyScene
*
* */
    public static void test_17() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.RUSSIA;//俄国
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();

        gdm.setCurrentChapter(101);
        gdm.chapters.get(101).finished = true;

        gdm.money = 2000000;
        gdm.setDiplomacy(ID.COUNTRY.USA, 6);
        gdm.setDiplomacy(ID.COUNTRY.USN, 6);
        gdm.setDiplomacy(ID.COUNTRY.RUSSIA, 3);
        gdm.setDiplomacy(ID.COUNTRY.GERMANY, 6);

        int id = 0;

        for (int i = 0; i < 5; i++) {
            BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(16));
            w.teamColor = ID.TEAM_COLOR.BLUE;
            w.x = 25;
            w.y = 3;
            w.teamOut = true;
            w.setOut = true;
            w.moveEnding = false;
            w.id = id++;
            gdm.weapons.put(w.id, w);
        }
        for (int i = 0; i < 5; i++) {
            BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
            w.teamColor = ID.TEAM_COLOR.BLUE;
            w.x = 25;
            w.y = 3;
            w.teamOut = true;
            w.setOut = true;
            w.moveEnding = false;
            w.id = id++;
            gdm.weapons.put(w.id, w);
        }
        for (int i = 0; i < 10; i++) {
            BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(7));
            w.teamColor = ID.TEAM_COLOR.BLUE;
            w.x = 25;
            w.y = 3;
            w.teamOut = true;
            w.setOut = true;
            w.moveEnding = false;
            w.life = 0;
            w.id = id++;
            gdm.weapons.put(w.id, w);
        }

        gdm.getCurrentChapter().getGameMap();
        WeaponBuyScene s = new WeaponBuyScene(true);
        SceneManager.render(s);

    }

    /*
* 测试  WeaponBuyScene
*
* */
    public static void test_18() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.RUSSIA;//俄国
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.enterChapter(101);

        gdm.weapons.clear();

        gdm.setCurrentChapter(101);
        gdm.chapters.get(101).finished = true;

        gdm.money = 2000000;
        gdm.setDiplomacy(ID.COUNTRY.USA, 6);
        gdm.setDiplomacy(ID.COUNTRY.USN, 6);
        gdm.setDiplomacy(ID.COUNTRY.RUSSIA, 3);
        gdm.setDiplomacy(ID.COUNTRY.GERMANY, 6);

        int id = 0;

        for (int i = 0; i < 5; i++) {
            BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(16));
            w.teamColor = ID.TEAM_COLOR.BLUE;
            w.x = 25;
            w.y = 3;
            w.teamOut = true;
            w.setOut = true;
            w.moveEnding = false;
            w.id = id++;
            gdm.weapons.put(w.id, w);
        }
        for (int i = 0; i < 5; i++) {
            BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2));
            w.teamColor = ID.TEAM_COLOR.BLUE;
            w.x = 25;
            w.y = 3;
            w.teamOut = true;
            w.setOut = true;
            w.moveEnding = false;
            w.id = id++;
            gdm.weapons.put(w.id, w);
        }
        for (int i = 0; i < 10; i++) {
            BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(7));
            w.teamColor = ID.TEAM_COLOR.BLUE;
            w.x = 25;
            w.y = 3;
            w.teamOut = true;
            w.setOut = true;
            w.moveEnding = false;
            w.life = 0;
            w.id = id++;
            gdm.weapons.put(w.id, w);
        }

        gdm.getCurrentChapter().getGameMap();
        DiplomacyScene s = new DiplomacyScene(true);
        SceneManager.render(s);

    }

    /*
* 测试   全地图寻径
*
* */
    public static void test_19() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.RUSSIA;//俄国
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.reInitChapters();

        SaveManager.enterChapter(101);

        gdm.weapons.clear();


        gdm.money = 2000000;
        gdm.setDiplomacy(ID.COUNTRY.USA, 6);
        gdm.setDiplomacy(ID.COUNTRY.USN, 6);
        gdm.setDiplomacy(ID.COUNTRY.RUSSIA, 3);
        gdm.setDiplomacy(ID.COUNTRY.GERMANY, 6);

        int id = 0;

        // for (int i = 0; i < 5; i++) {
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(134)); //16  56
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 24;
        w.y = 4;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        w.fuel = 1;
        w.move = 250;
        gdm.weapons.put(w.id, w);
//
//
//        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2)); //16  56
//        w.teamColor = ID.TEAM_COLOR.RED;
//        w.x = 20;
//        w.y = 24;
//        w.teamOut = true;
//        w.setOut = true;
//        w.moveEnding = false;
//        w.id = id++;
//
//        gdm.weapons.put(w.id, w);w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2)); //16  56
//        w.teamColor = ID.TEAM_COLOR.RED;
//        w.x = 22;
//        w.y = 23;
//        w.teamOut = true;
//        w.setOut = true;
//        w.moveEnding = false;
//        w.id = id++;
//        gdm.weapons.put(w.id, w);
//

        GameMap map = gdm.getCurrentChapter().getGameMap();

        //test 1
//        for (int y = 20; y < 27; y++) {
//            map.nodes[18][y].type = ID.MAP_NODE_TYPE.HAI_HU;
//            map.nodes[24][y].type = ID.MAP_NODE_TYPE.HAI_HU;
//            map.nodes[18][y].groundStep= 1000;
//            map.nodes[24][y].groundStep= 1000;
//        }
//        for (int x = 18; x < 25; x++) {
//            map.nodes[x][20].type = ID.MAP_NODE_TYPE.HAI_HU;
//            map.nodes[x][26].type = ID.MAP_NODE_TYPE.HAI_HU;
//            map.nodes[x][20].groundStep= 1000;
//            map.nodes[x][26].groundStep= 1000;
//        }




        // w.doSelect();

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        s.getLogicCamera().setCente(w.getPixelX(), w.getPixelY());
        SceneManager.render(s);

    }
    /*
* 测试   远程攻击武器  PzH2000 VS M1A1    PzH2000（遠程1-5，移動後可攻擊）
*
* */
    public static void test_20() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.RUSSIA;//俄国
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.reInitChapters();

        SaveManager.enterChapter(301);

        gdm.weapons.clear();


        gdm.money = 2000000;
        gdm.setDiplomacy(ID.COUNTRY.USA, 6);
        gdm.setDiplomacy(ID.COUNTRY.USN, 6);
        gdm.setDiplomacy(ID.COUNTRY.RUSSIA, 3);
        gdm.setDiplomacy(ID.COUNTRY.GERMANY, 6);

        int id = 0;

        // for (int i = 0; i < 5; i++) {
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(2)); //M1A1
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 24;
        w.y = 10;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(102)); //	PzH2000
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 24;
        w.y = 25;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        GameMap map = gdm.getCurrentChapter().getGameMap();

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        s.getLogicCamera().setCente(w.getPixelX(), w.getPixelY());
        SceneManager.render(s);

    }
    /*
* 测试   远程攻击武器  S-300 VS AH-64    S-300（遠程1-7，移動後不可攻擊）
*
* */
    public static void test_21() {

        GameDataManager gdm = GameDataManager.getInstance();
        gdm.gameBeginDiff = ID.GAME_DIFF.EASE;
        gdm.gameBeginLocal = ID.COUNTRY.RUSSIA;//俄国
        gdm.gameBeginCounty = ID.COUNTRY.USA;//美

        SaveManager.reInitChapters();

        SaveManager.enterChapter(301);

        gdm.weapons.clear();


        gdm.money = 2000000;
        gdm.setDiplomacy(ID.COUNTRY.USA, 6);
        gdm.setDiplomacy(ID.COUNTRY.USN, 6);
        gdm.setDiplomacy(ID.COUNTRY.RUSSIA, 3);
        gdm.setDiplomacy(ID.COUNTRY.GERMANY, 6);

        int id = 0;

        // for (int i = 0; i < 5; i++) {
        BaseWeapon w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(47)); //47	AH-64
        w.teamColor = ID.TEAM_COLOR.BLUE;
        w.x = 24;
        w.y = 10;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);


        w = WeaponFactory.getInstance().newWeapon(gdm.weapInfos.get(95)); //	95	S-300
        w.teamColor = ID.TEAM_COLOR.RED;
        w.x = 22;
        w.y = 19;
//        w.x = 22;
//        w.y = 16;
        w.teamOut = true;
        w.setOut = true;
        w.moveEnding = false;
        w.id = id++;
        gdm.weapons.put(w.id, w);

        GameMap map = gdm.getCurrentChapter().getGameMap();

        gdm.getCurrentChapter().getGameMap();
        BattlefieldScene s = new BattlefieldScene(true);
        s.getLogicCamera().setCente(w.getPixelX(), w.getPixelY());
        SceneManager.render(s);

    }
}
