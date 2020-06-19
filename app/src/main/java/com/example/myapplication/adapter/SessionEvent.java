package com.example.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.ListEvent.Session;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SessionEvent extends RecyclerView.Adapter<SessionEvent.MyView> {
    Context mContext;
    private List<Session> listSession;

    public SessionEvent(Context context, List<Session> horizontalList)
    {
        this.mContext = context;
        this.listSession = horizontalList;
    }
    @Override
    public SessionEvent.MyView onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // Inflate item.xml using LayoutInflator
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date_session, parent, false);
        // return itemView
        return new MyView(itemView);
    }
    @Override
    public void onBindViewHolder(SessionEvent.MyView holder, int position)
    {
        final Session sessionItem = listSession.get(position);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd\nMMM");
        Date today = sessionItem.getDay();
        String dateTime = dateFormat.format(today);
        // Set the text of each item of
        // Recycler view with the list items
        holder.txt_date.setText(dateTime);
    }
    // Override getItemCount which Returns
    // the length of the RecyclerView.
    @Override
    public int getItemCount()
    {
        return listSession.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        // Text View
        @BindView(R.id.txt_date) TextView txt_date;
        public MyView(View view)
        {
            super(view);
            // initialise TextView with id
            ButterKnife.bind(this, view);        }
    }
}
