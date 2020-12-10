package com.sefvi.seamarket.View.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Adapter.CartListAdapter;
import com.sefvi.seamarket.Adapter.OrderAdapter;
import com.sefvi.seamarket.Api.GetOrder.GetOrderApiLml;
import com.sefvi.seamarket.Interface.CartInterface;
import com.sefvi.seamarket.Model.CartModel;
import com.sefvi.seamarket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class OderFragment extends Fragment {
    RecyclerView order_recyclerview;
    String token;
    List<CartModel> cartModelList;
    OrderAdapter orderAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order,container,false);
        initEvents(v);
        initControls();
        getData();
        return v;
    }
    private void initEvents(View v){
        order_recyclerview = v.findViewById(R.id.order_recyclerview);
        SharedPreferences prefs = getActivity().getSharedPreferences("Sea",MODE_PRIVATE);
        token = prefs.getString("TOKEN", "");
    }
    private void initControls(){

    }
    private void getData(){
        cartModelList = new ArrayList<>();
        GetOrderApiLml getOrderApiLml = new GetOrderApiLml();
        getOrderApiLml.GetOrderApi(token, new CartInterface() {
            @Override
            public void getDataSuccess(String mess) {

            }

            @Override
            public void getDataError(String err) {

            }

            @Override
            public void getDataSuccess(JSONArray list) {
                try {
                    for (int i = 0; i <= list.length(); i++){
                        JSONObject jsonObject = new JSONObject(list.get(i).toString());
                        CartModel cartModel = new CartModel();
                        cartModel.setId(jsonObject.getInt("id"));
                        Integer sumPrice = jsonObject.getInt("sumPrice");
                        cartModel.setName(jsonObject.getString("name"));
                        cartModel.setPrice(jsonObject.getInt("price"));
                        cartModel.setImage(jsonObject.getJSONObject("image").getString("image"));
                        cartModel.setSumPrice(sumPrice);
                        cartModel.setQuantily(jsonObject.getInt("quantily"));
                        cartModel.setStatus(jsonObject.getInt("status"));
                        cartModelList.add(cartModel);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                orderAdapter = new OrderAdapter(getContext(), cartModelList);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1,GridLayoutManager.VERTICAL,false);
                order_recyclerview.setLayoutManager(gridLayoutManager);
                order_recyclerview.setAdapter(orderAdapter);
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void getDataSuccess(JSONObject jsonObject) {

            }
        });
    }
}
