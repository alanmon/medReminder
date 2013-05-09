package com.example.medreminder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListOfMed extends ListFragment {
 
     @Override  
     public void onCreate(Bundle savedInstanceState) {  
          super.onCreate(savedInstanceState);  
          SimpleAdapter adapter = new SimpleAdapter(this.getActivity(),getData(),R.layout.activity_medlist,
                  new String[]{"title","info","img"},
                  new int[]{R.id.title,R.id.info,R.id.img});
          setListAdapter(adapter);
          
         
     }
     
     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
    	 View view=inflater.inflate(R.layout.activity_test_my_view, container,false);
    	 Button addMed=(Button)view.findViewById(R.id.b_addmed);
    	 OnClickListener onClickListener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(getActivity(), AddMedicatioActivity.class);
				startActivity(intent);
			}
		};
    	addMed.setOnClickListener(onClickListener); 
    	 
         return view;
     }
     
     private List<Map<String, Object>> getData() {
         List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("title", "G1");
         map.put("info", "google 1");
         map.put("img", R.drawable.btn_history);
         list.add(map);
  
         map = new HashMap<String, Object>();
         map.put("title", "G2");
         map.put("info", "google 2");
         map.put("img", R.drawable.btn_log);
         list.add(map);
  
         map = new HashMap<String, Object>();
         map.put("title", "G3");
         map.put("info", "google 3");
         map.put("img", R.drawable.btn_med);
         list.add(map);
          
         return list;
     }
      
     public void onListItemClick(ListView parent, View v,   
     int position, long id)   
     {            
   
     }    

	
}
	