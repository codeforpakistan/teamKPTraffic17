package com.ghosttech.kptrafficapp.utilities;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Asus on 4/28/2017.
 */

public class LiveUpdatesMapCoorindates {
    public static final LatLng CHARSADDA_ROAD_1 = new LatLng(34.032627, 71.576201);//initialize first point of the route like this
    public static final ArrayList<LatLng> ARRAY_CHARSADDA_ROAD = new ArrayList<>();//define array name
    public static final LatLng KHYBER_ROAD_1 = new LatLng(34.014463, 71.567379);//initialize first point of the route like this
    public static final ArrayList<LatLng> ARRAY_KHYBER_ROAD = new ArrayList<>();//define array name

    public static ArrayList<LatLng> getArrayCharsaddaRoad() {
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.032627, 71.576201));//copy the coordinate of the starting point from above
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.034312, 71.5779767));//define rest of the path one by one
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.046545, 71.5806858));
        ARRAY_CHARSADDA_ROAD.add( new LatLng(34.05858, 71.5868058));
        return ARRAY_CHARSADDA_ROAD;
    }

    public static ArrayList<LatLng> getArrayKhyberRoad() {
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014463, 71.567379));//copy the coordinate of the starting point from above
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014480, 71.566886));//define rest of the path one by one
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014480, 71.566435));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014480, 71.565899));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014498, 71.565459));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014498, 71.564922));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014498, 71.564418));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014507, 71.563946));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014507, 71.563442));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014507, 71.562873));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014515, 71.561957));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014515, 71.561496));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014515, 71.561053));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014515, 71.560629));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014523, 71.560195));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014531, 71.559781));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014538, 71.559263));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014546, 71.558782));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014554, 71.558311));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014554, 71.557887));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014554, 71.557435));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014546, 71.556983));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014531, 71.556502));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014531, 71.556050));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014546, 71.555513));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014554, 71.555014));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014554, 71.554496));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014554, 71.554043));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014554, 71.553638));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014499, 71.553167));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014468, 71.552753));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014437, 71.552310));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014413, 71.551858));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014382, 71.551339));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014351, 71.550784));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014327, 71.550284));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014304, 71.549738));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014273, 71.549276));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014242, 71.548749));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014218, 71.548240));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014187, 71.547778));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014148, 71.547307));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014117, 71.546817));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014093, 71.546290));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014062, 71.545640));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.014031, 71.545140));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.013992, 71.544632));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.013953, 71.544066));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.013906, 71.543539));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.013867, 71.543020));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.013843, 71.542474));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.013828, 71.542229));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.013789, 71.541994));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.013679, 71.541767));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.013547, 71.541523));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.013414, 71.541287));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.013187, 71.541051));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.012859, 71.540825));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.012531, 71.540618));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.012188, 71.540401));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.011750, 71.540128));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.011243, 71.539808));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.010923, 71.539582));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.010470, 71.539271));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.010134, 71.539045));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.009946, 71.538856));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.009704, 71.538508));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.009517, 71.538187));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.009336, 71.537933));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.009064, 71.537715));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.008805, 71.537496));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.008456, 71.537223));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.008068, 71.536902));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.007737, 71.536645));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.007414, 71.536371));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.007084, 71.536098));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.006786, 71.535856));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.006495, 71.535614));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.006365, 71.535489));
        ARRAY_KHYBER_ROAD.add( new LatLng(34.006022, 71.534928));
        return ARRAY_KHYBER_ROAD;
    }

    public static final ArrayList<String> spotName = new ArrayList<>();

    public static ArrayList<String> getSpotName() {
        spotName.add("Ring Road Flyover");
        spotName.add("Charsadda Adda");
        spotName.add("Tori Kabab House");
        spotName.add("Faqir abad Police Station");
        return spotName;
    }
}
