package com.ahmedhamdy.healthcare.NearbyLocations;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GeometryController {

    public static boolean loading;
    public static ArrayList<NearbyHospitalsDetail> detailArrayList = new ArrayList<>();

    public static void manipulateData(StringBuffer buffer){
        loading = true;

        try{
            detailArrayList.clear();
            JSONObject jsonObject = new JSONObject(buffer.toString());
            JSONArray array = jsonObject.getJSONArray("results");

            for(int i = 0; i < array.length(); i++){
                try{
                    JSONObject jsonObject1 = array.getJSONObject(i);
                    NearbyHospitalsDetail hospitalsDetail = new NearbyHospitalsDetail();

                    if(jsonObject1.getString("name") != null)
                        hospitalsDetail.setHospitalName(jsonObject1.getString("name"));
                    else
                        hospitalsDetail.setHospitalName("Not Available");

                    try{
                        hospitalsDetail.setRating(String.valueOf(jsonObject1.getDouble("rating")));
                    }catch (Exception e){
                        hospitalsDetail.setRating("Not Available");
                    }

                    try{
                        if(jsonObject1.getJSONObject("opening_hours").getBoolean("open_now"))
                            hospitalsDetail.setOpeningHours("Opened");
                        else
                            hospitalsDetail.setOpeningHours("Closed");
                    }catch (Exception e){
                        hospitalsDetail.setOpeningHours("Not Available");
                    }

                    hospitalsDetail.setAddress(jsonObject1.getString("vicinity"));
                    hospitalsDetail.setGeometry(new double[]{jsonObject1.getJSONObject("geometry").getJSONObject("location").getDouble("lat"),
                    jsonObject1.getJSONObject("geometry").getJSONObject("location").getDouble("lng")});

                    detailArrayList.add(hospitalsDetail);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loading = false;
        Log.d("Array Loaded with size", "Size of " + detailArrayList.size());
    }

}
