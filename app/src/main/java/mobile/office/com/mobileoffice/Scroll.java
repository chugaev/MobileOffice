package mobile.office.com.mobileoffice;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Class makes the view scrollable in both directions.
 */

public class Scroll extends View {
    /**
     * Custom GestureDetector for supporting scroll and override possibilities
     * of single tap at children
     */
    private GestureDetector mGestureDetector;
    /**
     * size of scroll
     * w - width
     * h - height
     */
    private int w, h;
    /**
     * Current position of scrolling
     * scrollX - abscissa coordinate
     * scrollY - ordinate coordinate
     */
    private int scrollX, scrollY;

    public Scroll(Context context) {
        super(context);
        init(context);
    }

    public Scroll(Context context, AttributeSet attr) {
        super(context, attr);
        init(context);
    }

    public Scroll(Context context, AttributeSet attr, int style) {
        super(context, attr, style);
        init(context);
    }

    /**
     * Class initialization method
     * @param context - context
     */
    private void init(Context context) {
        mGestureDetector = new GestureDetector(context, new ScrollGestureListener());
    }

    /**
     * Set sizes of scroll
     * @param width - width
     * @param height - height
     */
    public void setSize(int width, int height) {
        w = width;
        h = height;
        updateScrollPosition();
    }

    /**
     * This method changes current scroll coordinate,
     * because if size is changed  scroll screen can go beyond from view
     */
    private void updateScrollPosition() {
        if (getHeight() >= h) {
            scrollBy(0, -scrollY);
        } else if (scrollY + getHeight() > h) {
            scrollTo(scrollX, h - getHeight());
        }
    }

    /**
     * To pass user actions to custom GestureDetector
     * @param event user action
     * @return true
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    /**
     * Auto set current scroll position
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        scrollX = l;
        scrollY = t;
    }

    /**
     * Custom GestureDetector for supporting scroll and override possibilities
     * of single tap at children
     */
    private class ScrollGestureListener extends GestureDetector.SimpleOnGestureListener {
        /**
         * override method to scroll both side
         */

        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanseX, float distanseY) {
            if (scrollX + distanseX > 0 && scrollX + distanseX < w - getWidth()) {
                scrollBy((int) distanseX, 0);
            }
            if (scrollY + distanseY > 0 && scrollY + distanseY < h - getHeight()) {
                scrollBy(0, (int) distanseY);
            }
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            onTapUp();
            postInvalidate();
            return true;
        }
    }

    /**
     * method for defining single click behavior
     */
    public void onTapUp() {

    }
}
