package com.example.tickerwatchlistmanager_demille;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TickerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TickerListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ListView listView;
    private ArrayList<String> mainList;
    private ArrayAdapter<String> adapter;
    private String mParam1;
    private String mParam2;

    public TickerListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TickerListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TickerListFragment newInstance(String param1, String param2) {
        TickerListFragment fragment = new TickerListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ticker_list, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        listView = view.findViewById(R.id.mainList);

        mainList = new ArrayList<>();
        mainList.add("AAPL");
        mainList.add("NEE");
        mainList.add("DIS");

        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, mainList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, itemView, position, id) -> {
            String ticker = mainList.get(position);
            String url = "https://seekingalpha.com/symbol/" + ticker;

            InfoWebFragment infoWebFragment = (InfoWebFragment) getParentFragmentManager()
                    .findFragmentById(R.id.fragmentContainerView2);

            if (infoWebFragment != null) {
                //change later?
                infoWebFragment.loadUrl(url);
            }
        });
    }

    public void addTicker(String ticker) {
        if (mainList.size() < 6) {
            mainList.add(ticker);
        } else {
            mainList.set(5, ticker);
        }
        adapter.notifyDataSetChanged();
    }

}