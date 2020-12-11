package com.sefvi.seamarket.View.Activity.Personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.sefvi.seamarket.Adapter.PhotoAdapter;
import com.sefvi.seamarket.Api.AddProduct.AddProductApiLml;
import com.sefvi.seamarket.Interface.ProductRandom;
import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.Utils.Checks;

import okhttp3.MediaType;
import okhttp3.MultipartBody;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AdminAddProducts extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
Spinner spinner;
EditText nameProduct, priceProduct, moTaProduct, originProduct, soLuongProduct;
RecyclerView adminAddRcvPhoto;
RelativeLayout btnAddFolder;
Button addProduct;
private PhotoAdapter photoAdapter;
String token;
Integer idType;
ArrayList<Uri> listImage;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_products);
        anhxa();
        event();
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


        photoAdapter = new PhotoAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1, LinearLayoutManager.HORIZONTAL,false);
        adminAddRcvPhoto.setLayoutManager(gridLayoutManager);
        adminAddRcvPhoto.setFocusable(false);
        adminAddRcvPhoto.setAdapter(photoAdapter);

    }

    private void anhxa() {
        spinner = findViewById(R.id.admin_choose_idtype);
        nameProduct = findViewById(R.id.admin_add_nameProduct);
        priceProduct = findViewById(R.id.admin_add_priceProduct);
        moTaProduct = findViewById(R.id.admin_add_moTaProduct);
        originProduct = findViewById(R.id.admin_add_originProduct);
        soLuongProduct = findViewById(R.id.admin_add_soLuongProduct);
        btnAddFolder = findViewById(R.id.admin_add_folder);
        adminAddRcvPhoto = findViewById(R.id.admin_add_rcv_photo);
        addProduct = findViewById(R.id.admin_button_addProduct);

        listImage = new ArrayList<>();

        SharedPreferences prefs = getSharedPreferences("Sea",MODE_PRIVATE);
        token = prefs.getString("TOKEN", "");

    }
    private void event(){
        btnAddFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions();
            }
        });
    }
    private void requestPermissions(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                oppenBottomPicket();
                Toast.makeText(AdminAddProducts.this, "xin quyen", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(AdminAddProducts.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }
    private void oppenBottomPicket(){
        TedBottomPicker.OnMultiImageSelectedListener listener = new TedBottomPicker.OnMultiImageSelectedListener() {
            @Override
            public void onImagesSelected(ArrayList<Uri> uriList) {
                photoAdapter.setData(uriList);
                for (int i = 0; i < uriList.size(); i++){
                    listImage.add(uriList.get(i));
                }

            }
        };
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(AdminAddProducts.this).setOnMultiImageSelectedListener(listener)
              .setCompleteButtonText("Xong")
                .setEmptySelectionText("Chua Co")
                .create();
        tedBottomPicker.show(getSupportFragmentManager());
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
        showProgress();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (int i = 0; i < listImage.size(); i++) {
            Uri uri = listImage.get(i);
            File file = new File(getRealPathFromURI(uri));
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
            builder.addFormDataPart("images[]", file.getName(), requestBody);
        }
        builder.addFormDataPart("idType", String.valueOf(idType));
        builder.addFormDataPart("name", nameProduct);
        builder.addFormDataPart("price", priceProduct);
        builder.addFormDataPart("description", moTa);
        builder.addFormDataPart("unit", "VND");
        builder.addFormDataPart("origin", origin);
        builder.addFormDataPart("warehouseNumber", soLuong);

        MultipartBody body = builder.build();
        AddProductApiLml addProductApiLml = new AddProductApiLml();
        addProductApiLml.AddProductApi(token,body, new ProductRandom() {
            @Override
            public void getDataSuccess(ProductModel productModel) {

            }

            @Override
            public void getDataError(String err) {
                Toast.makeText(getApplicationContext(), err, Toast.LENGTH_SHORT).show();
                dissmissDialog();
            }

            @Override
            public void getDataSuccess(JSONArray list) {

            }
        });

    }

}
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if ("Cá".equals(text)) {
            idType = 4;
        }else if ("Cua".equals(text)){
            idType = 5;
        }else if ("Tôm".equals(text)){
            idType = 6;
        }else  if ("Sò".equals(text)){
            idType = 7;
        }else if("Ốc".equals(text)){
            idType = 8;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void dissmissDialog() {
        mProgressDialog.dismiss();
    }

    private void showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Đang thêm sản phẩm...");
        }
        mProgressDialog.show();
    }
}