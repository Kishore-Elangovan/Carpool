package com.example.carpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.carpooling.Model.User;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FirstPage extends AppCompatActivity {

    PlacesClient placesClient;
    ImageButton btnNext;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference users;

    String addressLocation;
    String addressDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        //to save location details to user's database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        users = database.getReference().child("Users").child(auth.getCurrentUser().getUid());


        String apiKey = "AIzaSyDpFmWPF8EtI-1OQzNuBS2BsOf2RFhQcO0";

        if (!Places.isInitialized()){
            Places.initialize(getApplicationContext(),apiKey);
        }

        placesClient = Places.createClient(this);

        final AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.place_location);

        autocompleteSupportFragment.setHint("Your location");

        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG,Place.Field.NAME));

        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                addressLocation = place.getName();

                System.out.println(addressLocation);
            }

            @Override
            public void onError(@NonNull Status status) {

            }
        });


        //Destination
        AutocompleteSupportFragment autocompleteSupportFragmentDestination = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.place_destination);

        autocompleteSupportFragmentDestination.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG,Place.Field.NAME));

        autocompleteSupportFragmentDestination.setHint("Your destination");

        autocompleteSupportFragmentDestination.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                addressDestination = place.getName();

                System.out.println(addressDestination);
            }

            @Override
            public void onError(@NonNull Status status) {

            }
        });

        btnNext = (ImageButton)findViewById(R.id.btnNext);

        //adding
        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setLocation(addressLocation);
                user.setDestination(addressDestination);

                Map<String, Object> addressUpdate = new HashMap<>();
                addressUpdate.put("location", addressLocation);
                addressUpdate.put("destination", addressDestination);
                users.updateChildren(addressUpdate);

                startActivity(new Intent(FirstPage.this, HomePage.class));
                finish();
            }
        });
    }
}
