package com.ahmedhamdy.healthcare.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ahmedhamdy.healthcare.NearbyLocations.NearbyHospitalsDetail;
import com.ahmedhamdy.healthcare.R;

import java.util.ArrayList;
import java.util.List;



public class CustomPlacesAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> placeName;
    private ArrayList<String> ratingText;
    private ArrayList<String> openNowText;

    public CustomPlacesAdapter(Context context,ArrayList<String> placeName, ArrayList<String> ratingText, ArrayList<String> openNowText){
        this.context = context;
        this.placeName = placeName;
        this.ratingText = ratingText;
        this.openNowText = openNowText;

    }
    @Override
    public int getCount() {
        return placeName.size();
    }

    @Override
    public Object getItem(int position) {
        return placeName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 3232;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = convertView.inflate(context,R.layout.health_centers_result_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.placeTextView.setText(placeName.get(position));
        holder.ratingTextView.setText(ratingText.get(position));
        holder.openNowTextView.setText("Open now: " + openNowText.get(position));
        return convertView;
    }
    class ViewHolder{
        TextView placeTextView;
        TextView ratingTextView;
        TextView openNowTextView;
        public ViewHolder(View converView){
            placeTextView = converView.findViewById(R.id.placeNameTextView);
            ratingTextView = converView.findViewById(R.id.ratingTextView);
            openNowTextView = converView.findViewById(R.id.openingTime);
        }
    }
}
