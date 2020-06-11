package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.adapter.HistoryParticipation;
import com.example.myapplication.model.BaseListAllRespone;
import com.example.myapplication.model.ListAllEventResponse;
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab3Fragment extends Fragment {
    BaseApiService mApiService;
    List<ListAllEventResponse> listAllEventResponses;
    HistoryParticipation historyParticipation;
    public Tab3Fragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab3 ,container, false);
        mApiService = UtilsApi.getAPIService();
        listAllEventResponses = new ArrayList<>();
        RecyclerView rvListPast = (RecyclerView) rootView.findViewById(R.id.rvListPast);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvListPast.setLayoutManager(mLayoutManager);

//        data
        mApiService.getHistoryParticipate("PAST").enqueue(new Callback<BaseListAllRespone>() {
            @Override
            public void onResponse(Call<BaseListAllRespone> call, Response<BaseListAllRespone> response) {
                if(response.isSuccessful())
                {
                    final List<ListAllEventResponse> listPastEventItems = response.body().getResult();
                    rvListPast.setAdapter(new HistoryParticipation(listPastEventItems));
                    rvListPast.setItemAnimator(new DefaultItemAnimator());
                }
                else{
                    try {
                        JSONObject jsonError = new JSONObject(response.errorBody().string());
                        Log.e("debug", "onFailure: ERROR 600 > " + jsonError.getJSONObject("error").getString("message") );
                        Toast.makeText(getActivity(), jsonError.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseListAllRespone> call, Throwable t) {

            }
        });
        return rootView;
    }
}
