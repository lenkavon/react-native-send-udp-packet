package com.sendudppacket.RNSendUdpPacket;

import android.util.Log;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.io.*;
import java.net.*;

public class RNSendUdpPacketModule extends ReactContextBaseJavaModule {

  public RNSendUdpPacketModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNSendUdpPacket";
  }

  @ReactMethod
  public void sendPacket(String ip, int port, int arduinoPort, int command, int parameter) {
    try {

      byte[] message = new byte[3];
      message[0] = (byte)arduinoPort;
      message[1] = (byte)command;
      message[2] = (byte)parameter;

      // Initialize a datagram packet with data and address
      DatagramPacket packet = new DatagramPacket(message, message.length, InetAddress.getByName(ip), port);

      // Create a datagram socket, send the packet through it, close it.
      DatagramSocket dsocket = new DatagramSocket();
      dsocket.send(packet);
      dsocket.close();
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
