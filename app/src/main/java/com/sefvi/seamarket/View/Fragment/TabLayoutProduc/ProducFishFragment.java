package com.sefvi.seamarket.View.Fragment.TabLayoutProduc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Adapter.ProducAdapter;
import com.sefvi.seamarket.Api.GetProducts.GetProductsApiLml;
import com.sefvi.seamarket.Interface.ProductRandom;
import com.sefvi.seamarket.Model.ProducModel;
import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ProducFishFragment extends Fragment {
    RecyclerView producfishrcv;
    List<ProductModel> producFishModelList;

    String token;

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProducFishFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProducFishFragment newInstance(String param1, String param2) {
        ProducFishFragment fragment = new ProducFishFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_product_fish, container, false);
        anhxa(v);
        setProducfishrcv();

        return v;
    }
    private void anhxa(View v){
        producfishrcv = v.findViewById(R.id.product_fish_rcv);
        SharedPreferences prefs = getContext().getSharedPreferences("Sea",MODE_PRIVATE);
        token = prefs.getString("TOKEN", "");
    }
    private void setProducfishrcv (){
        producFishModelList = new ArrayList<>();
        GetProductsApiLml getProductsApiLml = new GetProductsApiLml();
        getProductsApiLml.GetProducts(token, 4, new ProductRandom() {
            @Override
            public void getDataSuccess(ProductModel productModel) {

            }

            @Override
            public void getDataError(String err) {

            }

            @Override
            public void getDataSuccess(JSONArray list) {
                for (int i = 0; i <= list.length(); i++){
                    try {
                        JSONObject jsonObject = new JSONObject(list.get(i).toString());
                        Log.d("ahihihi-", jsonObject.getString("name"));
                        ProductModel productModel = new ProductModel();
                        productModel.setId(jsonObject.getInt("id"));
                        productModel.setIdType(jsonObject.getInt("idType"));
                        productModel.setName(jsonObject.getString("name"));
                        productModel.setDescription(jsonObject.getString("description"));
                        productModel.setPrice(jsonObject.getInt("price"));
                        productModel.setUnit(jsonObject.getString("unit"));
                        productModel.setImage(jsonObject.getString("image"));
                        producFishModelList.add(productModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                producfishrcv.setLayoutManager(manager);

                ProducAdapter adapter = new ProducAdapter(getActivity(),producFishModelList);
                producfishrcv.setAdapter(adapter);
            }
        });


    }
}