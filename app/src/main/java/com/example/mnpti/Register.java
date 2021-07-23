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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Register extends AppCompatActivity {


//create variable for edit text and register button
    private Button Register;
    private EditText InputNom,InputPrenom,InputUser,InputPassword;
    private ProgressDialog loadingBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //find button by id
         Register = (Button) findViewById(R.id.register);
         InputNom =(EditText) findViewById(R.id.nom);
        InputPrenom =(EditText) findViewById(R.id.prenom);
        InputUser =(EditText) findViewById(R.id.username1);
        InputPassword =(EditText) findViewById(R.id.password1);
       //loading screen
        loadingBar = new ProgressDialog(this);


// Put action in register Button
   Register.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           //register method
           Register();


       }
   });
    }

    //Catch data from EditText field

    private void Register (){

   String Nom = InputNom.getText().toString();
   String Prenom = InputPrenom.getText().toString();
   String username = InputUser.getText().toString();
   String password = InputPassword.getText().toString();
         //check if edit text is empty
   if (TextUtils.isEmpty(Nom)){
           //show message if empty
       Toast.makeText(this,"Veuillez entrer votre nom",Toast.LENGTH_SHORT).show();
   }

        else if (TextUtils.isEmpty(Prenom)){

            Toast.makeText(this,"Veuillez entrer votre prenom",Toast.LENGTH_SHORT).show();
        }

       else if (TextUtils.isEmpty(username)){

            Toast.makeText(this,"Veuillez entrer votre username",Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password)){

            Toast.makeText(this,"Veuillez entrer votre password",Toast.LENGTH_SHORT).show();
        }
else {
   //loading
   loadingBar.setTitle("CreateAccount");
   loadingBar.setMessage("");
   loadingBar.setCanceledOnTouchOutside(false);
   loadingBar.show();

   //validation method
   ValidateUsername(Nom,Prenom,username,password);

   }











    }

    private void ValidateUsername(String nom, String prenom, String username, String password) {

            //connect to firebase
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();

        //create table and column in database
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (! (snapshot.child("Users").child(username).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("username", username);
                    userdataMap.put("password", password);
                    userdataMap.put("prenom", prenom);
                    userdataMap.put("nom", nom);

                    Rootref.child("Users").child(username).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(Register.this, "Votre account a bien ete creer", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        //to redirect to login page --intent(classWherYouFrom.this,classWhereYouGo.class)
                                        Intent test = new Intent(Register.this, login.class);
                                        //to launch login page
                                        startActivity(test);

                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(Register.this, "Network Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }  

             else{

             //show popup message   Toast.makeText(Register.this,"text to show ",Toast.LENGTH_SHORT).show();
             Toast.makeText(Register.this, "Ce "+username+" existe déjà",Toast.LENGTH_SHORT).show();
             loadingBar.dismiss();
             Toast.makeText(Register.this,"Veuiller entrer un nouveau username",Toast.LENGTH_SHORT).show();

             //return to register
                    //to redirect to login page --intent(classWherYouFrom.this,classWhereYouGo.class)
                    Intent test = new Intent(Register.this,login.class);
                    //to launch login page
                    startActivity(test);


                }










            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}