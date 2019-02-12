package com.example.rabby.finalyearproject_final.signin;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rabby.finalyearproject_final.MainActivity;
import com.example.rabby.finalyearproject_final.R;
import com.example.rabby.finalyearproject_final.SetupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private ImageView backArrow;

    private EditText regEmail;
    private EditText regPass;
    private EditText regConfirmPass;
    private Button regBtn;
    ProgressBar regProgressBar;

    FirebaseAuth mAuth;


    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_signup, container, false);

        mAuth = FirebaseAuth.getInstance();

        backArrow = view.findViewById(R.id.back_arrow);

        regEmail = view.findViewById(R.id.register_email);
        regPass = view.findViewById(R.id.register_password);
        regConfirmPass = view.findViewById(R.id.register_confirm_password);
        regBtn = view.findViewById(R.id.registerBtn);
        regProgressBar = view.findViewById(R.id.regProgressBar);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // go in the login page
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SigninActivity.fragmentManager.beginTransaction()
                        .replace(R.id.app_container,new SigninFragment(),null).commit();
            }
        });

        // create new account
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = regEmail.getText().toString();
                String pass = regPass.getText().toString();
                String ConfirmPass = regConfirmPass.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(ConfirmPass)){

                    if (pass.equals(ConfirmPass)){

                        regProgressBar.setVisibility(View.VISIBLE);

                        mAuth.createUserWithEmailAndPassword(email,pass)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()){
                                            Intent setupIntent = new Intent(getActivity(), SetupActivity.class);
                                            startActivity(setupIntent);
                                            getActivity().finish();
                                        }else {
                                            String error_mesage = task.getException().getMessage();
                                            Toast.makeText(getActivity(), error_mesage, Toast.LENGTH_SHORT).show();
                                        }
                                        regProgressBar.setVisibility(View.INVISIBLE);
                                    }
                                });

                    }else {
                        Toast.makeText(getActivity(), "Password need to be same", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            sendToMain();
        }
    }

    private void sendToMain() {
        Intent mainIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(mainIntent);
        getActivity().finish();
    }

}
