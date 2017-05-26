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
    public static final ArrayList<LatLng> ARRAY_UNIVERSITY_ROAD = new ArrayList<>();
    public static final LatLng UNIVERSITY_ROAD_1 = new LatLng(33.997592, 71.491419);
    public static final LatLng JAIL_ROAD_1 = new LatLng(34.009411, 71.565522);
    public static final ArrayList<LatLng> ARRAY_JAIL_ROAD = new ArrayList<>();
    public static final LatLng BAGH_E_NARAN_ROAD_1 = new LatLng(33.977600, 71.446726);
    public static final ArrayList<LatLng> ARRAY_BAGH_E_NARAN_ROAD = new ArrayList<>();
    public static final LatLng KOHAT_ROAD_1 = new LatLng(33.998861, 71.559799);
    public static final ArrayList<LatLng> ARRAY_KOHAT_ROAD = new ArrayList<>();
    public static final LatLng SADDAR_ROAD_1 = new LatLng(34.014953, 71.568376);//initialize first
    public static final ArrayList<LatLng> ARRAY_SADDAR_ROAD = new ArrayList<>();//define array name
    public static final LatLng GT_ROAD_1 = new LatLng(34.017106, 71.621971);
    public static final ArrayList<LatLng> ARRAY_GT_ROAD = new ArrayList<>();//define array name
    public static final ArrayList<LatLng> ARRAY_DALAZAK_ROAD = new ArrayList<>();//define array name
    public static final LatLng DALAZAK_ROAD_1 = new LatLng(34.018197, 71.580108);//initialize first point of the route like this
    public static final ArrayList<LatLng> ARRAY_WARSAK_ROAD = new ArrayList<>();//define array name
    public static final LatLng WARSAK_ROAD = new LatLng(34.013902, 71.542008);//initialize first point of the route like this


    public static ArrayList<LatLng> getArrayWarsakRoad() {
        ARRAY_WARSAK_ROAD.add(new LatLng(34.013985, 71.541996));//copy the coordinate of the starting point from above
        ARRAY_WARSAK_ROAD.add(new LatLng(34.014031, 71.541923));//define rest of the path one by one
        ARRAY_WARSAK_ROAD.add(new LatLng(34.014294, 71.541778));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.014589, 71.541632));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.014733, 71.541564));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.014945, 71.541428));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.015439, 71.541133));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.015646, 71.541009));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.016317, 71.540628));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.016891, 71.540295));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.017634, 71.539882));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.018514, 71.539375));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.018973, 71.539139));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.020289, 71.538399));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.020673, 71.538174));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.021447, 71.537726));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.021554, 71.537672));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.021881, 71.537428));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.022583, 71.537004));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.023579, 71.536451));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.024475, 71.535941));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.025251, 71.535488));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.026167, 71.534986));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.026887, 71.534610));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.027503, 71.534299));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.027921, 71.534063));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.028712, 71.533607));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.029579, 71.533116));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.030628, 71.532510));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.031330, 71.532070));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.032066, 71.531676));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.032715, 71.531292));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.033409, 71.530916));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.033838, 71.530696));
        ARRAY_WARSAK_ROAD.add(new LatLng(34.034047, 71.530570));
        return ARRAY_WARSAK_ROAD;
    }

    public static ArrayList<LatLng> getArrayDalazakRoad() {
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.018197, 71.580108));//copy the coordinate of the starting point from above
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.018199, 71.580594));//define rest of the path one by one
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.018230, 71.581881));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.018279, 71.583276));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.018319, 71.584585));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.018352, 71.585325));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.018846, 71.586162));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.019182, 71.586680));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.019499, 71.587098));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.019890, 71.587602));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.020088, 71.588042));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.020624, 71.588739));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.020924, 71.589171));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.021540, 71.590177));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.022100, 71.590995));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.022480, 71.591483));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.022989, 71.592317));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.023327, 71.592757));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.023627, 71.593186));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.024287, 71.594114));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.024558, 71.594516));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.025096, 71.595401));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.025676, 71.596222));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.026074, 71.596828));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.026319, 71.597142));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.026921, 71.598049));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.027686, 71.599251));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.027997, 71.599852));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.028657, 71.600936));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.029028, 71.601529));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.029622, 71.602487));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.030269, 71.603576));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.030836, 71.604555));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.031398, 71.605537));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.031689, 71.606119));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.031971, 71.606642));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.032409, 71.607393));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.032774, 71.607972));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.014639, 71.572949));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.033019, 71.608621));
        ARRAY_DALAZAK_ROAD.add(new LatLng(34.033190, 71.608881));
        return ARRAY_DALAZAK_ROAD;
    }

    public static ArrayList<LatLng> getArrayGtRoad() {
        ARRAY_GT_ROAD.add(new LatLng(34.017106, 71.621971));//copy the coordinate of the starting point from above
        ARRAY_GT_ROAD.add(new LatLng(34.017070, 71.621169));//define rest of the path one by one
        ARRAY_GT_ROAD.add(new LatLng(34.017032, 71.620246));
        ARRAY_GT_ROAD.add(new LatLng(34.016972, 71.619117));
        ARRAY_GT_ROAD.add(new LatLng(34.016948, 71.618294));
        ARRAY_GT_ROAD.add(new LatLng(34.016857, 71.616462));
        ARRAY_GT_ROAD.add(new LatLng(34.016755, 71.614850));
        ARRAY_GT_ROAD.add(new LatLng(34.016666, 71.613442));
        ARRAY_GT_ROAD.add(new LatLng(34.016690, 71.612645));
        ARRAY_GT_ROAD.add(new LatLng(34.016606, 71.611384));
        ARRAY_GT_ROAD.add(new LatLng(34.016488, 71.609592));
        ARRAY_GT_ROAD.add(new LatLng(34.016404, 71.608136));
        ARRAY_GT_ROAD.add(new LatLng(34.016335, 71.606744));
        ARRAY_GT_ROAD.add(new LatLng(34.016237, 71.604960));
        ARRAY_GT_ROAD.add(new LatLng(34.016179, 71.603702));
        ARRAY_GT_ROAD.add(new LatLng(34.016123, 71.602597));
        ARRAY_GT_ROAD.add(new LatLng(34.016041, 71.601245));
        ARRAY_GT_ROAD.add(new LatLng(34.015963, 71.599665));
        ARRAY_GT_ROAD.add(new LatLng(34.015905, 71.598485));
        ARRAY_GT_ROAD.add(new LatLng(34.015841, 71.596908));
        ARRAY_GT_ROAD.add(new LatLng(34.015774, 71.595371));
        ARRAY_GT_ROAD.add(new LatLng(34.015696, 71.593912));
        ARRAY_GT_ROAD.add(new LatLng(34.015632, 71.592254));
        ARRAY_GT_ROAD.add(new LatLng(34.015570, 71.590932));
        ARRAY_GT_ROAD.add(new LatLng(34.015521, 71.589862));
        ARRAY_GT_ROAD.add(new LatLng(34.015459, 71.588639));
        ARRAY_GT_ROAD.add(new LatLng(34.015406, 71.587360));
        ARRAY_GT_ROAD.add(new LatLng(34.015346, 71.586030));
        ARRAY_GT_ROAD.add(new LatLng(34.015324, 71.585258));
        ARRAY_GT_ROAD.add(new LatLng(34.015291, 71.584024));
        ARRAY_GT_ROAD.add(new LatLng(34.015255, 71.583463));
        ARRAY_GT_ROAD.add(new LatLng(34.015151, 71.582358));
        ARRAY_GT_ROAD.add(new LatLng(34.015049, 71.581218));
        ARRAY_GT_ROAD.add(new LatLng(34.014973, 71.579804));
        ARRAY_GT_ROAD.add(new LatLng(34.014882, 71.578310));
        ARRAY_GT_ROAD.add(new LatLng(34.014824, 71.577208));
        ARRAY_GT_ROAD.add(new LatLng(34.014748, 71.575735));
        ARRAY_GT_ROAD.add(new LatLng(34.014672, 71.574059));

        ARRAY_GT_ROAD.add(new LatLng(34.014639, 71.572949));
        ARRAY_GT_ROAD.add(new LatLng(34.014606, 71.571399));
        ARRAY_GT_ROAD.add(new LatLng(34.014548, 71.569452));
        ARRAY_GT_ROAD.add(new LatLng(34.014548, 71.568787));
        ARRAY_GT_ROAD.add(new LatLng(34.014572, 71.568006));


        return ARRAY_GT_ROAD;
    }

    public static ArrayList<LatLng> getArraySaddarRoad() {
        ARRAY_SADDAR_ROAD.add(new LatLng(34.014953, 71.568376));//copy the coordinate of the starting point from above
        ARRAY_SADDAR_ROAD.add(new LatLng(34.014728, 71.568135));//define rest of the path one by one
        ARRAY_SADDAR_ROAD.add(new LatLng(34.014386, 71.567698));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.013877, 71.567159));
        ARRAY_SADDAR_ROAD.add(new LatLng(33.996955, 71.559079));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.013088, 71.566231));
        ARRAY_SADDAR_ROAD.add(new LatLng(33.995721, 71.558897));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.012041, 71.565062));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.011623, 71.564512));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.011432, 71.564268));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.011231, 71.564021));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.010937, 71.563662));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.010308, 71.562919));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.010165, 71.562747));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.009467, 71.561588));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.009571, 71.560775));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.009120, 71.559646));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.008593, 71.558452));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.008462, 71.558213));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.007590, 71.557298));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.007345, 71.557038));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.006638, 71.555970));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.005786, 71.554691));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.005335, 71.553998));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.004417, 71.552649));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.003601, 71.551402));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.002745, 71.550072));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.002425, 71.549557));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.001618, 71.548229));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.000588, 71.546660));
        ARRAY_SADDAR_ROAD.add(new LatLng(34.000377, 71.546341));
        ARRAY_SADDAR_ROAD.add(new LatLng(33.999650, 71.545314));
        ARRAY_SADDAR_ROAD.add(new LatLng(33.998783, 71.544056));
        ARRAY_SADDAR_ROAD.add(new LatLng(33.997916, 71.542645));
        ARRAY_SADDAR_ROAD.add(new LatLng(33.996642, 71.540789));
        ARRAY_SADDAR_ROAD.add(new LatLng(33.995668, 71.539290));
        ARRAY_SADDAR_ROAD.add(new LatLng(33.994763, 71.537927));
        ARRAY_SADDAR_ROAD.add(new LatLng(33.994381, 71.537214));
        return ARRAY_SADDAR_ROAD;
    }


    public static ArrayList<LatLng> getArrayKohatRoad() {
        ARRAY_KOHAT_ROAD.add(new LatLng(33.998861, 71.559799));//copy the coordinate of the starting point from above
        ARRAY_KOHAT_ROAD.add(new LatLng(33.998665, 71.559774));//define rest of the path one by one
        ARRAY_KOHAT_ROAD.add(new LatLng(33.997771, 71.559565));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.997449, 71.559417));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.996955, 71.559079));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.995903, 71.558878));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.995721, 71.558897));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.995321, 71.559055));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.993978, 71.559420));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.992768, 71.559964));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.991298, 71.560844));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.990586, 71.561265));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.989728, 71.561316));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.988572, 71.561388));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.987867, 71.561444));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.986973, 71.561726));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.985883, 71.562016));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.985369, 71.562155));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.984239, 71.562313));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.983283, 71.562471));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.981664, 71.562610));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.981429, 71.562650));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.980468, 71.562808));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.980154, 71.562915));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.979631, 71.563323));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.979560, 71.563430));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.979424, 71.563441));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.979306, 71.563401));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.979066, 71.563425));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.978546, 71.563693));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.977930, 71.563950));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.977652, 71.564028));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.976595, 71.564181));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.975972, 71.564205));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.975336, 71.564202));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.974435, 71.564079));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.973530, 71.563966));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.972482, 71.563883));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.971886, 71.563840));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.971672, 71.563770));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.970869, 71.563349));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.969961, 71.562855));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.969038, 71.562364));
        ARRAY_KOHAT_ROAD.add(new LatLng(33.968351, 71.562005));

        return ARRAY_KOHAT_ROAD;
    }

    public static ArrayList<LatLng> getArrayBaghENaranRoad() {
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.977600, 71.446726));//copy the coordinate of the starting point from above
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.977638, 71.447112));//define rest of the path one by one
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.977424, 71.447622));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.977159, 71.448258));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.976785, 71.449108));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.976409, 71.449974));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.976084, 71.450677));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.975675, 71.451702));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.975286, 71.452654));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974910, 71.453585));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974501, 71.454591));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974134, 71.455578));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.973800, 71.456882));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.973671, 71.457799));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.973633, 71.458877));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.973693, 71.459843));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.973871, 71.461340));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974024, 71.462603));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974157, 71.463662));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974368, 71.465379));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974599, 71.467074));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974710, 71.467979));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974837, 71.468816));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974988, 71.470189));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.975041, 71.471214));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.975010, 71.472303));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974917, 71.473164));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974750, 71.474124));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974539, 71.474990));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.974238, 71.475851));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.973927, 71.476763));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.973529, 71.477951));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.973289, 71.479059));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.972984, 71.479924));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.972439, 71.481485));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.971847, 71.483220));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.971262, 71.484872));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.970710, 71.486540));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.970218, 71.487787));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.969691, 71.488943));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.968983, 71.490263));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.968485, 71.491172));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.967497, 71.492921));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.966545, 71.494611));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.965697, 71.496180));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.964983, 71.497478));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.964271, 71.498776));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.963617, 71.499991));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.962927, 71.501236));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.962286, 71.502413));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.961561, 71.503714));
        ARRAY_BAGH_E_NARAN_ROAD.add(new LatLng(33.961072, 71.504604));

        return ARRAY_BAGH_E_NARAN_ROAD;
    }

    public static ArrayList<LatLng> getArrayJailRoad() {
        ARRAY_JAIL_ROAD.add(new LatLng(34.009411, 71.565522));//copy the coordinate of the starting point from above
        ARRAY_JAIL_ROAD.add(new LatLng(34.009455, 71.565216));//define rest of the path one by one
        ARRAY_JAIL_ROAD.add(new LatLng(34.009371, 71.564722));
        ARRAY_JAIL_ROAD.add(new LatLng(34.009126, 71.564304));
        ARRAY_JAIL_ROAD.add(new LatLng(34.008904, 71.563920));
        ARRAY_JAIL_ROAD.add(new LatLng(34.008708, 71.563649));
        ARRAY_JAIL_ROAD.add(new LatLng(34.008671, 71.563585));
        ARRAY_JAIL_ROAD.add(new LatLng(33.995321, 71.559055));
        ARRAY_JAIL_ROAD.add(new LatLng(34.008449, 71.563083));
        ARRAY_JAIL_ROAD.add(new LatLng(34.008087, 71.562217));
        ARRAY_JAIL_ROAD.add(new LatLng(34.007796, 71.561485));
        ARRAY_JAIL_ROAD.add(new LatLng(34.007660, 71.561159));
        ARRAY_JAIL_ROAD.add(new LatLng(34.007493, 71.560797));
        ARRAY_JAIL_ROAD.add(new LatLng(34.007095, 71.559909));
        ARRAY_JAIL_ROAD.add(new LatLng(34.006927, 71.559560));
        ARRAY_JAIL_ROAD.add(new LatLng(34.006587, 71.558852));
        ARRAY_JAIL_ROAD.add(new LatLng(34.006376, 71.558420));
        ARRAY_JAIL_ROAD.add(new LatLng(34.005825, 71.557656));
        ARRAY_JAIL_ROAD.add(new LatLng(34.005207, 71.556838));
        ARRAY_JAIL_ROAD.add(new LatLng(34.004440, 71.555862));
        ARRAY_JAIL_ROAD.add(new LatLng(34.003886, 71.555157));
        ARRAY_JAIL_ROAD.add(new LatLng(34.003757, 71.554736));
        ARRAY_JAIL_ROAD.add(new LatLng(34.003421, 71.554028));
        ARRAY_JAIL_ROAD.add(new LatLng(34.003065, 71.553639));
        ARRAY_JAIL_ROAD.add(new LatLng(34.002373, 71.553073));
        ARRAY_JAIL_ROAD.add(new LatLng(34.002248, 71.552921));
        ARRAY_JAIL_ROAD.add(new LatLng(34.001854, 71.552449));
        ARRAY_JAIL_ROAD.add(new LatLng(34.001318, 71.551733));
        ARRAY_JAIL_ROAD.add(new LatLng(34.001293, 71.551575));
        ARRAY_JAIL_ROAD.add(new LatLng(34.001071, 71.551122));
        ARRAY_JAIL_ROAD.add(new LatLng(34.000925, 71.550831));
        ARRAY_JAIL_ROAD.add(new LatLng(34.000698, 71.550402));
        ARRAY_JAIL_ROAD.add(new LatLng(34.000576, 71.550176));
        ARRAY_JAIL_ROAD.add(new LatLng(34.000191, 71.549530));
        ARRAY_JAIL_ROAD.add(new LatLng(34.000055, 71.549294));
        ARRAY_JAIL_ROAD.add(new LatLng(33.999858, 71.548941));
        ARRAY_JAIL_ROAD.add(new LatLng(33.999438, 71.548150));
        ARRAY_JAIL_ROAD.add(new LatLng(33.999411, 71.547988));
        ARRAY_JAIL_ROAD.add(new LatLng(33.999480, 71.547723));
        ARRAY_JAIL_ROAD.add(new LatLng(33.999665, 71.547494));
        ARRAY_JAIL_ROAD.add(new LatLng(33.999952, 71.547198));
        ARRAY_JAIL_ROAD.add(new LatLng(34.000204, 71.546935));
        ARRAY_JAIL_ROAD.add(new LatLng(34.000434, 71.546722));
        ARRAY_JAIL_ROAD.add(new LatLng(34.000582, 71.546649));

        return ARRAY_JAIL_ROAD;
    }

    public static ArrayList<LatLng> getArrayUniversityRoad() {
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997592, 71.490824));//define rest of the path one by one
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997604, 71.490243));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997604, 71.489648));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997637, 71.489027));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997637, 71.488297));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997671, 71.487648));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997682, 71.487121));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997716, 71.486512));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997749, 71.485863));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997772, 71.485201));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997816, 71.484552));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997861, 71.483876));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997917, 71.483160));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997985, 71.482417));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998041, 71.481565));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998108, 71.480713));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998175, 71.479875));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998220, 71.479024));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998242, 71.478442));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998242, 71.477848));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998254, 71.477172));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998265, 71.476496));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998242, 71.475617));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998265, 71.474766));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998254, 71.474022));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998242, 71.473373));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998197, 71.472887));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998130, 71.472278));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.998063, 71.471643));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997996, 71.471129));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997917, 71.470548));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997828, 71.469953));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997727, 71.469332));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997637, 71.468764));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997547, 71.468169));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997480, 71.467520));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997391, 71.466912));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997290, 71.466250));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997211, 71.465736));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.997088, 71.465128));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.996942, 71.464425));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.996808, 71.463681));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.996696, 71.462938));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.996606, 71.462370));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.996438, 71.461910));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.996393, 71.461573));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.996360, 71.460937));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.996360, 71.460342));
        ARRAY_UNIVERSITY_ROAD.add(new LatLng(33.996360, 71.459788));

        return ARRAY_UNIVERSITY_ROAD;
    }

    public static ArrayList<LatLng> getArrayCharsaddaRoad() {
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.031714, 71.578076));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.031562, 71.577945));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.031411, 71.577780));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.031077, 71.577344));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.031291, 71.577638));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.031112, 71.577431));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.030925, 71.577201));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.030654, 71.576884));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.030283, 71.576478));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.029894, 71.576111));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.029733, 71.576020));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.029244, 71.575752));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.029007, 71.575645));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.028569, 71.575454));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.028093, 71.575315));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.027882, 71.575257));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.027320, 71.575064));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.027062, 71.574974));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.026351, 71.574718));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.026119, 71.574626));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.025904, 71.574539));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.025629, 71.574427));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.025022, 71.574167));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.024773, 71.574056));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.024046, 71.573728));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.023530, 71.573495));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.023307, 71.573400));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.022587, 71.573121));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.022351, 71.573024));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.021593, 71.572699));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.021407, 71.572636));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.020920, 71.572446));
        ARRAY_CHARSADDA_ROAD.add(new LatLng(34.020533, 71.572336));

        return ARRAY_CHARSADDA_ROAD;
    }

    public static ArrayList<LatLng> getArrayKhyberRoad() {
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014463, 71.567379));//copy the coordinate of the starting point from above
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014480, 71.566886));//define rest of the path one by one
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014480, 71.566435));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014480, 71.565899));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014498, 71.565459));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014498, 71.564922));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014498, 71.564418));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014507, 71.563946));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014507, 71.563442));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014507, 71.562873));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014515, 71.561957));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014515, 71.561496));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014515, 71.561053));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014515, 71.560629));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014523, 71.560195));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014531, 71.559781));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014538, 71.559263));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014546, 71.558782));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014554, 71.558311));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014554, 71.557887));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014554, 71.557435));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014546, 71.556983));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014531, 71.556502));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014531, 71.556050));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014546, 71.555513));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014554, 71.555014));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014554, 71.554496));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014554, 71.554043));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014554, 71.553638));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014499, 71.553167));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014468, 71.552753));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014437, 71.552310));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014413, 71.551858));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014382, 71.551339));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014351, 71.550784));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014327, 71.550284));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014304, 71.549738));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014273, 71.549276));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014242, 71.548749));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014218, 71.548240));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014187, 71.547778));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014148, 71.547307));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014117, 71.546817));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014093, 71.546290));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014062, 71.545640));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.014031, 71.545140));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.013992, 71.544632));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.013953, 71.544066));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.013906, 71.543539));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.013867, 71.543020));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.013843, 71.542474));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.013828, 71.542229));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.013789, 71.541994));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.013679, 71.541767));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.013547, 71.541523));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.013414, 71.541287));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.013187, 71.541051));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.012859, 71.540825));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.012531, 71.540618));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.012188, 71.540401));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.011750, 71.540128));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.011243, 71.539808));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.010923, 71.539582));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.010470, 71.539271));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.010134, 71.539045));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.009946, 71.538856));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.009704, 71.538508));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.009517, 71.538187));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.009336, 71.537933));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.009064, 71.537715));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.008805, 71.537496));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.008456, 71.537223));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.008068, 71.536902));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.007737, 71.536645));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.007414, 71.536371));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.007084, 71.536098));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.006786, 71.535856));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.006495, 71.535614));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.006365, 71.535489));
        ARRAY_KHYBER_ROAD.add(new LatLng(34.006022, 71.534928));
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
