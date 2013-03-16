package com.example.medreminder;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class medicationActivity extends ListFragment {

	public medicationActivity() {
	}

	static medicationActivity newInstance(int num) {
        medicationActivity med = new medicationActivity();

        Bundle args = new Bundle();
        args.putInt("num", num);

        med.setArguments(args);

        return med;
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.medication,
				container, false);
		Button b_addMed = (Button)rootView.findViewById(R.id.b_addmed);
		b_addMed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), AddMedicatioActivity.class);
	            startActivityForResult(myIntent, 0);	
			}
		});
		return rootView;
	}
}
