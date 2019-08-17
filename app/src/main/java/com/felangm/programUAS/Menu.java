package com.felangm.programUAS;
//21 May 2019 - 10116137 - Fauzi Elang M - AKB 4
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;

Intent login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.bottomnavigation_menu);
        fragmentManager = getSupportFragmentManager();

        //Untuk inisialisasi fragment pertama kali
        fragmentManager.beginTransaction().replace(R.id.main_container, new FragmentProfil()).commit();

        //Memberikan listener saat menu item di bottom navigation diklik
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.menu_account:
                        fragment = new Fragment_account();
                        break;
                    case R.id.menu_profil:
                        fragment = new FragmentProfil();
                        break;
                    case R.id.menu_kontak:
                        fragment = new FragmentKontak();
                        break;
                    case R.id.menu_fl:
                        fragment = new FragmentFl();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });


        final SharedPreferences sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
        final String register_name=sharedPreferences.getString("NAME","DEFAULT_NAME");
        Button logout=(Button)findViewById(R.id.btn_logout);
        TextView welcometext=(TextView)findViewById(R.id.android_text);
        welcometext.setText("Welcome"+register_name);
        login=new Intent(this,LoginActivity.class);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putBoolean("ISLOGGEDIN",false).commit();
                startActivity(login);
                finish();

            }
        });
    }
}
