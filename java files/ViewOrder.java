package com.example.shopping_cart;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewOrder extends Activity {

	SQLiteDatabase db;
	Cursor c;
	EditText vieworder;
	String vorder;
	TextView t1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_order);
	
	    vieworder=(EditText)findViewById(R.id.editorderid);
	    t1=(TextView)findViewById(R.id.orderviews);
	    db=openOrCreateDatabase("Shopping", Context.MODE_PRIVATE,null);
	    db.execSQL("Create table If not exists Customerproduct(OrderId varchar(10),PNAME varchar(30),PRICE varchar(20), QTY varchar(20), AMOUNT varchar(30), DISCOUNT varchar(20), TOTAL varchar(30))");
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_order, menu);
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
	
	public void ViewOrderbtn(View v){
		
		vorder=vieworder.getText().toString();
		if(vorder.trim().length()==0){
			
			Toast.makeText(getApplicationContext(), "Please provide OrderId", Toast.LENGTH_SHORT).show();
		}
		else{
		
			c=db.rawQuery("select * from customerproduct where OrderId='"+vorder+"'",null);
			if(c.getCount()==0){
				
				Toast.makeText(getApplicationContext(),"no record found", Toast.LENGTH_SHORT).show();
		}
		
		else{
			 c.moveToFirst();
	        StringBuffer sb=new StringBuffer();
	       
			sb.append("OrderId: "+c.getString(0)+ "\n");
			sb.append("Product Name: "+c.getString(1)+ "\n");
			sb.append("Price: "+c.getString(2)+"\n");
			sb.append("Quantity: "+c.getString(3)+"\n" );
			sb.append("Discount: "+c.getString(5)+"\n" );
			sb.append("Total Bill: "+c.getString(6)+"\n");
			
			showMessage("Order Detail",sb.toString());
		//	t1.setText("OrderId: "+c.getString(0)+"\n" + " " +"Product Name: "+c.getString(1)+ "\n" + " " +"Price: "+c.getString(2)+"\n" + " " + "Quantity: "+c.getString(3)+"\n" + " " + "Amount: "+c.getString(4)+"\n" + " " + "Discount: "+c.getString(5)+"\n" +" " + "Total Bill: "+c.getString(6)+"\n");
		
			
		
		}
	}
	}

	public void showMessage(String title,String message)
	{
		
            Builder builder=new Builder(this);
		    builder.setCancelable(true);
		    builder.setTitle(title);
		    builder.setMessage(message);
		    builder.show();

}
}