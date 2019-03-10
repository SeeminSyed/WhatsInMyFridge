package com.example.android.whatsinmyfridge;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public interface DatePickerCallback {
        void onDateSet(int day, int month, int year);
    }

    private DatePickerCallback callback;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void setCallback(DatePickerCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        callback.onDateSet(dayOfMonth, month, year);
    }
}
