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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryNote;
import ru.ialmostdeveloper.soulfire_mobile.network.models.UserAchievement;
import ru.ialmostdeveloper.soulfire_mobile.network.models.UserAchievementsResponse;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AchievementsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AchievementsFragment extends Fragment {

    private SessionManager sessionManager;
    private ApiClient apiClient;
    private Context self;
    private View view;

    private Button btn_addAchievement;

    public AchievementsFragment() {
        // Required empty public constructor
    }


    public static AchievementsFragment newInstance() {
        AchievementsFragment fragment = new AchievementsFragment();
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
        view = inflater.inflate(R.layout.fragment_achievements, container, false);

        btn_addAchievement = view.findViewById(R.id.btn_add_achievement);
        btn_addAchievement.setOnClickListener(v -> {
            startActivity(new Intent(self, AddUserAchievementActivity.class));
        });

        UserAchievement[] userAchievements = getUserAchievements();

        return view;
    }

    private UserAchievement[] getUserAchievements() {
        final UserAchievement[][] achievements = {{}};
        apiClient
                .getApiService()
                .getUserAchievements("Bearer " + sessionManager.fetchAuthToken(), sessionManager.fetchUserId())
                .enqueue(new Callback<UserAchievementsResponse>() {
                    @Override
                    public void onResponse(Call<UserAchievementsResponse> call, Response<UserAchievementsResponse> response) {
                        Toast.makeText(self, "Achievements get", Toast.LENGTH_SHORT).show();

                        achievements[0] = response.body().getUserAchievements();

                        LinearLayout notes_layout = view.findViewById(R.id.achievements_layout);
                        String currentDate = "";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

                        for (int i = 0; i < achievements[0].length; i++) {
                            UserAchievement diaryNote = achievements[0][i];


                            LocalDate date = LocalDate.parse(diaryNote.getUpdatedDate().substring(0, 10), formatter);
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"));

                            if (!currentDate.equals(date.format(formatter))) {
                                currentDate = diaryNote.getUpdatedDate().substring(0, 10);
                                TextView dateView = new TextView(self);
                                dateView.setText(date.format(dtf));
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT // CardView height
                                );
                                dateView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                dateView.setLayoutParams(layoutParams);
                                notes_layout.addView(dateView);
                            }

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
                                Intent intent = new Intent(self, EditUserAchievementActivity.class);
                                intent.putExtra("id", diaryNote.getId());
                                intent.putExtra("title", diaryNote.getTitle());
                                intent.putExtra("content", diaryNote.getContent());
                                intent.putExtra("description", diaryNote.getDescription());
                                intent.putExtra("updatedDate", diaryNote.getUpdatedDate());

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
                    public void onFailure(Call<UserAchievementsResponse> call, Throwable t) {
                        Toast.makeText(self, "Achievements get error", Toast.LENGTH_SHORT).show();
                    }
                });
        return achievements[0];
    }
}