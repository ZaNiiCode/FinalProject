package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    // initializing all variables
    private GoogleSignInClient mGOogleSignInClient;
    private SignInButton google_button;
    private int RC_SIGN_IN = 1;
    private TextView accName;
    private TextView accEmail;
    private TextView Email;
    private TextView NameDisplay;
    private Button sign_out;
    public static String accountEmail;
    private BottomNavigationView bottomNavigationView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.INVISIBLE);


        // giving variable value settings
        google_button = findViewById(R.id.google_button);
        accName = findViewById(R.id.accName);
        accEmail = findViewById(R.id.accEmail);
        sign_out = findViewById(R.id.sign_out);
        Email = findViewById(R.id.Email);
        NameDisplay = findViewById(R.id.NameDisplay);



        Email.setVisibility(View.INVISIBLE);
        NameDisplay.setVisibility(View.INVISIBLE);
        sign_out.setVisibility(View.INVISIBLE);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGOogleSignInClient = GoogleSignIn.getClient(this, gso);

        google_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });



        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGOogleSignInClient.signOut();
                google_button.setVisibility(View.VISIBLE);
                accName.setVisibility(View.INVISIBLE);
                accEmail.setVisibility(View.INVISIBLE);
                sign_out.setVisibility(View.INVISIBLE);
                Email.setVisibility(View.INVISIBLE);
                NameDisplay.setVisibility(View.INVISIBLE);

                bottomNavigationView.setVisibility(View.INVISIBLE);




            }
        });




        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Intent home = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(home);
                        break;
                    case R.id.action_search:
                        Intent search = new Intent(getApplicationContext(), Search.class);
                        startActivity(search);
                        break;
                    case R.id.action_results:
                        Intent results = new Intent(getApplicationContext(), Results.class);
                        startActivity(results);
                        break;
                    case R.id.action_favorite:
                        Intent fav = new Intent(getApplicationContext(), Favorites.class);
                        startActivity(fav);
                        break;
                }
                return true;
            }
        });



    }

    private void signIn() {
        Intent signInIntent = mGOogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        }
    }


    private void handleSignInResult(Task < GoogleSignInAccount > task) {
        try {
            GoogleSignInAccount acc = task.getResult(ApiException.class);
           accountEmail = acc.getEmail();

            accName.setText(acc.getDisplayName());
            accEmail.setText(acc.getEmail());
            google_button.setVisibility(View.INVISIBLE);
            sign_out.setVisibility(View.VISIBLE);
            Email.setVisibility(View.VISIBLE);
            NameDisplay.setVisibility(View.VISIBLE);
            accName.setVisibility(View.VISIBLE);
            accEmail.setVisibility(View.VISIBLE);
            bottomNavigationView.setVisibility(View.VISIBLE);
        } catch (ApiException e) {

        }
    }



}