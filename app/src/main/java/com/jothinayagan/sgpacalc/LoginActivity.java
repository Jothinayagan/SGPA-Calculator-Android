package com.jothinayagan.sgpacalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private long backPressedTime;
    private Toast backToast;
    private Button logButton;
    private EditText email, password;
    private TextView signup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView forgotPassword;

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

        logButton = (Button) findViewById(R.id.logBut);
        email = findViewById(R.id.regEmail);
        password = findViewById(R.id.logPassword);
        signup = findViewById(R.id.logSignup);
        forgotPassword = (TextView) findViewById(R.id.forgetPassword);

        logButton.setOnClickListener(this);
        signup.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
    }

    //Double tap to close the app
    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    private void userLogin(){
        String logemail = email.getText().toString().trim();
        String logpass = password.getText().toString().trim();

        //validate email and password
        if (!logemail.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
            Toast.makeText(this, "Invalid Email, Enter valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (logpass.equals("") || logpass.length()<6){
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
                        } else {
                            Toast.makeText(LoginActivity.this, "Login Failed...", Toast.LENGTH_SHORT).show();
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
        if(v == forgotPassword){
            finish();
            startActivity(new Intent(this, PasswordEmailActivity.class));
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
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                String body = "Hey there.. I'm using this app. Install this in your device.";
                share.putExtra(Intent.EXTRA_SUBJECT, body);
                startActivity(Intent.createChooser(share, "Share via"));
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
