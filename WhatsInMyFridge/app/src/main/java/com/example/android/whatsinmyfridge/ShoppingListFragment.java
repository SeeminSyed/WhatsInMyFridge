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
        mItems.add(new ShoppingListItem("Pineapple"));
        mItems.add(new ShoppingListItem("Grape Fruit"));
        mItems.add(new ShoppingListItem("Blue Berries"));
        mItems.add(new ShoppingListItem("Orange Juice"));
        mItems.add(new ShoppingListItem("Ketchup"));
        mItems.add(new ShoppingListItem("Cereal"));

        View rootView = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        rootView.findViewById(R.id.add_shopping_list_item_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddShoppingListItemDialog dialogFragment = new AddShoppingListItemDialog();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("add-shopping-dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                dialogFragment.show(ft, "add-shopping-dialog");
            }
        });

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
        refreshList();
    }

    public void refreshList() {
        mAdapter.notifyDataSetChanged();
    }
}
