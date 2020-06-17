package com.example.myapplication.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class SessionEvent extends RecyclerView.Adapter<SessionEvent.MyView> {
    private List<String> list;
    public class MyView
            extends RecyclerView.ViewHolder {

        // Text View
        TextView txt_date;

        public MyView(View view)
        {
            super(view);

            // initialise TextView with id
            txt_date = (TextView)view
                    .findViewById(R.id.txt_date);
        }

    }
    public SessionEvent(List<String> horizontalList)
    {
        this.list = horizontalList;
    }
    @Override
    public MyView onCreateViewHolder(ViewGroup parent,
                                     int viewType)
    {

        // Inflate item.xml using LayoutInflator
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_date_session,
                        parent,
                        false);

        // return itemView
        return new MyView(itemView);
    }
    @Override
    public void onBindViewHolder(final MyView holder,
                                 final int position)
    {

        // Set the text of each item of
        // Recycler view with the list items
        holder.txt_date.setText(list.get(position));
    }

    // Override getItemCount which Returns
    // the length of the RecyclerView.
    @Override
    public int getItemCount()
    {
        return list.size();
    }

}
