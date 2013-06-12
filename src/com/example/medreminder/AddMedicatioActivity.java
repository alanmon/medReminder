package com.example.medreminder;



import java.io.FileNotFoundException;

import com.example.medreminder.db.MedDatebase;

import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class AddMedicatioActivity extends Activity {
	ImageButton medtypeA;
	ImageButton medtypeB;
	ImageButton medtypeC; 
	int medtypeInt=0;
	ImageButton medDoseA;
	ImageButton medDoseB;
	ImageButton medDoseC;
	ImageButton medDoseD;
	ImageButton medDoseE;
	int medDoseInt=0;
	ImageButton medFreA;
	ImageButton medFreB;
	ImageButton medFreC;
	ImageButton medFreD;
	int medFre=0;
	Spinner timeSpinner4;
	Spinner timeSpinner3;
	Spinner timeSpinner2;
	Spinner timeSpinner1;
	Spinner daySpinner;
	ImageButton daychoose1;
	ImageButton daychoose2;
	ImageButton daychoose3;
	ImageButton daychoose4;
	ImageButton chooseexistButton;
	ImageButton choosecameraButton;
	ImageView photoImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_medication);
		Button doneButton=(Button)findViewById(R.id.addmeddonebutton);
		ActionBar actionBar=getActionBar();
		//actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		actionBar.setDisplayHomeAsUpEnabled(true); 
		doneButton.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				MedDatebase db = new MedDatebase(AddMedicatioActivity.this);
				db.insertMed("test", 1, "", 1, null, 1, 2, 1, null);
				db.close();
				finish();
				//Intent intent= new Intent();
				//intent.setClass(AddMedicatioActivity.this, MainActivity.class);
				//startActivity(intent);
			}
		});
		//find Button by id and set listener for Type button

		medtypeA =(ImageButton)findViewById(R.id.medtypea);
		medtypeB =(ImageButton)findViewById(R.id.medtypeb);
		medtypeC =(ImageButton)findViewById(R.id.medtypec);
		medtypeA.setOnClickListener(new medtypeOnClickListener());
		medtypeB.setOnClickListener(new medtypeOnClickListener());
		medtypeC.setOnClickListener(new medtypeOnClickListener());
		//find Button by id and set listener for Dose button
		medDoseA=(ImageButton)findViewById(R.id.meddosea);
		medDoseA.setOnClickListener(new medDoseOnClickListener());
		medDoseB=(ImageButton)findViewById(R.id.meddoseb);
		medDoseB.setOnClickListener(new medDoseOnClickListener());
		medDoseC=(ImageButton)findViewById(R.id.meddosec);
		medDoseC.setOnClickListener(new medDoseOnClickListener());
		medDoseD=(ImageButton)findViewById(R.id.meddosed);
		medDoseD.setOnClickListener(new medDoseOnClickListener());
		medDoseE=(ImageButton)findViewById(R.id.meddosee);
		medDoseE.setOnClickListener(new medDoseOnClickListener());
		//find Button by id and set listener for Frequency button
		medFreA=(ImageButton)findViewById(R.id.medfrequencea);
		medFreA.setOnClickListener(new medFrequencyListener());
		medFreB=(ImageButton)findViewById(R.id.medfrequenceb);
		medFreB.setOnClickListener(new medFrequencyListener());
		medFreC=(ImageButton)findViewById(R.id.medfrequencec);
		medFreC.setOnClickListener(new medFrequencyListener());
		medFreD=(ImageButton)findViewById(R.id.medfrequenced);
		medFreD.setOnClickListener(new medFrequencyListener());
		timeSpinner4= (Spinner)findViewById(R.id.whatTimeSpinner4);
		timeSpinner3= (Spinner)findViewById(R.id.whatTimeSpinner3);
		timeSpinner2= (Spinner)findViewById(R.id.whatTimeSpinner2);
		timeSpinner1= (Spinner)findViewById(R.id.whatTimeSpinner1);
		daySpinner=(Spinner)findViewById(R.id.whatDaySpinner);
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
				AddMedicatioActivity.this, R.array.Day,
				android.R.layout.simple_spinner_item);
		timeSpinner4.setAdapter(adapter1);
		timeSpinner4.setOnItemSelectedListener(new SpinnerOnSelectedListener());
		chooseexistButton=(ImageButton)findViewById(R.id.button_chooseexisting);
		chooseexistButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();               
                intent.setType("image/*");  
                intent.setAction(Intent.ACTION_GET_CONTENT);   
                startActivityForResult(intent, 1); 
			}
		});
		photoImageView =(ImageView)findViewById(R.id.image_addmed);
		choosecameraButton=(ImageButton)findViewById(R.id.button_takephoto);
		choosecameraButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			    startActivityForResult(takePictureIntent, 2);
			}
		});
		
		
	}
	
	@Override  
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
     
		if(requestCode==1){
		if (resultCode == RESULT_OK) {  
            Uri uri = data.getData();  
            Log.e("uri", uri.toString());  
            ContentResolver cr = this.getContentResolver();  
            try {  
            	BitmapFactory.Options options = new BitmapFactory.Options();  
    	        options.inJustDecodeBounds = true;  
    	        // 获取这个图片的宽和高，注意此处的bitmap为null  
    	        Bitmap bitmap=BitmapFactory.decodeStream(cr.openInputStream(uri),null,options);
    	        options.inJustDecodeBounds = false; // 设为 false  
    	        // 计算缩放比  
    	        int h = options.outHeight;  
    	        int w = options.outWidth;  
    	        int beWidth = w / 100;  
    	        int beHeight = h / 100;  
    	        int be = 1;  
    	        if (beWidth < beHeight) {  
    	            be = beWidth;  
    	        } else {  
    	            be = beHeight;  
    	        }  
    	        if (be <= 0) {  
    	            be = 1;  
    	        }  
    	        options.inSampleSize = be;
            	
    	        bitmap=BitmapFactory.decodeStream(cr.openInputStream(uri), null, options);
                photoImageView.setImageBitmap(bitmap);  
            } catch (FileNotFoundException e) {  
                Log.e("Exception", e.getMessage(),e);  
            }  
        }  
        super.onActivityResult(requestCode, resultCode, data);  
	}
		if(requestCode==2){
			if (resultCode == RESULT_OK) {  
				/*System.out.println("abc");
	            Uri uri = data.getData();  
	            System.out.println("2");
	            Log.e("Log", uri.toString());  
	            System.out.println("3");
	            ContentResolver cr = this.getContentResolver();  
	            System.out.println("4");*/
				Bundle bundle=data.getExtras();
				if (bundle!=null) {
					                 
					                Bitmap bm=(Bitmap) bundle.get("data");
					                photoImageView.setImageBitmap(bm);
					
					            }

			 
	           /* try {  
	            	BitmapFactory.Options options = new BitmapFactory.Options();  
	    	        options.inJustDecodeBounds = true;  
	    	        // 获取这个图片的宽和高，注意此处的bitmap为null  
	    	        //Bitmap bitmap=BitmapFactory.decodeStream(cr.openInputStream(uri),null,options);
	    	        options.inJustDecodeBounds = false; // 设为 false  
	    	        // 计算缩放比  
	    	        int h = options.outHeight;  
	    	        int w = options.outWidth;  
	    	        int beWidth = w / 100;  
	    	        int beHeight = h / 100;  
	    	        int be = 1;  
	    	        if (beWidth < beHeight) {  
	    	            be = beWidth;  
	    	        } else {  
	    	            be = beHeight;  
	    	        }  
	    	        if (be <= 0) {  
	    	            be = 1;  
	    	        }  
	    	        options.inSampleSize = be;
	    	        System.out.println("5");
	    	        bitmap=BitmapFactory.decodeStream(cr.openInputStream(uri), null, options);
	                photoImageView.setImageBitmap(bitmap);  
	            } catch (FileNotFoundException e) {  
	                Log.e("Exception", e.getMessage(),e);  
	            }  */
	        }  
	        super.onActivityResult(requestCode, resultCode, data); 
	        System.out.println("6");
		}
    }  
	
//Type button listener
	public class medtypeOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0.getId()==R.id.medtypea){
				medtypeA.setImageResource(R.drawable.btn_log);
				medtypeB.setImageResource(R.drawable.ic_launcher);
				medtypeC.setImageResource(R.drawable.ic_launcher);
				medtypeInt=1;
				medtypeA.setOnClickListener(null);
				medtypeB.setOnClickListener(new medtypeOnClickListener());
				medtypeC.setOnClickListener(new medtypeOnClickListener());

			}
			if(arg0.getId()==R.id.medtypeb){
				medtypeA.setImageResource(R.drawable.ic_launcher);
				medtypeB.setImageResource(R.drawable.btn_log);
				medtypeC.setImageResource(R.drawable.ic_launcher);
				medtypeInt=2;
				medtypeB.setOnClickListener(null);
				medtypeA.setOnClickListener(new medtypeOnClickListener());
				medtypeC.setOnClickListener(new medtypeOnClickListener());
			}
			if(arg0.getId()==R.id.medtypec){
				medtypeC.setImageResource(R.drawable.btn_log);
				medtypeB.setImageResource(R.drawable.ic_launcher);
				medtypeA.setImageResource(R.drawable.ic_launcher);
				medtypeInt=3;
				medtypeC.setOnClickListener(null);
				medtypeB.setOnClickListener(new medtypeOnClickListener());
				medtypeA.setOnClickListener(new medtypeOnClickListener());
			}
		}
		
	}
//Dose button listener	
	public class medDoseOnClickListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0.getId()==R.id.meddosea){
				medDoseA.setImageResource(R.drawable.btn_log);
				medDoseB.setImageResource(R.drawable.ic_launcher);
				medDoseC.setImageResource(R.drawable.ic_launcher);
				medDoseD.setImageResource(R.drawable.ic_launcher);
				medDoseE.setImageResource(R.drawable.ic_launcher);
				medtypeInt=1;
				/*medtypeA.setOnClickListener(null);
				medtypeB.setOnClickListener(new medtypeOnClickListener());
				medtypeC.setOnClickListener(new medtypeOnClickListener());*/

			}
			if(arg0.getId()==R.id.meddoseb){
				medDoseB.setImageResource(R.drawable.btn_log);
				medDoseA.setImageResource(R.drawable.ic_launcher);
				medDoseC.setImageResource(R.drawable.ic_launcher);
				medDoseD.setImageResource(R.drawable.ic_launcher);
				medDoseE.setImageResource(R.drawable.ic_launcher);
				medtypeInt=2;
				
			}
			if(arg0.getId()==R.id.meddosec){
				medDoseC.setImageResource(R.drawable.btn_log);
				medDoseA.setImageResource(R.drawable.ic_launcher);
				medDoseB.setImageResource(R.drawable.ic_launcher);
				medDoseD.setImageResource(R.drawable.ic_launcher);
				medDoseE.setImageResource(R.drawable.ic_launcher);
				medtypeInt=3;
				
			}
			if(arg0.getId()==R.id.meddosed){
				medDoseD.setImageResource(R.drawable.btn_log);
				medDoseA.setImageResource(R.drawable.ic_launcher);
				medDoseC.setImageResource(R.drawable.ic_launcher);
				medDoseB.setImageResource(R.drawable.ic_launcher);
				medDoseE.setImageResource(R.drawable.ic_launcher);
				medtypeInt=4;
				
			}
			if(arg0.getId()==R.id.meddosee){
				medDoseE.setImageResource(R.drawable.btn_log);
				medDoseA.setImageResource(R.drawable.ic_launcher);
				medDoseC.setImageResource(R.drawable.ic_launcher);
				medDoseD.setImageResource(R.drawable.ic_launcher);
				medDoseB.setImageResource(R.drawable.ic_launcher);
				medtypeInt=5;
				
			}
			
		}
		
	}
	//Frequency button listener
	public class medFrequencyListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0.getId()==R.id.medfrequencea){
				medFre=1;
				medFreA.setImageResource(R.drawable.btn_log);
				medFreB.setImageResource(R.drawable.ic_launcher);
				medFreC.setImageResource(R.drawable.ic_launcher);
				medFreD.setImageResource(R.drawable.ic_launcher);
			}
			if(arg0.getId()==R.id.medfrequenceb){
				medFre=2;
				medFreB.setImageResource(R.drawable.btn_log);
				medFreA.setImageResource(R.drawable.ic_launcher);
				medFreC.setImageResource(R.drawable.ic_launcher);
				medFreD.setImageResource(R.drawable.ic_launcher);
			}
			if(arg0.getId()==R.id.medfrequencec){
				medFre=3;
				medFreC.setImageResource(R.drawable.btn_log);
				medFreB.setImageResource(R.drawable.ic_launcher);
				medFreA.setImageResource(R.drawable.ic_launcher);
				medFreD.setImageResource(R.drawable.ic_launcher);
			}
			if(arg0.getId()==R.id.medfrequenced){
				medFre=4;
				medFreD.setImageResource(R.drawable.btn_log);
				medFreB.setImageResource(R.drawable.ic_launcher);
				medFreC.setImageResource(R.drawable.ic_launcher);
				medFreA.setImageResource(R.drawable.ic_launcher);
			}
			
			timeSpinner4.setVisibility(View.GONE);
			timeSpinner3.setVisibility(View.GONE);
			timeSpinner2.setVisibility(View.GONE);
			timeSpinner1.setVisibility(View.GONE);


		switch (medFre) {
		case 4:
			timeSpinner4.setVisibility(0);
			ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
					AddMedicatioActivity.this, R.array.Time,
					android.R.layout.simple_spinner_item);
			timeSpinner4.setAdapter(adapter1);
			timeSpinner4.setOnItemSelectedListener(new SpinnerOnSelectedListener());
		case 3:
			timeSpinner3.setVisibility(0);
			ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
					AddMedicatioActivity.this, R.array.Time,
					android.R.layout.simple_spinner_item);
			timeSpinner3.setAdapter(adapter2);
			timeSpinner3.setOnItemSelectedListener(new SpinnerOnSelectedListener());
		case 2:
			timeSpinner2.setVisibility(0);
			ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
					AddMedicatioActivity.this, R.array.Time,
					android.R.layout.simple_spinner_item);
			timeSpinner2.setAdapter(adapter3);
			timeSpinner2.setOnItemSelectedListener(new SpinnerOnSelectedListener());
		case 1:
			timeSpinner1.setVisibility(0);
			ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(
					AddMedicatioActivity.this, R.array.Time,
					android.R.layout.simple_spinner_item);
			timeSpinner1.setAdapter(adapter4);
			timeSpinner1.setOnItemSelectedListener(new SpinnerOnSelectedListener());
			

		default:
			break;
		}
		}
		
	}
	class SpinnerOnSelectedListener implements OnItemSelectedListener{
	    @Override
	    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id){
	        String selected = adapterView.getItemAtPosition(position).toString();}
	 
	    @Override
	    public void onNothingSelected(AdapterView<?> adapterView){
	        System.out.println("nothingSelected");}

		
	}
	
	class DayOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	            Intent intent = new Intent(this, MainActivity.class);
	            
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_medicatio, menu);
		return true;
	}
	
	/**
	 * Hide the soft keyboard when edittext or button is not focused.
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {

	    View v = getCurrentFocus();
	    boolean ret = super.dispatchTouchEvent(event);

	    if (v instanceof EditText) {
	        View w = getCurrentFocus();
	        int scrcoords[] = new int[2];
	        w.getLocationOnScreen(scrcoords);
	        float x = event.getRawX() + w.getLeft() - scrcoords[0];
	        float y = event.getRawY() + w.getTop() - scrcoords[1];

	        Log.d("Activity", "Touch event "+event.getRawX()+","+event.getRawY()+" "+x+","+y+" rect "+w.getLeft()+","+w.getTop()+","+w.getRight()+","+w.getBottom()+" coords "+scrcoords[0]+","+scrcoords[1]);
	        if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom()) ) { 

	            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
	        }
	    }
	return ret;
	}

}



/*
 * final ByteArrayOutputStream os = new ByteArrayOutputStream();  
        // 将Bitmap压缩成PNG编码，质量为100%存储          
        icon.compress(Bitmap.CompressFormat.PNG, 100, os);   
        // 构造SQLite的Content对象，这里也可以使用raw  
        ContentValues values = new ContentValues();   
        // 写入数据库的Browser.BookmarkColumns.TOUCH_ICON字段  
        values.put(Browser.BookmarkColumns.TOUCH_ICON, os.toByteArray());  
        */
