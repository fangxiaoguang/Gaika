package com.game.gaika.testtestbale;


        import android.support.test.rule.ActivityTestRule;
        import android.support.test.runner.AndroidJUnit4;
        import android.test.suitebuilder.annotation.LargeTest;
        import android.util.Log;

        import com.game.gaika.data.ID;
        import com.game.gaika.main.MainActivity;
        import com.game.test.GameDriver;
        import com.game.test.GameElement;

        import org.junit.Rule;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        import static android.os.Build.ID;
        import static android.support.test.espresso.Espresso.onView;
        import static android.support.test.espresso.action.ViewActions.click;
        import static android.support.test.espresso.matcher.ViewMatchers.withId;
        import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class Test1 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);



    @Test
    public void sayHello(){

        long l1 = System.currentTimeMillis();
        GameDriver d = new GameDriver<MainActivity>(mActivityRule.getActivity() );

        d.waitScenc(com.game.gaika.data.ID.SCENE_ID.LOGO_3);
        long l2 = System.currentTimeMillis();

        Log.d("TAG1", "l2 - l1 =  " + (l2 - l1));

        GameElement e = d.findElementById("id001");

        e.click();


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        String s = mActivityRule.toString();
        int l = s.length();
        Log.d("TAG1", s);
    }
}