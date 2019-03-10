package com.example.android.whatsinmyfridge;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

public class AddFridgeItemDialog extends DialogFragment implements DatePickerFragment.DatePickerCallback {

    private TextView expiryDateTextView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        final View dialog = inflater.inflate(R.layout.add_item_dialog, null);
        expiryDateTextView = dialog.findViewById(R.id.tv_expiry_date);
        // get String for current day
        Calendar calendar = Calendar.getInstance();
        expiryDateTextView.setText("" + calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR));

        dialog.findViewById(R.id.expiry_date_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setCallback(AddFridgeItemDialog.this);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("calendar-dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                datePickerFragment.show(ft, "calendar-dialog");
            }
        });

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialog)
                // Add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO add to fridge list
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddFridgeItemDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public void onDateSet(int day, int month, int year) {
        expiryDateTextView.setText("" + day + "/" + month + "/" + year);
    }


    public void openCalendarDialog(View view) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("calendar-dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        datePickerFragment.show(ft, "calendar-dialog");
    }
}