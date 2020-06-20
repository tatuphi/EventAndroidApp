package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Notification.Result;
import com.squareup.picasso.Picasso;

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
        String fullname,title, notiContent, type_noti;
        String url_icon_noti;
        title = notificationItem.getTitle();
        fullname = notificationItem.getUsersSender().getFullName();
        notiContent = title.replace("{sender}",fullname);

        type_noti = notificationItem.getType();
//        get suitable icon for each notification
        switch (type_noti){
            case "CREDIT_REFUND_SUCCESS":
                url_icon_noti="https://res.cloudinary.com/eventinyourhand/image/upload/v1592035622/NotifyIcon/credit-card-refund-pngrepo-com_jom3oh.png";
                break;
            case "CREDIT_REFUND_FAILED":
                url_icon_noti="https://res.cloudinary.com/eventinyourhand/image/upload/v1592035614/NotifyIcon/Failed-Payment-Limit-300x300_lxf4q7.png";
                break;
            case "SESSION_CANCEL":
                url_icon_noti="https://res.cloudinary.com/eventinyourhand/image/upload/v1592035650/NotifyIcon/calendar-cancel-512_f9syk9.png";
                break;
            case "EVENT_REJECT":
                url_icon_noti="https://res.cloudinary.com/eventinyourhand/image/upload/v1592035636/NotifyIcon/time_event_calendar_remove_cancel_delete-512_w3oyse.png";
                break;
            case "EVENT_CANCEL":
                url_icon_noti="https://res.cloudinary.com/eventinyourhand/image/upload/v1592035629/NotifyIcon/icons8-cancel_oighvs.png";
                break;
            case "ZALOPAY_REFUND_SUCCESS":
                url_icon_noti="https://res.cloudinary.com/eventinyourhand/image/upload/v1592035663/NotifyIcon/1024x1024bb_vxlebi.png";
                break;
            case "ZALOPAY_REFUND_FAILED":
                url_icon_noti ="https://res.cloudinary.com/eventinyourhand/image/upload/v1592035656/NotifyIcon/unnamed_hgy4pg.png";
            default:
                url_icon_noti ="https://res.cloudinary.com/eventinyourhand/image/upload/v1592467292/NotifyIcon/icons8-notification-64_qa0dfh.png";
        }
        Picasso.get().load(url_icon_noti).into(holder.img_notification);
        holder.txt_notification.setText(notiContent);
    }

    @Override
    public int getItemCount() {
        return notificationItemList.size();
    }

    public class NotificationHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_notification)
        TextView txt_notification;
        @BindView(R.id.img_notification)
        ImageView img_notification;

        public NotificationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}