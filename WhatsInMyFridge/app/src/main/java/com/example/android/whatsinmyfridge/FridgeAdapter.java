package com.example.android.whatsinmyfridge;

import android.content.Context;
import android.support.v4.app.Fragment;
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
    private Fragment fragment;

    /**
     *
     * @param context
     * @param fridgeList ArrayList<FridgeItem>
     */
    public FridgeAdapter(Context context, ArrayList<FridgeItem> fridgeList, Fragment fragment) {
        mInflater = LayoutInflater.from(context);
        this.fridgeList = fridgeList;
        this.fragment = fragment;
    }

    @Override
    public FridgeItemViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.fridge_item, viewGroup, false);
        return new FridgeItemViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder( final FridgeItemViewHolder fridgeItemHolder, final int i) {
        fridgeItemHolder.itemNameView.setText(fridgeList.get(i).name);
        fridgeItemHolder.itemExpView.setText(fridgeList.get(i).expiration.toString());
        int diff = (fridgeList.get(i)).expiration.difference();

        if(diff < 0) {
            fridgeItemHolder.expiredView.setText("Expired");
            fridgeItemHolder.expiredView.setVisibility(View.VISIBLE);
            fridgeItemHolder.itemDaysLeftView.setText("" + (0 - fridgeList.get(i).daysLeft));

            fridgeItemHolder.itemDaysLeftView.setTextColor(fragment.getActivity().getResources().getColor(R.color.holo_red_dark));
            fridgeItemHolder.itemDaysLeftLabelView.setText("Days Ago: ");
        } else if (diff == 0) {
            fridgeItemHolder.expiredView.setText("EXPIRES TODAY!");
            fridgeItemHolder.expiredView.setVisibility(View.VISIBLE);
            fridgeItemHolder.itemDaysLeftView.setText("" + (fridgeList.get(i).daysLeft));
        } else {
            fridgeItemHolder.expiredView.setVisibility(View.INVISIBLE);
            fridgeItemHolder.itemDaysLeftView.setText("" + (fridgeList.get(i).daysLeft));
        }

        // imageButton onclick Deleted Item from Fridge
        fridgeItemHolder.buttonMinusImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Deleted \"" + fridgeList.get(i).name + "\" from Fridge", Toast.LENGTH_SHORT).show();
                fridgeList.remove(i);
                FridgeAdapter.this.notifyDataSetChanged();
            }
        });

        // imageButton onclick Added to Shopping List
        fridgeItemHolder.addToCartImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Added \"" + fridgeList.get(i).name + "\" to Shopping List", Toast.LENGTH_SHORT).show();

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
        public final TextView itemDaysLeftLabelView;
        public final ImageButton buttonMinusImageButton;
        public final ImageButton addToCartImageButton;
        public final TextView expiredView;

        public FridgeItemViewHolder(View itemView, FridgeAdapter adapter) {
            super(itemView);
            itemNameView = itemView.findViewById(R.id.item_name);
            itemExpView = itemView.findViewById(R.id.item_expiration);
            itemDaysLeftView = itemView.findViewById(R.id.days_left);
            itemDaysLeftLabelView = itemView.findViewById(R.id.item_days_left_label);
            buttonMinusImageButton = itemView.findViewById(R.id.imageButton_minus);
            addToCartImageButton = itemView.findViewById(R.id.imageButton_shoppinglist);
            expiredView = itemView.findViewById(R.id.item_expired_label);
            this.mAdapter = adapter;
        }
    }
}


