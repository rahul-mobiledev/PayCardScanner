package com.rahul;

import static com.rahul.paycardscanner.ui.ScanCardFragment.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.rahul.paycardscanner.Card;
import com.rahul.paycardscanner.ScanCardIntent;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE_SCAN_CARD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.openCamera);
        btn.setOnClickListener(v -> {
            Intent intent = new ScanCardIntent.Builder(MainActivity.this).build();
            startActivityForResult(intent, REQUEST_CODE_SCAN_CARD);
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN_CARD) {
            if (resultCode == Activity.RESULT_OK) {
                Card card = data.getParcelableExtra(ScanCardIntent.RESULT_PAYCARDS_CARD);
                String cardData = "Card number: " + card.getCardNumberRedacted() + "\n"
                        + "Card holder: " + card.getCardHolderName() + "\n"
                        + "Card expiration date: " + card.getExpirationDate();
                Toast.makeText(MainActivity.this,cardData,Toast.LENGTH_LONG).show();
                Log.i(TAG, "Card info: " + cardData);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(MainActivity.this,"Scan canceled",Toast.LENGTH_LONG).show();
                Log.i(TAG, "Scan canceled");
            } else {
                Toast.makeText(MainActivity.this,"Scan failed",Toast.LENGTH_LONG).show();
                Log.i(TAG, "Scan failed");
            }
        }
    }
}
