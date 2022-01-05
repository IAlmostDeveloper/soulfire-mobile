package ru.ialmostdeveloper.soulfire_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddDiaryNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary_note);

        EditText note_title = findViewById(R.id.note_title);
        EditText note_content = findViewById(R.id.note_content);

        Button btn_addNote = findViewById(R.id.btn_add_note);
        btn_addNote.setOnClickListener(v -> {
            String title = note_title.getText().toString();
            String content = note_content.getText().toString();

            // Отправить тут нотифку на сервер
        });

    }
}