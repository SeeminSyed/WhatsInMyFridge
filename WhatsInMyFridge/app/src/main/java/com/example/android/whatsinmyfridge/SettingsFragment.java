package com.example.android.whatsinmyfridge;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SettingsFragment extends Fragment {

    public final static String NOTIFICATIONS_ON_KEY = "notifications_on";
    public final static String NOTIFICATIONS_DAYS_PRIOR_KEY = "notifications_days_prior";

    private boolean firstShow = true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        firstShow = true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ToggleButton toggle = view.findViewById(R.id.togglebutton);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(NOTIFICATIONS_ON_KEY, true);
                    editor.apply();
                    Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
                } else {
                    // The toggle is disabled
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(NOTIFICATIONS_ON_KEY, false);
                    editor.apply();
                    Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
                }
            }
        });


        EditText editText = view.findViewById(R.id.daysField);

        editText.setText("" + sharedPreferences.getInt(NOTIFICATIONS_DAYS_PRIOR_KEY, 1));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(firstShow) {
                    firstShow = false;
                    return;
                }
                // The toggle is disabled
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                try {
                    editor.putInt(NOTIFICATIONS_DAYS_PRIOR_KEY, Integer.parseInt(s.toString()));
                    editor.apply();
                    Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        super.onViewCreated(view, savedInstanceState);
    }
}
