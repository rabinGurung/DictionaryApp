package com.s.dictionaryapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String[] GAMES = {"BattleRoyale", "PUBG",
                                            "Tpp", "FORTNITE",
                                            "shooter", "CSGO",
                                            "Moba", "DOTA",
                                            "Soccer", "FIFA"};
    private ListView list;
    public static final String KEY1 = "KEY1";
    private Map<String, String> dictionary;
    private static final int AnotherActivityRequestCode = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);
        dictionary = new HashMap<>();


        for (int i = 0; i < GAMES.length; i += 2) {
            dictionary.put(GAMES[i], GAMES[i + 1]);
        }
        ArrayAdapter adap = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, new ArrayList(dictionary.keySet())
        );


        list.setAdapter(adap);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

                if (dictionary.containsKey(selectedItem)) {
                    String game = dictionary.get(selectedItem);

                    Intent i = new Intent(MainActivity.this, Another.class);
                    i.putExtra(KEY1, game);
                    startActivityForResult(i,AnotherActivityRequestCode);

                }

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(requestCode == AnotherActivityRequestCode){
                if(resultCode == Activity.RESULT_OK){
                    String message1 = data.getStringExtra(Another.MainActivitytag);
                    Toast.makeText(MainActivity.this,message1,Toast.LENGTH_SHORT).show();
                }else if(resultCode == Activity.RESULT_CANCELED){
                    Toast.makeText(MainActivity.this,"You came back",Toast.LENGTH_SHORT).show();
                }

            }
    }
}
