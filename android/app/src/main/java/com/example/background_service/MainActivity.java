package com.example.background_service;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import io.flutter.app.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {

    private Intent forService;

  /*@Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    GeneratedPluginRegistrant.registerWith(flutterEngine);
  }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forService = new Intent(MainActivity.this,MyService.class);
        if(forService == null) System.out.println("Null"); else System.out.println("Not Null");
        new MethodChannel(getFlutterView(),"com.example.messages")
                .setMethodCallHandler(new MethodChannel.MethodCallHandler() {
                    @Override
                    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
                        if(call.method.equals("startService")){
                            startService();
                            result.success("Service Started");
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Stopping");
        stopService(forService);
    }

    private void startService(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            startForegroundService(forService);
            System.out.println("Started service");
        } else {
            startService(forService);
        }
    }
}
