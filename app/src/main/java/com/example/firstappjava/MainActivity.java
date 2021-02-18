package com.example.firstappjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OverviewFragment.OnClickListener {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Spinner spinner = findViewById(R.id.spinner);
        String[] pokemons = {"Bulbasaur", "Dragonite", "Pikachu"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pokemons);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String pokemon = arrayAdapter.getItem(position);
                setPokemon(pokemon);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        boolean portrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
//        if(portrait){
//            manager.beginTransaction().add(R.id.container, new OverviewFragment(), "overview").commit();
//        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.toolbar_favorite)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setTitle("Fav");
            builder.setMessage("Favorited");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();
                }
            });

            builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();
                }
            });
            builder.create().show();
        }
        return true;
    }

    @Override
    public void onItemSelected(String pokemon) {
        boolean portrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        DetailFragment detailFragment = new DetailFragment();

        if(portrait){
            detailFragment.setArguments(new Bundle());
            detailFragment.getArguments().putString("pokemon", pokemon);

            manager.beginTransaction().replace(R.id.container, detailFragment, "detail").addToBackStack(null).commit();
        }
        else{
            detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
            detailFragment.setPokemon(pokemon);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.toolbar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setPokemon(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    public void setPokemon(String pokemon) {

        ImageView imageView = findViewById(R.id.imageView2);
        TextView textView = findViewById(R.id.textView3);
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