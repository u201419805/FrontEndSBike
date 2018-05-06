package biz.bikehub.rentbike.viewcontrollers.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import biz.bikehub.rentbike.R;
import biz.bikehub.rentbike.viewcontrollers.fragments.OrdersFragment;
import biz.bikehub.rentbike.viewcontrollers.fragments.PendingsFragment;
import biz.bikehub.rentbike.viewcontrollers.fragments.StationsFragment;

public class MainOpActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return navigateTo(item);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_op);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_op);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigateTo(navigation.getMenu().findItem(R.id.navigation_pendings));

    }

    private Fragment getFragmentFor(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_pendings: return new PendingsFragment();
            //case R.id.navigation_orders: return new OrdersFragment();
            //case R.id.navigation_comments: return new CommentsFragment();
            default: return new PendingsFragment();
        }
    }

    private boolean navigateTo(MenuItem item) {
        item.setChecked(true);
        return getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_operator, getFragmentFor(item))
                .commit() > 0;
    }

}