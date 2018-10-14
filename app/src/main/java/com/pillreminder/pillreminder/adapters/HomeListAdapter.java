package com.pillreminder.pillreminder.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pillreminder.pillreminder.HomeActivity;
import com.pillreminder.pillreminder.R;
import com.pillreminder.pillreminder.database.DatabaseHandler;
import com.pillreminder.pillreminder.helper.ReusableCode;
import com.pillreminder.pillreminder.model.NewMedModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class HomeListAdapter extends BaseAdapter {
    Context context;

    List<NewMedModel> newMedList = new ArrayList<>();

    public HomeListAdapter(Context context, List<NewMedModel> newMedList) {
        this.context = context;
        this.newMedList=newMedList;

    }

    /*private view holder class*/
    private class ViewHolder {
        TextView medName,medCount,intakeTime,intakeTime2,intakeTime3,meal_status,pill_status;
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
            holder.meal_status  = (TextView) convertView.findViewById(R.id.mealStatus);
            holder.pill_status  = (TextView) convertView.findViewById(R.id.pillStatus);

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
        if(nm.getIsWithRegardOrNot().equalsIgnoreCase("True")){
            holder.meal_status.setText("With Meal");
        }else {
            holder.meal_status.setText("Without Meal");
        }


        if(nm.getStatus().equalsIgnoreCase("Taken")){
            holder.pill_status.setVisibility(View.VISIBLE);
            holder.pill_status.setText(nm.getStatus());
            holder.pill_status.setTextColor(context.getResources().getColor(R.color.green));
        }else  if(nm.getStatus().equalsIgnoreCase("Skipped")){
            holder.pill_status.setVisibility(View.VISIBLE);
            holder.pill_status.setText(nm.getStatus());
            holder.pill_status.setTextColor(context.getResources().getColor(R.color.red));
        }

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
                    if(nm.getStatus().equalsIgnoreCase("Taken")||nm.getStatus().equalsIgnoreCase("Skipped")){
                    }else {
                        showItemPopup(context,nm);
                    }
                }

            }
        });
        Log.e("CDate",nm.getCurrDate());
        Log.e("name",nm.getMedName());
        Log.e("status",nm.getStatus());

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


    public static void showItemPopup(final Context context, final NewMedModel nm) {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_click_pop_up);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        DisplayMetrics mertics = context.getResources().getDisplayMetrics();
        int width = mertics.widthPixels;

        Window window = dialog.getWindow();
        window.setLayout((int) (width * 0.80), WindowManager.LayoutParams.WRAP_CONTENT);

        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

//        Constants fonts = new Constants();

        TextView medName = (TextView) dialog.findViewById(R.id.medNameText);
        TextView time1 = (TextView) dialog.findViewById(R.id.time1TV);
        TextView time2 = (TextView) dialog.findViewById(R.id.time2TV);
        TextView time3 = (TextView) dialog.findViewById(R.id.time3TV);
        TextView take = (TextView) dialog.findViewById(R.id.takeTV);
        TextView skip = (TextView) dialog.findViewById(R.id.skipTV);
        final TextView cancel = (TextView) dialog.findViewById(R.id.cancelTV);

        medName.setText(nm.getMedName());
        time1.setText(nm.getTime1());
        if(!nm.getTime2().equals("")){
            time2.setVisibility(View.VISIBLE);
            time2.setText(nm.getTime2());
        }
        if(!nm.getTime3().equals("")){
            time3.setVisibility(View.VISIBLE);
            time3.setText(nm.getTime3());
        }

        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler databaseHandler= new DatabaseHandler(context);
                nm.setTaken_time(ReusableCode.getFullDate(Calendar.getInstance().getTimeInMillis()));
                nm.setTaken_date(ReusableCode.getDate(Calendar.getInstance().getTimeInMillis()));

                long n =databaseHandler.insTakeValues(nm);
                if(n>0){
                    Toast.makeText(context, "Medicine taken.", Toast.LENGTH_SHORT).show();
                    long l = databaseHandler.updateStatus("Taken",nm.getcID());
                    Log.e("update result",""+l);
                    if(l>0){
                        Activity activity= (Activity) context;
                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);
                        activity.finish();


                    }
                }else {
                    Toast.makeText(context, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler databaseHandler= new DatabaseHandler(context);
                nm.setSkip_time(ReusableCode.getFullDate(Calendar.getInstance().getTimeInMillis()));
                nm.setSkip_date(ReusableCode.getDate(Calendar.getInstance().getTimeInMillis()));

                long n =databaseHandler.insSkipValues(nm);
                if(n>0){
                    Toast.makeText(context, "Medicine Skipped.", Toast.LENGTH_SHORT).show();
                    long l = databaseHandler.updateStatus("Skipped",nm.getcID());
                    Log.e("update result",""+l);
                    if(l>0){
                        Activity activity= (Activity) context;
                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);
                        activity.finish();
                    }
                }else {
                    Toast.makeText(context, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }



}