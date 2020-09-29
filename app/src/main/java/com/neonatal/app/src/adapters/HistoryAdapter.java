package com.neonatal.app.src.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.neonatal.app.src.DataEntryActivity;
import com.neonatal.app.src.R;
import com.neonatal.app.src.classes.Converters;
import com.neonatal.app.src.classes.PatientDatesEvents;
import com.neonatal.app.src.entity.DataEntry;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryAdapter extends ArrayAdapter<PatientDatesEvents> {
    private Context context;
    private ArrayList<PatientDatesEvents> datesEvents;

    public HistoryAdapter(Context context, int resource, ArrayList<PatientDatesEvents> datesEvents) {
        super(context, resource, datesEvents);
        this.context = context;
        this.datesEvents = datesEvents;
    }

    @Override
    public int getCount() {
        return datesEvents.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View v;
        int type = getItemViewType(position);
        final PatientDatesEvents patientDatesEvents = datesEvents.get(position);
        if (convertView == null) {
            if (type == 0) {
                // Inflate the layout with image
                v = LayoutInflater.from(context).inflate(R.layout.patient_date_list_item, parent, false);

                TextView tv_date = v.findViewById(R.id.tv_date);

                DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
                Date date = null;
                try {
                    date = (Date) formatter.parse(patientDatesEvents.getEventDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat newFormat = new SimpleDateFormat("MMMM dd yyyy");
                String finalString = newFormat.format(date);

                tv_date.setText(finalString);
            } else {
                v = LayoutInflater.from(context).inflate(R.layout.patient_history_list_item, parent, false);

                TextView tv_height = v.findViewById(R.id.tv_height);
                TextView tv_weight = v.findViewById(R.id.tv_weight);
                TextView tv_head = v.findViewById(R.id.tv_hd_circ);

                List<String> entries = Converters.fromString(patientDatesEvents.dataEntry.getValues());

                tv_height.setText(entries.get(0));
                tv_weight.setText(entries.get(1));
                tv_head.setText(entries.get(2));

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(context, "DataEntry ID: " +
//                                patientDatesEvents.dataEntry.getId(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, DataEntryActivity.class);
                        intent.putExtra("eventID", patientDatesEvents.dataEntry.getEventId());
                        intent.putExtra("dataEntryID", patientDatesEvents.dataEntry.getId());
                        context.startActivity(intent);
                    }
                });
            }
        }
        //convertView not null
        //so just set values to views like above no need to inflate
        else {
            v = convertView;

            if (type == 0) {

                TextView tv_date = v.findViewById(R.id.tv_date);

                DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
                Date date = null;
                try {
                    date = (Date) formatter.parse(patientDatesEvents.getEventDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat newFormat = new SimpleDateFormat("MMMM dd yyyy");
                String finalString = newFormat.format(date);

                tv_date.setText(finalString);
            } else {

                TextView tv_height = v.findViewById(R.id.tv_height);
                TextView tv_weight = v.findViewById(R.id.tv_weight);
                TextView tv_head = v.findViewById(R.id.tv_hd_circ);

                List<String> entries = Converters.fromString(patientDatesEvents.dataEntry.getValues());

                tv_height.setText(entries.get(0));
                tv_weight.setText(entries.get(1));
                tv_head.setText(entries.get(2));

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "DataEntry ID: " +
                                patientDatesEvents.dataEntry.getId(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, DataEntryActivity.class);
                        intent.putExtra("eventID", patientDatesEvents.dataEntry.getEventId());
                        intent.putExtra("dataEntryID", patientDatesEvents.dataEntry.getId());
                        context.startActivity(intent);
                    }
                });
            }

        }
        return v;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return (datesEvents.get(position).hasDate) ? 0 : 1;
    }
}