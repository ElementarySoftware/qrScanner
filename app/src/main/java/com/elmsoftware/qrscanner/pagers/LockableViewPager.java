package com.elmsoftware.qrscanner.pagers;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by PRODUCER on 31.03.2018.
 */

public class LockableViewPager extends ViewPager {
    public static boolean swipeable;
    private float initialXValue;
    private SwipeDirection direction;
    private float x1, x2;
    static final int min_distance = 20;
    SwiperListener mSwiperListener;

    boolean eventSent = false;

    public LockableViewPager(Context context) {
        super(context);
    }

    public LockableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.swipeable = false;
        this.direction = SwipeDirection.all;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.swipeable) {
            if (this.IsSwipeAllowed(event)) {
                return super.onTouchEvent(event);
            }
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.swipeable) {
            if (this.IsSwipeAllowed(event)) {
                return super.onInterceptTouchEvent(event);
            }
        }
        return false;
    }

    private boolean IsSwipeAllowed(MotionEvent event) {
        if(this.direction == SwipeDirection.all) return true;

        if(direction == SwipeDirection.none)//disable any swipe
            return false;

        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            initialXValue = event.getX();
            return true;
        }

        if(event.getAction()==MotionEvent.ACTION_MOVE) {
            try {
                float diffX = event.getX() - initialXValue;
                if (diffX > 0 && direction == SwipeDirection.right) {
                    // swipe from left to right detected
                    return false;
                }else
                if (diffX < 0 && direction == SwipeDirection.left) {
                    // swipe from right to left detected

                    return false;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return true;
    }

    public void setAllowedSwipeDirection(SwipeDirection direction) {
        this.direction = direction;
    }

    public void setSwipeable(boolean swipeable) {
        this.swipeable = swipeable;
    }

    public enum SwipeDirection {
        all, left, right, none;
    }

    public void setmSwiperListener(SwiperListener mSwiperListener) {
        this.mSwiperListener = mSwiperListener;
    }


    public static interface SwiperListener {
        public boolean onLeftSwipe();

        public boolean onRightSwipe();
    }
}