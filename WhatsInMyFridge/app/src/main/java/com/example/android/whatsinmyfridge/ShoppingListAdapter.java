package com.example.android.whatsinmyfridge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListItemViewHolder> {

    private final ArrayList<ShoppingListItem> shoppingListItems;

    private LayoutInflater mInflater;


    public ShoppingListAdapter(Context context, ArrayList<ShoppingListItem> shoppingListItems) {
        mInflater = LayoutInflater.from(context);
        this.shoppingListItems = shoppingListItems;
    }

    @Override
    public ShoppingListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.shopping_list_item, viewGroup, false);
        return new ShoppingListItemViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(final ShoppingListItemViewHolder shoppingListItemHolder, final int i) {
        shoppingListItemHolder.itemNameView.setText(shoppingListItems.get(i).name);

        // imageButton onclick Deleted Item from Shopping List
        shoppingListItemHolder.buttonMinusImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Deleted Item from Shopping List", Toast.LENGTH_SHORT).show();
                shoppingListItems.remove(i);
                ShoppingListAdapter.this.notifyDataSetChanged();
            }
        });

        // imageButton onclick Added to Shopping Cart
        shoppingListItemHolder.addToCartImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Added to Shopping Cart", Toast.LENGTH_SHORT).show();
//
//                String name = shoppingListItems.get(i).name;
//                // TODO: need the prompt to add the expiry date
//                myDate date = null;
//
//                FridgeFragment.getInstance().addToFridge(new FridgeItem(name, date));
//                shoppingListItems.remove(i);
//                ShoppingListAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingListItems.size();
    }

    // View holder
    public class ShoppingListItemViewHolder extends RecyclerView.ViewHolder {

        final ShoppingListAdapter mAdapter;

        public final TextView itemNameView;
        public final ImageButton buttonMinusImageButton;
        public final ImageButton addToCartImageButton;

        public ShoppingListItemViewHolder(View itemView, ShoppingListAdapter adapter) {
            super(itemView);
            itemNameView = itemView.findViewById(R.id.item_name_shoppinglist);
            buttonMinusImageButton = itemView.findViewById(R.id.imageButton_minus);
            addToCartImageButton = itemView.findViewById(R.id.imageButton_shoppinglist);
            this.mAdapter = adapter;
        }
    }
}


