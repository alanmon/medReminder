package com.example.medreminder;


import java.util.ArrayList;
import java.util.List;

import android.R.anim;
import android.R.string;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.AndroidCharacter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

<<<<<<< HEAD
public class medicationActivity extends Fragment {

	public medicationActivity() {
=======
public class medicationActivity extends ListFragment {
	/*String[] strings={
			"1",
			"2",
			"3",
			"1",
			"2",
			"3",
			"1",
			"2",
			"3",
			"1",
			"2",
			"3",
			"1",
			"2",
			"3"
			
	};*/
	public class Medicinelist{
		String aString;
		Button b;
>>>>>>> ListFragment
	}
	
	

	/*static medicationActivity newInstance(int num) {
        medicationActivity med = new medicationActivity();

        Bundle args = new Bundle();
        args.putInt("num", num);

        med.setArguments(args);

        return med;
<<<<<<< HEAD
   } 
=======
    }*/
>>>>>>> ListFragment
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		return inflater.inflate(R.layout.medication, container, false);
	}
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,strings));
		setListAdapter(new ListAdapter() {
			
			@Override
			public void unregisterDataSetObserver(DataSetObserver observer) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void registerDataSetObserver(DataSetObserver observer) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public int getViewTypeCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getItemViewType(int position) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public boolean isEnabled(int position) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean areAllItemsEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		//setListAdapter(new ArrayAdapter<String>(getActivity(),R.layout.medicine_list,strings));
		
	}
}

