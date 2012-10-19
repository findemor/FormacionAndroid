package es.solusoft.solusoftuc3m;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * Actividad mapa para el codigo de ejemplo del seminario Android.
 * Mapa + localizacion
 * @author findemor mgarcia@solusoft.es
 *
 */
public class MapActivity extends Activity {
	
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
    
}
