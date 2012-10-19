package es.solusoft.solusoftuc3m;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;

/**
 * Actividad mapa para el codigo de ejemplo del seminario Android.
 * Mapa + localizacion
 * @author findemor mgarcia@solusoft.es
 * 
 * Notas:
 * Obtener un apiKey para Google Maps: 
 * 		https://developers.google.com/maps/documentation/android/mapkey
 * Acerca del keystore de desarollador necesario para obtener el apiKey:
 *		http://developer.android.com/intl/es/tools/publishing/app-signing.html
 */
public class MapActivity extends com.google.android.maps.MapActivity {
	
	private LocationManager mLocationManager;
    private MapView mMapView;
	private MapController mMapController;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        //Obtenemos las referencias de la vista
        mMapView = (MapView)findViewById(R.id.mapview);
        mMapController = mMapView.getController();
        
        //Obtenemos la referencia del LocationManager del sistema
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
              
        //Obtenemos la ultima posici—n cacheada por el dispositivo
        String locationProvider = LocationManager.NETWORK_PROVIDER; // Or use LocationManager.GPS_PROVIDER
        Location lastKnownLocation = mLocationManager.getLastKnownLocation(locationProvider);

        //Centramos el mapa
        mMapController.setZoom(20); //Fixed Zoom Level
        centerLocation(lastKnownLocation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	protected boolean isRouteDisplayed() { return false; }
    
	
	
	/**
	 * Centra el mapa en la posicion especificada
	 * @param loc
	 */
	 private void centerLocation(Location loc)
	 {
	     GeoPoint geoPoint = new GeoPoint(
	     	(int)(loc.getLatitude()*1000000),
	     	(int)(loc.getLongitude()*1000000));
		 
		 mMapController.animateTo(geoPoint);
	 };
}
