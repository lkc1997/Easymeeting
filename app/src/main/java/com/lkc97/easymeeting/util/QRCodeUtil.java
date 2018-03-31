package com.lkc97.easymeeting.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

/**
 * Created by 77416 on 2018/3/31.
 */

public class QRCodeUtil {
    @Nullable
    public static Bitmap createQRCodeBitmap(String content, int width, int height){
        return createQRCodeBitmap(content,width,height,"UTF-8","H","2",Color.BLACK, Color.WHITE);
    }
    @Nullable
    public static Bitmap createQRCodeBitmap(String content, int width, int height,
                                            @Nullable String character_set, @Nullable String error_correction, @Nullable String margin,
                                            @ColorInt int color_black,@ColorInt int color_white){
        //参数合法性判断
        if(TextUtils.isEmpty(content)){
            return null;
        }
        if(width<0||height<0){
            return null;
        }
        try{
            //设置二维码相关配置，生产Bitmartix(位矩阵)对象
            Hashtable<EncodeHintType,String> hints=new Hashtable<>();
            if(!TextUtils.isEmpty(character_set)){
                hints.put(EncodeHintType.CHARACTER_SET,character_set);//字符转码格式
            }
            if(!TextUtils.isEmpty(error_correction)){
                hints.put(EncodeHintType.ERROR_CORRECTION,error_correction);//容错级
            }
            if(!TextUtils.isEmpty(margin)){
                hints.put(EncodeHintType.MARGIN,margin);//空白边设置
            }
            BitMatrix bitMatrix=new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels=new int[width*height];
            for(int y=0;y<height;y++){
                for(int x=0;x<width;x++){
                    if (bitMatrix.get(x,y))
                        pixels[y*width+x]=color_black;
                    else
                        pixels[y*width+x]=color_white;
                }
            }
            Bitmap bitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels,0,width,0,0,width,height);
            return bitmap;
        }catch (WriterException e){
            e.printStackTrace();
        }
        return null;
    }
}
