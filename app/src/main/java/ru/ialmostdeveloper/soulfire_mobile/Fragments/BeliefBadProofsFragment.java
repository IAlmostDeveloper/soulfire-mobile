package ru.ialmostdeveloper.soulfire_mobile.Fragments;

import android.content.Context;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.R;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.models.SelfBeliefProof;
import ru.ialmostdeveloper.soulfire_mobile.network.models.SelfBeliefProofResponse;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BeliefBadProofsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeliefBadProofsFragment extends Fragment {
    private SessionManager sessionManager;
    private ApiClient apiClient;
    private Context self;
    public String beliefId;
    public String beliefTitle;
    public String beliefContent;
    public ArrayList<String> goodBeliefs;
    public View view;

    public BeliefBadProofsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static BeliefBadProofsFragment newInstance(String beliefId, String beliefTitle, String beliefContent, ArrayList<String> goodBeliefs) {
        BeliefBadProofsFragment fragment = new BeliefBadProofsFragment();
        Bundle args = new Bundle();
        fragment.beliefId = beliefId;
        fragment.beliefTitle = beliefTitle;
        fragment.beliefContent = beliefContent;
        fragment.goodBeliefs = goodBeliefs;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        self = this.getContext();
        sessionManager = new SessionManager(self);
        apiClient = new ApiClient();

        view = inflater.inflate(R.layout.fragment_my1, container, false);
        TextView label = view.findViewById(R.id.label);
        label.setText(beliefTitle);

        EditText proof_input = view.findViewById(R.id.proof_input);
        LinearLayout proof_layout = view.findViewById(R.id.proofs_layout);

        for (String proof: goodBeliefs){
            TextView proofText = new TextView(self);
            proofText.setText(proof);
            proof_layout.addView(proofText);
        }

        Button btn_addproof = view.findViewById(R.id.btn_add_proof);
        btn_addproof.setOnClickListener(v -> {
            String proof_content = proof_input.getText().toString();
            SelfBeliefProof proof = new SelfBeliefProof(sessionManager.fetchUserId(), beliefId, "Bad", proof_content, "");
            addBeliefProof(proof);
        });
        return view;
    }

    private void addBeliefProof(SelfBeliefProof proof) {
        apiClient
                .getApiService()
                .addBeliefProof("Bearer " + sessionManager.fetchAuthToken(), proof.getId(), proof)
                .enqueue(new Callback<SelfBeliefProofResponse>() {
                    @Override
                    public void onResponse(Call<SelfBeliefProofResponse> call, Response<SelfBeliefProofResponse> response) {
                        Toast.makeText(self, "Added proof", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<SelfBeliefProofResponse> call, Throwable t) {
                        Toast.makeText(self, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}