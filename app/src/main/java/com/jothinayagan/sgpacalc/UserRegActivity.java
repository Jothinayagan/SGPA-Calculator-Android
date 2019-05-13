package com.jothinayagan.sgpacalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserRegActivity extends AppCompatActivity implements View.OnClickListener {

    private Button regButton;
    private EditText name, email, password;
    private TextView signin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userreg);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //Profile Activity
            finish();
            startActivity(new Intent(getApplicationContext(), UserActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        regButton = findViewById(R.id.regBut);

        name = findViewById(R.id.regName);
        email = findViewById(R.id.regEmail);
        password = findViewById(R.id.regPassword);

        signin = findViewById(R.id.regSignin);

        regButton.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    private void registerUser(){
        String regname = name.getText().toString().trim();
        String regemail = email.getText().toString().trim();
        String regpass = password.getText().toString().trim();

        //validation if empty

        if(!regname.matches("^[A-Za-z\\\\s]{1,}[\\\\.]{0,1}[A-Za-z\\\\s]{0,}$")){
            Toast.makeText(this, "Invalid name, use alphabets only", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!regemail.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
            //email.setError("Invalid Email");
            Toast.makeText(this, "Invalid Email, Enter valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (regpass.equals("") || regpass.length()<=6){
            //password.setError("Enter valid password");
            Toast.makeText(this, "Password must be more than 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        //if validation pass, show Progressbar
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(regemail,regpass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(UserRegActivity.this, "User registered Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        }
                        else
                            Toast.makeText(UserRegActivity.this, "Colud not register.. Please try again..", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == regButton)
            registerUser();
        else if(v == signin) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    //Option Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mmshare:
                Toast.makeText(this, "Ping Pong...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.mmrate:
                Toast.makeText(this, "Ping Pong...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.mmfeedback:
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "v.i.jothinayagan.307@gmail.com" });
                Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
                break;

            case R.id.mmexit:
                Toast.makeText(this, "Ping Pong...", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
