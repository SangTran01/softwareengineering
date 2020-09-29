package com.neonatal.app.src;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.view.View.OnClickListener;
import android.widget.TextView;

public class EquipmentActivity extends DrawerActivity implements OnClickListener {
    private Map<String, Integer> equipMap;
    private ListView equipListView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_equipment);


        super.onCreateDrawer();

        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_equipment);
        View inflated = stub.inflate();

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Definitions & Equipment");
        //toolbar.setTitle(getResources().getString(R.string.equip_name));

        String[] equipArr = getResources().getStringArray(R.array.equipmentArr);
        Arrays.asList(equipArr);

        equipListView = findViewById(R.id.equipmentList);
        equipListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, equipArr));
        getIndexList(equipArr);
        displayIndex();

        equipListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                Intent intent;
                String selectedItem = equipListView.getItemAtPosition(i).toString();
                if (selectedItem.equalsIgnoreCase("Levels of Care")) {
                    intent = new Intent(EquipmentActivity.this, EquipDetailActivityAlt.class);
                } else {
                    intent = new Intent(EquipmentActivity.this, EquipDetailActivity.class);
                }
                intent.putExtra("EquipName", equipListView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });
    }

    private void getIndexList(String[] equipArr) {
        equipMap = new LinkedHashMap<String, Integer>();

        for (int j = 0; j < equipArr.length; j++) {
            String equip = equipArr[j];
            String index = equip.substring(0, 1);

            if (equipMap.get(index) == null) {
                equipMap.put(index, j);
            }
        }
    }

    private void displayIndex() {
        LinearLayout indexLayout = (LinearLayout) findViewById(R.id.sideIndex);
        TextView txtView;
        List<String> indexList = new ArrayList<String>(equipMap.keySet());

        for (String index : indexList) {
            txtView = (TextView) getLayoutInflater().inflate(
                    R.layout.equipment_index, null);
            txtView.setText(index);
            txtView.setOnClickListener(this);
            indexLayout.addView(txtView);
        }
    }

    @Override
    public void onClick(View v) {
        TextView selectedIndex = (TextView) v;
        equipListView.setSelection(equipMap.get(selectedIndex.getText()));
    }

    public boolean onCreateMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_journal, m);
        return true;
    }


}
