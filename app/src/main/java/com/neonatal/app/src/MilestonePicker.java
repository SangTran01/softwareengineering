package com.neonatal.app.src;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.neonatal.app.src.database.AppDatabase;
import com.neonatal.app.src.entity.Milestone;

import java.util.ArrayList;
import java.util.List;

public class MilestonePicker extends DialogFragment {


    public interface MilestoneListener{
        public void returnMilestone(String milestone);
    }

    private MilestoneListener mLisenter;
    private ArrayList<String> milestones;

    public Dialog onCreateDialog (Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AppDatabase db = AppDatabase.getAppDatabase(getContext());
        milestones = new ArrayList<String>();

        List<Integer> exsistingMilestones = db.journalEntryDAO().getExsistingMilestones();
        Log.d("debug",""+exsistingMilestones.size());
        for(int i : exsistingMilestones){
            String stone = db.milestoneDAO().getMilestoneName(i);
            milestones.add(stone) ;
        }
        Log.d("debug2",""+milestones.size());
        mLisenter = (MilestoneListener) getActivity();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, milestones);

        builder
                .setTitle("Search For Milestone")
                .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), milestones.get(which), Toast.LENGTH_SHORT).show();
                        mLisenter.returnMilestone(milestones.get(which));
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }


}
