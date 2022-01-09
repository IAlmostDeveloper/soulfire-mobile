package ru.ialmostdeveloper.soulfire_mobile.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.Activities.AddDiaryNoteActivity;
import ru.ialmostdeveloper.soulfire_mobile.Activities.AddSelfBeliefActivity;
import ru.ialmostdeveloper.soulfire_mobile.Activities.EditDiaryNoteActivity;
import ru.ialmostdeveloper.soulfire_mobile.Activities.EditSelfBeliefActivity;
import ru.ialmostdeveloper.soulfire_mobile.Adapters.PresetsAdapter;
import ru.ialmostdeveloper.soulfire_mobile.R;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryNote;
import ru.ialmostdeveloper.soulfire_mobile.network.models.SelfBelief;
import ru.ialmostdeveloper.soulfire_mobile.network.models.SelfBeliefsResponse;


public class SelfBelievesFragment extends Fragment {

    private SessionManager sessionManager;
    private ApiClient apiClient;
    private Context self;
    private View view;

    public SelfBelievesFragment() {
        // Required empty public constructor
    }

    public static SelfBelievesFragment newInstance(String param1, String param2) {
        SelfBelievesFragment fragment = new SelfBelievesFragment();
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
        view = inflater.inflate(R.layout.fragment_self_believes, container, false);
        Button btn_addnote = view.findViewById(R.id.btn_add_note);
        btn_addnote.setOnClickListener(v -> {
            startActivity(new Intent(this.requireActivity(), AddSelfBeliefActivity.class));
        });
        SelfBelief[] selfBeliefs = getSelfBeliefs();
        return view;
    }

    private SelfBelief[] getSelfBeliefs() {
        final SelfBelief[][] selfBeliefs = {{}};
        apiClient.getApiService().getSelfBeliefs("Bearer " + sessionManager.fetchAuthToken(), sessionManager.fetchUserId())
                .enqueue(new Callback<SelfBeliefsResponse>() {
                    @Override
                    public void onResponse(Call<SelfBeliefsResponse> call, Response<SelfBeliefsResponse> response) {
                        Toast.makeText(self, "Notes get success", Toast.LENGTH_SHORT).show();
                        selfBeliefs[0] = response.body().getContent();

                        LinearLayout notes_layout = view.findViewById(R.id.notes_layout);
                        String currentDate = "";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

                        for (int i = 0; i < selfBeliefs[0].length; i++) {
                            SelfBelief diaryNote = selfBeliefs[0][i];

                            CardView cardView = new CardView(self);

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
                                Intent intent = new Intent(self, EditSelfBeliefActivity.class);
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
                    public void onFailure(Call<SelfBeliefsResponse> call, Throwable t) {
                        Toast.makeText(self, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        return selfBeliefs[0];
    }
}