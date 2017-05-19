package com.example.jay.spt.station;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class StationContent {

    /**
     * An array of stations.
     */
    public static final List<StationItem> ITEMS = new ArrayList<StationItem>();

    /**
     * A map of stations, by ID.
     */
    public static final Map<String, StationItem> ITEM_MAP = new HashMap<String, StationItem>();


    public static void setItems(List<StationItem> items) {
        ITEMS.clear();
        ITEM_MAP.clear();

        ITEMS.addAll(items);

        for (StationItem item : items) {
            ITEM_MAP.put(item.id, item);
        }
        Log.i("TAG", ITEMS.toString());
    }

    /**
     * Classes for the location service
     */
    public static class StationItem {
        public String id;
        public String name;
        public Coordinate coordinate;

        @Override
        public String toString() {
            return name;
        }
    }

    public class Stations {
        public List<StationItem> stations;
    }

    public class Coordinate {
        public double x;
        public double y;
    }


}

