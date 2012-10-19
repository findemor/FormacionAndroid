package es.solusoft.solusoftuc3m;

import java.util.ArrayList;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
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
        
        //Comenzamos a atender actualizaciones en las posiciones
        CustomLocationListener customLocationListener = new CustomLocationListener();

        mLocationManager.requestLocationUpdates(
          LocationManager.GPS_PROVIDER,
          2000, //minTime
          0, //minDistance
          customLocationListener);
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

		 updateMyIconAndRefresh(
				 loc.getProvider().toString() + 
				 ",\r\nLat:" + Double.toString(loc.getLatitude()) +
				 ",\r\nLng:" + Double.toString(loc.getLongitude()) + 
				 ",\r\nAcc:" + Double.toString(loc.getAccuracy()) + "m" +
				 ",\r\nVel:" + Double.toString(loc.getSpeed()) + "m/s",
				 geoPoint);
	 }

	 /**
	  * Actualiza el icono de posicion del usuario y refresca las capas
	  * dibujadas del mapa
	  * @param loc Localizacion del usuario
	  * @param geoPoint
	  */
	private void updateMyIconAndRefresh(String description, GeoPoint geoPoint) {
		//Mostramos el icono donde estamos
		 //1 - creamos el marker
		 Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
		 CustomMapMarker itemizedoverlay = new CustomMapMarker(drawable,this);
		 //2 - geolocalizamos el marker
		 OverlayItem overlayitem = new OverlayItem(geoPoint, getString(R.string.me), description);
		 //3 - A–adimos el marker al mapa
		 itemizedoverlay.addOverlay(overlayitem);
		 mMapView.getOverlays().clear(); //Limpiamos todo lo que habiamos dibujado antes
		 mMapView.getOverlays().add(itemizedoverlay);
	};
	 
	 
	 
	 /**
	  * Location Listener personalizado
	  * @author findemor
	  *
	  */
	 private class CustomLocationListener implements LocationListener{

		  public void onLocationChanged(Location argLocation) {
			  centerLocation(argLocation);
		  }

		  public void onProviderDisabled(String provider) {}

		  public void onProviderEnabled(String provider) {}

		  public void onStatusChanged(String provider,
		    int status, Bundle extras) {}
	 }
	 
	 
	 /**
	  * Marcador de mapa personalizado
	  * @author findemor
	  *
	  */
	 public class CustomMapMarker extends ItemizedOverlay {

		    private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
		    private Context mContext;

		    
		    public CustomMapMarker(Drawable defaultMarker) {
		        super(boundCenterBottom(defaultMarker));
		    }

		    public CustomMapMarker(Drawable defaultMarker, Context context) {
		        this(defaultMarker);
		        mContext = context;
		    }

		    public void addOverlay(OverlayItem item) {
		        mOverlays.add(item);
		        populate();
		    }

		    @Override
		    protected OverlayItem createItem(int i) {
		        return mOverlays.get(i);
		    }
		    @Override
		    public int size() {
		        return mOverlays.size();
		    }
		    @Override
		    protected boolean onTap(int index) { 
		    	
		    	//Ejemplo de AlertDialog
		    	OverlayItem item = mOverlays.get(index);
		        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		        dialog.setTitle(item.getTitle());
		        dialog.setMessage(item.getSnippet());
		        dialog.setPositiveButton(mContext.getString(R.string.ok), new OnClickListener() {    
		            @Override
		            public void onClick(DialogInterface dialog, int which) {
		                dialog.dismiss();
		            }
		        });
		        dialog.show();
		        return true;
		    }
		}

}
