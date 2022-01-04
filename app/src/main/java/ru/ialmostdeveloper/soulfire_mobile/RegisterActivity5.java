package ru.ialmostdeveloper.soulfire_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.UserCredentials;
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignInRequest;
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignInResponse;
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignUpRequest;
import ru.ialmostdeveloper.soulfire_mobile.network.models.SignUpResponse;

public class RegisterActivity5 extends AppCompatActivity {

    private Button btn_register;
    private EditText username_input;
    private EditText password_input;

    private SessionManager sessionManager;
    private ApiClient apiClient;

    private Context self;
    SharedPreferences sprefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register5);
        self = this;
        sprefs = this.getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", Context.MODE_PRIVATE);
        apiClient = new ApiClient();
        sessionManager = new SessionManager(this);
        btn_register = findViewById(R.id.signUpButton);
        username_input = findViewById(R.id.usernameInput);
        password_input = findViewById(R.id.passwordInput);

        String characterType = sprefs.getString("userCharacterType", "Uundefinedd");
        Object[] autoThoughtsRaw = sprefs.getStringSet("userAutoThoughts", new HashSet<String>()).toArray();
        Object[] middleThoughtsRaw = sprefs.getStringSet("userMiddleThoughts", new HashSet<String>()).toArray();
        Object[] deepThoughtsRaw = sprefs.getStringSet("userDeepThoughts", new HashSet<String>()).toArray();

        String[] autoThoughts = Arrays.copyOf(autoThoughtsRaw, autoThoughtsRaw.length, String[].class);
        String[] middleThoughts = Arrays.copyOf(middleThoughtsRaw, middleThoughtsRaw.length, String[].class);
        String[] deepThoughts = Arrays.copyOf(deepThoughtsRaw, deepThoughtsRaw.length, String[].class);


        btn_register.setOnClickListener(v -> {
            String userLogin = username_input.getText().toString();
            String userPassword = password_input.getText().toString();

            register(userLogin, userPassword, characterType, autoThoughts, middleThoughts, deepThoughts);

        });
    }

    private void register(String username, String password, String characterType, String[] autoThoughts, String[] middleThoughts, String[] deepThoughts) {
        apiClient
                .getApiService()
                .register(new SignUpRequest(username,password, characterType, autoThoughts, middleThoughts, deepThoughts))
                .enqueue(new Callback<SignUpResponse>( ) {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        SignUpResponse loginResponse = response.body();
                        RequestBody rq = call.request().body();
                        if (response.isSuccessful()){

                            apiClient.getApiService().login(new SignInRequest(username, password)).enqueue(new Callback<SignInResponse>() {
                                @Override
                                public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                                    SignInResponse signInResponse = response.body();
                                    Toast.makeText(self, "Успешная регистрация", Toast.LENGTH_SHORT).show();

                                    assert signInResponse != null;
                                    sessionManager.saveUserCredentials(new UserCredentials(signInResponse.getUsername(),
                                            signInResponse.getUserId(), signInResponse.getToken()));
                                    sprefs.edit().putBoolean("isUserLoggedIn", true).apply();
                                    startActivity(new Intent(self, MainActivity.class));
                                }

                                @Override
                                public void onFailure(Call<SignInResponse> call, Throwable t) {

                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        String loginResponse = t.getMessage();
                        RequestBody rq = call.request().body();
                        HttpUrl url = call.request().url();
                        Toast.makeText(self, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}