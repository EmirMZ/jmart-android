package com.EmirMuhamadZaidJmartAK.jmart_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

/**
 * AccountActivity class
 * contains 1 fragment
 * the AboutMeFragment
 */
public class AccountActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        tabLayout = findViewById(R.id.selectionTab);
        viewPager = findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new AboutMeFragment(), "ABOUT ME");
        vpAdapter.addFragment(new StoreFragment(), "MY STORE");
        viewPager.setAdapter(vpAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.account_button) {
            Toast.makeText(this, "About Me Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AccountActivity.this, AboutMeFragment.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.add_button) {
            Toast.makeText(this, "Store Info Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AccountActivity.this, StoreFragment.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}