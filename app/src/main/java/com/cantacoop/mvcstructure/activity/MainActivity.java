package com.cantacoop.mvcstructure.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cantacoop.mvcstructure.R;
import com.cantacoop.mvcstructure.util.ScreenUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int screenWidth = ScreenUtils.getInstance().getScreenWidth();
        int screenHeight = ScreenUtils.getInstance().getScreenHeight();

        // Toast screenWidth & screenHeight
        Toast.makeText(MainActivity.this, "ScreenWidth:" + screenWidth +
                " ScreenHeight:" + screenHeight, Toast.LENGTH_SHORT)
                .show();
    }
}
