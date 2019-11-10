package com.example.carpooling.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpooling.MessageActivity;
import com.example.carpooling.Model.User;
import com.example.carpooling.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>
{
    private Context mContext;
    private List<User> mUsers;

    public UserAdapter(Context mContext, List<User> mUsers)
    {
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);

        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = mUsers.get(position);

        holder.username.setText(user.getName());

        if(user.getImageURL().equals("default"))
        {
            holder.profile_view.setImageResource(R.mipmap.userface);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("username", user.getName());
                intent.putExtra("userid", user.getUid());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView username;
        public ImageView profile_view;

        public ViewHolder(View itemView)
        {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            profile_view = itemView.findViewById(R.id.profile_image);
        }
    }


}