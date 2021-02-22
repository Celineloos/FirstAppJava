package com.example.firstappjava;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
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
    public static final String POKEMON_CHANNEL = "POKEMON_CHANNEL";

    FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            NotificationChannel channel = new NotificationChannel(POKEMON_CHANNEL, "Name", NotificationManager.IMPORTANCE_DEFAULT);
//            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            manager.createNotificationChannel(channel);
//        }
//
//        Intent intent = new Intent(this, PokemonService.class);
//        startService(intent);
    }

    BroadcastReceiver receiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            int favorite = intent.getIntExtra("favorite", 0);

            TextView textView = findViewById(R.id.textViewService);
            ImageView imageView = findViewById(R.id.imageViewService);

            textView.setText(PokemonService.getName(favorite));
            imageView.setImageResource(PokemonService.getIcon(favorite));
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(PokemonService.POKEMON_BROADCAST);

        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.toolbar_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

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

//        SearchView searchView = (SearchView) menu.findItem(R.id.toolbar_search).getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                setPokemon(query);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });

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