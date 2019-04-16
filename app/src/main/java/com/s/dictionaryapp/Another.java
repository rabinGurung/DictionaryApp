package com.s.dictionaryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Another extends AppCompatActivity {
    public static final String MainActivitytag = "MainActivitytag";
    private TextView tv;
    private Button btnback, exit;
    private AlertDialog.Builder builder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another);
        tv = findViewById(R.id.tvresult);
        btnback = findViewById(R.id.btnback);
        builder = new AlertDialog.Builder(this);
        exit = findViewById(R.id.exit);

        if(getIntent().getStringExtra(MainActivity.KEY1) != null){
            String message = getIntent().getStringExtra(MainActivity.KEY1);
            tv.setText("You selected this game : "+message);
        }else{
            tv.setText("Something Wrong: Could not get value from former activty");
        }



        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Another.this,MainActivity.class);
                i.putExtra(MainActivitytag,"Thank you for your selection");
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Exit the App");
                builder.setMessage("Do you really wanna exit the APP");
                builder.setCancelable(true);

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Another.this,"You exited",Toast.LENGTH_SHORT).show();
                        Another.super.finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(Another.this,"You Backed",Toast.LENGTH_SHORT).show();
                    }
                });


                AlertDialog alert = builder.create();

                alert.show();
            }
        });


    }
}
