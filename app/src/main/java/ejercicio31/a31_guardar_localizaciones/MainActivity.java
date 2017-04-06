package ejercicio31.a31_guardar_localizaciones;

import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import modelo.GestionLocalizacion;
import modelo.Posicion;

public class MainActivity extends AppCompatActivity {

    GestionLocalizacion gloc;
    LocationManager lm;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gloc=new GestionLocalizacion(this,"posicion");
        lst=(ListView)this.findViewById(R.id.lstLoc);
        lm=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        try {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 10, new CambioLocalizacion());
        }catch(SecurityException ex){
            ex.printStackTrace();
        }
    }

    public void mostrar(View v){
        Cursor c=gloc.mostrarDatos();
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.fila,c,
                                                            new String[]{"latitud","longitud"},
                                                            new int[]{R.id.tvLat,R.id.tvLong},
                                                            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lst.setAdapter(adapter);
    }

    class CambioLocalizacion implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            Posicion p=new Posicion(location.getLatitude(),location.getLongitude());
            gloc.guardarPosicion(p);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onProviderDisabled(String provider) {}
    }
}
