package com.example.userinformationapplication;

import android.content.Intent;
import android.net.Uri;
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

public class ThankYouActivity extends AppCompatActivity {
    int validationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thank_you);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String name = getIntent().getExtras().getString("name");
        String email = getIntent().getExtras().getString("email");
        validationCode = getIntent().getExtras().getInt("code");

        TextView thankyouText = findViewById(R.id.thankYouText);

        thankyouText.setText("Thank you " + name + ", your request is being processed " );


        String[] address = {email};
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));

        emailIntent.putExtra(Intent.EXTRA_EMAIL, address);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, " Validation Code");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Your validation code is: "+ validationCode);

        if(emailIntent.resolveActivity(getPackageManager()) != null){
            startActivity(emailIntent);
        }
    }
    public void validateCode(View view){
        EditText codeInput = findViewById(R.id.codeInput);
        String enteredCode = codeInput.getText().toString();

        if(enteredCode.isEmpty()){
            Toast.makeText(this, "PLease enter the validation code", Toast.LENGTH_SHORT).show();
            return;
        }
        int code = Integer.parseInt(enteredCode);

        if (code == validationCode){
            Toast.makeText(this,"Validated", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, "Incorrect code", Toast.LENGTH_LONG).show();
        }
    }
}

