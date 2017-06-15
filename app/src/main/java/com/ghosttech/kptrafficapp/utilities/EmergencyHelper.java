package com.ghosttech.kptrafficapp.utilities;

import android.media.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 2/1/2017.
 */

public class EmergencyHelper {
    String strHelperName,strHelperPhoneNumber,strHelperDistance,strHelperLocation;


    EmergencyHelper(String strHelperName, String strHelperDistance, String strHelperLocation,String strHelperPhoneNumber) {
        this.strHelperName = strHelperName;
        this.strHelperDistance = strHelperDistance;
        this.strHelperLocation = strHelperLocation;
        this.strHelperPhoneNumber = strHelperPhoneNumber;

    }
}
