package com.example.firstappjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityFragments extends AppCompatActivity {

    public ActivityFragments() {
        super(R.layout.activity_fragments);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, DetailFragment.class, null)
                    .add(R.id.fragment_container_view2, OverviewFragment.class, null)
                    .commit();
        }
    }
}