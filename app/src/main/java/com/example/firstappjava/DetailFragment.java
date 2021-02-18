package com.example.firstappjava;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    ImageView imageView;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        imageView = view.findViewById(R.id.imageViewDetail);
        textView = view.findViewById(R.id.textViewDetail);


        if(getArguments() != null){
            String pokemon = getArguments().getString("pokemon");

            setPokemon(pokemon);
        }

        return view;
    }

    public void setPokemon(String pokemon) {
        textView.setText(pokemon);

        switch (pokemon){
            case "Bulbasaur":
                imageView.setImageResource(R.drawable.bulbasaur);
                break;
            case "Dragonite":
                imageView.setImageResource(R.drawable.dragonite);
                break;
            case "Pikachu":
                imageView.setImageResource(R.drawable.pikachu);
                break;
        }
    }
}