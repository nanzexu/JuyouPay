package com.juyou.juyoupay.Utils;

import android.content.Context;
import android.content.SharedPreferences;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;


/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class SharedPreferencesUtils {
    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "juyou_pay";
    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     * @param context
     * @param key
     * @param object
     */
    public static void setParam(Context context , String key, Object object){

        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if("String".equals(type)){
            editor.putString(key, (String)object);
        }
        else if("Integer".equals(type)){
            editor.putInt(key, (Integer)object);
        }
        else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean)object);
        }
        else if("Float".equals(type)){
            editor.putFloat(key, (Float)object);
        }
        else if("Long".equals(type)){
            editor.putLong(key, (Long)object);
        }
        else {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                ObjectOutputStream os=new ObjectOutputStream(bos);
                //将对象序列化写入byte缓存
                os.writeObject(object);
                //将序列化的数据转为16进制保存
                String bytesToHexString = bytesToHexString(bos.toByteArray());
                editor.putString(key, bytesToHexString);
            } catch (IOException e) {
                // TODO Auto-generated
                e.printStackTrace();
                Log.e("", "保存obj失败");
            }
        }

        editor.commit();
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object getParam(Context context , String key, String type)  {
        //String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if("String".equals(type)){
            return sp.getString(key, "");
        }
        else if("Integer".equals(type)){
            return sp.getInt(key, 1);
        }
        else if("Boolean".equals(type)){
            return sp.getBoolean(key, true);
        }
        else if("Float".equals(type)){
            return sp.getFloat(key, 1.1f);
        }
        else if("Long".equals(type)){
            return sp.getLong(key, 100L);
        }
        else{
            String string = sp.getString(key, "");
            if(TextUtils.isEmpty(string)){
                return null;
            }
            else{
                byte[] stringToBytes = StringToBytes(string);
                ByteArrayInputStream bis=new ByteArrayInputStream(stringToBytes);
                ObjectInputStream is= null;
                try {
                    is = new ObjectInputStream(bis);
                } catch (IOException e) {

                    e.printStackTrace();
                }
                //返回反序列化得到的对象
                Object readObject = null;
                try {
                    readObject = is.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return readObject;
            }
        }

    }
    /**
     * desc:将数组转为16进制
     * @param bArray
     * @return
     * modified:
     */
    public static String bytesToHexString(byte[] bArray) {
        if(bArray == null){
            return null;
        }
        if(bArray.length == 0){
            return "";
        }
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
    /**
     * desc:将16进制的数据转为数组
     * <p>创建人：聂旭阳 , 2014-5-25 上午11:08:33</p>
     * @param data
     * @return
     * modified:
     */
    public static byte[] StringToBytes(String data){
        String hexString=data.toUpperCase().trim();
        if (hexString.length()%2!=0) {
            return null;
        }
        byte[] retData=new byte[hexString.length()/2];
        for(int i=0;i<hexString.length();i++)
        {
            int int_ch;  // 两位16进制数转化后的10进制数
            char hex_char1 = hexString.charAt(i); ////两位16进制数中的第一位(高位*16)
            int int_ch1;
            if(hex_char1 >= '0' && hex_char1 <='9')
                int_ch1 = (hex_char1-48)*16;   //// 0 的Ascll - 48
            else if(hex_char1 >= 'A' && hex_char1 <='F')
                int_ch1 = (hex_char1-55)*16; //// A 的Ascll - 65
            else
                return null;
            i++;
            char hex_char2 = hexString.charAt(i); ///两位16进制数中的第二位(低位)
            int int_ch2;
            if(hex_char2 >= '0' && hex_char2 <='9')
                int_ch2 = (hex_char2-48); //// 0 的Ascll - 48
            else if(hex_char2 >= 'A' && hex_char2 <='F')
                int_ch2 = hex_char2-55; //// A 的Ascll - 65
            else
                return null;
            int_ch = int_ch1+int_ch2;
            retData[i/2]=(byte) int_ch;//将转化后的数放入Byte里
        }
        return retData;
    }
}
