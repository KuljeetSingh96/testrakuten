package com.testrakuten;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.testrakuten.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = createMainActivityViewModel();
        setupBinding(viewModel);
    }

    private void setupBinding(MainActivityViewModel viewModel) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setClickHandler(this);
        binding.setViewModel(viewModel);
    }

    private MainActivityViewModel createMainActivityViewModel() {
        MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.counterLabel.setValue("0");
        return viewModel;
    }

    public void onHitClicked() {
        viewModel.counterLabel.setValue(String.valueOf(incrementCount()));
    }

    public native int incrementCount();

}