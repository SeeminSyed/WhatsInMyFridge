package com.example.android.whatsinmyfridge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FridgeFragment extends Fragment implements AddFridgeItemDialog.AddFridgeItemCallback {

    private ArrayList<FridgeItem> mItems = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FridgeAdapter mAdapter;

    private static FridgeFragment instance = null;

    public static FridgeFragment getInstance() {
        if(instance == null) {
            instance = new FridgeFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        // this is data for recycler view
        mItems.add(new FridgeItem("Indigo", new myDate(1, 2, 2019)));
        mItems.add(new FridgeItem("Red", new myDate(3, 5, 2011)));
        mItems.add(new FridgeItem("Blue", new myDate(3, 5, 2017)));
        mItems.add( new FridgeItem("Green", new myDate(3, 5, 2014)));
        mItems.add(new FridgeItem("Amber", new myDate(3, 5, 2016)));
        mItems.add(new FridgeItem("Deep Orange", new myDate(3, 5, 2020)));

        View rootView = inflater.inflate(R.layout.fragment_fridge, container, false);

        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        // Create an adapter and supply the data to be displayed.
        mAdapter = new FridgeAdapter(getActivity(), mItems);

        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }


    public void addToFridge(FridgeItem item) {
        mItems.add(item);
        refreshList();
    }

    public void refreshList() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.add_fridge_item_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFridgeItemDialog dialogFragment = new AddFridgeItemDialog();
                dialogFragment.setCallback(FridgeFragment.this);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                dialogFragment.show(ft, "dialog");
            }
        });
    }

    @Override
    public void onAddItem(String name, myDate date) {
        addToFridge(new FridgeItem(name, date));
    }
}
