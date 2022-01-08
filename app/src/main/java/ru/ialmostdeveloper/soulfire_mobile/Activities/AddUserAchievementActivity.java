package ru.ialmostdeveloper.soulfire_mobile.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.R;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.models.UserAchievement;
import ru.ialmostdeveloper.soulfire_mobile.network.models.UserAchievementResponse;

public class AddUserAchievementActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private ApiClient apiClient;
    private Context self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_achievement);
        self = this;
        apiClient = new ApiClient();
        sessionManager = new SessionManager(this);

        EditText title_input = findViewById(R.id.title_input);
        EditText content_input = findViewById(R.id.content_input);
        EditText description_input = findViewById(R.id.description_input);

        Button btn_add = findViewById(R.id.btn_add_achievement);
        btn_add.setOnClickListener(v -> {
            String title = title_input.getText().toString();
            String content = content_input.getText().toString();
            String description = description_input.getText().toString();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String updatedDate = now.format(dtf);
            UserAchievement userAchievement = new UserAchievement("BD5EEABE-E1D8-4CA5-6149-08D9D0804D3C", sessionManager.fetchUserId(), title, content, description, updatedDate);
            addUserAchievement(userAchievement);
        });
    }

    private void addUserAchievement(UserAchievement userAchievement) {
        apiClient
                .getApiService()
                .addUserAchievement("Bearer " + sessionManager.fetchAuthToken(), userAchievement)
                .enqueue(new Callback<UserAchievementResponse>() {
                    @Override
                    public void onResponse(Call<UserAchievementResponse> call, Response<UserAchievementResponse> response) {
                        Toast.makeText(self, "Added achievement", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UserAchievementResponse> call, Throwable t) {
                        Toast.makeText(self, "failed to add achievement", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}