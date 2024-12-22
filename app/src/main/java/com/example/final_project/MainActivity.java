package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        Log.d("NavController", "Graph: " + navController.getGraph().toString());
        Log.d("NavController", "Current Destination: " + navController.getCurrentDestination());


        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        binding.navView.setNavigationItemSelectedListener(item -> {
            boolean handled = NavigationUI.onNavDestinationSelected(item, navController);

            if (handled) {
                Log.d("Drawer", "Handled menu item: " + item.getTitle());

                if (item.getItemId() == R.id.nav_home) {
                    NavOptions navOptions = new NavOptions.Builder()
                            .setPopUpTo(R.id.nav_home, true)
                            .build();
                    navController.navigate(R.id.nav_home, null, navOptions);
                }

            } else {
                Log.d("Drawer", "Unhandled menu item: " + item.getTitle());

                if (item.getItemId() == R.id.nav_home) {
                    NavOptions navOptions = new NavOptions.Builder()
                            .setPopUpTo(R.id.nav_home, true)
                            .build();
                    navController.navigate(R.id.nav_home, null, navOptions);
                    handled = true;
                } else if (item.getItemId() == R.id.nav_gallery) {
                    navController.navigate(R.id.nav_gallery);
                    handled = true;
                } else if (item.getItemId() == R.id.nav_slideshow) {
                    navController.navigate(R.id.nav_slideshow);
                    handled = true;
                } else {
                    Log.w("Drawer", "Unknown menu item: " + item.getItemId());
                }
            }

            drawer.closeDrawers();
            return handled;
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        Log.d("NavController", "Current Destination: " + navController.getCurrentDestination());
        NavBackStackEntry currentEntry = navController.getCurrentBackStackEntry();
        if (currentEntry != null) {
                Log.d("NavHost", "현재백스택엔트리 :: " + currentEntry.getDestination().getLabel());
        }
        Log.d("NavController", "Graph: " + navController.getGraph().toString());
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}