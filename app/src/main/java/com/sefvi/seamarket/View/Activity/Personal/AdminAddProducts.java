package com.sefvi.seamarket.View.Activity.Personal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sefvi.seamarket.R;
import com.sefvi.seamarket.Utils.Checks;

public class AdminAddProducts extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
Spinner spinner;
EditText nameProduct, priceProduct, moTaProduct, originProduct, soLuongProduct;
LinearLayout btnAddFolder;
Button addProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_products);
        anhxa();
        ImageView backicon = findViewById(R.id.toolbar_back);
        TextView name = findViewById(R.id.toolbar_name);
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name.setText("Thêm Sản Phẩm");
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.idType, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lavigate(nameProduct.getText().toString(), priceProduct.getText().toString(), moTaProduct.getText().toString(), originProduct.getText().toString(),soLuongProduct.getText().toString());
            }
        });
    }

    private void anhxa() {
        spinner = findViewById(R.id.admin_choose_idtype);
        nameProduct = findViewById(R.id.admin_add_nameProduct);
        priceProduct = findViewById(R.id.admin_add_priceProduct);
        moTaProduct = findViewById(R.id.admin_add_moTaProduct);
        originProduct = findViewById(R.id.admin_add_originProduct);
        soLuongProduct = findViewById(R.id.admin_add_soLuongProduct);
        btnAddFolder = findViewById(R.id.admin_add_folder);
        addProduct = findViewById(R.id.admin_button_addProduct);
    }
private void lavigate(String nameProduct, String priceProduct, String moTa, String origin, String soLuong){
    if(nameProduct.isEmpty()) {
        Toast.makeText(this, "Không được để trống Tên sản phẩm!", Toast.LENGTH_SHORT).show();
    }else if(priceProduct.isEmpty()){
        Toast.makeText(this, "Không được để trống Giá sản phẩm!", Toast.LENGTH_SHORT).show();
    }else if(moTa.isEmpty()){
        Toast.makeText(this, "Không được để trống Mô tả sản phẩm!", Toast.LENGTH_SHORT).show();
    }else if(origin.isEmpty()){
        Toast.makeText(this, "Không được để trống Nơi xuất xứ!", Toast.LENGTH_SHORT).show();
    }else if(soLuong.isEmpty()){
        Toast.makeText(this, "Không được để trống Số lượng!", Toast.LENGTH_SHORT).show();
    }else{
        Toast.makeText(this, "oke", Toast.LENGTH_SHORT).show();
    }
}
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}