package saidpurcity.mb.example.com.saidpurhelpline;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Home extends AppCompatActivity {

    LinearLayout sdpcity, historicalPlace, freedom_history, educational, bank, resturant, hotel, hospital, park, travel_agency, shopingmal,organization, localNewspaper,emergencyNum, travelGuide;


    SliderLayout sliderLayout;
    static final int REQUEST_PERMISSION = 1;
    LocationManager locationManager;

    RequestQueue requestQueue;

    TextView City, Country, WeatherDes, Temp, Pressure, Wind, Humdity, Icon;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        City = findViewById(R.id.city);
        Country = findViewById(R.id.country);
        Temp = findViewById(R.id.temp);
        WeatherDes = findViewById(R.id.weatherDes);
        Pressure = findViewById(R.id.pressure);
        Wind = findViewById(R.id.wind);
        Humdity = findViewById(R.id.humdity);
        Icon = findViewById(R.id.icon);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentJobs(),"চাকুরীর খবর");
        adapter.AddFragment(new FragmentHouseRant(),"টিউশন ও বাড়ি ভাড়া");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        sdpcity = findViewById(R.id.sdpcity);
        historicalPlace = findViewById(R.id.historicalPlace);
        freedom_history = findViewById(R.id.freedomHistory);
        educational = findViewById(R.id.educational);
        bank = findViewById(R.id.bank);
        resturant = findViewById(R.id.resturant);
        hotel = findViewById(R.id.hotel);
        park = findViewById(R.id.park);
        travel_agency = findViewById(R.id.travelAgency);
        shopingmal = findViewById(R.id.shopingMal);
        organization = findViewById(R.id.organization);
        localNewspaper = findViewById(R.id.localNews);
        emergencyNum = findViewById(R.id.emegency_phone);
        travelGuide = findViewById(R.id.travelGuide);

        sdpcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "City", Toast.LENGTH_SHORT).show();
            }
        });

        historicalPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Historical Place", Toast.LENGTH_SHORT).show();
            }
        });

        freedom_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Freedom History", Toast.LENGTH_SHORT).show();
            }
        });

        educational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Educational Institute", Toast.LENGTH_SHORT).show();
            }
        });

        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Bank", Toast.LENGTH_SHORT).show();
            }
        });


        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        setSliderViews();

        getLocation();


    }





    //>>>>>>>>>>>>>>>>> Weathe Code <<<<<<<<<<<<<<<<<<<<<<<<<
    public void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if(location!=null){
            double latitude = location.getLatitude();
            double langitude = location.getLongitude();

            Log.d("Lat&Lang",String.valueOf(latitude));
            Log.d("Lat&Lang",String.valueOf(langitude));
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case(REQUEST_PERMISSION):{

                getLocation();
                break;

            }

        }

    }

    @Override
    protected void onResume() {


        super.onResume();
        Log.d("Main", "In Resume");

        Thread thread = new Thread(){
            @Override
            public void run(){
                try{
                    while (!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                GetWeather();
                            }
                        });
                    }
                }catch (InterruptedException e){

                }
            }
        };
        thread.start();


    }

    //>>>>>>>>>>>>>>> GET WEATHER <<<<<<<<<<<<<<<<<
    public void GetWeather(){

        String url ="https://api.openweathermap.org/data/2.5/weather?&lat=25.78&lon=88.89&cnt=4&units=metric&appid=65e7dbbdd6f14136834e9d4a1d723e2f";
       //String url ="https://api.openweathermap.org/data/2.5/find?q=Saidpur,BD&units=metric&appid=65e7dbbdd6f14136834e9d4a1d723e2f";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("Main", String.valueOf(response));

                JSONObject list;
                JSONObject main;
                JSONObject wind;
                JSONObject weather;
                JSONObject sys;



                try {


                    JSONObject jsonObject = response.getJSONObject("main");
                    JSONArray jsonArray = response.getJSONArray("weather");
                    JSONObject object = jsonArray.getJSONObject(0);
                    String temp = String.valueOf(jsonObject.getDouble("temp"));
                    String description = object.getString("description");
                    String city = response.getString("name");
                    String icon = object.getString("icon");

                    Icon.setText(icon);

                    double temp_int = Double.parseDouble(temp);
                    int x = (int)temp_int;
                    Temp.setText(String.valueOf(x));

                    WeatherDes.setText(description);
                    City.setText(city);

                    main = jsonObject.getJSONObject("main");
                    String pressure = main.getString("pressure");
                    Pressure.setText(pressure);








//
//                    JSONArray jsonArray = response.getJSONArray("list");
//
//                    for(int i=0;i<=jsonArray.length();i++ ){
//
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                        main = jsonObject.getJSONObject("main");
//                        String temp = main.getString("temp");
//                        String pressure = main.getString("pressure");
//                        String humidity = main.getString("humidity");
//
//
//
//
//                        wind = jsonObject.getJSONObject("wind");
//                        String speed = wind.getString("speed");
//
//
//                        String city = jsonObject.getString("name");
//
//
//
//                        sys = jsonObject.getJSONObject("sys");
//                        String country = sys.getString("country");
//
//
//                        Wind.setText(speed);
//                        City.setText(city);
//                        Country.setText(country);
//                        Humdity.setText(humidity);
//                        Pressure.setText(pressure);
//
//
//                        double temp_int = Double.parseDouble(temp);
//                        int x = (int)temp_int;
//                        Temp.setText(String.valueOf(x));
//
//
//
//
//                    }







                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

              //  location.setText("Saidpur");
               // tempp.setText("0");



                //des.setText("Error Update");


            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);

    }

    //>>>>>>>>>>>>>>>>> Slider <<<<<<<<<<<<<<<<<<<<<<<<<
    private void setSliderViews() {

        for (int i = 0; i <= 5; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.chini_mosque);
                    sliderView.setDescription("ঐতিহাসিক চিনি মসজিদ");
                    //sliderView.setImageUrl("https://images.pexels.com/photos/547114/pexels-photo-547114.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.chini_mosque);
                    sliderView.setDescription("মর্তুজা ইন্সটিটিউট");
                    //sliderView.setImageUrl("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.chini_mosque);
                    sliderView.setDescription("গোলাহাট বধ্যভূমি");
                    // sliderView.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.chini_mosque);
                    sliderView.setDescription("সৈয়দপুর বিমানবন্দর");
                    // sliderView.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
                case 4:
                    sliderView.setImageDrawable(R.drawable.chini_mosque);
                    sliderView.setDescription("সৈয়দপুর রেলওয়ে কারখানা");
                    break;
                case 5:
                    sliderView.setImageDrawable(R.drawable.chini_mosque);
                    sliderView.setDescription("কান্তজীর মন্দির");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//            sliderView.setDescription(" " + (i + 1));
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(Home.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }

    }



}