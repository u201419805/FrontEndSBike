package biz.bikehub.rentbike.viewcontrollers.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import biz.bikehub.rentbike.R;
import biz.bikehub.rentbike.models.User;
import biz.bikehub.rentbike.network.Services;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPassword);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button register = (Button) findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RegisterActivity = new Intent(getApplicationContext(), biz.bikehub.rentbike.viewcontrollers.activities.RegisterActivity.class);
                startActivity(RegisterActivity);
            }
        });



        Button signIn = (Button) findViewById(R.id.btnSignIn);
        signIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(final View view){

                AndroidNetworking.post(Services.login())
                        .addBodyParameter("email", email.getText().toString())
                        .addBodyParameter("password", password.getText().toString())
                        //.addBodyParameter("usertype", "1")
                        .setTag("RentBike")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // do anything with response
                                try {
                                    Boolean status = response.getBoolean("status");

                                    if(status == true){

                                        users = User.from(response.getJSONArray("user"));

                                        User user = users.get(0);

                                        SharedPreferences preferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("userid", user.getUserid());
                                        editor.commit();
                                        finish();

                                        if(user.getUsertype().equals("1")){
                                            Intent MainActivity = new Intent(getApplicationContext(), biz.bikehub.rentbike.viewcontrollers.activities.MainActivity.class);
                                            startActivity(MainActivity);
                                        }else{
                                            Intent MainOpActivity = new Intent(getApplicationContext(), biz.bikehub.rentbike.viewcontrollers.activities.MainOpActivity.class);
                                            startActivity(MainOpActivity);
                                        }


                                    }else{
                                        Snackbar.make(view, "Usuario o contraseña incorrecta "+ response.getString("status"), Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            @Override
                            public void onError(ANError error) {
                                // handle error
                            }
                        });

            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
