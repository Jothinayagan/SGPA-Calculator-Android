package com.jothinayagan.sgpacalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logButton;
    private EditText email, password;
    private TextView signup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //Profile Activity
            finish();
            startActivity(new Intent(getApplicationContext(), UserActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        logButton = findViewById(R.id.logBut);

        email = findViewById(R.id.regEmail);
        password = findViewById(R.id.logPassword);

        signup = findViewById(R.id.logSignup);

        logButton.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    private void userLogin(){
        String logemail = email.getText().toString().trim();
        String logpass = password.getText().toString().trim();

        //validation if empty
        if (!logemail.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
            //email.setError("Invalid Email");
            Toast.makeText(this, "Invalid Email, Enter valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (logpass.equals("") || logpass.length()<=6){
            //password.setError("Enter valid password");
            Toast.makeText(this, "Password must be more than 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        //if validation pass, show Progressbar
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(logemail, logpass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == logButton)
            userLogin();
        if(v == signup){
            finish();
            startActivity(new Intent(this, UserRegActivity.class));
        }
    }
}
