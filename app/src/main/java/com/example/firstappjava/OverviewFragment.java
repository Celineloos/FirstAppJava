package com.example.firstappjava;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OverviewFragment extends Fragment {

//    public OverviewFragment() {
//        super(R.layout.fragment_overview);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_overview, container, false);

        String[] pokemons = {"Bulbasaur", "Dragon", "Pikachu"};

        ListView listView = view.findViewById(R.id.listView2);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, pokemons);
        listView.setAdapter(arrayAdapter);

        return view;
    }
}