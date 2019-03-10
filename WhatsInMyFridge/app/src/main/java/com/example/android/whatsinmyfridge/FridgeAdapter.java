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


public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.FridgeItemViewHolder> {

    private final ArrayList<FridgeItem> fridgeList;

    private LayoutInflater mInflater;

    /**
     *
     * @param context
     * @param fridgeList ArrayList<FridgeItem>
     */
    public FridgeAdapter(Context context, ArrayList<FridgeItem> fridgeList) {
        mInflater = LayoutInflater.from(context);
        this.fridgeList = fridgeList;
    }

    @Override
    public FridgeItemViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.fridge_item, viewGroup, false);
        return new FridgeItemViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder( final FridgeItemViewHolder fridgeItemHolder, final int i) {
        // TODO: Complete difference() and then based on that, change the text on "Days left"
        fridgeItemHolder.itemNameView.setText(fridgeList.get(i).name);
        fridgeItemHolder.itemExpView.setText(fridgeList.get(i).expiration.toString());
        fridgeItemHolder.itemDaysLeftView.setText("" + fridgeList.get(i).daysLeft);

        // imageButton onclick Deleted Item from Fridge
        fridgeItemHolder.buttonMinusImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Deleted Item from Fridge", Toast.LENGTH_SHORT).show();
                fridgeList.remove(i);
                FridgeAdapter.this.notifyDataSetChanged();
            }
        });

        // imageButton onclick Added to Shopping List
        fridgeItemHolder.addToCartImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Added to Shopping List", Toast.LENGTH_SHORT).show();

                String name = fridgeList.get(i).name;

                ShoppingListFragment.getInstance().addToShoppingList(new ShoppingListItem(name));

                ShoppingListFragment.getInstance().refreshList();
            }
        });
    }

    @Override
    public int getItemCount() {
        return fridgeList.size();
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
            // TODO: change the view to use the right background and add textboxes
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


