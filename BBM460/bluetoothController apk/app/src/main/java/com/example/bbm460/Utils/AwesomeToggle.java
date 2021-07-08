package com.example.bbm460.Utils;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.bbm460.R;

public class AwesomeToggle extends View {

    private Paint outerCirclePaint, innerCirclePaint, textPaint;
    private int width;
    private int height;
    private int animChange = 2;
    private float x, y, textX, textXOff, textY, textXOn;
    private static boolean isChecked;
    private RectF viewRectangle;
    private String textOn = "On";
    private String textOff = "Off";
    private String drawText = textOff;
    private static ValueAnimator animator;
    private int left, top;
    private boolean isUserChecked;
    private static OnCheckedChangeListner onCheckedChangeListner;
    private int activeBackgroundColor, inActiveBackgroundColor, innerToggleColor, textColor, backgroundColor;

    public AwesomeToggle(Context context) {
        super(context);
        initColors();
        init();
    }

    public AwesomeToggle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initColors(context, attrs);
        init();

    }

    private void initColors() {
        activeBackgroundColor = Color.parseColor("#FF1DE9B6");
        inActiveBackgroundColor = Color.parseColor("#FF9E9E9E");
        innerToggleColor = Color.WHITE;
        textColor = Color.WHITE;
    }

    private void initColors(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AwesomeToggle, 0, 0);
        try {
            activeBackgroundColor = typedArray.getColor(R.styleable.AwesomeToggle_active_background_color, Color.parseColor("#FF1DE9B6"));
            inActiveBackgroundColor = typedArray.getColor(R.styleable.AwesomeToggle_inactive_background_color, Color.parseColor("#FF9E9E9E"));
            innerToggleColor = typedArray.getColor(R.styleable.AwesomeToggle_inner_toggle_color, Color.WHITE);
            textColor = typedArray.getColor(R.styleable.AwesomeToggle_font_color, Color.WHITE);
        } finally {
            typedArray.recycle();
        }

    }

    private void init() {
        outerCirclePaint = new Paint();
        innerCirclePaint = new Paint();
        textPaint = new Paint();
        innerCirclePaint.setStyle(Paint.Style.FILL);
        backgroundColor = inActiveBackgroundColor;
        outerCirclePaint.setStyle(Paint.Style.FILL);
        this.setLayerType(LAYER_TYPE_SOFTWARE, outerCirclePaint);
        outerCirclePaint.setShadowLayer(2.0f, 1.0f, 4.0f, Color.GRAY);
        animator = ValueAnimator.ofFloat(x, y);
        viewRectangle = new RectF(0, 0, 0, 0);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 100;
        int desiredHeight = 50;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width+25, height+25);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
        x = (this.left + (width / 2) / 2) + animChange;
        y = (this.top + (width / 2) / 2) + 2;
        textX = x + ((width / 2) / 2);
        textXOff = textX;
        textXOn = x - ((width / 2) / 2) / 2 - 10;
        textY = top + ((width / 2) / 2) + ((((width / 2) / 2) / 2)) - 2;
        viewRectangle.set(this.left, this.top, width, (width / 2));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        textPaint.setColor(textColor);
        innerCirclePaint.setColor(innerToggleColor);
        outerCirclePaint.setColor(backgroundColor);
        textPaint.setTextSize(width / 4);
        canvas.drawRoundRect(viewRectangle, width / 3, width / 3, outerCirclePaint);
        canvas.drawCircle(x, y, ((width / 2) / 2) - 10, innerCirclePaint);
        if (!animator.isRunning()) {
            canvas.drawText(drawText, textX, textY, textPaint);
            if (onCheckedChangeListner != null)
                onCheckedChangeListner.onChecked(isChecked);
        }
        setOnClickListener(onClickListener);
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!isChecked) {
                int exact = width - ((width / 2) / 2);
                animator = ValueAnimator.ofFloat(x, exact);
                animator.setDuration(200);
                animator.addUpdateListener(animatorUpdateListener);
                animator.setInterpolator(new DecelerateInterpolator());
                animator.start();
            } else {
                int exact = (left + (width / 2) / 2) + 2;
                animator = ValueAnimator.ofFloat(x, exact);
                animator.setDuration(200);
                animator.addUpdateListener(animatorUpdateListener);
                animator.setInterpolator(new DecelerateInterpolator());
                animator.start();
            }
            isChecked = !isChecked;
            isUserChecked = isChecked;
        }
    };
    ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            x = (Float) (animation.getAnimatedValue());
            if (isChecked) {
                textX = textXOn;
                drawText = textOn;
                backgroundColor = activeBackgroundColor;
            } else {
                textX = textXOff;
                drawText = textOff;
                backgroundColor = inActiveBackgroundColor;
            }
            invalidate();

        }
    };

    /**
     * Method to toggle between on and off state
     *
     * @param isChecked check the button if passed value is true uncheck otherwise
     */
    public void isChecked(final boolean isChecked) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isUserChecked != isChecked) {
                    if (isChecked) {
                        textX = textXOn;
                        drawText = textOn;
                        backgroundColor = activeBackgroundColor;
                        x = width - ((width / 2) / 2);

                    } else {
                        textX = textXOff;
                        drawText = textOff;
                        backgroundColor = inActiveBackgroundColor;
                        x = (left + (width / 2) / 2) + 2;
                    }
                    AwesomeToggle.isChecked = isChecked;
                    isUserChecked = AwesomeToggle.isChecked;
                    invalidate();
                }
            }
        }, 1000);
    }

    /**
     * Listner to get notified in case of toggle changes
     *
     * @author Jinesh Francis
     */
    public interface OnCheckedChangeListner {
        /**
         * Method to notify toggle changes
         *
         * @param isChecked true if toggle button is on else false
         */
        void onChecked(boolean isChecked);
    }

    /**
     * Method sets a listner to get notified about toggle changes
     *
     * @param onCheckedChangeListner pass reference of implemented {@link OnCheckedChangeListner}
     */
    public void setOnCheckedChangeListner(OnCheckedChangeListner onCheckedChangeListner) {
        AwesomeToggle.onCheckedChangeListner = onCheckedChangeListner;
    }

    /**
     * Sets background color for toggle button in checked state
     *
     * @param activeBacgroundColor pass color to set as background
     */
    public void setActiveBackgroundColor(int activeBacgroundColor) {
        this.activeBackgroundColor = activeBacgroundColor;
        backgroundColor = activeBacgroundColor;
        invalidate();
    }

    /**
     * Sets background color for toggle button in unchecked state
     *
     * @param inActiveBacgroundColor pass color to set as background
     */
    public void setInActiveBackgroundColor(int inActiveBacgroundColor) {
        this.inActiveBackgroundColor = inActiveBacgroundColor;
        backgroundColor = inActiveBacgroundColor;
        invalidate();
    }

    /**
     * Sets font color for the toggle text
     *
     * @param fontColor pass color to set as font color
     */
    public void setFontColor(int fontColor) {
        this.textColor = fontColor;
        invalidate();
    }

    /**
     * Sets background color for inner moving toggle circle
     *
     * @param innerToggleColor color to set inner toggle background
     */
    public void setInnerToggleColor(int innerToggleColor) {
        this.innerToggleColor = innerToggleColor;
        invalidate();
    }

}
