package com.e.oraapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

public class MainActivity extends AppCompatActivity {
    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        headerNavigation = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withHeaderBackground(R.color.colorPrimary)
                .build();
        navigationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withAccountHeader(headerNavigation)
                .withSelectedItem(0)
                .build();
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Home").withIcon(getResources().getDrawable(R.drawable.ic_account_balance_24px)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Profile").withIcon(getResources().getDrawable(R.drawable.profil)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("About").withIcon(getResources().getDrawable(R.drawable.tentang)));

        LinearLayout btnLayanan = (LinearLayout) findViewById(R.id.btnLayanan);

        btnLayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                Intent i = new Intent(getApplicationContext(), MenuLayanan.class);
                startActivity(i);
            }
        });
        LinearLayout btnInformasi = (LinearLayout) findViewById(R.id.btnInformasi);
        btnInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                Intent i = new Intent(getApplicationContext(), MenuInformasi.class);
                startActivity(i);
            }
        });
        LinearLayout btnKeluar = (LinearLayout) findViewById(R.id.btnKeluar);
        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                showAlertDialog();
            }
        });
        LinearLayout btnLokasi = (LinearLayout) findViewById(R.id.btnLokasi);
        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                Intent i = new Intent(getApplicationContext(), MenuLokasi.class);
                startActivity(i);
            }
        });
    }
    public void onBackPressed(){
        showAlertDialog();
    }
    private void showAlertDialog(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this)
                .setMessage("Anda Yakin Ingin Keluar?")
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}