package com.example.shopping_cart;



import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

   
    SQLiteDatabase db;
 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          
         db=openOrCreateDatabase("Shopping", Context.MODE_PRIVATE,null);
         db.execSQL("Create table If not exists Product(PNAME varchar(30), PRICE Int(10))");
         db.execSQL("Create table If not exists Customerproduct(OrderId varchar(10),PNAME varchar(30),PRICE varchar(20), QTY varchar(20), AMOUNT varchar(30), DISCOUNT varchar(20), TOTAL varchar(30))");
      
         /*   
        String query0="Insert into product(pname,price) values ('"+"Select an Item"+"','"+0+"');";
        String query="Insert into product(pname,price) values ('"+"Laptop"+"','"+25000+"');";
        String query1="Insert into product(pname,price) values ('"+"LCD"+"','"+8000+"');";
        String query2="Insert into product(pname,price) values ('"+"GSM Phone"+"','"+5000+"');";
        String query3="Insert into product(pname,price) values ('"+"Smart Phone"+"','"+9500+"');";
        String query4="Insert into product(pname,price) values ('"+"Head Phone"+"','"+1500+"');";
        String query5="Insert into product(pname,price) values ('"+"Refrigerator"+"','"+40000+"');";
        String query6="Insert into product(pname,price) values ('"+"Washing Machine"+"','"+45000+"');";
        String query7="Insert into product(pname,price) values ('"+"Iron"+"','"+4000+"');";
        String query8="Insert into product(pname,price) values ('"+"Cooler"+"','"+15000+"');";
        String query9="Insert into product(pname,price) values ('"+"TV"+"','"+12000+"');";
        String query10="Insert into product(pname,price) values ('"+"LED"+"','"+10000+"');";
             
        db.execSQL(query0);
        db.execSQL(query);
	    db.execSQL(query1);
	    db.execSQL(query2);
	    db.execSQL(query3);
	    db.execSQL(query4);
	    db.execSQL(query5);
	    db.execSQL(query6);
	    db.execSQL(query7);
	    db.execSQL(query8);
	    db.execSQL(query9);
	    db.execSQL(query10);
	    */
         
	   // Toast.makeText(getApplicationContext(), "Data Entered", Toast.LENGTH_SHORT).show();
       
    
    }
    
    public void PlaceOrder(View v){
       
    	Intent i1=new Intent(getApplicationContext(),PlaceOrder.class);
    	startActivity(i1);
    }
    
    public void CancelOrder( View v){
    	
    	Intent i2=new Intent(getApplicationContext(),CancelOrder.class);
    	startActivity(i2);
    }
    
    public void ViewOrder(View v){
    	
       Intent i3=new Intent(getApplicationContext(),ViewOrder.class);
       startActivity(i3);
    }
    
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.cleartable) {
			
			db.execSQL("delete from Customerproduct");
	    	Toast.makeText(getApplicationContext()," Customerproduct Table Cleared", Toast.LENGTH_SHORT).show();
	    	
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


    }
