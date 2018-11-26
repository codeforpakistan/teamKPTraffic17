package com.kptrafficpolice.trafficapp.utilities;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by kaxhif on 3/24/17.
 *///raabta
//rabta

public class StringUtils {
    public static String getText(View view){
        if(view instanceof EditText){
            return ((EditText) view).getText().toString();
        }else if(view instanceof TextView){
            return ((TextView) view).getText().toString();
        }else if(view instanceof
                Button){
            return ((Button) view).getText().toString();
        }
        return "";

    }
    public static boolean isEmpty(String s){
        if(s!= "" || s!= null || s!="null"){
            return false;
        }
        return true;
    }
    public static int getLength(String s){
        if(isEmpty(s))
            return 0;
        return s.length();
    }


}
