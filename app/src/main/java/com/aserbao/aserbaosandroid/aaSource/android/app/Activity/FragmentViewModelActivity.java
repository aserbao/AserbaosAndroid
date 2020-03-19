package com.aserbao.aserbaosandroid.aaSource.android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.ui.fragmentviewmodel.FragmentViewModelFragment;

public class FragmentViewModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_model_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FragmentViewModelFragment.newInstance())
                .commitNow();
        }
    }
}
