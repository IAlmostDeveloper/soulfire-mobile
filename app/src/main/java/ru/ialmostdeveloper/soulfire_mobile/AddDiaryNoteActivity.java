package ru.ialmostdeveloper.soulfire_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.models.AddDiaryNoteRequest;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryNote;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryNoteResponse;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryQuestion;

public class AddDiaryNoteActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private ApiClient apiClient;
    private Context self;

    private ViewPager viewPager;
    private DiarySlideAdapter diarySlideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary_note);
        self = this;
        sessionManager = new SessionManager(this);
        apiClient = new ApiClient();

        viewPager = findViewById(R.id.viewpager);
        diarySlideAdapter = new DiarySlideAdapter(this);
        viewPager.setAdapter(diarySlideAdapter);

        //EditText note_title = findViewById(R.id.note_title);
        //EditText note_content = findViewById(R.id.note_content);

//        Button btn_addNote = findViewById(R.id.btn_add_note);
//        btn_addNote.setOnClickListener(v -> {
//            //String title = note_title.getText().toString();
//            //String content = note_content.getText().toString();
//
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            LocalDateTime now = LocalDateTime.now();
//            String updatedDate = now.format(dtf);
//            DiaryNote diaryNote = new DiaryNote("", Objects.requireNonNull(sessionManager.fetchUserId()), title, content, updatedDate);
//            addDiaryNote(diaryNote);
//        });


    }

    private void addDiaryNote(DiaryNote diaryNote) {
        AddDiaryNoteRequest addDiaryNoteRequest = new AddDiaryNoteRequest(
                Objects.requireNonNull(sessionManager.fetchUserId()), diaryNote.getTitle(), diaryNote.getContent(), diaryNote.getUpdatedDate()
        );
        apiClient
                .getApiService()
                .addDiaryNote("Bearer " + sessionManager.fetchAuthToken(), addDiaryNoteRequest)
                .enqueue(new Callback<DiaryNoteResponse>() {
                    @Override
                    public void onResponse(Call<DiaryNoteResponse> call, Response<DiaryNoteResponse> response) {
                        Toast.makeText(self, "Added note", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<DiaryNoteResponse> call, Throwable t) {
                        Toast.makeText(self, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}