package iiitd.com.wardi;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView data;
    private int flag=0, c=0;
    private SensorManager SM;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(iiitd.com.wardi.R.layout.activity_main);

        data = (TextView) findViewById(iiitd.com.wardi.R.id.text);
        data.setText("See, I told you");

        SM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(sensor == null) data.setText("NO SENSOR");
        else SM.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(sensor != null) SM.unregisterListener(this);
    }

    private void toggle() {
        if(flag == 1) data.setText(R.string.you);
        else data.setText(R.string.me);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

//        System.out.println(abs(sensorEvent.values[1]) + " " + flag);
        /*if(abs(sensorEvent.values[1]) > 1) {
            if(flag == 0 && c==0) flag=1;
            else if(c==0) flag=0;
            toggle();
            ++c;
        }
        else c=0;*/

        if (sensorEvent.values[1] < -0.6f) data.setText(R.string.you);
        else if (sensorEvent.values[1] > 0.6f ) data.setText(R.string.me);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
