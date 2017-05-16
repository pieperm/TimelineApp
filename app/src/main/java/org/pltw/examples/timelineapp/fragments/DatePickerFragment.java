package org.pltw.examples.timelineapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import org.pltw.examples.timelineapp.R;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static android.R.string.ok;

/**
 * Created by pieperm on 5/15/17.
 */

public class DatePickerFragment extends DialogFragment {

    public static final String DATE_ARGUMENT = "Event Date";
    private static GregorianCalendar eventDate;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_event_date, null);
        DatePicker datePicker = (DatePicker)getActivity().findViewById(R.id.dialog_event_date_picker);

        int year = datePicker.getYear();
        int month = datePicker.getMonth() + 1;
        int day = datePicker.getDayOfMonth();

        eventDate = new GregorianCalendar(year, month, day);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Event Date")
                .setPositiveButton(ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        eventDate = new GregorianCalendar(Locale.US);
                    }
                })
                .create();

    }

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(DATE_ARGUMENT, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static GregorianCalendar getDate() {
        return eventDate;
    }

}
