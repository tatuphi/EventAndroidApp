package com.example.myapplication;

import android.content.Context;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab1Fragment extends Fragment {
    BaseApiService mApiService;
    List<ListAllEventResponse> listAllEventResponses ;
    HistoryParticipation historyParticipationAdapter;
    public Tab1Fragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        Initial
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab1, container, false);
        mApiService = UtilsApi.getAPIService();
        listAllEventResponses = new ArrayList<>();
        // 1. get a reference to recyclerView
        RecyclerView rvListAll = (RecyclerView) rootView.findViewById(R.id.rvListAll);

        // 2. set layoutManger
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvListAll.setLayoutManager(mLayoutManager);
        // this is data for recycler view

        mApiService.getHistoryParticipate("ALL").enqueue(new Callback<BaseListAllRespone>() {
            @Override
            public void onResponse(Call<BaseListAllRespone> call, Response<BaseListAllRespone> response) {
                if(response.isSuccessful())
                {
                    final List<ListAllEventResponse> listAllEventItems = response.body().getResult();

                    rvListAll.setAdapter(new HistoryParticipation(listAllEventItems));
//                    historyParticipationAdapter.notifyDataSetChanged();
                    // 5. set item animator to DefaultAnimator
                    rvListAll.setItemAnimator(new DefaultItemAnimator());
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

        // 3. create an adapter
//        historyParticipationAdapter = new HistoryParticipation(listAllEventResponses);
//        // 4. set adapter
//        rvListAll.setAdapter(historyParticipationAdapter);
//        // 5. set item animator to DefaultAnimator
//        rvListAll.setItemAnimator(new DefaultItemAnimator());

        return rootView;

    }
}
