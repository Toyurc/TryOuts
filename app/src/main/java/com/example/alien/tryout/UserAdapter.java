package com.example.alien.tryout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

/**
 * Created by Alien on 12/08/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    public static final String KEY_NAME = "name";
    public static final String KEY_URL = "git_url";
    public static final String KEY_IMAGE = "image";

    private ArrayList<User> UserList;
    private Context context;
    public UserAdapter(Context ctx,ArrayList<User> mUSerList){
        this.context = ctx;
        this.UserList = mUSerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_view, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final User UsersList = UserList.get(position);
        holder.UserName.setText(UsersList.getUserName());

        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(3)
                .cornerRadiusDp(30)
                .oval(false)
                .build();

        Picasso.with(context)
                .load(UsersList.getImg_Url())
                .fit()
                .transform(transformation)
                .into(holder.Img_Url);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User UsersList = UserList.get(position);

                Intent profileIntent = new Intent(view.getContext(), UsersProfile.class);
                profileIntent.putExtra(KEY_NAME, UsersList.getUserName());
                profileIntent.putExtra(KEY_URL, UsersList.getGitHub_Url());
                profileIntent.putExtra(KEY_IMAGE, UsersList.getImg_Url());
                view.getContext().startActivity(profileIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView UserName;
        public ImageView Img_Url;
        public TextView GitHub_Url;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            UserName = (TextView) itemView.findViewById(R.id.mProfileName);
            Img_Url = (ImageView) itemView.findViewById(R.id.mProfilePic);
            GitHub_Url = (TextView) itemView.findViewById(R.id.mGitUrl);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.mLinearLayout);
        }
    }
}
