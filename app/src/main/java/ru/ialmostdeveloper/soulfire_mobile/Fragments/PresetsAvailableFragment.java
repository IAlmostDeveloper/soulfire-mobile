package ru.ialmostdeveloper.soulfire_mobile.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.R;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.models.Preset;
import ru.ialmostdeveloper.soulfire_mobile.network.models.PresetResponse;


public class PresetsAvailableFragment extends Fragment {
    private SessionManager sessionManager;
    private ApiClient apiClient;
    private Context self;

    public String[] presets = {
            "Установка 1",
            "Установка 2",
            "Установка 3",
            "Установка 4",
            "Установка 5",
            "Установка 6",
            "Установка 7",
    };

    public PresetsAvailableFragment() {
        // Required empty public constructor
    }

    public static PresetsAvailableFragment newInstance() {
        PresetsAvailableFragment fragment = new PresetsAvailableFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_presets_available, container, false);

        for (int i=0;i<presets.length; i++){
            View presetView = inflater.inflate(R.layout.preset_card, container, false);
            LinearLayout presetLayout = view.findViewById(R.id.preset_layout);
            TextView presetLabel = presetView.findViewById(R.id.preset_label);
            presetLabel.setText(presets[i]);
            Button btn_addPreset = presetView.findViewById(R.id.btn_add_preset);
            int finalI = i;
            btn_addPreset.setOnClickListener(v -> {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String updatedDate = now.format(dtf);
                Preset preset = new Preset(sessionManager.fetchUserId(), sessionManager.fetchUserId(), presets[finalI], "", updatedDate);
                addUserPreset(preset);
            });
            presetLayout.addView(presetView);
        }

        return view;
    }

    private void addUserPreset(Preset preset){
        apiClient
                .getApiService()
                .addUserPreset("Bearer " + sessionManager.fetchAuthToken(), preset)
                .enqueue(new Callback<PresetResponse>() {
            @Override
            public void onResponse(Call<PresetResponse> call, Response<PresetResponse> response) {
                Toast.makeText(self, "Preset added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PresetResponse> call, Throwable t) {
                Toast.makeText(self, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}