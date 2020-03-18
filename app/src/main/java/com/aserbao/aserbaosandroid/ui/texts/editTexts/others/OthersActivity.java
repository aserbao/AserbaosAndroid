package com.aserbao.aserbaosandroid.ui.texts.editTexts.others;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OthersActivity extends AppCompatActivity {

    @BindView(R.id.other_edit)
    EditText mOtherEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mOtherEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OthersActivity.this, "diandiand", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
