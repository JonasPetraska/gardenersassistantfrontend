package com.psi.gardenerasistance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.title_activity_home_screen);
        InitializeMainView();
    }

    private void AddGardenOwnerButtons(LinearLayout layout){
        // Dummy
        for(int i = 1; i < 5; i++) {
            Button button = new Button(this);
            button.setText(getString(R.string.gardenButtonTitle) + " #" + i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OpenLoggedInHomeScreen();
                }
            });
            layout.addView(button);
        }
    }

    private void AddGardenWorkerButtons(LinearLayout layout){
        // Dummy
        for(int i = 1; i < 5; i++) {
            Button button = new Button(this);
            button.setText(getString(R.string.gardenButtonTitle) + " #" + i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OpenLoggedInHomeScreen();
                }
            });
            layout.addView(button);
        }
    }

    private void InitializeMainView(){

        LinearLayout ownersButtonView = findViewById(R.id.ownerButtonsView);
        AddGardenOwnerButtons(ownersButtonView);

        LinearLayout gardenersButtonView = findViewById(R.id.gardenersButtonsView);
        AddGardenWorkerButtons(gardenersButtonView);
    }

    private void OpenLoggedInHomeScreen(){
        Intent i = new Intent(HomeScreenActivity.this, NavigationDrawerActivity.class);
        startActivity(i);
    }

}
