package com.example.android.mapstutorial;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnPolygonClickListener,
        GoogleMap.OnPolylineClickListener {

    private GoogleMap mMap;

    private static final int POLYLINE_STROKE_WIDTH_PX = 12;
    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;

    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;

    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);


    private static final PatternItem DOT = new Dot();
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    public static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);
    // Create a stroke pattern of a gap followed by a dash.
    public static final List<PatternItem>PATTERN_POLYGON_ALPHA = Arrays.asList(GAP,DASH);
    //Create a stroke pattern of a dot following by a gap,a dash and another gap.
    public static final List<PatternItem>PATTERN_POLYGON_BETA = Arrays.asList(DOT,GAP,DASH,GAP);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        /*
        Add Polyline and polygons to the map.This section shows just a single polyline.
         */
        Polyline mPolyLine1 = googleMap.addPolyline(new PolylineOptions().clickable(true).add(
                new LatLng(-35.016, 143.321),
                new LatLng(-34.747, 145.592),
                new LatLng(-34.364, 147.891),
                new LatLng(-33.501, 150.217),
                new LatLng(-32.306, 149.248),
                new LatLng(-32.491, 147.309)
        ));

        mPolyLine1.setTag("A");

        Polygon mPolyGone1 = googleMap.addPolygon(new PolygonOptions().clickable(true).add(
                new LatLng(-27.457, 153.040),
                new LatLng(-33.852, 151.211),
                new LatLng(-37.813, 144.962),
                new LatLng(-34.928, 138.599)
        ));

        mPolyGone1.setTag("Alpha");
        stylePolygon(mPolyGone1);

        Polygon mPolyGone2 = googleMap.addPolygon(new PolygonOptions().clickable(true).add(
                new LatLng(-31.673, 128.892),
                new LatLng(-31.952, 115.857),
                new LatLng(-17.785, 122.258),
                new LatLng(-12.4258, 130.7932)
        ));

//        Circle circle = googleMap.addCircle(new CircleOptions().center(new LatLng(-12.4258, 130.7932))
//        .radius(30).strokeColor(Color.RED).fillColor(Color.BLUE).strokeWidth(2));
//        circle.setTag("New");

        mPolyGone2.setTag("Beta");
        stylePolygon(mPolyGone2);
        /*
        Position the map's camera near allice spring in the centre of Australia,
        and set the zoom factor so most of the Australia shows in here
         */

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-23.684, 133.903), 4));

        //set listener for click events
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
        stylePolyline(mPolyLine1);
    }

    @Override
    public void onPolygonClick(Polygon polygon) {

        



    }

    @Override
    public void onPolylineClick(Polyline polyline) {

        //Flip from solid stroke to dotted stroke pattern
        if ((polyline.getPattern() == null) || (!polyline.getPattern().contains(DOT))) {

            polyline.setPattern(PATTERN_POLYLINE_DOTTED);

        } else {
            //The default pattern is a solid stroke
            polyline.setPattern(null);
        }

        Toast.makeText(this, "Route Type" + polyline.getTag().toString(), Toast.LENGTH_SHORT).show();
    }

    //styling polyline in the map
    public void stylePolyline(Polyline polyline) {
        String type = "";

        //get a data object stored with polyline
        if (polyline.getTag() != null) {
            type = polyline.getTag().toString();
        }

        switch (type) {
            //if no type is given, allow the api to use the default
            case "A":
                //Use a custom bitmap as the cap at the start of the line
                polyline.setStartCap(
                        new CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.ic_arrow), 10));
                break;

            case "B":
                polyline.setStartCap(new RoundCap());
                break;
        }

        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(COLOR_BLACK_ARGB);
        polyline.setJointType(JointType.ROUND);
    }

    //styling polygon in the map
    public void stylePolygon(Polygon polygon){
        String type = "";
        //Get the data object stored with the polygon
        if (polygon.getTag()!=null){
            type = polygon.getTag().toString();
        }

        List<PatternItem>pattern = null;
        int strokeColor = COLOR_BLACK_ARGB;
        int fillColor = COLOR_WHITE_ARGB;

        switch (type){
            //If no type is given,allow the api to use the default
            case "Alpha":
                //Alpha is a stroke pattern to render a dashed line and defined color
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_GREEN_ARGB;
                fillColor = COLOR_PURPLE_ARGB;
                break;

            case "Beta":
                //Apply a stroke pattern to render a line of dots and dashes and define colors
                pattern = PATTERN_POLYGON_BETA;
                strokeColor = COLOR_BLUE_ARGB;
                fillColor = COLOR_ORANGE_ARGB;
                break;
        }
        polygon.setStrokePattern(pattern);
        polygon.setStrokeWidth(POLYLINE_STROKE_WIDTH_PX);
        polygon.setStrokeColor(strokeColor);
        polygon.setFillColor(fillColor);
    }
}
