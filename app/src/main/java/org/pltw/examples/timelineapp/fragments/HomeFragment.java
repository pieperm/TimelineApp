package org.pltw.examples.timelineapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.backendless.Backendless;

import org.pltw.examples.timelineapp.R;

/**
 * Created by pieperm on 3/8/17.
 */

public class HomeFragment extends Fragment {

    private TextView usernameText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        usernameText = (TextView)rootView.findViewById(R.id.home_profile_username);

        usernameText.setText(Backendless.UserService.CurrentUser().getProperty("name").toString());

        return rootView;

    }
}
