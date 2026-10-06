package com.example.userinformationapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void button1_click(View view){

        EditText editText = (EditText)findViewById(R.id.name);
        EditText emailEditText = (EditText)findViewById(R.id.email);

        String name = editText.getText().toString();
        String email = emailEditText.getText().toString();

        Random random  = new Random();

        //this makes a random 4 digit code which will be sent to the email
        int validationCode = 1000 + random.nextInt(9000);

        Intent intent = new Intent(this, ThankYouActivity.class);

        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("code", validationCode);



        startActivity(intent);

    }

}