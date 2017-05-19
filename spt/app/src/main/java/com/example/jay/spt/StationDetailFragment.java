package com.example.jay.spt;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jay.spt.station.StationBoardContent;
import com.example.jay.spt.station.StationContent;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A fragment representing a single Station detail screen.
 * This fragment is either contained in a {@link StationListActivity}
 * in two-pane mode (on tablets) or a {@link StationDetailActivity}
 * on handsets.
 */
public class StationDetailFragment extends Fragment {
    private RequestQueue queue;
    private static String baseUrl = "http://transport.opendata.ch/v1/stationboard?limit=5&id=";

    private ListView listView;
    private ArrayAdapter adapter;

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The station content this fragment is presenting.
     */
    private StationContent.StationItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StationDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initiate queue for network request
        queue = Volley.newRequestQueue(this.getContext());

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = StationContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.name);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.station_board, container, false);
        listView = (ListView) rootView.findViewById(R.id.station_board_list);

        adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_2, android.R.id.text1, StationBoardContent.ITEMS) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.i("Adapter", "Juhuuuu");
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                StationBoardContent.StationBoardItem boardItem = StationBoardContent.ITEMS.get(position);

                text1.setText(boardItem.name + " nach " + boardItem.to + ((boardItem.stop.platform != null && boardItem.stop.platform.length() > 0) ? " auf Gleis " +  boardItem.stop.platform : ""));
                text2.setText("Abfahrt um " + new SimpleDateFormat("HH:mm").format(boardItem.stop.departure));
                return view;
            }
        };

        listView.setAdapter(adapter);

        StationBoardContent.clearItems();

        // Show the dummy content as text in a TextView.
        if (mItem != null) {


            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl + mItem.id,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("Network", "JSON Response is: " + response);
                            StationBoardContent.setItems(new Gson().fromJson(response, StationBoardContent.StationBoard.class).stationboard);
                            Log.i("Network", "SIZE: " + StationBoardContent.ITEMS.size());
                            Log.i("Network", "Dataset changed - informing adapter");
                            adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Network", "That didn't work!");
                }
            });

            // Add the request to the RequestQueue.
            Log.i("Network", "request URL:" + baseUrl + mItem.id);
            queue.add(stringRequest);
        }

        return rootView;
    }
}
