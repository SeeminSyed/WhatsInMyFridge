package com.example.android.whatsinmyfridge;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FridgeFragment extends Fragment implements AddFridgeItemDialog.AddFridgeItemCallback {

    private ArrayList<FridgeItem> mItems = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FridgeAdapter mAdapter;

    private static FridgeFragment instance = null;

    public static FridgeFragment getInstance() {
        if(instance == null) {
            instance = new FridgeFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        // this is data for recycler view
        mItems.add(new FridgeItem("Apple", new myDate(1, 2, 2019)));
        mItems.add(new FridgeItem("Banana", new myDate(3, 3, 2019)));
        mItems.add(new FridgeItem("Grapes", new myDate(4, 4, 2019)));
        mItems.add( new FridgeItem("Bread", new myDate(19, 3, 2019)));
        mItems.add(new FridgeItem("Milk", new myDate(23, 2, 2019)));
        mItems.add(new FridgeItem("Waffles", new myDate(27, 1, 2019)));
        mItems.add(new FridgeItem("Kiwi", new myDate(8, 2, 2019)));

        View rootView = inflater.inflate(R.layout.fragment_fridge, container, false);

        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        // Create an adapter and supply the data to be displayed.
        mAdapter = new FridgeAdapter(getActivity(), mItems, this);

        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Collections.sort(mItems);
        refreshList();

        return rootView;
    }


    public void addToFridge(FridgeItem item) {
        mItems.add(item);
        Collections.sort(mItems);

        // check if need to push notification
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if(sharedPreferences.getBoolean(SettingsFragment.NOTIFICATIONS_ON_KEY, true)) {
            Date d = new Date();
            Date expiryDate = new Date(item.expiration.year - 1900, item.expiration.month, item.expiration.day);
            long difference = expiryDate.getTime() - d.getTime();

            int daysUntilExpiry = (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS) + 1;
            if(daysUntilExpiry >= 0 && daysUntilExpiry < sharedPreferences.getInt(SettingsFragment.NOTIFICATIONS_DAYS_PRIOR_KEY, 1)) {

                // Create an explicit intent for an Activity in your app
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "abc")
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle("Expiry Notification")
                        .setContentText("\"" + item.name + "\" is expiring in " + daysUntilExpiry + (daysUntilExpiry == 1 ? " day" : " days"))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        // Set the intent that will fire when the user taps the notification
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                // create notification
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());

                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(1, builder.build());
            }
        }

        refreshList();
    }

    public void refreshList() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.add_fridge_item_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAddFridgeItemDialog();
            }
        });
    }

    private void createAddFridgeItemDialog() {
        AddFridgeItemDialog dialogFragment = new AddFridgeItemDialog();
        dialogFragment.setCallback(FridgeFragment.this);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        dialogFragment.show(ft, "dialog");
    }

    @Override
    public void onAddItem(String name, myDate date) {
        if(name.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage("Food Name cannot be empty")
                    .setTitle("Alert")
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            createAddFridgeItemDialog();

                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            addToFridge(new FridgeItem(name, date));
        }
    }

}

