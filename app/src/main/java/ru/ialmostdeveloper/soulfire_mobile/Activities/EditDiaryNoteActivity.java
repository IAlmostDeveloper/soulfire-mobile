package ru.ialmostdeveloper.soulfire_mobile.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.R;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryNoteResponse;
import ru.ialmostdeveloper.soulfire_mobile.network.models.UpdateNoteRequest;

public class EditDiaryNoteActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private ApiClient apiClient;
    private Context self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary_note);

        sessionManager = new SessionManager(this);
        apiClient = new ApiClient();
        self = this;

        EditText note_title = findViewById(R.id.note_title);
        EditText note_content = findViewById(R.id.note_content);

        note_title.setText(getIntent().getStringExtra("title"));
        note_content.setText(getIntent().getStringExtra("content"));

        Button btn_save = findViewById(R.id.note_save_btn);
        Button btn_delete = findViewById(R.id.note_delete_btn);


        btn_save.setOnClickListener(v -> {
            String title = note_title.getText().toString();
            String content = note_content.getText().toString();

            updateDiaryNote(getIntent().getStringExtra("id"), title, content);
        });

        btn_delete.setOnClickListener(v -> {
            deleteDiaryNote(getIntent().getStringExtra("id"));
        });

    }

    private void updateDiaryNote(String noteId, String title, String content) {
        apiClient
                .getApiService()
                .updateDiaryNote("Bearer " + sessionManager.fetchAuthToken(), noteId, new UpdateNoteRequest(title, content))
                .enqueue(new Callback<DiaryNoteResponse>() {
                    @Override
                    public void onResponse(Call<DiaryNoteResponse> call, Response<DiaryNoteResponse> response) {
                        Toast.makeText(self, "updated", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<DiaryNoteResponse> call, Throwable t) {

                    }
                });
    }

    private void deleteDiaryNote(String noteId) {
        apiClient
                .getApiService()
                .deleteDiaryNote("Bearer " + sessionManager.fetchAuthToken(), noteId)
                .enqueue(new Callback<DiaryNoteResponse>() {
                    @Override
                    public void onResponse(Call<DiaryNoteResponse> call, Response<DiaryNoteResponse> response) {
                        Toast.makeText(self, "deleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<DiaryNoteResponse> call, Throwable t) {

                    }
                });
    }
}