package com.example.firstappjava;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OverviewFragment extends Fragment {

    interface OnClickListener{
        void onItemSelected(String pokemon);
    }

    OnClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.overview_fragment, container, false);

        String[] pokemons = {"Bulbasaur", "Dragonite", "Pikachu"};

        ListView listView = view.findViewById(R.id.listView2);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, pokemons);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pokemon = arrayAdapter.getItem(position);

                if(listener != null) {
                    listener.onItemSelected(pokemon);
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listener = (MainActivity) getActivity();
    }
}