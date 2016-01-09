package tuxdev.studio.demokutaibarat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ukietux on 09/01/16.
 */
public class LoginActivity  extends AppCompatActivity {
    private EditText editUsername;
    private EditText editPassword;
    private Button btnLogin;
    String usname, pass;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usname = "demo";
        pass = "omed";

        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn() == true) {
            Intent a = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(a);
            finish();
        }
        initUI();
    }

    private void initUI() {
        editUsername = (EditText) findViewById(R.id.username);
        editUsername.getText();
        editPassword = (EditText) findViewById(R.id.password);
        editPassword.getText();
        btnLogin = (Button) findViewById(R.id.login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                        R.style.MyTheme);
                progressDialog.setIndeterminate(false);
                progressDialog.setMessage("Harap Tunggu\nSedang Terhubung ke Server...");
                progressDialog.show();
                if (v == btnLogin) {
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    // On complete call either onLoginSuccess or onLoginFailed
                                    if (editUsername.getText().toString().equals(usname) && editPassword.getText().toString().equals(pass)) {
                                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                        session.createLoginSession(usname, pass);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Snackbar snackbar = Snackbar
                                                .make(v, "Nama pengguna atau kata sandi tidak sesuai", Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                    }
                                    // onLoginFailed();
                                    progressDialog.dismiss();
                                }
                            }, 3000);

                }
            }
        });
    }
}
