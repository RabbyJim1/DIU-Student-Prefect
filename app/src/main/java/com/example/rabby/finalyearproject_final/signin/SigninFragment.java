package com.example.rabby.finalyearproject_final.signin;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.rabby.finalyearproject_final.MainActivity;
import com.example.rabby.finalyearproject_final.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment {

    private RelativeLayout loginRellay1, loginRellay2;
    private Button registrationButton;

    //Code by Mashuir Rahman------------Log in Process
    private EditText loginEmailText;
    private EditText loginPassText;
    private Button loginBtn;
  //  private ProgressBar loginProgress;
    FirebaseAuth mAuth;


    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            loginRellay1.setVisibility(View.VISIBLE);
            loginRellay2.setVisibility(View.VISIBLE);

        }
    };


    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        registrationButton = view.findViewById(R.id.register_account_link);

        //visualization of main login UI
        loginRellay1 = view.findViewById(R.id.login_rellay1);
        loginRellay2 = view.findViewById(R.id.login_rellay2);

        loginEmailText = view.findViewById(R.id.login_email);
        loginPassText = view.findViewById(R.id.login_password);
        loginBtn = view.findViewById(R.id.login);
       // loginProgress = view.findViewById(R.id.loginProgress);

        mAuth = FirebaseAuth.getInstance();


        handler.postDelayed(runnable, 2000);

        
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // log in button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get the user information of email and pass
                String loginEmail = loginEmailText.getText().toString();
                String loginPass = loginPassText.getText().toString();

                // check the email and pass field is empty or not
                if (!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)){

                    // show the progressbar
                    //loginProgress.setVisibility(View.VISIBLE);

                    // sign in with email and passord
                    mAuth.signInWithEmailAndPassword(loginEmail,loginPass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()){
                                        Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                                        startActivity(mainIntent);

                                    }else {
                                        String error_message = task.getException().getMessage();
                                        Toast.makeText(getActivity(), "Error : "+error_message,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                  //  loginProgress.setVisibility(View.INVISIBLE);
                                }
                            });
                }
            }
        });

        ///////////////////---- Register Button ----////////////////////////

        //click register button
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SigninActivity.fragmentManager.beginTransaction()
                        .replace(R.id.app_container,new SignupFragment(),null).commit();
            }
        });
    }

}
