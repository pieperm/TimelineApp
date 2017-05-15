package org.pltw.examples.timelineapp.activities;

import android.app.ProgressDialog;
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
 * Created by pieperm on 2/28/17.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText emailEdit, passwordEdit;
    private Button loginButton;
    private TextView loginLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Backendless.initApp(this, MainActivity.APPLICATION_ID, MainActivity.SECRET_KEY, MainActivity.VERSION);

        emailEdit = (EditText)findViewById(R.id.login_email_edit);
        passwordEdit = (EditText)findViewById(R.id.login_password_edit);
        loginButton = (Button)findViewById(R.id.login_button);
        loginLink = (TextView)findViewById(R.id.login_link);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "Login", "Logging in...");

                Backendless.UserService.login(
                        emailEdit.getText().toString(),
                        passwordEdit.getText().toString(),
                        new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(loginIntent);
                                dialog.cancel();
                                finish();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                MainActivity.createAlertDialog(LoginActivity.this, "Login Failed", fault.getMessage());
                            }
                        });


            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSignupActivity = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(goToSignupActivity);
            }
        });





    }

}
