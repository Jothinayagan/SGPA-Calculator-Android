package com.jothinayagan.sgpacalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PasswordEmailActivity extends AppCompatActivity {

    private EditText email;
    private Button resetButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_email);

        email = (EditText) findViewById(R.id.fpEmail);
        resetButton = (Button) findViewById(R.id.fpResetPasswordButton);
        firebaseAuth = FirebaseAuth.getInstance();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = email.getText().toString().trim();

                if(useremail.equals("")){
                    Toast.makeText(PasswordEmailActivity.this, "Please enter your registered email address", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(PasswordEmailActivity.this, "Reset link sent to your email", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PasswordEmailActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(PasswordEmailActivity.this, "This email address is not registered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
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
