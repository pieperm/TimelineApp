package org.pltw.examples.timelineapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.pltw.examples.timelineapp.R;

/**
 * Created by pieperm on 3/1/17.
 */

public class SignupActivity extends AppCompatActivity {

    private EditText usernameEdit, passwordEdit, emailEdit;
    private Button signupButton;
    private TextView signupLink;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.i("SignupActivity", "onCreate called");

        usernameEdit = (EditText)findViewById(R.id.signup_username_edit);
        passwordEdit = (EditText)findViewById(R.id.signup_password_edit);
        emailEdit = (EditText)findViewById(R.id.signup_email_edit);
        signupButton = (Button)findViewById(R.id.signup_button);
        signupLink = (TextView)findViewById(R.id.signup_link);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create Backendless account
                Intent signupIntent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(signupIntent);
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLoginActivity = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(goToLoginActivity);
            }
        });

    }
}
