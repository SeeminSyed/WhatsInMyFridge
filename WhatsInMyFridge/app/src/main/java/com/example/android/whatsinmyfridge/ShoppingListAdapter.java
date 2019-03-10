package com.example.android.whatsinmyfridge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListItemViewHolder> {

    private final ShoppingListItem[] shoppingListItems;

    private LayoutInflater mInflater;


    public ShoppingListAdapter(Context context, ShoppingListItem[] shoppingListItems) {
        mInflater = LayoutInflater.from(context);
        this.shoppingListItems = shoppingListItems;
    }

    @Override
    public ShoppingListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.shopping_list_item, viewGroup, false);
        return new ShoppingListItemViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ShoppingListItemViewHolder shoppingListItemHolder, int i) {
        shoppingListItemHolder.itemNameView.setText(shoppingListItems[i].name);

        // imageButton onclick
        shoppingListItemHolder.buttonMinusImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "fight me", Toast.LENGTH_SHORT).show();
            }
        });

        // imageButton onclick
        shoppingListItemHolder.addToCartImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "i win", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingListItems.length;
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


