package com.example.rabby.finalyearproject_final;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.rabby.finalyearproject_final.signin.SigninActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private BottomNavigationView bottomNavigationView;
    private Fragment selectedFragment = new FragmentHome();
    private ImageButton addNewPostButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        bottomNavigationView = findViewById(R.id.mainBottomNav);
        addNewPostButton = findViewById(R.id.add_post_btn);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);

        addNewPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Add a new post!", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new FragmentHome();
                            addNewPostButton.setVisibility(View.VISIBLE);
                            break;
                        case R.id.nav_notification:
                            selectedFragment = new FragmentNotification();
                            addNewPostButton.setVisibility(View.INVISIBLE);
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new FragmentProfile();
                            addNewPostButton.setVisibility(View.INVISIBLE);
                            break;
                        case R.id.nav_more:
                            selectedFragment = new FragmentMore();
                            addNewPostButton.setVisibility(View.INVISIBLE);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };

    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            sendUserToLoginActivity();
        }
    }

    private void sendUserToLoginActivity() {
        Intent loginIntent = new Intent(MainActivity.this, SigninActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
}
