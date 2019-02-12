package com.example.rabby.finalyearproject_final.signin;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rabby.finalyearproject_final.R;

public class SigninActivity extends AppCompatActivity {

    public static android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.app_container)!=null){
            if (savedInstanceState!=null){
                return;
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            SigninFragment signinFragment = new SigninFragment();
            fragmentTransaction.add(R.id.app_container,signinFragment,null);
            fragmentTransaction.commit();
        }
    }
}
