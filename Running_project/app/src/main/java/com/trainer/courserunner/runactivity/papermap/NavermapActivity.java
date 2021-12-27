package com.trainer.courserunner.runactivity.papermap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.overlay.PolylineOverlay;
import com.trainer.courserunner.R;
import com.trainer.courserunner.component.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.component.drawer.drawtype.DrawingPath;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class NavermapActivity extends AppCompatActivity implements OnMapReadyCallback, MapDrawer {
    protected NaverMap naverMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navermap);
        settingNavermap();
    }

    private void settingNavermap() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.navermap_fragment);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.navermap_fragment, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
    }

    @Override
    public List<Object> drawOverlayMarkers(DrawingPath drawingPath) {
        List<Object> markerObjects = new ArrayList<>();
        for (DrawingAddress drawingAddress : drawingPath) {
            Marker marker = new Marker();
            marker.setPosition(new LatLng(drawingAddress.getLatitude(), drawingAddress.getLongitude()));
            marker.setMap(this.naverMap);
            markerObjects.add(marker);
        }
        return markerObjects;
    }

    @Override
    public Object drawOverlayPolyline(DrawingPath drawingPath) {
        Consumer<Object> property = drawingPath.getProperty();
        //setting
        List<LatLng> lngList = new ArrayList<>();
        for (DrawingAddress drawingAddress : drawingPath) {
            Double longitude = drawingAddress.getLongitude();
            Double latitude = drawingAddress.getLatitude();
            lngList.add(new LatLng(latitude, longitude));
        }
        //draw
        PolylineOverlay polyline = new PolylineOverlay();
        polyline.setCoords(lngList);
        property.accept(polyline);
        polyline.setMap(naverMap);
        return polyline;
    }

    @Override
    public Object drawOverlayPathline(DrawingPath drawingPath) {
        Consumer<Object> property = drawingPath.getProperty();
        //setting
        List<LatLng> lngList = new ArrayList<>();
        for (DrawingAddress drawingAddress : drawingPath) {
            Double longitude = drawingAddress.getLongitude();
            Double latitude = drawingAddress.getLatitude();
            lngList.add(new LatLng(latitude, longitude));
        }
        //draw
        PathOverlay pathOverlay = new PathOverlay();
        pathOverlay.setCoords(lngList);
        property.accept(pathOverlay);
        pathOverlay.setMap(naverMap);
        return pathOverlay;
    }

    @Override
    public void clearDraw(Object drawObject) {
        if (drawObject instanceof Marker) {
            ((Marker) drawObject).setMap(null);
        } else if (drawObject instanceof PolylineOverlay) {
            ((PolylineOverlay) drawObject).setMap(null);
        } else if (drawObject instanceof PathOverlay) {
            ((PathOverlay) drawObject).setMap(null);
        } else {
            throw new IllegalArgumentException();
        }
    }
}