package org.pltw.examples.timelineapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import org.pltw.examples.timelineapp.R;
import org.pltw.examples.timelineapp.timeline.Timeline;
import org.pltw.examples.timelineapp.timeline.TimelineEvent;

/**
 * Created by pieperm on 3/8/17.
 */

public class SearchFragment extends Fragment {

    private android.widget.SearchView searchView;
    private ListView searchResults;
    private Spinner searchSpinner;
    private ArrayAdapter<Timeline> timelineArrayAdapter;
    private ArrayAdapter<TimelineEvent> timelineEventArrayAdapter;
    private ArrayAdapter<BackendlessUser> userArrayAdapter;
    private static final String TAG = "SearchFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = (android.widget.SearchView) rootView.findViewById(R.id.search_view);
        searchResults = (ListView)rootView.findViewById(R.id.search_results);
        searchSpinner = (Spinner)rootView.findViewById(R.id.search_filter_spinner);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                String whereClause;
                BackendlessDataQuery query = new BackendlessDataQuery();
                final ProgressDialog dialog = ProgressDialog.show(getActivity(), "Loading", "Searching for results...");


                switch(searchSpinner.getSelectedItemPosition()) {

                    case 0:
                        Log.i(TAG, "Searching timelines");
                        whereClause = "name = '" + searchView.getQuery() + "'";
                        query.setWhereClause(whereClause);

                        Backendless.Persistence.find(Timeline.class, query, new AsyncCallback<BackendlessCollection<Timeline>>() {
                            @Override
                            public void handleResponse(BackendlessCollection<Timeline> response) {

                                timelineArrayAdapter.clear();
                                timelineEventArrayAdapter.clear();
                                userArrayAdapter.clear();

                                dialog.cancel();

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(getActivity(), fault.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        break;
                    case 1:
                        Log.i(TAG, "Searching events");
                        whereClause = "title = '" + searchView.getQuery() + "'";
                        query.setWhereClause(whereClause);

                        Backendless.Persistence.find(TimelineEvent.class, query, new AsyncCallback<BackendlessCollection<TimelineEvent>>() {
                            @Override
                            public void handleResponse(BackendlessCollection<TimelineEvent> response) {

                                timelineArrayAdapter.clear();
                                timelineEventArrayAdapter.clear();
                                userArrayAdapter.clear();

                                dialog.cancel();

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(getActivity(), fault.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        break;

                    case 2:
                        Log.i(TAG, "Searching users");
                        whereClause = "name = '" + searchView.getQuery() + "'";
                        query.setWhereClause(whereClause);

                        Backendless.Persistence.find(BackendlessUser.class, query, new AsyncCallback<BackendlessCollection<BackendlessUser>>() {
                            @Override
                            public void handleResponse(BackendlessCollection<BackendlessUser> response) {

                                timelineArrayAdapter.clear();
                                timelineEventArrayAdapter.clear();
                                userArrayAdapter.clear();

                                dialog.cancel();

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(getActivity(), fault.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

                        break;
                    default:
                        Log.wtf(TAG, "Spinner error");
                        break;

                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        return rootView;

    }

}
