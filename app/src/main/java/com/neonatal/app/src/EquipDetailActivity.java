package com.neonatal.app.src;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nenad on 2017-12-12.
 */

public class EquipDetailActivity extends DrawerActivity {

    Toolbar mToolbar;
    ImageView equipImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        super.onCreateDrawer();

        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_equip_details);
        View inflated = stub.inflate();

        mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(mToolbar);

        equipImage = findViewById(R.id.photoView);
        TextView textView = findViewById(R.id.textViewEquip);

        Bundle bundle = getIntent().getExtras();
        mToolbar.setTitleTextColor(Color.WHITE);

        if(bundle != null) {
            getSupportActionBar().setTitle(bundle.getString("EquipName"));

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Cardiopulmonary Monitor")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_cardiopulmonary_monitor));

                textView.setText(R.string.equip_cm_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Central Venous Catheter")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_central_venous_catheter));
                textView.setText(R.string.equip_cvc_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Continuous Positive Airway Pressure")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_continuous_pos_airway_pressure));
                textView.setText(R.string.equip_cpap_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Incubator")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_incubator));
                textView.setText(R.string.equip_i_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Nasogastric Tube")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_nasogastric_tube));
                textView.setText(R.string.equip_nt_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Peripheral Intravenous Catheter")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_peripheral_intrav_catheter));
                textView.setText(R.string.equip_pic_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Phototherapy")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_phototherapy));
                textView.setText(R.string.equip_p_summary);
            }

            if(mToolbar.getTitle().toString().equalsIgnoreCase("Pulse Oximeter")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_pulse_oximeter));
                textView.setText(R.string.equip_po_summary);
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Umbilical Catheter")) {
                equipImage.setImageDrawable(ContextCompat.getDrawable(EquipDetailActivity.this,
                        R.drawable.equip_umbilical_catheter));
                textView.setText(R.string.equip_uc_summary);
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Business Clerk")) {
                textView.setText(getString(R.string.define_bus_clerk));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Neonatologist")) {
                textView.setText(getString(R.string.define_neonatologist));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Neonatal Fellow")) {
                textView.setText(getString(R.string.define_neo_fellow));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Resident")) {
                textView.setText(getString(R.string.define_resident));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Nurse Practitioner (NP)")) {
                textView.setText(getString(R.string.define_np));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Registered Nurse (RN)")) {
                textView.setText(getString(R.string.define_rn));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Charge Nurse/Resource Nurse (RN)")) {
                textView.setText(getString(R.string.define_charge_nurse));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Respiratory Therapist (RT)")) {
                textView.setText(getString(R.string.define_rt));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Social Worker")) {
                textView.setText(getString(R.string.define_social_worker));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Pharmacist")) {
                textView.setText(getString(R.string.define_pharmacist));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Occupational Therapist (OT)")) {
                textView.setText(getString(R.string.define_ot));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Dietitian")) {
                textView.setText(getString(R.string.define_dietitian));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Lactation Consultant")) {
                textView.setText(getString(R.string.define_lac_consult));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Health Care Aide")) {
                textView.setText(getString(R.string.define_health_care_aide));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Environmental Aide (EA)")) {
                textView.setText(getString(R.string.define_ea));
            }
            if(mToolbar.getTitle().toString().equalsIgnoreCase("Learners")) {
                textView.setText(getString(R.string.define_learners));
            }
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Kangaroo Care")) {
                textView.setText(R.string.define_kangaroo_care);
            }
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Hand Hugging")) {
                textView.setText(R.string.define_hand_hugging);
            }
        }
    }
}
