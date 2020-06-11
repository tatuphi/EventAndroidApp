package com.example.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.ListAllEventResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HistoryParticipation extends RecyclerView.Adapter<HistoryParticipation.HistoryParticipationHolder> {
    List<ListAllEventResponse> listAllEventResponses;
    Context mContext;

    public HistoryParticipation( List<ListAllEventResponse> allEventList){
        listAllEventResponses = allEventList;
    }

    @Override
    public HistoryParticipationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        return new HistoryParticipationHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryParticipationHolder holder, int position) {
         ListAllEventResponse listAllEventitem = listAllEventResponses.get(position);

//        address, price,
        Picasso.get().load(listAllEventitem.getBannerUrl()).into(holder.imageView);

        holder.txt_eventName.setText(listAllEventitem.getName());
        holder.txt_address.setText("227 Nguyen Van Cu Street, District 5");
        holder.txt_price.setText("Free");
        holder.txt_category.setText(listAllEventitem.getEventCategory().getName());
        holder.txt_time.setText("12:00 AM");

    }

    @Override
    public int getItemCount() {
        return listAllEventResponses.size();
    }

    public class HistoryParticipationHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imageView) ImageView imageView;
        @BindView(R.id.txt_eventName) TextView txt_eventName;
        @BindView(R.id.txt_category) TextView txt_category;
        @BindView(R.id.txt_time) TextView txt_time;
        @BindView(R.id.txt_price) TextView txt_price;
        @BindView(R.id.txt_address) TextView txt_address;


        public HistoryParticipationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
