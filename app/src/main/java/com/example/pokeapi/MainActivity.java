package com.example.pokeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements Adapter.onItemClickListener,FragmentPokemonInfo.OnClose {

    TextView text_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("Pokemon");
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, Fragment_Recycler.newInstance()).commit();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemClick(String name) {
        FragmentPokemonInfo fragmentPokemonInfo = FragmentPokemonInfo.newInstance() ;
        fragmentPokemonInfo.setId(name);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout,fragmentPokemonInfo).addToBackStack("tag").commit();
        text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText(name.substring(0,1).toUpperCase()+name.substring(1));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onClose() {
        text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("Pokemon");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

}
