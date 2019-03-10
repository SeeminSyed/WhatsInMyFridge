package com.example.android.whatsinmyfridge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ShoppingListFragment extends Fragment {

    private ArrayList<ShoppingListItem> mItems = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ShoppingListAdapter mAdapter;

    private static ShoppingListFragment instance = null;

    public static ShoppingListFragment getInstance() {
        if(instance == null) {
            instance = new ShoppingListFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        // this is data for recycler view
        mItems.add(new ShoppingListItem("Indigo"));
        mItems.add(new ShoppingListItem("Red"));
        mItems.add(new ShoppingListItem("Blue"));
        mItems.add(new ShoppingListItem("Green"));
        mItems.add(new ShoppingListItem("Amber"));
        mItems.add(new ShoppingListItem("Deep Orange"));

        View rootView = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_shoppinglist);

        // Create an adapter and supply the data to be displayed.
        mAdapter = new ShoppingListAdapter(getActivity(), mItems, this);

        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;

    }

    public void addToShoppingList(ShoppingListItem item) {
        mItems.add(item);
    }

    public void refreshList() {
        mAdapter.notifyDataSetChanged();
    }
}
