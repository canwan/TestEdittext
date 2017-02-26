package com.example.can.testedittext;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;


/**
 * Created by Administrator on 2017/1/8.
 */

public class MyScroolView extends ScrollView implements View.OnTouchListener {
    String TAG = "MyScroolView";
    int vmaxh, vminh;
    int offy = 1000;
    int HIDE = -1, SHOW = 1, IDLE = 0;
    int state = 0;
    View lastView;
    Activity activity;
    private int childHeight;
    ViewGroup childGroup;

    public MyScroolView(Context context) {
        super(context);
    }

    public MyScroolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (vmaxh != 0) {
                Log.i(TAG, "onTouch" + offy);
                scrollTo(0, offy);
            }
        }
    };

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int h = b - t;
        int state = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        if (state == 0) {
            if (vmaxh < h) {
                vmaxh = h;
                state = IDLE;
            }
            if (h < vmaxh) {
                vminh = h;
            }
            if (h < vmaxh) {
                state = SHOW;
            } else if (h == vmaxh) {
                if (state == SHOW) {
                    state = HIDE;
                }
            }

            if (childHeight == 0) {
                childHeight = getChildAt(0).getHeight();
            }
            if (childGroup == null) {
                childGroup = (LinearLayout) getChildAt(0);
                int childCount = childGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = childGroup.getChildAt(i);
                    if (child instanceof EditText) {
                        child.setOnTouchListener(this);
                        lastView = child;
                    }
                }
            }
        }
        Log.i(TAG, "" + state);
        if (state == SHOW) {
            postDelayed(runnable, 100);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v == lastView) {
            offy = childGroup.getChildAt(childGroup.getChildCount() - 1).getBottom() - vminh;
        } else {
            offy = v.getBottom() - vminh + 4;
        }
        return false;
    }
}
