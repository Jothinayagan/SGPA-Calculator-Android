package com.jothinayagan.sgpacalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    private TextView userText;
    private Button res;
    private Spinner c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;
    private Spinner g1, g2, g3, g4, g5, g6, g7, g8, g9, g10;
    private String cre1, cre2, cre3, cre4, cre5, cre6, cre7, cre8, cre9, cre10;
    private String gra1, gra2, gra3, gra4, gra5, gra6, gra7, gra8, gra9, gra10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        userText = findViewById(R.id.userActText);
        userText.setText("Welcome " + user.getEmail());

        //Spinner
        ArrayAdapter<CharSequence> adapterCredit = ArrayAdapter.createFromResource(this, R.array.credit, android.R.layout.simple_spinner_item);
        adapterCredit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterGrade = ArrayAdapter.createFromResource(this, R.array.grade, android.R.layout.simple_spinner_item);
        adapterGrade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Credit
        c1 = findViewById(R.id.credit1);
        c2 = findViewById(R.id.credit2);

        c1.setAdapter(adapterCredit);
        c2.setAdapter(adapterCredit);

        //Grade
        g1 = findViewById(R.id.grade1);
        g2 = findViewById(R.id.grade2);

        //SetAdapter
        g1.setAdapter(adapterGrade);
        g2.setAdapter(adapterGrade);

        //ItemSelectedListener
        c1.setOnItemSelectedListener(this);
        g1.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuLogout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cre1 = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
