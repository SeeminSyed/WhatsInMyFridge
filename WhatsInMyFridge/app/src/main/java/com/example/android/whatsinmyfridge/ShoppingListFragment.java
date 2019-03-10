package com.example.android.whatsinmyfridge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShoppingListFragment extends Fragment {

    private ShoppingListItem[] mItems;
    private RecyclerView mRecyclerView;
    private ShoppingListAdapter mAdapter;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        // this is data for recycler view
        ShoppingListItem[] itemsData = {
                new ShoppingListItem("Indigo"),
                new ShoppingListItem("Red"),
                new ShoppingListItem("Blue"),
                new ShoppingListItem("Green"),
                new ShoppingListItem("Amber"),
                new ShoppingListItem("Deep Orange")};

        View rootView = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_shoppinglist);

        // Create an adapter and supply the data to be displayed.
        mAdapter = new ShoppingListAdapter(getActivity(), itemsData);

        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;

    }
}
