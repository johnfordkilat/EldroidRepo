package com.example.kilat_product_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddProductActivity extends AppCompatActivity {

    EditText name,price,quantity,id;
    Button add;
    Connection connect;
    String connectionResult="";
    String[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        refs();

    }

    public void AddProduct(View v)
    {
        if (id.getText().toString().isEmpty())
        {
            Toast.makeText(AddProductActivity.this, "Please enter ID", Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                int dbId = Integer.parseInt(id.getText().toString());
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connect = connectionHelper.connectionClass();
                if (connect != null)
                {

                    String query1 = "SELECT * FROM ProductItems";
                    Statement smt = connect.createStatement();
                    ResultSet rs = smt.executeQuery(query1);
                    int count = 0;
                    while(rs.next())
                    {
                        count++;
                    }
                    data = new String[count];
                    rs.close();
                    smt = connect.createStatement();
                    rs = smt.executeQuery(query1);
                    int counter = 0;
                    while(rs.next())
                    {
                        data[counter] = rs.getString(1);
                        counter++;
                    }

                    int[] newData = new int[count];
                    for(int i=0; i<count; i++) {
                        newData[i] = Integer.parseInt(data[i]);
                    }
                    boolean val = contains(newData, dbId);
                    if(val == true)
                    {
                        Toast.makeText(getApplicationContext(), "The Product ID is already in Database", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        String sql = "INSERT INTO ProductItems (ProductID,ProductName,ProductPrice,ProductQuantity) VALUES ('"+dbId+"','"+name.getText()+"','"+price.getText()+"','"+quantity.getText()+"')";
                        smt = connect.createStatement();
                        smt.executeUpdate(sql);
                        Toast.makeText(getApplicationContext(), "Product ID: " + dbId + " Added Successfully", Toast.LENGTH_LONG).show();
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

    public static boolean contains(final int[] arr, final int key) {
        return ArrayUtils.contains(arr, key);
    }

    public void refs()
    {
        id = (EditText)findViewById(R.id.txtProductID);
        name = (EditText)findViewById(R.id.txtProductName);
        price = (EditText)findViewById(R.id.txtPrice);
        quantity = (EditText)findViewById(R.id.txtQuantity);
        add = (Button)findViewById(R.id.cmdAddProduct);
    }

}