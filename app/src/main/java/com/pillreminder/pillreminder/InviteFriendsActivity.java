package com.pillreminder.pillreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by LENOVO on 10/9/2018.
 */

public class InviteFriendsActivity extends AppCompatActivity {
    Button shareBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitefriends_activity);

        getSupportActionBar().hide();

        shareBtn=(Button)findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Pill Tracker");
                intent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!");
                startActivity(Intent.createChooser(intent, "share via"));

            }
        });
    }

}
