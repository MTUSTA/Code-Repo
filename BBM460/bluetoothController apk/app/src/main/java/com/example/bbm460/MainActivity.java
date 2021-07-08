package com.example.bbm460;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbm460.Utils.AwesomeToggle;

import com.suke.widget.SwitchButton;


import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BluetoothHomeAutomation";

    private BluetoothSocket socket;
    private BluetoothDevice device;
    private OutputStream os;
    private BluetoothAdapter mBluetoothAdapter;
    public String deviceName;
    public String deviceAddress;
    UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    TextView nameView;

    SwitchButton device1, device2, device3, device4, device_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameView = (TextView)findViewById(R.id.controllerName);

        device1 = (SwitchButton) findViewById(R.id.device1_toggle);
        device2 = (SwitchButton) findViewById(R.id.device2_toggle);
        device3 = (SwitchButton) findViewById(R.id.device3_toggle);
        device4 = (SwitchButton) findViewById(R.id.device4_toggle);
        device_all = (SwitchButton) findViewById(R.id.device_all_toggle);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(),R.string.Bluetooth_NA, Toast.LENGTH_LONG).show();
        }
        else if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBtIntent);
        }

        device1.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    sendSignal(1);
                }
                else {
                    sendSignal(5);
                }
            }
        });

        device2.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked)
                    sendSignal(2);
                else
                {
                    sendSignal(6);
                }
            }
        });

        device3.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked)
                    sendSignal(3);
                else
                {
                    sendSignal(7);
                }
            }
        });

        device4.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked)
                    sendSignal(4);
                else
                {
                    sendSignal(8);
                }
            }
        });

        device_all.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    //sendSignal(0);
                    device1.setChecked(true);
                    device2.setChecked(true);
                    device3.setChecked(true);
                    device4.setChecked(true);
                }
                else {
                    //sendSignal(10);
                    device1.setChecked(false);
                    device2.setChecked(false);
                    device3.setChecked(false);
                    device4.setChecked(false);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("preferences",MODE_PRIVATE);
        deviceName = preferences.getString("controllerName", "NA");
        deviceAddress = preferences.getString("controllerAddress", "");
        nameView.setText("Connected to " + "\"" + deviceName + "\"");
        if(!deviceAddress.equals("")) {
            device = mBluetoothAdapter.getRemoteDevice(deviceAddress);
            try {
                socket = device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                //nameView.setText("Connected to " + "\"" + "NA" + "\"");
                //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            mBluetoothAdapter.cancelDiscovery();
            if(socket != null){
                try {
                    socket.connect();
                } catch (IOException e) {
                    //nameView.setText("Connected to " + "\"" + "NA" + "\"");
                    //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e(TAG, "socket connect failed: " + e.getMessage() + "\n");
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        Log.e(TAG, "socket closing failed: " + e1.getMessage() + "\n");
                        //Toast.makeText(getApplicationContext(), e1.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
            if (socket != null){
                try {
                    os = socket.getOutputStream();
                } catch (IOException e) {
                    Log.e(TAG, "getting output stream failed: " + e.getMessage() + "\n");
                    //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Connect to a device",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (os != null && socket.isConnected()) {
            try {
                os.flush();
            } catch (IOException e) {
                Log.e(TAG, "flushing output stream failed: " + e.getMessage() + "\n");
                Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        try {
            if(socket != null)
                socket.close();
        } catch (IOException e) {
            Log.e(TAG, "closing socket failed: " + e.getMessage() + "\n");
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void sendSignal(int message) {
        if(socket != null){
            if(socket.isConnected()) {
                try {
                    if (os != null){
                        os.write(message);
                        Log.d("OS Message : ", "" + message);
                    }
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            else {
                //Toast.makeText(getBaseContext(), "Connect to the selected bluetooth device first", Toast.LENGTH_LONG).show();
            }
        }
        else {
            //Toast.makeText(getBaseContext(), "Connect to the selected bluetooth device first", Toast.LENGTH_LONG).show();
        }
    }

    public void selectFromList(View view) {
        Intent intent = new Intent(this,SelectController.class);
        startActivity(intent);
    }
}
