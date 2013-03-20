package com.example.medreminder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
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
