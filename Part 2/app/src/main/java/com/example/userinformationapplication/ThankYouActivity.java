package com.example.userinformationapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThankYouActivity extends AppCompatActivity {

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
        int validationCode = getIntent().getExtras().getInt("code");

        TextView thankyouText = findViewById(R.id.thankYouText);

        thankyouText.setText("Thank you " + name + ", your request is being processed " );


        String[] address = {email};
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto"));

        emailIntent.putExtra(Intent.EXTRA_EMAIL, address);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, " Validation Code");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Your validation code is: "+ validationCode);

        if(emailIntent.resolveActivity(getPackageManager()) != null){
            startActivity(emailIntent);
        }






    }
}

