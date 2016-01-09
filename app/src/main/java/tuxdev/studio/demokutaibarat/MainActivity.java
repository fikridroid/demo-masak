package tuxdev.studio.demokutaibarat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.drakeet.materialdialog.MaterialDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    boolean doubleback;
    SessionManager session;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    View parentLayout;
    MaterialDialog mKeluar;
    android.support.v7.widget.Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);


        parentLayout = findViewById(R.id.rootView);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mtoolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //cek session
        session = new SessionManager(getApplicationContext());
        session.checkLogin();


        //set berita as first view
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new FragmentBerita()).commit();
        mtoolbar.setTitle(R.string.m_berita);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void Keluar() {
        mKeluar = new MaterialDialog(this).setTitle("Peringatan")
                .setMessage("Apakah anda yakin ingin keluar ?")
                .setPositiveButton("Ya", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        session.logoutUser();
                        startActivity(intent1);
                        finish();
                        mKeluar.dismiss();

                    }
                })
                .setNegativeButton("Batal", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mKeluar.dismiss();
                    }
                });
        mKeluar.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleback) {
                super.onBackPressed();
                return;
            }
            this.doubleback = true;
            Snackbar snackbar = Snackbar
                    .make(parentLayout, "Tekan kembali sekali lagi untuk keluar", Snackbar.LENGTH_LONG);
            snackbar.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleback = false;
                }
            }, 2000); //delay 2 detik
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.keluar) {
            Keluar();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_berita) {
            FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.containerView, new FragmentBerita()).commit();
            //set title
            mtoolbar.setTitle(R.string.m_berita);
        } else if (id == R.id.nav_wisata) {
            FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.containerView, new FragmentWisata()).commit();
            //set title
            mtoolbar.setTitle(R.string.m_wisata);
        } else if (id == R.id.nav_aduan) {
            FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.containerView, new FragmentHome()).commit();
            mtoolbar.setTitle(R.string.m_aduan);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
