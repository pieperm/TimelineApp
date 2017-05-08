package org.pltw.examples.timelineapp.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.pltw.examples.timelineapp.R;
import org.pltw.examples.timelineapp.activities.LoginActivity;
import org.pltw.examples.timelineapp.activities.MainActivity;

/**
 * Created by pieperm on 3/8/17.
 */

public class SettingsFragment extends Fragment {

    private Button logoutButton;
    public static final String TAG = "SettingsFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        logoutButton = (Button)rootView.findViewById(R.id.settings_logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        MainActivity.createAlertDialog(getActivity(), "Log Out", "Are you sure you want to log out?", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent logoutIntent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(logoutIntent);
                                Log.i(TAG, "Logged out");
                            }
                        });
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                    }
                });
            }
        });

        return rootView;

    }
}
