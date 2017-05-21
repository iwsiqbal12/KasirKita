package com.example.schopfer.kasir;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.talagasoft.schopfer.R;


public class Credit extends AppCompatActivity {
    TextView txtnama,txtkelas;
    Button btnFace, btnIns, btnTwi, btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        txtnama = (TextView) findViewById(R.id.textView6);
        txtkelas = (TextView) findViewById(R.id.textView7);
        btnFace = (Button) findViewById(R.id.button3);
        btnFace.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent();
                                           intent.setAction(Intent.ACTION_VIEW);
                                           intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                           intent.setData(Uri.parse("https://www.facebook.com/oesman.wahyu?ref=bookmarks"));
                                           startActivity(intent);

                                       }
                                   }

        );
        btnIns = (Button) findViewById(R.id.button4);
        btnIns.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent();
                                           intent.setAction(Intent.ACTION_VIEW);
                                           intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                           intent.setData(Uri.parse("https://www.instagram.com/iws_iqbal12/"));
                                           startActivity(intent);

                                       }
                                   }

        );
        btnTwi = (Button) findViewById(R.id.button4);
        btnTwi.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent = new Intent();
                                          intent.setAction(Intent.ACTION_VIEW);
                                          intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                          intent.setData(Uri.parse("https://twitter.com/_iwsiqbal12"));
                                          startActivity(intent);

                                      }
                                  }

        );
        btnKembali = (Button) findViewById(R.id.button6);
        btnKembali.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Credit.this.finish();
                                      }
                                  }

        );


    }
}
