package biz.bikehub.rentbike.viewcontrollers.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import biz.bikehub.rentbike.R;
import biz.bikehub.rentbike.models.Order;
import biz.bikehub.rentbike.network.Services;
import biz.bikehub.rentbike.viewcontrollers.adapters.PendingsAdapter;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingsFragment extends Fragment {
    private RecyclerView ordersRecyclerView;
    private PendingsAdapter pendingsAdapter;
    private RecyclerView.LayoutManager ordersLayoutManager;
    private List<Order> orders;
    private static String TAG = "RentBike";


    public PendingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pendings, container, false);



        ordersRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_pendings);
        orders = new ArrayList<>();
        pendingsAdapter = new PendingsAdapter(orders);
        ordersLayoutManager = new GridLayoutManager(view.getContext(),
                getSpanCount(getResources().getConfiguration()));
        ordersRecyclerView.setAdapter(pendingsAdapter);
        ordersRecyclerView.setLayoutManager(ordersLayoutManager);

        updateData();
        return view;
    }

    private void updateData() {

        SharedPreferences preferences = this.getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = preferences.getString("userid", null);

        AndroidNetworking.post(Services.allOrdersByStation())
                .addBodyParameter("userid", userid)
                .addBodyParameter("status", "0")
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try{
                            orders = Order.from(response);
                        }catch (Exception e){

                        }

                        pendingsAdapter.setOrders(orders);
                        pendingsAdapter.notifyDataSetChanged();

                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, String.format("anError: %s", anError.getErrorDetail()));
                    }
                });
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateLayout(newConfig);
    }

    private int getSpanCount(Configuration configuration) {
        return configuration.orientation == ORIENTATION_PORTRAIT ? 1 : 2;
    }

    private void updateLayout(Configuration configuration) {
        ((GridLayoutManager) ordersLayoutManager)
                .setSpanCount(getSpanCount(configuration));
    }

}