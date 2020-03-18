package hu.elte.prjg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button loginButton;
    EditText usernameEditText=null;
    EditText passwordEditText=null;
    LoggedInUser loggedInUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        loggedInUser=new LoggedInUser(usernameEditText.getText().toString());
        Intent i = new Intent(this, SignedInMainActivity.class);
        i.putExtra("Value1","This is activityTwo!");
        startActivity(i);
    }
}
