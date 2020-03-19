package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.ui.fragmentviewmodel;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.R;

public class FragmentViewModelFragment extends Fragment {

    private FragmentViewModelViewModel mViewModel;

    public static FragmentViewModelFragment newInstance() {
        return new FragmentViewModelFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_model_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentViewModelViewModel.class);
        // TODO: Use the ViewModel
    }

}
