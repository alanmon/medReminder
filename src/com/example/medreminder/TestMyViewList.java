package com.example.medreminder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class TestMyViewList extends ListFragment {

	String[] presidents = {  
	          "Dwight D. Eisenhower",  
	          "John F. Kennedy",  
	          "Lyndon B. Johnson",  
	          "Richard Nixon",  
	          "Gerald Ford",  
	          "Jimmy Carter",  
	          "Ronald Reagan",  
	          "George H. W. Bush",  
	          "Bill Clinton",  
	          "George W. Bush",  
	          "Barack Obama"  
	     };  

	
 
     @Override  
     public void onCreate(Bundle savedInstanceState) {  
          super.onCreate(savedInstanceState);  
          SimpleAdapter adapter = new SimpleAdapter(this.getActivity(),getData(),R.layout.activity_test_my_view_list,
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
          Toast.makeText(getActivity(),   
               "You have selected " + presidents[position],   
               Toast.LENGTH_SHORT).show();  
     }    

	
}
