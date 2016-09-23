package com.game.gaika.data;

public class EffectNode extends IMapPoint {
	public enum EEffectID{EFFECT_ID_MARKER04}

	public int x;
	public int y;
	
	public EEffectID  id;

	public float delayS;

	public EffectNode(int pMapX, int pMapY, EEffectID pId) {
		this(pMapX, pMapY, pId, -1.0f);
	}

	public EffectNode(int pMapX, int pMapY, EEffectID pId, float pDelayS) {
		this.x = pMapX;
		this.y = pMapY;

		this.id = pId;
		delayS =pDelayS;
	}
//	public int getPixelX(){
//		return x * 50;	
//	}
//	public int getPixelY(){
//		if(x % 2 == 0){
//			return y * 48 + 24;
//		}else{
//			return y * 48;
//		}		
//	}
	

	@Override
	public int getMapX() {
		return this.x;
	}

	@Override
	public int getMapY() {
		return this.y;
	}

}
