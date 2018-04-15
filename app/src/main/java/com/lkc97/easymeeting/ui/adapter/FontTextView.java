package com.lkc97.easymeeting.ui.adapter;

/**
 * 1.version:
 * 2.date:2017/1/13 15:26
 * 3.update:2017/1/13.
 * 4.autour:张玉杰
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.util.FontUtils;

/**
 * Created by Administrator on 2015/10/27.
 */
public class FontTextView extends AppCompatTextView {
    /** The file name of the font data in the assets directory*/
    private String mFontPath = null;

    public FontTextView(Context context) {
        super(context);
        init(context, null, 0);
    }
    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }
    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public String getFontPath() {
        return mFontPath;
    }

    /**
     * <p>Set font file fontPath</p>
     * @param fontPath The file name of the font data in the assets directory
     */
    public void setFontPath(String fontPath) {
        mFontPath = fontPath;

        if (!TextUtils.isEmpty(mFontPath)) {
            FontUtils.getInstance().replaceFontFromAsset(this, mFontPath);
        }
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FontTextView, defStyleAttr, 0);
        mFontPath = typedArray.getString(R.styleable.FontTextView_font_path);
        typedArray.recycle();

        if (!TextUtils.isEmpty(mFontPath)) {
            FontUtils.getInstance().replaceFontFromAsset(this, mFontPath);
        }
    }
}