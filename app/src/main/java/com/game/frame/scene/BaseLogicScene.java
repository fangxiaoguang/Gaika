package com.game.frame.scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.IEntityMatcher;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.RectangularShape;

import com.game.frame.FSM.FSMClass;
import com.game.frame.FSM.IMessageHandler;
import com.game.frame.FSM.TouchMessage;
import com.game.gaika.data.GameSetup;
import com.game.gaika.data.ID;
import com.game.gaika.data.Pair;
import com.game.frame.graph.MyScreenCapture;
import com.game.frame.graph.MyScreenCapture.IScreenCaptureCallback;
import com.game.gaika.main.MainActivity;
import com.game.frame.scene.camera.BaseLogicCamera;
import com.game.frame.scene.camera.CameraRange;
import com.game.frame.scene.camera.FillDisplayCamera;
import com.game.frame.scene.dialg.DialogScene;
import com.game.frame.sprite.BaseSprite;
import com.game.frame.sprite.DelaySprite;

public abstract class BaseLogicScene implements IMessageHandler {
    public Scene bkScne;

    private static Map<ID.SCENE_ID, FSMClass> fsmClasses = new HashMap<>();


    public ID.SCENE_ID sceneId;
    public ID.SCENE_ID preSceneID;

    private List<BaseSprite> spriteList;
    protected float offsetX;
    protected float offsetY;
    protected float width;
    protected float height;

    private BaseLogicScene parentScene;
    private List<BaseLogicScene> childScenes;
    private BaseLogicScene hudScene;
    private DialogScene dialogSecne;

    private static Map<ID.SCENE_ID, SceneValueMap> sceneValuesMaps = new HashMap<>();

    public MyScreenCapture screenCapture = new MyScreenCapture();

    public static class SceneValueMap {
        private Map<String, Object> values = new HashMap<String, Object>();

        public void cleanAllValues() {
            values.clear();
        }

        public void remove(String pKey) {
            values.remove(pKey);
        }

        public boolean containsKey(String pKey) {
            return values.containsKey(pKey);
        }

        public void setInt(String pKey, int pIntValue) {
            values.put(pKey, pIntValue);
        }

        public int getInt(String pKey) {
            return (int) (values.get(pKey));
        }

        public void setFloat(String pKey, float pFlaotValue) {
            values.put(pKey, pFlaotValue);
        }

        public float getFloat(String pKey) {
            return (float) (values.get(pKey));
        }

        public void setEnum(String pKey, Enum pEnumValue) {
            values.put(pKey, pEnumValue);
        }

        public Enum getEnum(String pKey) {
            return (Enum) (values.get(pKey));
        }

        public void setObject(String pKey, Object pObject) {
            values.put(pKey, pObject);
        }

        public Object getObject(String pKey) {
            return values.get(pKey);
        }
    }

    public SceneValueMap getSceneValuesMap() {
        return getSceneValuesMap(sceneId);
    }

    public static SceneValueMap getSceneValuesMap(ID.SCENE_ID pSceneID) {
        if (sceneValuesMaps.containsKey(pSceneID) == false) {
            sceneValuesMaps.put(pSceneID, new SceneValueMap());
        }
        return sceneValuesMaps.get(pSceneID);
    }

    public BaseLogicScene(ID.SCENE_ID pSceneId) {
        this(pSceneId, 800.0f, 600.0f);
    }

    public BaseLogicScene(ID.SCENE_ID pSceneId, float pWidth, float pHeight) {
        this(pSceneId, 0.0f, 0.0f, pWidth, pHeight);
    }

    public BaseLogicScene(ID.SCENE_ID pSceneId, float pOffsetX, float pOffsetY, float pWidth, float pHeight) {
        sceneId = pSceneId;
        offsetX = pOffsetX;
        offsetY = pOffsetY;
        width = pWidth;
        height = pHeight;
        spriteList = new ArrayList<BaseSprite>();
    }

    public ID.SCENE_ID getSceneId() {
        return sceneId;
    }

    public void addChildScene(BaseLogicScene pChildScene) {
        if (parentScene != null) {
            throw new IllegalArgumentException("Scene layer outnumber 2. ");
        }

        if (childScenes == null) {
            childScenes = new ArrayList<BaseLogicScene>();
        }
        pChildScene.setParentScene(this);
        childScenes.add(pChildScene);
    }

    public void setParentScene(BaseLogicScene pParentScene) {
        parentScene = pParentScene;
    }

    public BaseLogicScene getParentScene() {
        return parentScene;
    }

    public void setHUDScene(BaseLogicScene pHUDScene) {
        hudScene = pHUDScene;
    }


    public BaseLogicScene getHUDScene() {
        return hudScene;
    }

    public void setDialogSecne(DialogScene pDialogScene) {
        if (pDialogScene != null) {
            pDialogScene.setParentScene(this);
        }
        dialogSecne = pDialogScene;
    }

    public DialogScene getDialogSecne() {

        DialogScene dialog = null;
        if (dialogSecne != null) {
            dialog = dialogSecne;
        }
        if (childScenes != null) {
            for (BaseLogicScene childScene : childScenes) {
                if (childScene.getDialogSecne() != null) {
                    if (dialog == null) {
                        dialog = childScene.getDialogSecne();
                    } else {
                        throw new IllegalArgumentException("DialogScenes count outnumber 2 --   Dialge 1: " + dialog.getSceneId() + "   Dialog 2: " + childScene.getDialogSecne());
                    }
                }
            }
        }

        return dialog;

    }

    public Scene createBackupScene() {

        return new Scene();
    }


    public abstract boolean isBacegroundEnabled();

    private Scene getBackupScene() {
        Set<Integer> setZ = new HashSet<>();

        for (BaseSprite sprite : spriteList) {
            setZ.add(sprite.getZ());
        }
        Scene bkScene = createBackupScene();
        for (Integer z : setZ) {
            Entity entity = new Entity();
            entity.setZIndex(z);
            bkScene.attachChild(entity);
        }
        bkScene.sortChildren();
        return bkScene;

    }

    private static IEntity getChildByZIndex(Scene pBkScne, final int pZ) {
        IEntity zEntity = pBkScne.getChildByMatcher(new IEntityMatcher() {
            @Override
            public boolean matches(IEntity pEntity) {
                return pEntity.getZIndex() == pZ;
            }
        });
        return zEntity;
    }

    public Scene getScene() {

        this.buildScene();
        bkScne = getBackupScene();

        bkScne.setBackgroundEnabled(isBacegroundEnabled());

        bkScne.clearTouchAreas();

//        sortSpriteListByZIndex();
        for (BaseSprite baseSprite : spriteList) {

            final RectangularShape shape = baseSprite.getShape();
            if (shape != null) {

                IEntity zEntity = getChildByZIndex(bkScne, baseSprite.getZ());
                zEntity.attachChild(shape);

                if (baseSprite.getTouchMessage() != null) {
                    bkScne.registerTouchArea(shape);
                }
            }
        }

        if (childScenes != null) {
            Scene lostChildScene = bkScne;
            float unOffsetX = 0.0f;
            float unOffsetY = 0.0f;
            for (BaseLogicScene childScene : childScenes) {
                Scene scene = childScene.getScene();

                scene.setPosition(childScene.getFinalOffsetX(), childScene.getFinaOffsetY());
                lostChildScene.setChildScene(scene);

                unOffsetX -= childScene.getFinalOffsetX();
                unOffsetY -= childScene.getFinaOffsetY();

                lostChildScene = scene;
            }
        }
        bkScne.setTouchAreaBindingOnActionDownEnabled(true);
        return bkScne;
    }

    public abstract void buildScene();


    public void addSprite(BaseSprite pSprite) {



        //Debug code begin
        if (GameSetup.isDebug_delay_to_touch) {
            float x, y;
            if (pSprite instanceof DelaySprite) {

                if (sceneId == ID.SCENE_ID.BATTLEFIELD) {
                    x = MainActivity.mGameActiviy.getEngine().getCamera().getCenterX() - 25;
                    y = MainActivity.mGameActiviy.getEngine().getCamera().getCenterY() - 24;
                } else {
                    x = 100;
                    y = 100;
                }
                pSprite.setX(x);
                pSprite.setY(y);
            }
        }
        //Debug code end


        spriteList.add(pSprite);
        pSprite.setParentScene(this);

    }

    public void setOffsetXY(float pOffsetX, float pOffsetY) {
        offsetX = pOffsetX;
        offsetY = pOffsetY;
    }

    public float getFinalOffsetX() {
        if (parentScene == null) {
            return offsetX;
        } else {
            return parentScene.getFinalOffsetX() + offsetX;
        }

    }

    public float getFinaOffsetY() {
        if (parentScene == null) {
            return offsetY;
        } else {
            return parentScene.getFinaOffsetY() + offsetY;
        }
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    //LogicCamera ......
    public BaseLogicCamera getLogicCamera() {
        FillDisplayCamera camera = new FillDisplayCamera(width, height);
        camera.setParentScene(this);
        return camera;
    }

    public final void loadCameraPoint() {
        BaseLogicCamera camera = getLogicCamera();
        CameraRange cr = camera.getCameraRenge();

        MainActivity.mGameActiviy.getEngine().getCamera().set(cr.xMin, cr.yMin, cr.xMax, cr.yMax);

        Pair<Float> centerXY = camera.getCenterXY();
        getLogicCamera().setCente(centerXY.getX(), centerXY.getY());

    }

    //FSMClass ......
    public void addFSMClass(FSMClass pFSMClass) {
        fsmClasses.put(this.sceneId, pFSMClass);
    }

    public FSMClass getFSMClass() {
        return fsmClasses.get(this.sceneId);
    }

  /*  public void addFSMClass(ID.SCENE_ID pKey, FSMClass pFSMClass) {
        fsmClasses.put(pKey, pFSMClass);
    }*/

    private static FSMClass getFSMClass(ID.SCENE_ID pKey) {
        return fsmClasses.get(pKey);
    }

    public void captureScene(String path, final IMessageHandler pPopupMenuHandler) {

        screenCapture = new MyScreenCapture();
        bkScne.attachChild(screenCapture);

        float captureWidth = GameSetup.deviceHeightPixels * 4.0f / 3.0f;
        float captureHeight = GameSetup.deviceHeightPixels;

        float captureX = (GameSetup.deviceWidthPixels - captureWidth) / 2;
        float captureY = 0.0f;


        screenCapture.capture((int) captureX, (int) captureY, (int) captureWidth, (int) captureHeight,
                path, new IScreenCaptureCallback() {

                    @Override
                    public void onScreenCaptured(String pFilePath) {

                        TouchMessage msg = new TouchMessage(ID.MSG_ID.MSG_SCENE_SYSTEM_POPUP_MENU__CAPTURED, null, pPopupMenuHandler);
                        msg.doProcess();
                    }

                    @Override
                    public void onScreenCaptureFailed(String pFilePath, Exception pException) {

                    }

                });
    }

//    private void sortSpriteListByZIndex() {
//        Collections.sort(spriteList, new Comparator<BaseSprite>() {
//            @Override
//            public int compare(BaseSprite arg0, BaseSprite arg1) {
//                int ret = 0;
//                if (arg0.getZ() > arg1.getZ()) {
//                    ret = 1;
//                } else if (arg0.getZ() < arg1.getZ()) {
//                    ret = -1;
//                }
//                return ret;
//            }
//        });
//    }

    public List<BaseSprite> getSpriteList() {
        return spriteList;
    }
}
