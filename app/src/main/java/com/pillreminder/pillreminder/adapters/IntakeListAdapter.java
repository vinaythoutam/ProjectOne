package com.pillreminder.pillreminder.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pillreminder.pillreminder.R;
import com.pillreminder.pillreminder.database.DatabaseHandler;
import com.pillreminder.pillreminder.helper.ReusableCode;
import com.pillreminder.pillreminder.model.NewMedModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class IntakeListAdapter extends BaseAdapter {
    Context context;
    long skipLong=0,takeLong=0;

//    List<NewMedModel> MedList = new ArrayList<>();
    List<NewMedModel> newMedList = new ArrayList<>();
    DatabaseHandler db;

    public IntakeListAdapter(Context context, List<NewMedModel> newMedList) {
        this.context = context;
        this.newMedList=newMedList;
        this.db=new DatabaseHandler(context);

//        for(int i=0;i<MedList.size();i++){
//            if(MedList.get(i).getStatus().equals("")){
//                newMedList.add(MedList.get(i));
//            }
//        }
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView medName,medCount,intakeTime,intakeTime2,intakeTime3;
        ImageView medIcon;
        LinearLayout itemLL,delayL,skipL,takeL;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.intake_list_item, null);
            holder = new ViewHolder();
            holder.medName  = (TextView) convertView.findViewById(R.id.medNameTV);
            holder.medCount  = (TextView) convertView.findViewById(R.id.medCountTV);
            holder.intakeTime  = (TextView) convertView.findViewById(R.id.intakeTimeTV);
            holder.intakeTime2  = (TextView) convertView.findViewById(R.id.intakeTimeTV2);
            holder.intakeTime3  = (TextView) convertView.findViewById(R.id.intakeTimeTV3);
            holder.medIcon = (ImageView) convertView.findViewById(R.id.medIconIV);
            holder.itemLL = (LinearLayout) convertView.findViewById(R.id.itemL);
            holder.delayL = (LinearLayout) convertView.findViewById(R.id.delayLL);
            holder.skipL = (LinearLayout) convertView.findViewById(R.id.skipLL);
            holder.takeL = (LinearLayout) convertView.findViewById(R.id.takeLL);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

//        holder.question.setTypeface(Typeface.createFromAsset(context.getAssets(), fonts.SF_BOLD));
//        holder.answer.setTypeface(Typeface.createFromAsset(context.getAssets(), fonts.SF_LIGHT));

        final NewMedModel nm= newMedList.get(position);

        holder.medName.setText(nm.getMedName());
        Log.e("IntakeStatus",nm.getStatus());
        holder.medCount.setText(nm.getDosageCount()+" "+nm.getDosageType());
        holder.intakeTime.setText("Intake today at " + nm.getTime1());
        if(!nm.getTime2().equals("")){
            holder.intakeTime2.setVisibility(View.VISIBLE);
            holder.intakeTime2.setText(nm.getTime2());

        }
        if(!nm.getTime3().equals("")){
            holder.intakeTime3.setVisibility(View.VISIBLE);
            holder.intakeTime3.setText(nm.getTime3());
        }
        String PACKAGE_NAME = context.getPackageName();
        int imgId = context.getResources().getIdentifier(PACKAGE_NAME+":drawable/"+nm.getMedImageID() , null, null);
        holder.medIcon.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),imgId));

        holder.delayL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ReusableCode.showDelayPopup(context,nm);
            }
        });

        holder.skipL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nm.setSkip_time(ReusableCode.getFullDate(Calendar.getInstance().getTimeInMillis()));
                nm.setSkip_date(ReusableCode.getDate(Calendar.getInstance().getTimeInMillis()));
                skipLong = db.insSkipValues(nm);
                if(skipLong>0){
                    Toast.makeText(context, "Medicine Skipped", Toast.LENGTH_SHORT).show();
                    long l = db.updateStatus("Skipped",nm.getcID());
                    long k = db.updateStatusIntake("Skipped",nm.getcID());
                    Log.e("update skip",l+"");
                }else {
                    Toast.makeText(context, "Something went wrong. Please try agian", Toast.LENGTH_SHORT).show();
                }
                Log.e("skipintake",skipLong+"");
                Activity activity=(Activity) context;
                activity.recreate();
            }
        });

        holder.takeL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nm.setTaken_time(ReusableCode.getFullDate(Calendar.getInstance().getTimeInMillis()));
                nm.setTaken_date(ReusableCode.getDate(Calendar.getInstance().getTimeInMillis()));
                takeLong = db.insTakeValues(nm);
                if(takeLong>0){
                    Toast.makeText(context, "Medicine Taken", Toast.LENGTH_SHORT).show();
                    long l = db.updateStatus("Taken",nm.getcID());
                    long k = db.updateStatusIntake("Taken",nm.getcID());
                    Log.e("update taken",l+"");

                }else {
                    Toast.makeText(context, "Something went wrong. Please try agian", Toast.LENGTH_SHORT).show();
                }
                Log.e("takeintake",takeLong+"");

                Activity activity=(Activity) context;
                activity.recreate();

            }
        });

        Log.e("CDate",nm.getCurrDate());
        Log.e("name",nm.getMedName());

        return convertView;
    }

    @Override
    public int getCount() {
        return newMedList.size();
    }

    @Override
    public Object getItem(int position) {
        return newMedList.size();
    }

    @Override
    public long getItemId(int i) {
        return newMedList.size();
    }


}