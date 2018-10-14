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

import com.pillreminder.pillreminder.R;
import com.pillreminder.pillreminder.helper.ReusableCode;
import com.pillreminder.pillreminder.model.NewMedModel;

import java.util.ArrayList;
import java.util.List;


public class MedListAdapter extends BaseAdapter {
    Context context;

    List<NewMedModel> newMedList = new ArrayList<>();

    public MedListAdapter(Context context, List<NewMedModel> newMedList) {
        this.context = context;
        this.newMedList=newMedList;

    }

    /*private view holder class*/
    private class ViewHolder {
        TextView medName,medCount,intakeTime,intakeTime2,intakeTime3;
        ImageView medIcon;
        LinearLayout itemLL;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.new_med_list_item, null);
            holder = new ViewHolder();
            holder.medName  = (TextView) convertView.findViewById(R.id.medNameTV);
            holder.medCount  = (TextView) convertView.findViewById(R.id.medCountTV);
            holder.intakeTime  = (TextView) convertView.findViewById(R.id.intakeTimeTV);
            holder.intakeTime2  = (TextView) convertView.findViewById(R.id.intakeTimeTV2);
            holder.intakeTime3  = (TextView) convertView.findViewById(R.id.intakeTimeTV3);
            holder.medIcon = (ImageView) convertView.findViewById(R.id.medIconIV);
            holder.itemLL = (LinearLayout) convertView.findViewById(R.id.itemL);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

//        holder.question.setTypeface(Typeface.createFromAsset(context.getAssets(), fonts.SF_BOLD));
//        holder.answer.setTypeface(Typeface.createFromAsset(context.getAssets(), fonts.SF_LIGHT));

        final NewMedModel nm= newMedList.get(position);

        holder.medName.setText(nm.getMedName());
        holder.medCount.setText(nm.getDosageCount()+" "+nm.getDosageType());
        holder.intakeTime.setText(nm.getTime1());
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

        holder.itemLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("StDate",nm.getStartDate()+"");
                Log.e("edDate",nm.getEndDate()+"");
                Log.e("Today",System.currentTimeMillis()+"");

                if(System.currentTimeMillis()>nm.getStartDate() && System.currentTimeMillis()<nm.getEndDate()){
//                    Toast.makeText(context, "POP UP", Toast.LENGTH_SHORT).show();
                    ReusableCode.showItemPopup(context,nm);
                }

            }
        });
        Log.e("CDate",nm.getCurrDate());
        Log.e("name",nm.getMedName());

//        Log.e("sDate",nm.getStartDate());
//        Log.e("eDate",nm.getEndDate());
//        Log.e("intakeTime",nm.getIntakeTime1());
//        Log.e("reminderTime",nm.getRefillReminderTime());
//        Log.e("selectedFreq",nm.getFrequency());




        return convertView;
    }

    @Override
    public int getCount() {
        return newMedList.size();
//        return 2;
    }

    @Override
    public Object getItem(int position) {
        return newMedList.size();
//        return 2;
    }

    @Override
    public long getItemId(int i) {
        return newMedList.size();
    }


}