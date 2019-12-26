package com.example.loginactivitytest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginactivitytest.R;
import com.example.loginactivitytest.model.Login;
import com.example.loginactivitytest.model.User;
import com.example.loginactivitytest.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://172.16.31.189:8085/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    UserService userService = retrofit.create(UserService.class);

    EditText edtUsername, edtPassword;
    Button btnLogin;
    String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername =  findViewById(R.id.edtUsername);
        edtPassword =  findViewById(R.id.edtPassword);
        btnLogin =  findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                //validate form
                if(validateLogin(username, password)){
                    //do login
                    doLogin(username, password);
                }
            }
        });
        //getSecret();
    }

    public boolean validateLogin(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void doLogin(final String username,final String password){

        Log.d("fjdnsjna","called: userName:"+username+" password:"+password);
        Login login = new Login(username,password);
        Call<User> call = userService.login(login);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("Test Response"+response);
                Log.d("fjdnsjna","called:"+response.toString());
                if (response.isSuccessful()){

                    Log.d("fjdnsjna","called:"+response.toString());
                    /*Toast.makeText(MainActivity.this, "Login Happy!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();*/

                    token = response.body().getToken();
                    Log.d("fjdnsjna","called:"+token);
                    Intent intent=  new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("token",String.valueOf(token));
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "username or password incorrect!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
   /* private void getSecret(){
        Call<ResponseBody> call= userService.getSecret(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        Toast.makeText(MainActivity.this, "username or password Iscorrect!", Toast.LENGTH_SHORT).show();

                        Toast.makeText(MainActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    *//*token = response.body().getToken();*//*
                    //Intent intent=  new Intent(MainActivity.this, HomeActivity.class);
                    *//*intent.putExtra("token",String.valueOf(token));*//*
                   // startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "username or password incorrect!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }*/
}

