package com.example.android.whatsinmyfridge;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class AddShoppingListItemDialog extends DialogFragment {

    private EditText foodNameEditText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        final View dialog = inflater.inflate(R.layout.add_item_shopping_list_dialog, null);
        foodNameEditText = dialog.findViewById(R.id.et_food_name);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialog)
                // Add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String name = foodNameEditText.getText().toString();
                        ShoppingListFragment.getInstance().addToShoppingList(new ShoppingListItem(name));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddShoppingListItemDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
