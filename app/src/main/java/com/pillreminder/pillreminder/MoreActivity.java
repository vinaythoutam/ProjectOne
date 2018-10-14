package com.pillreminder.pillreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by LENOVO on 10/9/2018.
 */

public class MoreActivity extends AppCompatActivity {

    LinearLayout pharmacyL,inviteFriend,termsL,appointmentsL;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_activity);

        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView toolImageView = (ImageView) findViewById(R.id.toolImage);

        toolImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pharmacyL=(LinearLayout)findViewById(R.id.pharmacyLL);
        inviteFriend=(LinearLayout)findViewById(R.id.inviteFriendLL);
        termsL=(LinearLayout)findViewById(R.id.termsLL);
        appointmentsL=(LinearLayout)findViewById(R.id.appointmentsLL);



        pharmacyL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MoreActivity.this,MapsActivity.class));
            }
        });
        inviteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MoreActivity.this,InviteFriendsActivity.class));
            }
        });
        appointmentsL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MoreActivity.this,AppointmentsActivity.class));
            }
        });

    }
}
