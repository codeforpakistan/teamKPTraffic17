package com.ghosttech.kptrafficapp.utilities;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Asus on 4/28/2017.
 */

public class LiveUpdatesMapCoorindates {
    public static final LatLng CHARSADDA_ROAD_1 = new LatLng(34.032627, 71.576201);//initialize first point of the route like this
    public static final ArrayList<LatLng> ARRAY_CHARSADDA_ROAD = new ArrayList<>();//define array name

    public static ArrayList<LatLng> getArrayCharsaddaRoad() {
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.032627, 71.576201));//copy the coordinate of the starting point from above
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.034312, 71.5779767));//define rest of the path one by one
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.046545, 71.5806858));
        ARRAY_CHARSADDA_ROAD.add( new LatLng(34.05858, 71.5868058));
        return ARRAY_CHARSADDA_ROAD;
    }

    public static final ArrayList<String> spotName = new ArrayList<>();

    public static ArrayList<String> getSpotName() {
        spotName.add("Ring Road Flyover");
        spotName.add("Charsadda Adda");
        spotName.add("TOri Kabab House");
        spotName.add("Faqir abad Police Station");
        return spotName;
    }
}
