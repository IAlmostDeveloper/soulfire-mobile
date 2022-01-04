package ru.ialmostdeveloper.soulfire_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.UserCredentials;
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignInRequest;
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignInResponse;

public class SignInActivity extends AppCompatActivity {

    private EditText username_input;
    private EditText password_input;
    private Button signInButton;

    private SessionManager sessionManager;
    private ApiClient apiclient;

    private Context self;
    SharedPreferences sprefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        self = this;
        sprefs = this.getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", Context.MODE_PRIVATE);
        sessionManager = new SessionManager(this);
        apiclient = new ApiClient();

        username_input = findViewById(R.id.usernameInput);
        password_input = findViewById(R.id.passwordInput);
        signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(v -> {
            String username = username_input.getText().toString();
            String password = password_input.getText().toString();

            login(username, password);
        });

    }

    private void login(String username, String password) {
        apiclient.getApiService().login(new SignInRequest(username, password))
                .enqueue(new Callback<SignInResponse>(){

                    @Override
                    public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                        SignInResponse signInResponse = response.body();
                        if(response.isSuccessful()){
                            Toast.makeText(self, "Успешная логин", Toast.LENGTH_SHORT).show();
                            assert signInResponse != null;
                            sessionManager.saveUserCredentials(new UserCredentials(signInResponse.getUsername(),
                                    signInResponse.getUserId(), signInResponse.getToken()));
                            sprefs.edit().putBoolean("isUserLoggedIn", true).apply();
                            startActivity(new Intent(self, MainActivity.class));
                        } else
                        Toast.makeText(self, "Неуспешная логин 401 вводить правильный пароль да", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<SignInResponse> call, Throwable t) {
                        Toast.makeText(self, "Неудачный логин", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}