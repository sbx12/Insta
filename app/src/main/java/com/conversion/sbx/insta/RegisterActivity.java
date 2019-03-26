package com.conversion.sbx.insta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.net.PasswordAuthentication;

public class RegisterActivity extends AppCompatActivity {

    EditText etRegisterUsername;
    EditText etRegisterPassword;
    Button btnRegister;
    Button btnGobacktoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegisterUsername = findViewById(R.id.etRegisterUsername);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnGobacktoLogin = findViewById(R.id.btnGobacktoLogin);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etRegisterUsername.getText().toString();
                String password = etRegisterPassword.getText().toString();

                if(username.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(password.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                    registerUser(username, password);

            }
        });

        btnGobacktoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLoginActivty();
            }
        });
    }

    private void registerUser(String username, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.d("Error", "Saving ERROROR nothing entered ");
                    e.printStackTrace();
                    return;
                }
                Log.d("Pass", "Success1!!");
                Toast.makeText(RegisterActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                goLoginActivty();
            }
        });

    }

    private void goLoginActivty() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
