package com.example.mnpti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mnpti.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    //initiate variable
    private EditText Username,Password;
    private Button loginButton;
    private ProgressDialog loading1;

    private String DBname="Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //find by id
        Username =(EditText) findViewById(R.id.username);
        Password =(EditText) findViewById(R.id.password);
        loginButton =(Button) findViewById(R.id.login);
        //for progress bar
        loading1 = new ProgressDialog(this);


        //set login button action
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });



    }




    private void loginUser() {

        //catch data from EditText
        String username =Username.getText().toString();
        String password = Password.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this,"Entrer votre nom d'utilisateur",Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Entrer votre mot de passe",Toast.LENGTH_SHORT).show();
        }
        else{

            loading1.setTitle("Login account");
            loading1.setMessage("");
            loading1.setCanceledOnTouchOutside(false);
            loading1.show();


            AllowAcces(username,password);
        }


    }

    private void AllowAcces(String username, String password) {

        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child(DBname).child(username).exists()){
                    Users userdata = snapshot.child(DBname).child(username).getValue(Users.class);
                    if(userdata.getUsername().equals(username)){
                        if(userdata.getPassword().equals(password)){

                            Toast.makeText(login.this, "login", Toast.LENGTH_SHORT).show();
                            loading1.dismiss();

                            //to redirect to login page --intent(classWherYouFrom.this,classWhereYouGo.class)
                            Intent test = new Intent(login.this,dashboard.class);
                            //to launch login page
                            startActivity(test);







                        }

                    }
                }
                else{
                    Toast.makeText(login.this, "Account don't exist", Toast.LENGTH_SHORT).show();
                    loading1.dismiss();
                    Toast.makeText(login.this, "Create account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }
}