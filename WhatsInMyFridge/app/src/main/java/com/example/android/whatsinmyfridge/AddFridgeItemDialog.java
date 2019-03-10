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
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class AddFridgeItemDialog extends DialogFragment implements DatePickerFragment.DatePickerCallback {

    public interface AddFridgeItemCallback {
        void onAddItem(String name, myDate date);
    }

    private AddFridgeItemCallback callback;
    private EditText foodNameEditText;
    private TextView expiryDateTextView;

    public void setCallback(AddFridgeItemCallback callback) {
        this.callback = callback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        final View dialog = inflater.inflate(R.layout.add_item_dialog, null);
        foodNameEditText = dialog.findViewById(R.id.et_food_name);
        expiryDateTextView = dialog.findViewById(R.id.tv_expiry_date);
        // get String for current day
        final Calendar calendar = Calendar.getInstance();
        expiryDateTextView.setText(
                ((calendar.get(Calendar.DAY_OF_MONTH) > 9) ? calendar.get(Calendar.DAY_OF_MONTH): "0" + calendar.get(Calendar.DAY_OF_MONTH)) + "/" +
                ((calendar.get(Calendar.MONTH) + 1) > 9 ? (calendar.get(Calendar.MONTH) + 1): "0" + (calendar.get(Calendar.MONTH) + 1))
                + "/" + calendar.get(Calendar.YEAR));

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
                        String name = foodNameEditText.getText().toString();
                        String dateString = expiryDateTextView.getText().toString();
                        String[] dateStrings = dateString.split("/");
                        myDate date = new myDate(Integer.parseInt(dateStrings[0]),
                                Integer.parseInt(dateStrings[1]),
                                Integer.parseInt(dateStrings[2]));
                        callback.onAddItem(name, date);
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
        expiryDateTextView.setText(((day > 9) ? day: "0" + day)
                + "/" + ((month > 9) ? month: "0"+month)
                + "/" + year);
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