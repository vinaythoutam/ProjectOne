package com.pillreminder.pillreminder.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pillreminder.pillreminder.R;

import java.util.ArrayList;
import java.util.List;


public class FormListAdapter extends BaseAdapter {
    Context context;

    List<String> names = new ArrayList<>();
    List<Integer> imageIDs = new ArrayList<>();
    List<String> imageTags = new ArrayList<>();



    public FormListAdapter(Context context,List<String> names, List<Integer> imageIDs,List<String> imageTags) {
        this.context = context;
        this.names=names;
        this.imageIDs=imageIDs;
        this.imageTags=imageTags;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView name;
        ImageView icon;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.form_listview_item, null);
            holder = new ViewHolder();
            holder.name  = (TextView) convertView.findViewById(R.id.nameTV);
            holder.icon = (ImageView) convertView.findViewById(R.id.iconIV);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

//        holder.question.setTypeface(Typeface.createFromAsset(context.getAssets(), fonts.SF_BOLD));
//        holder.answer.setTypeface(Typeface.createFromAsset(context.getAssets(), fonts.SF_LIGHT));
        holder.name.setText(names.get(position));
        holder.icon.setImageResource(imageIDs.get(position));

        return convertView;
    }

    @Override
    public int getCount() {
        return names.size();
//        return 2;
    }

    @Override
    public Object getItem(int position) {
        return names.size();
//        return 2;
    }

    @Override
    public long getItemId(int i) {
        return names.size();
    }


}