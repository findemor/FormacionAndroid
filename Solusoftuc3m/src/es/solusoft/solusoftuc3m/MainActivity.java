package es.solusoft.solusoftuc3m;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.NavUtils;

/**
 * Actividad principal para el codigo de ejemplo del seminario Android.
 * v1 - Prueba de navegacion, activity state y mapas + localizacion
 * @author findemor mgarcia@solusoft.es
 *
 */
public class MainActivity extends Activity {

	boolean mSwitchOn = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Refrescamos el texto del boton.
        refreshSwitchText(); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean("mainactivity_mswitchon", mSwitchOn);
	};

	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);

		mSwitchOn = state.getBoolean("mainactivity_mswitchon");
	}
    
    /**
     * Actualiza el texto del boton de la vista 
     * en funci—n del valor del miembro mSwitchOn
     */
    private void refreshSwitchText()
    {
    	//Obtenemos la referencia al boton en la vista
    	Button switchButton = (Button)findViewById(R.id.buttonSwitch);
    	
    	if (switchButton != null)
    	{
    		//Establecemos el texto obteniendo el string desde el fichero de recursos values/strings.xml
    		switchButton.setText(mSwitchOn ? getString(R.string.switch_on): getString(R.string.switch_off) );
    	}
    }
    
    /**
     * Binding que permite controlar el evento de pulsacion del boton switch
     * La llamada est‡ en el layout res/activity_main
     * @param v Control que realiz— la llamada
     */
    public void onSwitchClic(View v) {
    	
    	//alternamos entre un valor u otro de la variable miembro que define el estado del bot—n
    	mSwitchOn = !mSwitchOn;
    	
    	//refrescamos el boton al nuevo estado
    	refreshSwitchText();
	}

    
}
