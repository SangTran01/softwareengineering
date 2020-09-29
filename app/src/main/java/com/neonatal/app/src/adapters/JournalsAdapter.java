package com.neonatal.app.src.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.neonatal.app.src.entity.JournalEntry;

import java.util.ArrayList;

/**
 * Created by Owen Bryan on 1/7/2018.
 */

public class JournalsAdapter extends ArrayAdapter<JournalEntry> {
    ArrayList<JournalEntry> data = new ArrayList<>();

    public JournalsAdapter(Context context, ArrayList<JournalEntry> data) {
        super(context,0,data);
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JournalEntry journal = data.get(position);
        String entry = journal.getId() + " " + journal.getBodyText();

        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(android.R.layout.simple_list_item_1, null);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(entry);
        return view;
    }
}
