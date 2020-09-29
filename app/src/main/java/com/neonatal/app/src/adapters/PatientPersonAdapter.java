package com.neonatal.app.src.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.neonatal.app.src.classes.PatientPerson;

import java.util.ArrayList;

/**
 * Created by ufe-i134-pc10 on 06/01/2018.
 */

public class PatientPersonAdapter extends ArrayAdapter<PatientPerson> {
    ArrayList<PatientPerson> data = new ArrayList<>();

    public PatientPersonAdapter(Context context, int textViewResourceId, ArrayList<PatientPerson> data) {
        super(context,textViewResourceId,data);
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PatientPerson patient = data.get(position);
        String patientName = patient.getFirstName() + " " + patient.getLastName();

        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(android.R.layout.simple_list_item_1, null);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(patientName);
        return view;
    }
}
