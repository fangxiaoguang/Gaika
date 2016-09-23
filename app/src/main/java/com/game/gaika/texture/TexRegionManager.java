package com.game.gaika.texture;

import java.util.HashMap;
import java.util.Map;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.color.Color;
import org.andengine.util.modifier.IModifier;

import com.game.gaika.FSM.TouchMessage;
import com.game.gaika.db.DatabaseManager;
import com.game.gaika.main.MainActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.util.Log;

public class TexRegionManager {
    private static boolean LAZY_LOAD = true;

    private Map<String, TiledTextureRegionNode> texRegs;
    private Font font32;
    private Font font16;
    private Font font16Grey;
    private Font font12;
    private Font font12Stroke;

    public void init() {

        texRegs = new HashMap<String, TiledTextureRegionNode>();

        try {

            /*GaikaDBOpenHelper dbOpenHelperData = new GaikaDBOpenHelper(MainActivity.mGameActiviy.getBaseContext(), "data.db", null, 1);

            SQLiteDatabase dbData = dbOpenHelperData.getReadableDatabase();*/
            SQLiteDatabase dbData = DatabaseManager.getSqLiteDatabase("data.db");

            Cursor cursor = dbData.query("TEXTURE", new String[]{"name", "sizex", "sizey", "column", "row"}, null, null, null, null, null);

            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {

                // Log.d("Gaika", "name: " +
                // cursor.getString(cursor.getColumnIndex("name")));

                String name = cursor.getString(cursor.getColumnIndex("name"));
                int sizex = cursor.getInt(cursor.getColumnIndex("sizex"));
                int sizey = cursor.getInt(cursor.getColumnIndex("sizey"));
                int column = cursor.getInt(cursor.getColumnIndex("column"));
                int row = cursor.getInt(cursor.getColumnIndex("row"));

                TiledTextureRegionNode node = new TiledTextureRegionNode(name, sizex, sizey, column, row);

                this.texRegs.put(name, node);

                cursor.moveToNext();
            }
            cursor.close();
            dbData.close();

        } catch (Exception e) {
            Log.e("Gaika", e.getMessage());
        }


        font32 = FontFactory.create(MainActivity.mGameActiviy.getFontManager(), MainActivity.mGameActiviy.getTextureManager(), 1024, 1024,
                Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), 24, Color.WHITE_ARGB_PACKED_INT);

        font32.load();
        font16 = FontFactory.create(MainActivity.mGameActiviy.getFontManager(), MainActivity.mGameActiviy.getTextureManager(), 512, 512,
                Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 16, Color.WHITE_ARGB_PACKED_INT);
        font16.load();
        font16Grey = FontFactory.create(MainActivity.mGameActiviy.getFontManager(), MainActivity.mGameActiviy.getTextureManager(), 512, 512,
                Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 16, new Color(0.5f, 0.5f, 0.5f, 1.0f).getARGBPackedInt());
        font16Grey.load();
        font12 = FontFactory.create(MainActivity.mGameActiviy.getFontManager(), MainActivity.mGameActiviy.getTextureManager(), 512, 512,
                Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), 12, Color.WHITE_ARGB_PACKED_INT);
        font12.load();

        final ITexture strokeOnlyFontTexture = new BitmapTextureAtlas(MainActivity.mGameActiviy.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
        font12Stroke = FontFactory.createStroke(MainActivity.mGameActiviy.getFontManager(), strokeOnlyFontTexture,
                Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 16.0f, true, Color.WHITE_ARGB_PACKED_INT, 1.0f, Color.BLACK_ARGB_PACKED_INT);
        font12Stroke.load();


    }

    private static TexRegionManager _nistance = null;

    public static TexRegionManager getInstance() {
        if (_nistance == null) {
            _nistance = new TexRegionManager();
        }
        return _nistance;
    }

    private TexRegionManager() {
    }

    public TiledTextureRegion getTexRegion(String pId) {

        TiledTextureRegionNode textureRegionNode = texRegs.get(pId);
        if (textureRegionNode == null) {
            throw new IllegalArgumentException("Error ID on TiledTextureRegion   TextureKey: " + pId);
        }
        return textureRegionNode.getTextureRegion();
    }

    public Font getFont32() {
        return font32;
    }

    public Font getFont16() {
        return font16;
    }

    public Font getFont16Grey() {
        return font16Grey;
    }

    public Font getFont12() {
        return font12;
    }

    public Font getFont12Stroke() {
        return font12Stroke;
    }

    private static float mLastX;
    private static float mLastY;
    private static float distanceX;
    private static float distanceY;

    public static TiledSprite getSprite(float offsetX, float offsetY, String texKey, int tileIndex, final TouchMessage pTouchMessage) {
        TiledTextureRegion texReg = TexRegionManager.getInstance().getTexRegion(texKey);
        if (tileIndex >= texReg.getTileCount()) {
            Log.e("Gaika", "TexRegionManager.getTexRegion(texKey) index out name = " + texKey + "  TexRegion.size() = " + texReg.getTileCount() + "  inputIndex = " + tileIndex);
        }
        return getSprite(offsetX, offsetY, texReg, tileIndex, pTouchMessage);
    }

    public static TiledSprite getSprite(float offsetX, float offsetY, TiledTextureRegion pTexReg, int tileIndex, final TouchMessage pTouchMessage) {

        //

        if (pTouchMessage == null) {

            final TiledSprite tiledSprite = new TiledSprite(offsetX, offsetY, pTexReg.deepCopy(), MainActivity.mGameActiviy.getVertexBufferObjectManager());
            tiledSprite.setCurrentTileIndex(tileIndex);
            return tiledSprite;
        } else {

            final TiledSprite tiledSprite = new TiledSprite(offsetX, offsetY, pTexReg.deepCopy(), MainActivity.mGameActiviy.getVertexBufferObjectManager()) {
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
                                pTouchMessage.doProcess();
                                // Log.d("gaika","TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");

                            }

                            break;
                    }
                    return ret;
                }
            };
            tiledSprite.setCurrentTileIndex(tileIndex);
            return tiledSprite;
        }

    }

    public static TiledSprite getDelaySprite(float pDelayTimeS, final TouchMessage pTimeOutMessage) {
        TiledSprite splash = new TiledSprite(0.0f, 0.0f, TexRegionManager.getInstance().getTexRegion("delay").deepCopy(),
                MainActivity.mGameActiviy.getVertexBufferObjectManager());
        splash.registerEntityModifier(new DelayModifier(pDelayTimeS, new IEntityModifier.IEntityModifierListener() {

            @Override
            public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {

                pTimeOutMessage.doProcess();
                    /*IMessageHandler msgHandler = pDelayhMessage.getHandler();
                    msgHandler.onHandlMessage(pDelayhMessage);*/
                return;
            }

            @Override
            public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
                // TODO Auto-generated method stub
            }
        }));

        splash.setCurrentTileIndex(0);
        return splash;
    }

    public static TiledSprite getAnimatedSprite(float pOffsetX, float pOffsetY, String pTexKey, int pBeginTiledIndex, int pEndTiledIndex, int pTimeMS, TouchMessage pTouchMessage) {
        AnimatedSprite splash = new AnimatedSprite(pOffsetX, pOffsetY, TexRegionManager.getInstance().getTexRegion(pTexKey).deepCopy(),
                MainActivity.mGameActiviy.getVertexBufferObjectManager());
        long[] itmes = new long[pEndTiledIndex - pBeginTiledIndex + 1];
        for (int i = 0; i < itmes.length; i++) {
            itmes[i] = pTimeMS;
        }
        splash.animate(itmes, pBeginTiledIndex, pEndTiledIndex, true);
        return splash;
    }

    public static TiledSprite getAnimatedSprite(float pOffsetX, float pOffsetY, String pTexKey, int[] pTiledIndexs, int pTimeMS, TouchMessage pTouchMessage) {
        AnimatedSprite splash = new AnimatedSprite(pOffsetX, pOffsetY, TexRegionManager.getInstance().getTexRegion(pTexKey).deepCopy(),
                MainActivity.mGameActiviy.getVertexBufferObjectManager());
        long[] itmes = new long[pTiledIndexs.length];
        for (int i = 0; i < itmes.length; i++) {
            itmes[i] = pTimeMS;
        }
        splash.animate(itmes, pTiledIndexs);
        return splash;
    }
}
