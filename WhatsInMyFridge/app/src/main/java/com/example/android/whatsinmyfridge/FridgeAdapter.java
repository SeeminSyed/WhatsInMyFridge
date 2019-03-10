package com.example.android.whatsinmyfridge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.FridgeItemViewHolder> {

    private final FridgeItem[] fridgeList;

    private LayoutInflater mInflater;

    /**
     *
     * @param context
     * @param fridgeList FridgeItem[]
     */
    public FridgeAdapter(Context context, FridgeItem[] fridgeList) {
        mInflater = LayoutInflater.from(context);
        this.fridgeList = fridgeList;
    }

    @Override
    public FridgeItemViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.fridge_item, viewGroup, false);
        return new FridgeItemViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder( FridgeItemViewHolder fridgeItemHolder, int i) {
        fridgeItemHolder.itemNameView.setText(fridgeList[i].name);
        fridgeItemHolder.itemExpView.setText(fridgeList[i].expiration.toString());
        fridgeItemHolder.itemDaysLeftView.setText("" + fridgeList[i].daysLeft);

        // imageButton onclick
        fridgeItemHolder.buttonMinusImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // imageButton onclick
        fridgeItemHolder.addToCartImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "tapped", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return fridgeList.length;
    }

    // View holder
    public class FridgeItemViewHolder extends RecyclerView.ViewHolder {

        final FridgeAdapter mAdapter;

        public final TextView itemNameView;
        public final TextView itemExpView;
        public final TextView itemDaysLeftView;
        public final ImageButton buttonMinusImageButton;
        public final ImageButton addToCartImageButton;

        public FridgeItemViewHolder(View itemView, FridgeAdapter adapter) {
            super(itemView);
            itemNameView = itemView.findViewById(R.id.item_name);
            itemExpView = itemView.findViewById(R.id.item_expiration);
            itemDaysLeftView = itemView.findViewById(R.id.days_left);
            buttonMinusImageButton = itemView.findViewById(R.id.imageButton_minus);
            addToCartImageButton = itemView.findViewById(R.id.imageButton_shoppinglist);
            this.mAdapter = adapter;
        }
    }
}


