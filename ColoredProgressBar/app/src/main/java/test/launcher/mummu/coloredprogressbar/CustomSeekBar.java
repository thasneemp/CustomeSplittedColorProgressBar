package test.launcher.mummu.coloredprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class CustomSeekBar extends ProgressBar {
    private float totalSpan = 6;
    private float redSpan = 2;
    private float yellowSpan = 1;
    private float redSpan2 = 3;
    private float darkGreySpan;
    private ArrayList<ProgressItem> mProgressItemsList;
    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem mProgressItem;

    public CustomSeekBar(Context context) {
        super(context);
        initDataToSeekbar();
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDataToSeekbar();
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initDataToSeekbar();
    }

    public void initData(ArrayList<ProgressItem> progressItemsList) {
        this.mProgressItemsList = progressItemsList;

    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec,
                                          int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        if (mProgressItemsList.size() > 0) {
            int progressBarWidth = getWidth();
            int progressBarHeight = getHeight();
//            int thumboffset = getThumbOffset();
            int lastProgressX = 0;
            int progressItemWidth, progressItemRight;
            for (int i = 0; i < mProgressItemsList.size(); i++) {
                ProgressItem progressItem = mProgressItemsList.get(i);
                Paint progressPaint = new Paint();
                progressPaint.setColor(getResources().getColor(
                        progressItem.color));

                progressItemWidth = (int) (progressItem.progressItemPercentage
                        * progressBarWidth / 100);

                progressItemRight = lastProgressX + progressItemWidth;

                // for last item give right to progress item to the width
                if (i == mProgressItemsList.size() - 1
                        && progressItemRight != progressBarWidth) {
                    progressItemRight = progressBarWidth;
                }
                Rect progressRect = new Rect();
                progressRect.set(lastProgressX, 1 / 2,
                        progressItemRight, progressBarHeight - progressBarHeight / 2);
                canvas.drawRect(progressRect, progressPaint);
                lastProgressX = progressItemRight;
            }
            super.onDraw(canvas);
        }

    }

    private void initDataToSeekbar() {
        progressItemList = new ArrayList<ProgressItem>();
        // red span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = ((redSpan / totalSpan) * 100);
        Log.i("Mainactivity", mProgressItem.progressItemPercentage + "");
        mProgressItem.color = R.color.colorAccent;
        progressItemList.add(mProgressItem);


        // yellow span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (yellowSpan / totalSpan) * 100;
        mProgressItem.color = R.color.yellow;
        progressItemList.add(mProgressItem);

        // yellow span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (redSpan2 / totalSpan) * 100;
        mProgressItem.color = R.color.colorAccent;
        progressItemList.add(mProgressItem);


        this.initData(progressItemList);
        this.invalidate();
    }
}
