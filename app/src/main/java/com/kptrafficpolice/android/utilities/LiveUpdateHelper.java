package com.kptrafficpolice.android.utilities;

/**
 * Created by Asus on 2/1/2017.
 */

public class LiveUpdateHelper {
    String strRoadName,strRoadStartEndPoint;
    int imageID;


    public LiveUpdateHelper(int imageID, String name, String strRoadStartEndPoint) {
        this.strRoadName = name;
        this.strRoadStartEndPoint = strRoadStartEndPoint;
        this.imageID = imageID;
    }
}
