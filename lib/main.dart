import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main(){
  runApp(LoginApp());
}

class LoginApp extends StatelessWidget{

  void startServiceInPlatform() async{
    if(Platform.isAndroid){
      var methodChannel = MethodChannel("com.example.first_app");
      int data = await methodChannel.invokeMethod("startService");
      print(data);
    }
  }

  @override
  Widget build(BuildContext context) {
    String text = "Hello";
    return MaterialApp(
      title: "Login App",
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: Text("Login App"),
        ),
        body: Container(
            alignment: Alignment.center,
            child: RaisedButton(
              child: Text("Login"),
              onPressed: () {
                startServiceInPlatform();
              },
            )
        ),
      )
    );
  }

}