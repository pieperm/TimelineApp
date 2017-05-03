package org.pltw.examples.timelineapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

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

        Backendless.initApp(this, MainActivity.APPLICATION_ID, MainActivity.SECRET_KEY, MainActivity.VERSION);

        usernameEdit = (EditText)findViewById(R.id.signup_username_edit);
        passwordEdit = (EditText)findViewById(R.id.signup_password_edit);
        emailEdit = (EditText)findViewById(R.id.signup_email_edit);
        signupButton = (Button)findViewById(R.id.signup_button);
        signupLink = (TextView)findViewById(R.id.signup_link);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BackendlessUser user = new BackendlessUser();
                user.setProperty("username", usernameEdit.getText().toString());
                user.setEmail(emailEdit.getText().toString());
                user.setPassword(passwordEdit.getText().toString());

                Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        MainActivity.createAlertDialog(SignupActivity.this, "Signup Successful", response.getEmail() + " was registered.", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent signupIntent = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(signupIntent);
                            }
                        });
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        MainActivity.createAlertDialog(SignupActivity.this, "Signup Failed", fault.getMessage());
                    }
                });


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
