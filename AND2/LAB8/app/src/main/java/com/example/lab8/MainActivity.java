package com.example.lab8;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    SignInButton google;
    Button btndangxuat;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button map = findViewById(R.id.map);
        Button facebook = findViewById(R.id.facebook);
         btndangxuat = findViewById(R.id.btndangxuat);
         google = findViewById(R.id.google);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Map.class));
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signinIntent = mGoogleSignInClient.getSignInIntent();
                resultLauncher.launch(signinIntent);
            }
        });
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut();
                Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            Toast.makeText(this, "Đã đăng nhập tài khoản " + account.getEmail(),
                    Toast.LENGTH_SHORT).show();
            google.setVisibility(View.GONE);
            btndangxuat.setVisibility(View.VISIBLE);
        }
    }
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        try {
                            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                            // thông tin mail
                            GoogleSignInAccount account = task.getResult(ApiException.class);
                            // get email
                            String email = account.getEmail();
                            // get name
                            String name = account.getDisplayName();
                            // lấy ảnh người dùng
                            String avatar = account.getPhotoUrl().toString();
                            Toast.makeText(MainActivity.this, "email : "+email+"\n"+"name : "+name, Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            Log.e(TAG,"onFailure",e);
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
}