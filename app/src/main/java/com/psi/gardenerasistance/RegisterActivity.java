package com.psi.gardenerasistance;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Models.ApplicationUser;
import Models.HttpResult;
import Services.IProfileService;
import Services.ProfileService;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_register);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameSurnameField = findViewById(R.id.name_surname);
                EditText passwordField = findViewById(R.id.password);
                EditText emailField = findViewById(R.id.email);

                String nameSurname = nameSurnameField.getText().toString();
                String password = passwordField.getText().toString();
                String email = emailField.getText().toString();

                String name = nameSurname.substring(0, nameSurname.indexOf(' '));
                String surname = nameSurname.substring(nameSurname.indexOf(' '));

                ApplicationUser user = new ApplicationUser();
                user.Email = email;
                user.FirstName = name;
                user.LastName = surname;
                user.PasswordHash = password;
                user.PhoneNumber = " ";
                user.UserName = user.Email;

                IProfileService profileService = new ProfileService();
                HttpResult<ApplicationUser> result = profileService.Register(user);
                if(result.succes)
                {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                }

                CharSequence msg = result.errors.get(0);
                Toast.makeText(view.getContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

}
