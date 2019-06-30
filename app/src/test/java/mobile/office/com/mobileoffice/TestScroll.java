package mobile.office.com.mobileoffice;

import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class TestScroll {

    private Scroll mScroll;

    @Before
    public void setUp() throws Exception {
        AppCompatActivity activity = Robolectric.buildActivity(AppCompatActivity.class).create().get();
        mScroll = new Scroll(activity);
        mScroll.setSize(800, 2000);
    }

    @Test
    public void testUserScroll() {
        mScroll.onTouchEvent(getScrollEvent(100, 100));
        assert mScroll.getScrollX() == 100 && mScroll.getScrollY() == 100;
        mScroll.onTouchEvent(getScrollEvent(0, 100));
        assert mScroll.getScrollX() == 100 && mScroll.getScrollY() == 200;
        mScroll.onTouchEvent(getScrollEvent(100, 0));
        assert mScroll.getScrollX() == 200 && mScroll.getScrollY() == 200;
        mScroll.onTouchEvent(getScrollEvent(-100, -100));
        assert mScroll.getScrollX() == 100 && mScroll.getScrollY() == 100;
        mScroll.onTouchEvent(getScrollEvent(-1000, -1000));
        assert mScroll.getScrollX() == 100 && mScroll.getScrollY() == 100;
    }

    private MotionEvent getScrollEvent(int x, int y) {
        return MotionEvent.obtain(System.currentTimeMillis(),
                System.currentTimeMillis() + 100,
                MotionEvent.ACTION_MOVE, -x, -y, 20);

    }

    @Test
    public void testResizeScroll() {
        mScroll.onTouchEvent(getScrollEvent(400, 1000));
        mScroll.setSize(800, 900);
        assert mScroll.getScrollX() == 400 && mScroll.getScrollY() == 900;
        mScroll.setSize(800, 1000);
        assert mScroll.getScrollX() == 400 && mScroll.getScrollY() == 900;
        mScroll.setSize(800, 700);
        assert mScroll.getScrollX() == 400 && mScroll.getScrollY() == 700;
    }

}
