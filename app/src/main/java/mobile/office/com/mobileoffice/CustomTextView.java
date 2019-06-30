package mobile.office.com.mobileoffice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;

/**
 * Class for drawing text with different sizes and styles
 */

public class CustomTextView extends Scroll {

//    private static final String TAG = "CustomTextView";

    private static final int DEFAULT_COLOR = Color.BLACK;
    private static final int DEFAULT_TEXT_SIZE = 16;

    /**
     * Paint to drow text
     */
    private TextPaint mTextPaint;

    /**
     * String with applying styles to text
     */
    private SpannableStringBuilder mText;

    /**
     * Layout with auto word wrapping
     */
    private DynamicLayout mDynamicLayout;

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    /**
     * Class initialization method
     */
    void init() {
        mTextPaint = new TextPaint();
        mText = new SpannableStringBuilder();
        mTextPaint.setAntiAlias(true);
        setTextSize(DEFAULT_TEXT_SIZE);
        mTextPaint.setColor(DEFAULT_COLOR);
    }

    /**
     * if single tap then screen goes to start position
     */
    @Override
    public void onTapUp() {
        scrollTo(0, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (mDynamicLayout == null) {
            mDynamicLayout = new DynamicLayout(mText,
                    mTextPaint,
                    width,
                    Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setSize(mDynamicLayout.getWidth(), mDynamicLayout.getHeight());
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        mDynamicLayout.draw(canvas);
        canvas.restore();
    }

    /**
     * text for drawing on screen
     * @param text String
     */
    public void setText(String text) {
        mText.clear();
        mText.append(text);
        invalidate();
    }

    /**
     * Get current text
     * @return String
     */
    public String getText() {
        return mText.toString();
    }

    /**
     * Set text size
     * @param textSize - sp
     */
    public void setTextSize(float textSize) {
        float size = textSize * getResources().getDisplayMetrics().density;
        AbsoluteSizeSpan[] spans = mText.getSpans(0, mText.length(), AbsoluteSizeSpan.class);
        for (AbsoluteSizeSpan span : spans) {
            mText.removeSpan(span);
        }
        mText.setSpan(new AbsoluteSizeSpan((int) size), 0, mText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextPaint.setTextSize(size);
        invalidate();
    }

    /**
     * get text size
     * @return text size sp
     */
    public float getTextSize() {
        return mTextPaint.getTextSize() / getResources().getDisplayMetrics().density;
    }

    /**
     * set style to text
     * BOLD - android.graphics.Typeface.BOLD (=1)
     * ITALIC - android.graphics.Typeface.ITALIC (=2)
     * @param style int
     */
    private void setStyle(int style) {
        mText.setSpan(new StyleSpan(style), 0, mText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        invalidate();
    }


    /**
     * true if text has specified style else false
     * BOLD - android.graphics.Typeface.BOLD (=1)
     * ITALIC - android.graphics.Typeface.ITALIC (=2)
     * @param style int
     * @return boolean
     */
    private boolean hasStyle(int style) {
        StyleSpan[] styles = mText.getSpans(0, mText.length(), StyleSpan.class);
        for (StyleSpan span : styles) {
            if (span.getStyle() == style) {
                return true;
            }
        }
        return false;
    }

    /**
     * remove specified style
     * BOLD - android.graphics.Typeface.BOLD (=1)
     * ITALIC - android.graphics.Typeface.ITALIC (=2)
     * @param style int
     */
    private void removeStyle(int style) {
        StyleSpan[] styles = mText.getSpans(0, mText.length(), StyleSpan.class);
        for (StyleSpan span : styles) {
            if (span.getStyle() == style) {
                mText.removeSpan(span);
            }
        }
        invalidate();
    }

    /**
     * make text the bold
     * @param bold true - text is bold, false - text is not bold
     */
    public void setBold(boolean bold) {
        if (bold) {
            setStyle(Typeface.BOLD);
        } else {
            removeStyle(Typeface.BOLD);
        }
    }


    /**
     * true if text is bold else false
     * @return boolean
     */
    public boolean isBold() {
        return hasStyle(Typeface.BOLD);
    }

    /**
     * make text the italic
     * @param italic true - text is italic, false - text is not italic
     */
    public void setItalic(boolean italic) {
        if (italic) {
            setStyle(Typeface.ITALIC);
        } else {
            removeStyle(Typeface.ITALIC);
        }
    }

    /**
     * true if text is italic else false
     * @return boolean
     */
    public boolean isItalic() {
        return hasStyle(Typeface.ITALIC);
    }
}
