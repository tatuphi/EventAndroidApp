package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Notification.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder>{
    Context mContext;
    List<Result> notificationItemList;
    public NotificationAdapter(Context context, List<Result> notificationList) {
        this.mContext = context;
        notificationItemList = notificationList;
    }

    @Override
    public NotificationAdapter.NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_notification, parent, false);
        return new NotificationHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.NotificationHolder holder, int position) {
        final Result notificationItem = notificationItemList.get(position);
        holder.txt_notification.setText(notificationItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return notificationItemList.size();
    }

    public class NotificationHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_notification)
        TextView txt_notification;

        public NotificationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
