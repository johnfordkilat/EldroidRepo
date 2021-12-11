package com.example.kilat_product_activity;

import static android.widget.Toast.LENGTH_LONG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ViewAllProductsActivity extends AppCompatActivity {

    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Connection connect;
    String ConnectionResult="";
    ListView viewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_products);

        viewAll = findViewById(R.id.productList);


        try
        {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionClass();
            if (connect != null)
            {
                String query = "SELECT * FROM ProductItems";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                int i = 0;
                while (rs.next())
                {
                        listItems.add(i,rs.getString(2));
                        i++;
                }
            }
            else
            {
                ConnectionResult = "Check Connection";
            }
        }
        catch (Exception e)
        {
            Log.e("Error = ", e.getMessage());
        }
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        viewAll.setAdapter(adapter);

        viewAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position;
                    Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
                    intent.putExtra("message_key", pos);
                    startActivity(intent);
            }

        });


    }

}