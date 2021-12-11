package com.example.kilat_product_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductDetailsActivity extends AppCompatActivity {

    Connection connect;
    Statement smt;
    String connectionResult="";
    TextView dtID,dtName,dtPrice,dtQuantity,id,name,price,quantity;
    private int otherPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        dtID = findViewById(R.id.ProductIDtxtView);
        dtName = findViewById(R.id.ProductNametxtView);
        dtPrice = findViewById(R.id.ProductPricetxtView);
        dtQuantity = findViewById(R.id.ProductQuantitytxtView);
        id = findViewById(R.id.editTextTextPersonName9);
        name = findViewById(R.id.editTextTextPersonName3);
        price = findViewById(R.id.editTextTextPersonName4);
        quantity = findViewById(R.id.editTextTextPersonName);

        dtID.setEnabled(false);
        dtName.setEnabled(false);
        dtPrice.setEnabled(false);
        dtQuantity.setEnabled(false);
        id.setEnabled(false);
        name.setEnabled(false);
        price.setEnabled(false);
        quantity.setEnabled(false);

        Intent intent = getIntent();
        otherPos = intent.getIntExtra("message_key",0);
        otherPos++;

        try
            {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connect = connectionHelper.connectionClass();
                if (connect != null)
                {
                    String query = "SELECT * FROM ProductItems WHERE ProductID = '" + otherPos + "'";
                    smt = connect.createStatement();
                    ResultSet rs = smt.executeQuery(query);

                    while(rs.next())
                    {
                        dtID.setText(rs.getString(1));
                        dtName.setText(rs.getString(2));
                        dtPrice.setText(rs.getString(3));
                        dtQuantity.setText(rs.getString(4));
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
