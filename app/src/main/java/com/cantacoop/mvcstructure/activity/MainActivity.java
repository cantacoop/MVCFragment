package com.cantacoop.mvcstructure.activity;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cantacoop.mvcstructure.R;
import com.cantacoop.mvcstructure.fragment.MainFragment;
import com.cantacoop.mvcstructure.fragment.SecondFragment;
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

            SecondFragment secondFragment = SecondFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer,
                            secondFragment,
                            "SecondFragment")
                    .detach(secondFragment)
                    .commit();
        }

    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (savedInstanceState == null) {
            MainFragment fragment = (MainFragment)
                    getSupportFragmentManager().findFragmentByTag("MainFragment");
            fragment.setHelloText("Woo Hooooooo\nWoo Hooooooo\nWoo Hooooooo\nWoo Hooooooo\nWoo Hooooooo\n" +
                    "Woo Hooooooo\nWoo Hooooooo\nWoo Hooooooo\nWoo Hooooooo\n" +
                    "Woo Hooooooo\nWoo Hooooooo\n");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_first_tab: {
                MainFragment mainFragment = (MainFragment)
                        getSupportFragmentManager().findFragmentByTag("MainFragment");
                SecondFragment secondFragment = (SecondFragment)
                        getSupportFragmentManager().findFragmentByTag("SecondFragment");
                getSupportFragmentManager().beginTransaction()
                        .attach(mainFragment)
                        .detach(secondFragment)
                        .commit();
                return true;
            }
            case R.id.action_second_tab: {
                MainFragment mainFragment = (MainFragment)
                getSupportFragmentManager().findFragmentByTag("MainFragment");
                SecondFragment secondFragment = (SecondFragment)
                        getSupportFragmentManager().findFragmentByTag("SecondFragment");
                getSupportFragmentManager().beginTransaction()
                        .attach(secondFragment)
                        .detach(mainFragment)
                        .commit();
                return true;
            }
            case R.id.action_second_fragment: {

                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.contentContainer);

                if (fragment instanceof SecondFragment == false) {
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(
                                    R.anim.from_right, R.anim.to_left,
                                    R.anim.from_left, R.anim.to_right
                            )
                            .replace(R.id.contentContainer, SecondFragment.newInstance())
                            .addToBackStack(null)
                            .commit();
                }

                Toast.makeText(MainActivity.this,
                        "Second Fragment",
                        Toast.LENGTH_SHORT)
                        .show();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
