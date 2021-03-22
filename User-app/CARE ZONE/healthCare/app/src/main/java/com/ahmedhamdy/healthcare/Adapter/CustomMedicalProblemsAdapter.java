package com.ahmedhamdy.healthcare.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmedhamdy.healthcare.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomMedicalProblemsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> idImages;
    private ArrayList<String> textViewList;
    private List<String> cloudImages;

    public CustomMedicalProblemsAdapter(Context context, ArrayList<Integer> idImages, ArrayList<String> textViewList, List<String> cloudImages){
        this.context = context;
        this.idImages = idImages;
        this.textViewList = textViewList;
        this.cloudImages = cloudImages;
    }
    @Override
    public int getCount() {
        return textViewList.size();
    }

    @Override
    public Object getItem(int position) {
        return textViewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        /*
        if(position < idImages.size())
            return idImages.get(position);
        */
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            convertView = convertView.inflate(context, R.layout.medical_problems_list_design,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(position < idImages.size())
            holder.imageView.setImageResource(idImages.get(position));
        else
            Picasso.get().load(cloudImages.get(position-idImages.size())).into(holder.imageView);

        holder.textView.setText(textViewList.get(position));
        return convertView;

    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;
        ViewHolder(View converView){
            imageView = converView.findViewById(R.id.medicalProblemImage);
            textView = converView.findViewById(R.id.medicalProblemText);
        }
    }
}
