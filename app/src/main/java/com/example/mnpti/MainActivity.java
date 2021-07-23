package com.example.mnpti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//to make button active
    //create variable button

    private Button Login , Signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find button by id (R.id.ButtonId)
        Login =(Button) findViewById(R.id.main_login);
        Signup =(Button) findViewById(R.id.main_signup);

       //to activate login button
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //to redirect to login page --intent(classWherYouFrom.this,classWhereYouGo.class)
                Intent test = new Intent(MainActivity.this,login.class);
                //to launch login page
                startActivity(test);

            }
        });

        //to active Sign up button
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //to redirect to login page --intent(classWherYouFrom.this,classWhereYouGo.class)
                Intent test = new Intent(MainActivity.this,Register.class);
                //to launch login page
                startActivity(test);



            }
        });


    }
}