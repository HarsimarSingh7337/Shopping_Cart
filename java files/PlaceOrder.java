package com.example.shopping_cart;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PlaceOrder extends Activity {

	SQLiteDatabase db;
	Cursor c;
	EditText et1,et2,et3,discount,tBill;
	Spinner spProduct,spQty;
	String oid="O0000",itm,qty,tbill,prc,amnt,dis;
	long total;
	int disc;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_order);
		
		et1=(EditText)findViewById(R.id.editOrderId);
		et2=(EditText)findViewById(R.id.editPrice);
		et3=(EditText)findViewById(R.id.editAmount);
		tBill=(EditText)findViewById(R.id.editTBill);
		discount=(EditText)findViewById(R.id.editDiscount);
		spProduct=(Spinner)findViewById(R.id.spinnerProduct);
		spQty=(Spinner)findViewById(R.id.spinnerQty);
		
	    db=openOrCreateDatabase("Shopping", Context.MODE_PRIVATE,null);
	    db.execSQL("Create table If not exists Customerproduct(OrderId varchar(10),PNAME varchar(30),PRICE varchar(20), QTY varchar(20), AMOUNT varchar(30), DISCOUNT varchar(20), TOTAL varchar(30))");
	    db.execSQL("Create table If not exists Product(PNAME varchar(30), PRICE Int(10))");
	   	
	    Check();
	   	SpinnerProductName();
	   	SpinnerQty();
	   	
	
	 }
	
	public void Check(){
		   
		   c=db.rawQuery("Select * from Customerproduct",null);
		    
		   if(c.getCount()==0){
		    	 db.execSQL("Insert into Customerproduct(OrderId,PNAME,PRICE,QTY,AMOUNT,DISCOUNT,TOTAL) values('"+oid+"','"+null+"','"+null+"','"+null+"','"+null+"','"+null+"','"+null+"')");
		    et1.setText("O"+"0"+"0"+"0"+0);
	         Toast.makeText(getApplicationContext(), "Initialised", Toast.LENGTH_SHORT).show();
		    }
		    
		    else{
		    	
		    	c=db.rawQuery("Select max(OrderId) from Customerproduct",null);
			  	
				if(c.moveToFirst()){
			      	     	
				   String ab=c.getString(0);
			       String oid1=ab.substring(1,5);
			       int oid2=Integer.parseInt(oid1);
			       oid2=oid2+1;
			       
			       
			      if (oid2<10){
			    	
			    	   et1.setText("O"+"0"+"0"+"0"+oid2);
				       //db.execSQL("Insert into Customerproduct(OrderId) values('"+et1.getText()+"');");
			    	  
			       }
			         
			      else if(oid2>=10 && oid2<100){
			    	   
			    	   et1.setText("O"+"0"+"0"+oid2);
			    	   //db.execSQL("Insert into Customerproduct values('"+et1.getText()+"');");
			    	  
			       }
			       
			      else if(oid2>=100 && oid2<1000){
			    	   et1.setText("O"+"0"+oid2);
			    	  // db.execSQL("Insert into Customerproduct values('"+et1.getText()+"');");
			    	  			       }
			       
			      else if(oid2>=1000 && oid2<10000){
			    	   et1.setText("O"+oid2);
			    	   //db.execSQL("Insert into Customerproduct values('"+et1.getText()+"');");
			    	  
			       }
			   	}	
		      }		
		    }
		
	public void GoBack(View v){
		
		Intent i1=new Intent(getApplicationContext(),MainActivity.class);
		startActivity(i1);
	}
	
	public void Proceed(View v){
	
		if(et1.getText().toString().trim().length()==0 && itm.equals("") && prc.equals("") && qty.equals("") && amnt.equals("") && dis.equals("") && tbill.equals("")){
			
			Toast.makeText(getApplicationContext(), "Please provide all information", Toast.LENGTH_SHORT).show();
		}
		else{
			String query="Insert into Customerproduct(OrderId,PNAME,PRICE,QTY,AMOUNT,DISCOUNT,TOTAL) values('"+et1.getText().toString()+"','"+itm+"','"+prc+"','"+qty+"','"+amnt+"','"+dis+"','"+tbill+"')";
			db.execSQL(query);
			
			Toast.makeText(getApplicationContext()," Data Entered Successfully ", Toast.LENGTH_SHORT).show();
		}
			
	}
	
	public void SpinnerProductName(){
	
		ArrayList<String> list=new ArrayList<String>();
    list.add("Select an Item");
	list.add("Laptop");
	list.add("LED");
	list.add("LCD");
	list.add("GSM Phone");
	list.add("Smart Phone");
	list.add("Head Phone");
	list.add("Refrigerator");
	list.add("Washing Machine");
	list.add("Iron");
	list.add("Cooler");
	list.add("TV");
	
	ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_item,list);
	spProduct.setAdapter(adapter);
	spProduct.setOnItemSelectedListener(new OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int pos,
				long id) {
			
		   itm=(String)parent.getSelectedItem();
		 
		   c=db.rawQuery("Select PRICE from Product where PNAME='"+itm+"'",null);
		   c.moveToFirst();
		   et2.setText(c.getString(c.getColumnIndex("PRICE")));
		   prc=et2.getText().toString();
		   et3.setText("");
		   tBill.setText("");
		  
		   
		   
		   
		   
		}
		
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
	    	}
       });
    }

	public void SpinnerQty(){
		
		ArrayList<String> list=new ArrayList<String>();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10");
		list.add("25");
		list.add("50");
	
       ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_item,list);
       spQty.setAdapter(adapter);
       spQty.setOnItemSelectedListener(new OnItemSelectedListener(){

   		@Override
   		public void onItemSelected(AdapterView<?> parent, View v, int pos,
   				long id) {
   			
   		   qty=(String)parent.getSelectedItem();
   		   
   		   int qty1=Integer.parseInt(qty.toString());
   	   	   int item=Integer.parseInt(et2.getText().toString());
   		   total=qty1*item;
   		   et3.setText(String.valueOf(total));
   		   amnt=et3.getText().toString();
   		   tBill.setText("");
   		  		   
   		}

   		@Override
   		public void onNothingSelected(AdapterView<?> arg0) {
   			// TODO Auto-generated method stub
   			
   	    	}
          });
       }

       
	public void GenerateBill(View v){
		 
		if(discount.getText().toString().trim().length()==0){
		
			Toast.makeText(getApplicationContext(), "Please provide discount", Toast.LENGTH_SHORT).show();
		}
		else{
			disc=Integer.parseInt(discount.getText().toString());
		    long grand=disc*total/100;
			long totalamount=total-grand;
			
			tBill.setText(String.valueOf(totalamount));
			
			dis=String.valueOf(disc);
			tbill=tBill.getText().toString();	
		}
	    
	}
	
	
	}

	
	


 
	    	
	    
	      	 
	    
	    
		
	
	              
	
	

