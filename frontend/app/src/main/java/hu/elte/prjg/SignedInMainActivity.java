package hu.elte.prjg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SignedInMainActivity extends AppCompatActivity {

    TextView emailTextView;
    TextView passwordTextView;
    LoggedInUser loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in_main);

        emailTextView = findViewById(R.id.emailTextview);
        emailTextView.setText("username");
    }
}
