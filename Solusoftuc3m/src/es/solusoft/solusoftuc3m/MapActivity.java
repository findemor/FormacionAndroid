package es.solusoft.solusoftuc3m;

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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	protected boolean isRouteDisplayed() { return false; }
    
}
