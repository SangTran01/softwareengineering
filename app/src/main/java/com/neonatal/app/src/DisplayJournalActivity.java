package com.neonatal.app.src;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayJournalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_journal);

        Intent intent = getIntent();

        TextView body = (TextView) findViewById(R.id.tv_body);
        TextView date = (TextView) findViewById(R.id.tv_date);
        TextView milestone = (TextView) findViewById(R.id.tv_milestone);

        ImageView image = (ImageView) findViewById(R.id.img_journal_image);

        body.setText(intent.getStringExtra("body"));
        date.setText(intent.getStringExtra("date"));
        if(intent.hasExtra("image"))
        {
            byte[] decodedString = Base64.decode(intent.getStringExtra("image"), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            image.setImageBitmap(decodedByte);
        }
        if(intent.hasExtra("milestone"))
        {
            milestone.setVisibility(View.VISIBLE);
            milestone.setText("Milestone: " + intent.getStringExtra("milestone"));
        }

    }
}
