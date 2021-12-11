package com.example.kilat_product_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class DeleteProductActivity extends AppCompatActivity {

    Connection connect;
    String connectionResult="";
    String[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

    }

    public void DeleteProduct(View v)
    {
        EditText search1 = (EditText) findViewById(R.id.search1Edittxt);
        if (search1.getText().toString().isEmpty())
        {
            Toast.makeText(DeleteProductActivity.this, "Please enter ID", Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                int id = Integer.parseInt(search1.getText().toString());
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
                    //Toast.makeText(getApplicationContext(), "Product: " + Arrays.toString(data), Toast.LENGTH_SHORT).show();

                    int[] newData = new int[count];
                    for(int i=0; i<count; i++) {
                        newData[i] = Integer.parseInt(data[i]);
                    }
                    boolean val = contains(newData, id);
                    if(val == true)
                    {
                        String query = "DELETE FROM ProductItems WHERE ProductID = '" + id + "'";
                        smt.executeUpdate(query);
                        Toast.makeText(getApplicationContext(), "Product ID: " + id + " Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "The Product ID is not in Database", Toast.LENGTH_SHORT).show();
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
}