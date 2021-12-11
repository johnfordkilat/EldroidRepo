package com.example.kilat_product_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateProductActivity extends AppCompatActivity {

    Connection connect;
    Statement smt;
    EditText name,price,quantity,search;
    String connectionResult="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        Button update = (Button) findViewById(R.id.updateButton);
        name = (EditText) findViewById(R.id.nameEditTxt);
        price = (EditText) findViewById(R.id.priceEditTxt);
        quantity = (EditText) findViewById(R.id.quantityEditTxt);
        search = (EditText) findViewById(R.id.searchTxt);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateProductActivity.UpdateProduct().execute("");
            }
        });
    }
    public void SearchProduct(View v)
    {
        if (search.getText().toString().isEmpty())
        {
            Toast.makeText(UpdateProductActivity.this, "Please enter ID", Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connect = connectionHelper.connectionClass();
                if (connect != null)
                {
                    int id = Integer.parseInt(search.getText().toString());
                    String query = "SELECT * FROM ProductItems WHERE ProductID = '" + id + "'";
                    smt = connect.createStatement();
                    ResultSet rs = smt.executeQuery(query);

                    if (rs.next())
                    {
                        name.setText(rs.getString(2));
                        price.setText(rs.getString(3));
                        quantity.setText(rs.getString(4));
                    }
                    else
                    {
                        Toast.makeText(UpdateProductActivity.this, "Product ID Does Not Exist", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    connectionResult = "Check Connection";
                }
            }
            catch (Exception e)
            {
                Log.e("Error = ", e.getMessage());
            }
        }

    }

    public class UpdateProduct extends AsyncTask<String, String , String> {

        String z = "";
        Boolean isSuccess = false;


        @Override
        protected void onPostExecute(String s) {
            if(isSuccess == true)
            {
                Toast.makeText(UpdateProductActivity.this, "Product Updated Succesfully", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            int id = Integer.parseInt(search.getText().toString());
            float dbPrice = Float.parseFloat(price.getText().toString());
            int dbQuantity = Integer.parseInt(quantity.getText().toString());
            try
            {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connect = connectionHelper.connectionClass();
                if(connect == null)
                {
                    z = "Check Your Internet Connection";
                }
                else
                {
                    String sql = "UPDATE ProductItems Set ProductName = '"+name.getText()+"', ProductPrice = '"+dbPrice+"', ProductQuantity = '"+dbQuantity+"' WHERE ProductID = '"+id+"'";
                    smt = connect.createStatement();
                    smt.executeUpdate(sql);
                    isSuccess = true;
                }

            }
            catch (Exception e)
            {
                isSuccess = false;
                z = e.getMessage();
            }

            return z;
        }
    }

}