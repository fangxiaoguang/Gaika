package com.game.gaika.data;

//import com.game.gaika.action.StatusMachine;
//import com.game.gaika.graph.TexRegionManager;
//import com.game.gaika.id.ID;


import com.game.gaika.data.weapon.BaseWeapon;

public class ColorBox extends IMapPoint {

	public int id;
	public int x;
	public int y;
	public float costFromStart;
	public BaseWeapon weapon;
	public ID.BOX_COLOR color;

	public ColorBox(int pX, int pY, ID.BOX_COLOR pColor) {
		id = pX * 100 + pY;
		x = pX;
		y = pY;
		color = pColor;
	}

//	public int getPixelX() {
//		return x * 50;
//	}
//
//	public int getPixelY() {
//		if (x % 2 == 0) {
//			return y * 48 + 24;
//		} else {
//			return y * 48;
//		}
//	}

	@Override
	public String toString() {
		return "   X = " + x + "  Y = " + y;
	}

/*
	private static float mLastX;
	private static float mLastY;
	private static float distanceX;
	private static float distanceY;

	public void showAndRegTouchArea() {
		GameDataManager gdm = GameDataManager.getInstance();
		int status = StatusMachine.getInstance().status;

		int colorIndex = 0;

		if (color == ID.BOX_COLOR.GREEN) {
			colorIndex = 0;
		} else if (color == ID.BOX_COLOR.RED) {
			colorIndex = 1;
		} else {
			throw  new IllegalArgumentException("ColorBox color is not support    color:" + color)
		}

		if (status == ID.STATUS_BULE_SELECTED) {

			//transportPlane  
			BaseWeapon weap = gd.getWeaponNodeFromSetoutAndLife(this, ID.COLOR_BLUE);
			BaseWeapon selWeap = gd.getSelectedWeapNode(ID.COLOR_BLUE);
			
			if (weap == null || (weap != null && weap.canTransport(selWeap))) {

				TiledSprite splash = new TiledSprite(getPixelX(), getPixelY(), TexRegionManager.getInstance().getTexRegion("greenBox").deepCopy(),
						MainActivity.mGameActiviy.getVertexBufferObjectManager()) {
					@Override
					public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {

						boolean ret = false;

						switch (pAreaTouchEvent.getAction()) {
						case TouchEvent.ACTION_DOWN:

							mLastX = pAreaTouchEvent.getX();
							mLastY = pAreaTouchEvent.getY();

							distanceX = 0;
							distanceY = 0;

							break;
						case TouchEvent.ACTION_MOVE:
							float tempDistanceX = pAreaTouchEvent.getX() - mLastX;
							float tempDistanceY = pAreaTouchEvent.getY() - mLastY;

							if (Math.abs(tempDistanceX) > distanceX) {
								distanceX = Math.abs(tempDistanceX);
							}
							if (Math.abs(tempDistanceY) > distanceY) {
								distanceY = Math.abs(tempDistanceY);
							}
							// onInput(butonID, pParam);

							break;
						case TouchEvent.ACTION_UP:
							tempDistanceX = pAreaTouchEvent.getX() - mLastX;
							tempDistanceY = pAreaTouchEvent.getY() - mLastY;

							if (Math.abs(tempDistanceX) > distanceX) {
								distanceX = Math.abs(tempDistanceX);
							}
							if (Math.abs(tempDistanceY) > distanceY) {
								distanceY = Math.abs(tempDistanceY);
							}

							if (distanceX < 10 && distanceY < 10) {
								ret = true;
								SceneManager.getTopLogicScene().onInput(ID.BID_BATTLEFIELD_BOX, x * 100 + y);
							}

							break;
						}
						return ret;
					}
				};

				if(gd.getMapNodeFromXY(this).type == ID.QIAN_TAN){
					splash.setCurrentTileIndex(2);
				}else{
					splash.setCurrentTileIndex(colorIndex);	
				}
				
				SceneManager.getTopLogicScene().bkScne.getChildByIndex(ID.S1_LAYER_GREENBOX).attachChild(splash);
				SceneManager.getTopLogicScene().bkScne.registerTouchArea(splash);

			}

		} else {
//			TiledSprite boxSprite = BaseRender.getShape(getPixelX(), getPixelY(), "greenBox", colorIndex);
//			SceneManager.getTopLogicScene().bkScne.getChildByIndex(ID.S1_LAYER_GREENBOX).attachChild(boxSprite);
		}

	}
*/

	@Override
	public int getMapX() {
		return this.x;
	}

	@Override
	public int getMapY() {
		return this.y;
	}

}
