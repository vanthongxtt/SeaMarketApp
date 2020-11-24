package com.sefvi.seamarket.View.Fragment.TabLayoutProduc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Adapter.ProducAdapter;
import com.sefvi.seamarket.Model.ProducModel;
import com.sefvi.seamarket.R;

import java.util.ArrayList;
import java.util.List;

public class
ProductCrabFragment extends Fragment {
    RecyclerView produccrabrcv;
    List<ProducModel> producModelList;
    List<String> name;
    List<Integer> price;
    List<Integer> img;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductCrabFragment() {
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
    public static ProductCrabFragment newInstance(String param1, String param2) {
        ProductCrabFragment fragment = new ProductCrabFragment();
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
        View v = inflater.inflate(R.layout.fragment_product_crab, container, false);
        anhxa(v);
        setProducfishrcv();

        return v;
    }
    private void anhxa(View v){
        produccrabrcv = v.findViewById(R.id.product_crab_rcv);
    }
    private void setProducfishrcv (){
        producModelList = new ArrayList<>();

        producModelList.add(new ProducModel("cua",53423,R.drawable.cua1));
        producModelList.add(new ProducModel("cua",122132,R.drawable.cua2));
        producModelList.add(new ProducModel("cua",1222132,R.drawable.cua3));
        producModelList.add(new ProducModel("cua",1222132,R.drawable.cua4));
        producModelList.add(new ProducModel("cua",1222132,R.drawable.cua5));
        producModelList.add(new ProducModel("cua",53423,R.drawable.cua1));
        producModelList.add(new ProducModel("cua",122132,R.drawable.cua2));
        producModelList.add(new ProducModel("cua",1222132,R.drawable.cua3));
        producModelList.add(new ProducModel("cua",1222132,R.drawable.cua4));
        producModelList.add(new ProducModel("cua",1222132,R.drawable.cua5));
        producModelList.add(new ProducModel("cua",53423,R.drawable.cua1));
        producModelList.add(new ProducModel("cua",122132,R.drawable.cua2));
        producModelList.add(new ProducModel("cua",1222132,R.drawable.cua3));
        producModelList.add(new ProducModel("cua",1222132,R.drawable.cua4));
        producModelList.add(new ProducModel("cua",1222132,R.drawable.cua5));


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        produccrabrcv.setLayoutManager(manager);

        ProducAdapter adapter = new ProducAdapter(getActivity(),producModelList);
        produccrabrcv.setAdapter(adapter);

    }
}