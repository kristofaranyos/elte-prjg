package hu.elte.prjg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button loginButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    boolean isEmailValid;
    boolean isPasswordValid;
    LoggedInUser loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(SetValidationEmail(emailEditText) && SetValidationPassword()){
            loggedInUser=new LoggedInUser(emailEditText.getText().toString());
            Intent i = new Intent(this, SignedInMainActivity.class);
            i.putExtra("Value1","This is activityTwo!");
            startActivity(i);
        }
    }

    // Check for a valid email address.
    public boolean SetValidationEmail(EditText email) {
        if (email.getText().toString().isEmpty()) {
            email.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()) {
            emailEditText.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }

        return isEmailValid;
    }

    // Check for a valid password.
    public boolean SetValidationPassword() {
        if (passwordEditText.getText().toString().isEmpty()) {
            passwordEditText.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (passwordEditText.getText().length() < 6) {
            passwordEditText.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }

        return isPasswordValid;
    }
}
