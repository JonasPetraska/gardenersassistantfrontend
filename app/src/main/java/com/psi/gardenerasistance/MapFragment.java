package com.psi.gardenerasistance;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener {
    View myView;
    MapView mapView;
    GoogleMap map;

    private final int REQUEST_LOCATION_PERMISSION = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savingInstance) {
        myView = inflater.inflate(R.layout.fragment_map, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) myView.findViewById(R.id.mapView);
        mapView.onCreate(savingInstance);

        // Gets to GoogleMap from the MapView and does initialization stuff
        mapView.getMapAsync(this);

        this.getActivity().setTitle(R.string.map);

        FloatingActionButton floatingActionButton = (FloatingActionButton) myView.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<LatLng> list = GetPoints();
                final int POLYGON_PADDING_PREFERENCE = 200;
                final LatLngBounds latLngBounds = getPolygonLatLngBounds(list);
                map.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, POLYGON_PADDING_PREFERENCE));
            }
        });



        return myView;
    }

    @Override
    public void onMapReady(GoogleMap mapComplete) {
        map = mapComplete;
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
        requestLocationPermission();

        /*if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }*/
    }

    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(REQUEST_LOCATION_PERMISSION)
    public void requestLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if(EasyPermissions.hasPermissions(this.getActivity(), perms))
        {

            PolygonOptions polygonOptions = new PolygonOptions();
            ArrayList<LatLng> points = GetPoints();

            for(LatLng point : points)
            {
                MarkerOptions vilniusMarker = new MarkerOptions().position(point)
                        .title(point.toString());
                map.addMarker(vilniusMarker);
                polygonOptions.add(vilniusMarker.getPosition());
            }


            polygonOptions.strokeColor(Color.BLACK);
            polygonOptions.strokeWidth(5);
            polygonOptions.fillColor(0x884d4d4d);

            Polygon polygon = map.addPolygon(polygonOptions);

            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
            MapsInitializer.initialize(this.getActivity());


            final int POLYGON_PADDING_PREFERENCE = 200;
            final LatLngBounds latLngBounds = getPolygonLatLngBounds(points);
            map.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, POLYGON_PADDING_PREFERENCE));
        }
        else
        {
            EasyPermissions.requestPermissions(this.getActivity(), "Please grant the location permission", REQUEST_LOCATION_PERMISSION, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this.getActivity());
    }


    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this.getActivity(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    private static LatLngBounds getPolygonLatLngBounds(final List<LatLng> polygon) {
        final LatLngBounds.Builder centerBuilder = LatLngBounds.builder();
        for (LatLng point : polygon) {
            centerBuilder.include(point);
        }
        return centerBuilder.build();
    }

    private static ArrayList<LatLng> GetPoints(){
        LatLng vilnius1 = new LatLng(54.686609, 25.189247);
        LatLng vilnius2 = new LatLng(54.686844, 25.191135);
        LatLng vilnius3 = new LatLng(54.686596, 25.190964);
        LatLng vilnius4 = new LatLng(54.686373, 25.189376);

        ArrayList<LatLng> list = new ArrayList<LatLng>();
        list.add(vilnius1);
        list.add(vilnius2);
        list.add(vilnius3);
        list.add(vilnius4);

        return list;
    }
}
