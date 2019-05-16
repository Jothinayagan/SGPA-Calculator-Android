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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    private FirebaseAuth firebaseAuth;
    private TextView userText;
    private TextView resmama;
    private Button resultButton;
    private Button resetButton;
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

        resultButton = findViewById(R.id.resultBut);
        resetButton = findViewById(R.id.resultBut2Reset);
        resmama = findViewById(R.id.resultmama);
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
        c3 = findViewById(R.id.credit3);
        c4 = findViewById(R.id.credit4);
        c5 = findViewById(R.id.credit5);
        c6 = findViewById(R.id.credit6);
        c7 = findViewById(R.id.credit7);
        c8 = findViewById(R.id.credit8);
        c9 = findViewById(R.id.credit9);
        c10 = findViewById(R.id.credit10);

        c1.setAdapter(adapterCredit);
        c2.setAdapter(adapterCredit);
        c3.setAdapter(adapterCredit);
        c4.setAdapter(adapterCredit);
        c5.setAdapter(adapterCredit);
        c6.setAdapter(adapterCredit);
        c7.setAdapter(adapterCredit);
        c8.setAdapter(adapterCredit);
        c9.setAdapter(adapterCredit);
        c10.setAdapter(adapterCredit);

        //Grade
        g1 = findViewById(R.id.grade1);
        g2 = findViewById(R.id.grade2);
        g3 = findViewById(R.id.grade3);
        g4 = findViewById(R.id.grade4);
        g5 = findViewById(R.id.grade5);
        g6 = findViewById(R.id.grade6);
        g7 = findViewById(R.id.grade7);
        g8 = findViewById(R.id.grade8);
        g9 = findViewById(R.id.grade9);
        g10 = findViewById(R.id.grade10);


        //SetAdapter
        g1.setAdapter(adapterGrade);
        g2.setAdapter(adapterGrade);
        g3.setAdapter(adapterGrade);
        g4.setAdapter(adapterGrade);
        g5.setAdapter(adapterGrade);
        g6.setAdapter(adapterGrade);
        g7.setAdapter(adapterGrade);
        g8.setAdapter(adapterGrade);
        g9.setAdapter(adapterGrade);
        g10.setAdapter(adapterGrade);

        //ItemSelectedListener
        c1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cre1 = parent.getItemAtPosition(position).toString();
               // resmama.setText(cre1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        g1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gra1 = parent.getItemAtPosition(position).toString();
                //resmama.setText(gra1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        c2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cre2 = parent.getItemAtPosition(position).toString();
               // resmama.setText(cre2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        g2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gra2 = parent.getItemAtPosition(position).toString();
                //resmama.setText(gra2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        c3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cre3 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        g3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gra3 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        c4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cre4 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        g4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gra4 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        c5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cre5 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        g5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gra5 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        c6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 cre6 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        g6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gra6 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        c7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cre7  = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        g7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gra7  = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        c8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cre8  = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        g8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gra8  = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        c9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cre9 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        g9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gra9  = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        c10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cre10  = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        g10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gra10  = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Result Button
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calculation
                float totalCredit = floatCoversion(cre1)
                                        + floatCoversion(cre2)
                                            + floatCoversion(cre3)
                                                + floatCoversion(cre4)
                                                   + floatCoversion(cre5)
                                                        + floatCoversion(cre6)
                                                            + floatCoversion(cre7)
                                                                + floatCoversion(cre8)
                                                                    + floatCoversion(cre9)
                                                                        + floatCoversion(cre10);

                float t1 = cal(floatCoversion(cre1), gra1);
                float t2 = cal(floatCoversion(cre2), gra2);
                float t3 = cal(floatCoversion(cre3), gra3);
                float t4 = cal(floatCoversion(cre4), gra4);
                float t5 = cal(floatCoversion(cre5), gra5);
                float t6 = cal(floatCoversion(cre6), gra6);
                float t7 = cal(floatCoversion(cre7), gra7);
                float t8 = cal(floatCoversion(cre8), gra8);
                float t9 = cal(floatCoversion(cre9), gra9);
                float t10 = cal(floatCoversion(cre10), gra10);

                float fin = t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10;
                float sgpa = fin / totalCredit;
                resmama.setText("Your SGPA is " + sgpa);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1.setSelection(0);
                c2.setSelection(0);
                c3.setSelection(0);
                c4.setSelection(0);
                c5.setSelection(0);
                c6.setSelection(0);
                c7.setSelection(0);
                c8.setSelection(0);
                c9.setSelection(0);
                c10.setSelection(0);

                g1.setSelection(0);
                g2.setSelection(0);
                g3.setSelection(0);
                g4.setSelection(0);
                g5.setSelection(0);
                g6.setSelection(0);
                g7.setSelection(0);
                g8.setSelection(0);
                g9.setSelection(0);
                g10.setSelection(0);

                resmama.setText("Enter your details below..");
            }
        });
    }

    private float cal(float credit, String grade) {
        float k = 0;
        if("S".equals(grade)){
            k = 10 * credit;
        } else if("A".equals(grade)){
            k = 9 * credit;
        } else if("B".equals(grade)){
            k = 8 * credit;
        } else if("C".equals(grade)){
            k = 7 * credit;
        } else if("D".equals(grade)){
            k = 6 * credit;
        } else if("E".equals(grade)){
            k = 9 * credit;
        } else {
            k = 0;
        }
        return k;
    }

    private float floatCoversion(String credit) {
        float convert = 0;
        if("1".equals(credit) || "2".equals(credit) || "3".equals(credit) || "4".equals(credit) || "0".equals(credit)){
            convert = Float.valueOf(credit);
        }
        return convert;
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
}
