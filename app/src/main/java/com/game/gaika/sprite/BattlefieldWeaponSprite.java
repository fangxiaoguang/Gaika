package com.game.gaika.sprite;


import com.game.frame.sprite.BaseSprite;
import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.MapNode;
import com.game.gaika.data.Pair;
import com.game.gaika.data.weapon.BaseWeapon;
import com.game.frame.flyweight.AnimeFlyweight;
import com.game.frame.flyweight.NormalFlyweight;
import com.game.frame.flyweight.TextFlyweight;
import com.game.frame.texture.TexRegionManager;

import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;

import java.util.List;

/**
 * Created by fangxg on 2015/7/22.
 */
public class BattlefieldWeaponSprite extends BaseSprite {
    public BattlefieldWeaponSprite(BaseWeapon pWeapon, TouchMessage pTouchMessage) {
        super(pWeapon.getPixelX(), pWeapon.getPixelY());

        NormalFlyweight bkFlyweight = new NormalFlyweight(0.0f, 0.0f, "invisible50_48");

        int untilIndex = pWeapon.info.texIndex * 6;
        if (pWeapon.teamColor == ID.TEAM_COLOR.BLUE) {
            if (pWeapon.moveEnding == false) {
                untilIndex += 0;
            } else {
                untilIndex += 1;
            }
        } else if (pWeapon.teamColor == ID.TEAM_COLOR.RED) {
            if (pWeapon.moveEnding == false) {
                untilIndex += 2;
            } else {
                untilIndex += 5;
            }
        } else if (pWeapon.teamColor == ID.TEAM_COLOR.YELLOW) {
            if (pWeapon.moveEnding == false) {
                untilIndex += 3;
            } else {
                untilIndex += 5;
            }
        } else if (pWeapon.teamColor == ID.TEAM_COLOR.GREEN) {
            if (pWeapon.moveEnding == false) {
                untilIndex += 4;
            } else {
                untilIndex += 5;
            }
        }



        NormalFlyweight untiFlyweight = new NormalFlyweight(0, 0, "unit01", untilIndex);
        bkFlyweight.addChildFlyweight(untiFlyweight);

        NormalFlyweight flagFlyweight = new NormalFlyweight(0, -5, "flag01", pWeapon.info.country.ordinal());
        bkFlyweight.addChildFlyweight(flagFlyweight);

        NormalFlyweight lifeExFlyweight = new NormalFlyweight(18, -5, "font01", pWeapon.getLifeEx());
        bkFlyweight.addChildFlyweight(lifeExFlyweight);

        if (pWeapon.path != null && pWeapon.path.size() >= 2) {

            List<MapNode> pathList = pWeapon.path;

            PathModifier.Path p = new PathModifier.Path(pathList.size());
            for (MapNode mapNode : pathList) {
                p.to(mapNode.getPixelX(), mapNode.getPixelY());
            }

            if (pWeapon.transporter == null) {
                this.registerEntityModifier(new PathModifier(GameSetup.DELAY_PATH_S * p.getSize(), p));
            } else {
                PathModifier.Path p2 = new PathModifier.Path(pathList.size());
                p2.to(-100, -100);
//                p2.to(-100, -100);
                this.registerEntityModifier(
                    new SequenceEntityModifier(
                            new PathModifier(GameSetup.DELAY_PATH_S * p.getSize(), p), new PathModifier(0.0f, p2)
                     )
                );


//                this.registerEntityModifier(new ParallelEntityModifier(new PathModifier(GameSetup.DELAY_PATH_S * p.getSize(),p), new AlphaModifier(2.1f, 1.0f, 0.0f)));
//                        new SequenceEntityModifier(/*new DelayModifier(GameSetup.DELAY_PATH_S * p.getSize()),*/ new AlphaModifier(0.1f, 1.0f, 0.0f))));
            }
        }

        pWeapon.path = null;

        if (pWeapon.selected == true) {
            AnimeFlyweight markFlyweight = new AnimeFlyweight(-15, -16, "marker05", 0, 5, 100);
            bkFlyweight.addChildFlyweight(markFlyweight);
        }


        if (pWeapon.advantage != ID.ADVANTAGE.NON) {

            NormalFlyweight advFlyweight = new NormalFlyweight(0, 0, "marker02", pWeapon.advantage.ordinal());
            bkFlyweight.addChildFlyweight(advFlyweight);
        }

        Pair<Integer> texIndexs = pWeapon.getTransportFlagTexIndex();
        if (texIndexs != null) {
            AnimeFlyweight marker01Flyweight = new AnimeFlyweight(0.0f, 0.0f, "marker01", texIndexs.getX(), texIndexs.getY(), 300);
            bkFlyweight.addChildFlyweight(marker01Flyweight);
        }
/*

		if (status == ID.STATUS_BLUE_NONE) {
			if (moveEnding == false && teamColor == ID.COLOR_BLUE) {
				BaseRender.addTouchArea(bkScne, weapSprite, ID.BID_BATTLEFIELD_SELECT_BLUE_WEAPON, id, true);
			}
			if (moveEnding == true && teamColor == ID.COLOR_BLUE) {
				if (this.canTransportFromMapNode() == true && this.getPassengers().size() > 0) {
					BaseRender.addTouchArea(bkScne, weapSprite, ID.BID_BATTLEFIELD_SELECT_BLUE_WEAPON, id, true);
				}
			}
		} else if (status == ID.STATUS_BULE_SELECTED) {

			if (selected == true && teamColor == ID.COLOR_BLUE) {
				BaseRender.addTouchArea(bkScne, weapSprite, ID.BID_BATTLEFIELD_SELECT_BLUE_WEAPON, id, true);
			}

			if (gd.getCurrentChapter().side2.contains(teamColor)) {
				BaseRender.addTouchArea(bkScne, weapSprite, ID.BID_BATTLEFIELD_WEAP_SIDE_2, id, true);
			}
		} else if (status == ID.STATUS_BULE_MOVEED) {
			if (selected == true && teamColor == ID.COLOR_BLUE) {
				BaseRender.addTouchArea(bkScne, getPixelX(), getPixelY(), weapSprite.getWidth(), weapSprite.getHeight(), ID.BID_BATTLEFIELD_SELECT_BLUE_WEAPON,
						id, true);
			}

			if (gd.getCurrentChapter().side2.contains(teamColor)) {
				BaseRender.addTouchArea(bkScne, weapSprite, ID.BID_BATTLEFIELD_WEAP_SIDE_2, id, true);
			}
		}
		*/
        if(GameSetup.isDebug_Weapon_id == true){
            TextFlyweight idFlyweight = new TextFlyweight(30.0f, 0.0f, true, "=" + pWeapon.id, TexRegionManager.getInstance().getFont12());
            bkFlyweight.addChildFlyweight(idFlyweight);
        }

        setFlyweight(bkFlyweight);


        setTouchMessage(pTouchMessage);


    }
}
