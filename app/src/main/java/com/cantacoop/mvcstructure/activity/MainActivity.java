package com.cantacoop.mvcstructure.activity;

import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cantacoop.mvcstructure.R;
import com.cantacoop.mvcstructure.fragment.MainFragment;
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

        // Separate by Android version
        if (Build.VERSION.SDK_INT >= 21) {
            // Run on Android 21+
        } else {
            // Run on Android 1 - 20
        }

        if (savedInstanceState == null) {
            // First created
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer,
                            MainFragment.newInstance(123),
                            "MainFragment")
                    .commit();
        }

    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (savedInstanceState == null) {
            MainFragment fragment = (MainFragment)
                    getSupportFragmentManager().findFragmentByTag("MainFragment");
            fragment.setHelloText("Woo Hooooooo");
        }
    }
}
