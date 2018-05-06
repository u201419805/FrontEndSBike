package biz.bikehub.rentbike.viewcontrollers.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.widget.ANImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import biz.bikehub.rentbike.R;
import biz.bikehub.rentbike.RentBikeApp;
import biz.bikehub.rentbike.models.Station;
import biz.bikehub.rentbike.models.User;
import biz.bikehub.rentbike.network.Services;

public class OrderActivity extends AppCompatActivity {

    private ANImageView pictureImageView;
    private TextView nameTextView;
    private TextView addressTextView;
    private TextView capacityTextView;
    private Button orderButton;
    private Station station;

    private static String TAG = "RentBike";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        if(intent == null) return;

        pictureImageView = (ANImageView) findViewById(R.id.image_picture);
        nameTextView = (TextView) findViewById(R.id.text_name);
        addressTextView = (TextView) findViewById(R.id.text_address);
        capacityTextView = (TextView) findViewById(R.id.text_capacity);
        orderButton = (Button) findViewById(R.id.button_order);

        station = Station.from(intent.getExtras());

        updateViews(station);
    }

    private void updateViews(final Station station) {
        nameTextView.setText(station.getName());
        addressTextView.setText(station.getAddress());
        capacityTextView.setText(station.getCapacity());
        pictureImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        pictureImageView.setErrorImageResId(R.mipmap.ic_launcher);
        pictureImageView.setImageUrl(station.getUrlimage());
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO ACCIÓN PARA REALIZAR UNA ORDEN

                //Snackbar.make(v, station.getUrlimage(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

                SharedPreferences preferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                String userid = preferences.getString("userid", null);

                newOrder(v, userid, station.getStationid());
            }
        });
    }

    private void newOrder(final View v, String userid, String stationId) {
        AndroidNetworking.post(Services.newOrder())
                .addBodyParameter("userid", userid)
                .addBodyParameter("stationid", stationId)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener()  {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Boolean status = response.getBoolean("status");
                            if(status == true){
                                Snackbar.make(v, status.toString(), Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                OrderActivity.super.finish();

                            }else{
                                Snackbar.make(v, status.toString(), Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, String.format("anError: %s", anError.getErrorDetail()));
                    }
                });
    }

}
