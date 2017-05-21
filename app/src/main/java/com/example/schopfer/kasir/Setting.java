package com.example.schopfer.kasir;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.talagasoft.schopfer.R;

/**
 * Created by compaq on 03/20/2016.
 */
public class Setting extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        InitButtons();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    private void InitButtons(){


    }


}
