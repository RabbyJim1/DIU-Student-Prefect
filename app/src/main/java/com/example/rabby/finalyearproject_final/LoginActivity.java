package com.example.rabby.finalyearproject_final;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class LoginActivity extends AppCompatActivity {

    private RelativeLayout loginRellay1, loginRellay2;
    private Button registrationButton;


    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            loginRellay1.setVisibility(View.VISIBLE);
            loginRellay2.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialization
        registrationButton = findViewById(R.id.register_account_link);


        //visualization of main login UI
        loginRellay1 = findViewById(R.id.login_rellay1);
        loginRellay2 = findViewById(R.id.login_rellay2);
        handler.postDelayed(runnable, 2000);


        //click register button
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToRegistrationActivity();
            }
        });
    }

    private void sendUserToRegistrationActivity() {
        Intent registrationIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(registrationIntent);
    }
}
