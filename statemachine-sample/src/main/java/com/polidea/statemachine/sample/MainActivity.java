package com.polidea.statemachine.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.polidea.statemachine.sample.fragment.NotLoggedInFragment;

public class MainActivity extends AppCompatActivity {

    LoginStateableHandler handler;

    ViewGroup progressContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressContainer = (ViewGroup) findViewById(R.id.progress_container);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragment_container) == null) {
            showFragment(new NotLoggedInFragment());
        }

        handler = new LoginStateableHandler(this, this);
        handler.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        handler.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        handler.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        handler.onSaveInstanceState(outState);
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment.getClass().isInstance(currentFragment)) {
            return;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
