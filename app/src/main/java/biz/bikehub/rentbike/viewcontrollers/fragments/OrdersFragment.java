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
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import biz.bikehub.rentbike.R;
import biz.bikehub.rentbike.models.Order;
import biz.bikehub.rentbike.models.Station;
import biz.bikehub.rentbike.network.Services;
import biz.bikehub.rentbike.viewcontrollers.adapters.OrdersAdapter;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment {

    private RecyclerView OrdersRecyclerView;
    private OrdersAdapter ordersAdapter;
    private RecyclerView.LayoutManager ordersLayoutManager;
    private List<Order> orders;
    private static String TAG = "RentBike";

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);


        OrdersRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_orders);
        orders = new ArrayList<>();
        ordersAdapter = new OrdersAdapter(orders);
        ordersLayoutManager = new GridLayoutManager(view.getContext(),
                getSpanCount(getResources().getConfiguration()));
        OrdersRecyclerView.setAdapter(ordersAdapter);
        OrdersRecyclerView.setLayoutManager(ordersLayoutManager);

        updateData();
        return view;
    }

    private void updateData() {

        SharedPreferences preferences = this.getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = preferences.getString("userid", null);

        AndroidNetworking.post(Services.lastOrder())
                .addBodyParameter("userid", userid)
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

                        ordersAdapter.setOrders(orders);
                        ordersAdapter.notifyDataSetChanged();

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