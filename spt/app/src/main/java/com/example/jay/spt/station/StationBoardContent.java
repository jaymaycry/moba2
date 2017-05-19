package com.example.jay.spt.station;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jay on 19.05.17.
 */

public class StationBoardContent {
    public static final List<StationBoardItem> ITEMS = new ArrayList<StationBoardItem>();

    public static void setItems(List<StationBoardItem> items) {
        ITEMS.clear();
        ITEMS.addAll(items.subList(0, 5));
        Log.i("StationBoardContent", ITEMS.get(0).name);
    }

    public static void clearItems() {
        ITEMS.clear();
    }

    public class TrainStop {
        public Date departure;
        public String platform;
    }

    public class StationBoardItem {
        public TrainStop stop;
        public String name;
        public String to;
    }

    public class StationBoard {
        public List<StationBoardItem> stationboard;
    }
}
