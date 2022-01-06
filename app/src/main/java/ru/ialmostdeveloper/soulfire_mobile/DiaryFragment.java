package ru.ialmostdeveloper.soulfire_mobile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryNote;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryNotesResponse;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiaryFragment extends Fragment {

    private SessionManager sessionManager;
    private ApiClient apiClient;
    private Context self;
    private View view;

    public DiaryFragment() {
        // Required empty public constructor
    }


    public static DiaryFragment newInstance() {
        DiaryFragment fragment = new DiaryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = this.requireActivity();
        sessionManager = new SessionManager(self);
        apiClient = new ApiClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_diary, container, false);
        Button btn_addnote = view.findViewById(R.id.btn_add_note);
        btn_addnote.setOnClickListener(v -> {
            startActivity(new Intent(this.requireActivity(), AddDiaryNoteActivity.class));
        });
        DiaryNote[] diaryNotes = getDiaryNotes();


        return view;
    }

    private DiaryNote[] getDiaryNotes() {
        final DiaryNote[][] diaryNotes = {{}};
        String token = "Bearer " + sessionManager.fetchAuthToken();
        String userId = sessionManager.fetchUserId();
        apiClient
                .getApiService()
                .getDiaryNotes(token, userId)
                .enqueue(new Callback<DiaryNotesResponse>() {
                    @Override
                    public void onResponse(Call<DiaryNotesResponse> call, Response<DiaryNotesResponse> response) {
                        DiaryNotesResponse body = response.body();
                        Toast.makeText(self, "Notes get success", Toast.LENGTH_SHORT).show();
                        diaryNotes[0] = response.body().getContent();

                        LinearLayout notes_layout = view.findViewById(R.id.notes_layout);
                        for (int i = 0; i < diaryNotes[0].length; i++) {
                            CardView cardView = new CardView(self);
                            DiaryNote diaryNote = diaryNotes[0][i];
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                    400, 400 // CardView height
                            );
                            layoutParams.setMargins(20, 20, 20, 20);
                            cardView.setLayoutParams(layoutParams);
                            cardView.setRadius(16F);

                            cardView.setContentPadding(25, 25, 25, 25);
                            cardView.setCardBackgroundColor(Color.LTGRAY);
                            cardView.setCardElevation(8F);
                            cardView.setMaxCardElevation(12F);
                            cardView.setOnClickListener(v -> {
                                Intent intent = new Intent(self, EditDiaryNoteActivity.class);
                                intent.putExtra("id", diaryNote.getId());
                                intent.putExtra("title", diaryNote.getTitle());
                                intent.putExtra("content", diaryNote.getContent());

                                startActivity(intent);
                            });

                            TextView text = new TextView(self);
                            text.setText(diaryNote.getTitle());

                            cardView.addView(text);
                            TextView content = new TextView(self);
                            content.setText(diaryNote.getContent());
                            content.setGravity(Gravity.CENTER);
                            cardView.addView(content);
                            notes_layout.addView(cardView);
                        }

                    }

                    @Override
                    public void onFailure(Call<DiaryNotesResponse> call, Throwable t) {
                        Toast.makeText(self, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        return diaryNotes[0];
    }

}