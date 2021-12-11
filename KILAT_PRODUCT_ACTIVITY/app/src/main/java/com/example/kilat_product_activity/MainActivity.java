package com.example.kilat_product_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Button cmdAdds, cmdView, updatebtn, delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updatebtn = findViewById(R.id.updateButtonMain);
        cmdAdds = findViewById(R.id.cmdAdd);
        cmdView = findViewById(R.id.cmdViewAll);
        delete = findViewById(R.id.deleteMain);

        cmdAdds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductActivity();
            }
        });
        cmdView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAllProductActivity();
            }
        });
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProductActivity();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProductActivity();
            }
        });

    }

    private void addProductActivity()
    {
        Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private void viewAllProductActivity()
    {
        Intent intent = new Intent(MainActivity.this, ViewAllProductsActivity.class);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private void updateProductActivity()
    {
        Intent intent = new Intent(MainActivity.this, UpdateProductActivity.class);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void deleteProductActivity()
    {
        Intent intent = new Intent(MainActivity.this, DeleteProductActivity.class);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}


