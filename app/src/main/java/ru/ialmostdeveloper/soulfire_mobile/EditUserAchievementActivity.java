package ru.ialmostdeveloper.soulfire_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryNoteResponse;
import ru.ialmostdeveloper.soulfire_mobile.network.models.UpdateNoteRequest;
import ru.ialmostdeveloper.soulfire_mobile.network.models.UserAchievement;
import ru.ialmostdeveloper.soulfire_mobile.network.models.UserAchievementResponse;

public class EditUserAchievementActivity extends AppCompatActivity {
    private SessionManager sessionManager;
    private ApiClient apiClient;
    private Context self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_achievement);

        sessionManager = new SessionManager(this);
        apiClient = new ApiClient();
        self = this;

        EditText note_title = findViewById(R.id.note_title);
        EditText note_content = findViewById(R.id.note_content);
        EditText note_description = findViewById(R.id.note_description);

        note_title.setText(getIntent().getStringExtra("title"));
        note_content.setText(getIntent().getStringExtra("content"));
        note_description.setText(getIntent().getStringExtra("description"));

        Button btn_save = findViewById(R.id.note_save_btn);
        Button btn_delete = findViewById(R.id.note_delete_btn);

        btn_save.setOnClickListener(v -> {
            String title = note_title.getText().toString();
            String content = note_content.getText().toString();
            String description = note_description.getText().toString();

            updateUserAchievement(getIntent().getStringExtra("id"), title, content, description);
        });

        btn_delete.setOnClickListener(v -> {
            deleteUserAchievement(getIntent().getStringExtra("id"));
        });


    }

    private void updateUserAchievement(String noteId, String title, String content, String description) {
        apiClient
                .getApiService()
                .updateUserAchievement("Bearer " + sessionManager.fetchAuthToken(),
                        noteId, new UserAchievement(noteId, sessionManager.fetchUserId(), title, content, description, getIntent().getStringExtra("updatedDate")))
                .enqueue(new Callback<UserAchievementResponse>() {
                    @Override
                    public void onResponse(Call<UserAchievementResponse> call, Response<UserAchievementResponse> response) {
                        Toast.makeText(self, "updated", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<UserAchievementResponse> call, Throwable t) {

                    }
                });
    }

    private void deleteUserAchievement(String noteId) {
        apiClient
                .getApiService()
                .deleteUserAchievement("Bearer " + sessionManager.fetchAuthToken(), noteId)
                .enqueue(new Callback<UserAchievementResponse>() {
                    @Override
                    public void onResponse(Call<UserAchievementResponse> call, Response<UserAchievementResponse> response) {
                        Toast.makeText(self, "deleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<UserAchievementResponse> call, Throwable t) {

                    }
                });
    }

}