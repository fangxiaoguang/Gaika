package com.game.frame.texture;

import java.io.File;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.FileBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import com.game.gaika.data.ID;
import com.game.gaika.debug.DebugManager;
import com.game.gaika.main.MainActivity;
import com.game.gaika.data.GameSetup;
/*import com.game.gaika.scene.BaseLogicScene;
import com.game.frame.tool.Tools;*/

public class TiledTextureRegionNode {

    private String key;
    private int sizeX, sizeY;
    private int column, row;
    // private BuildableBitmapTextureAtlas src.texture;
    // private BitmapTextureAtlas src.texture;

    private TiledTextureRegion textureRegion;

/*    public TiledTextureRegionNode() {

    }*/

    public TiledTextureRegionNode(String pKey, int pSizeX, int pSizeY, int pColumn, int pRow) {

        key = pKey;
        sizeX = pSizeX;
        sizeY = pSizeY;
        column = pColumn;
        row = pRow;
        textureRegion = null;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }


    public TiledTextureRegion getTextureRegion() {
        if (textureRegion == null) {

            //if (GameSetup.isDebug_read_SD_res == true) {
            if (DebugManager.getAppRunModel() == ID.RUN_MODELE.DEBUG) {

                File resFile = new File(GameSetup.sdcartdPahtTexture + key + ".png");
                FileBitmapTextureAtlasSource fileBitmapTextureAtlasSource = FileBitmapTextureAtlasSource.create(resFile);
                BitmapTextureAtlas texture = new BitmapTextureAtlas(MainActivity.mGameActiviy.getTextureManager(), getTextureSize(getSizeX()),
                        getTextureSize(getSizeY()), TextureOptions.BILINEAR);
                textureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromSource(texture, fileBitmapTextureAtlasSource, 0, 0, column, row);
                texture.load();
            } else {
                BuildableBitmapTextureAtlas texture = new BuildableBitmapTextureAtlas(MainActivity.mGameActiviy.getTextureManager(),
                        getTextureSize(getSizeX()), getTextureSize(getSizeY()), TextureOptions.BILINEAR);

                textureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(texture, MainActivity.mGameActiviy, "texture/" + key + ".png", column,
                        row);
                try {
                    texture.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
                    texture.load();
                } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
                    throw new IllegalArgumentException("TextureRegion load error :" + key + ".png");
                }
            }

        }
        return textureRegion;
    }

    public static int getTextureSize(int i) {
        int ret = 1;
        while (ret < i) {
            ret = ret * 2;
        }
        return ret;
    }
}