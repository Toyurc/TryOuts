package com.example.alien.tryout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Alien on 12/08/2017.
 */

public class UsersProfile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        ImageView profileImageView = (ImageView) findViewById(R.id.MDevImage);
        TextView userNameTextView = (TextView) findViewById(R.id.mUSerName);
        ImageButton shareProfile = (ImageButton) findViewById(R.id.mImgButton);
        TextView GitHubUrl = (TextView) findViewById(R.id.mGitUrl);


        Intent intent = getIntent();
        final String userName = intent.getStringExtra(UserAdapter.KEY_NAME);
        String image = intent.getStringExtra(UserAdapter.KEY_IMAGE);
        final String profileUrl = intent.getStringExtra(UserAdapter.KEY_URL);


        Picasso.with(this)
                .load(image)
                .into(profileImageView);

        userNameTextView.setText(userName);
        GitHubUrl.setText(profileUrl);
        GitHubUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = profileUrl;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        shareProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer " + userName +
                        ", " + profileUrl);
                Intent shareVia = Intent.createChooser(shareIntent, "Share via");
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(shareVia);
                }
            }
        });


    }
}
