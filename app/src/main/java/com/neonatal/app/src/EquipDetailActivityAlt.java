package com.neonatal.app.src;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class EquipDetailActivityAlt extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView equipImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_detail_alt);

        equipImage = findViewById(R.id.photoView);
        mToolbar = findViewById(R.id.toolbar2);
        TextView tvLevel1 = findViewById(R.id.tv_left_1);
        TextView tvLevel2 = findViewById(R.id.tv_left_2);
        TextView tvLevel3 = findViewById(R.id.tv_left_3);

        TextView tvText1 = findViewById(R.id.tv_text_1);
        TextView tvText2 = findViewById(R.id.tv_text_2);
        TextView tvText3 = findViewById(R.id.tv_text_3);

        Bundle bundle = getIntent().getExtras();
        mToolbar.setTitleTextColor(Color.WHITE);

        if (bundle != null) {
            mToolbar.setTitle(bundle.getString("EquipName"));

            if (mToolbar.getTitle().toString().equalsIgnoreCase("Levels of Care")) {

                tvLevel1.setText("Level 1");
                tvLevel2.setText("Level 2");
                tvLevel3.setText("Level 3");

                tvText1.setText(getString(R.string.define_LOC1));
                tvText2.setText(getString(R.string.define_LOC2));
                tvText3.setText(getString(R.string.define_LOC3));
            }
        }
    }
}
