package com.example.shopping_cart;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CancelOrder extends Activity {

	SQLiteDatabase db;
	Cursor c;
	EditText cancelorder;
	String cancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cancel_order);
     
		cancelorder=(EditText)findViewById(R.id.cancelorderid);
		
		db=openOrCreateDatabase("Shopping", Context.MODE_PRIVATE,null);
		db.execSQL("Create table If not exists Customerproduct(OrderId varchar(10),PNAME varchar(30),PRICE varchar(20), QTY varchar(20), AMOUNT varchar(30), DISCOUNT varchar(20), TOTAL varchar(30))");
 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cancel_order, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void CancelOrderbtn(View v){
		
		cancel=cancelorder.getText().toString();
		if(cancel.trim().length()==0){
			Toast.makeText(getApplicationContext()," Please provide OrderId ", Toast.LENGTH_LONG).show();
		}
		else{
			  c=db.rawQuery("select * from customerproduct where OrderId='"+cancel+"'",null);
			  if(c.moveToFirst()){
				  db.execSQL("delete from customerproduct where OrderId='"+cancel+"'");
				  Toast.makeText(getApplicationContext(),"Order deleted Sucessfully", Toast.LENGTH_LONG).show();
			  }
			  else{
				  Toast.makeText(getApplicationContext(),"Please provide valid OrderId", Toast.LENGTH_LONG).show();
				  cancelorder.setText("");  
			  }
			  
		}
		
	}

}
