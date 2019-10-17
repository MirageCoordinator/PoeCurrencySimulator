package ru.dellirium.poecurrencysimulator;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import ru.dellirium.poecurrencysimulator.fragments.FlaskFragment;
import ru.dellirium.poecurrencysimulator.fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);
        if (savedInstanceState == null) {
            Fragment fragment = MainFragment.getInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.main_fragment, fragment)
                    .commit();
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_flasks:
                fragment = FlaskFragment.getInstance();
                break;
            case R.id.nav_links:
            default:
                fragment = MainFragment.getInstance();
        }

        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment, fragment)
                .commit();

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}