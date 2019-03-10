package com.example.android.whatsinmyfridge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FridgeFragment extends Fragment {

    private FridgeItem[] mItems;
    private RecyclerView mRecyclerView;
    private FridgeAdapter mAdapter;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        // this is data for recycler view
        FridgeItem[] itemsData = {
                new FridgeItem("Indigo", new myDate(1, 2, 2019)),
                new FridgeItem("Red", new myDate(3, 5, 2011)),
                new FridgeItem("Blue", new myDate(3, 5, 2017)),
                new FridgeItem("Green", new myDate(3, 5, 2014)),
                new FridgeItem("Amber", new myDate(3, 5, 2016)),
                new FridgeItem("Deep Orange", new myDate(3, 5, 2020))};

        View rootView = inflater.inflate(R.layout.fragment_fridge, container, false);

        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        // Create an adapter and supply the data to be displayed.
        mAdapter = new FridgeAdapter(getActivity(), itemsData);

        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        view.findViewById(R.id.add_item_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AddFridgeItemDialog dialogFragment = new AddFridgeItemDialog();
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
//                if (prev != null) {
//                    ft.remove(prev);
//                }
//                ft.addToBackStack(null);
//
//                dialogFragment.show(ft, "dialog");
//            }
//        });
    }
}
