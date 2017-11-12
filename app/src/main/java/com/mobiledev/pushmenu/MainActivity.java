package com.mobiledev.pushmenu;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.mobiledev.pushmenu.fragments.FragmentDraftItems;
import com.mobiledev.pushmenu.fragments.FragmentDrawer;
import com.mobiledev.pushmenu.fragments.FragmentFriends;
import com.mobiledev.pushmenu.fragments.FragmentHome;
import com.mobiledev.pushmenu.fragments.FragmentNotification;
import com.mobiledev.pushmenu.fragments.FragmentSentItems;
import com.mobiledev.pushmenu.fragments.FragmentSettings;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, FragmentDrawer.SlideListener{

    private static String TAG = MainActivity.class.getSimpleName();
     private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private LinearLayout mMainContainer;
    private float lastTranslate = 0.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar =  findViewById(R.id.toolbar);
         setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mMainContainer =  findViewById(R.id.mainContainer);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this, this);

        // display the first navigation drawer view on app launch
        displayView(0);
    }

    public void updateTitle(String mTitle){
        getSupportActionBar().setTitle(mTitle);
    }
    @Override
    public void onSlideFinished(float moveFactor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mMainContainer.setTranslationX(moveFactor);
        } else {
            TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
            anim.setDuration(0);
            anim.setFillAfter(true);
            mMainContainer.startAnimation(anim);
            lastTranslate = moveFactor;
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new FragmentHome();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new FragmentFriends();
                title = getString(R.string.title_friends);
                break;
            case 2:
                fragment = new FragmentNotification();
                title = getString(R.string.title_messages);
                break;
            case 3:
                fragment = new FragmentSentItems();
                title = getString(R.string.title_settings);
                break;
            case 4:
                fragment = new FragmentDraftItems();
                title = getString(R.string.title_settings);
                break;
            case 5:
                fragment = new FragmentSettings();
                title = getString(R.string.title_settings);
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }


}