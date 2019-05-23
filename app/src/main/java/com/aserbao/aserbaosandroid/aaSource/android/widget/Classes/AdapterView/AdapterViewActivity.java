package com.aserbao.aserbaosandroid.aaSource.android.widget.Classes.AdapterView;

import android.database.Cursor;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;

public class AdapterViewActivity extends BaseRecyclerViewActivity {

    @Override
    public void initGetData() {

    }

    @Override
    public void itemClickBack(View view, int position) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGetData();

    }

}
