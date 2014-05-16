package me.chilled.driverstation;

import android.util.Log;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

/**
 * Created by Connor on 5/15/14.
 */
public class MainConnection
{
    private Thread fromRobot;
    private Thread toRobot;

    private boolean isRunning;

    private DatagramSocket fromSocket;
    private DatagramSocket toSocket;

    private int fromPort = 1150;
    private int toPort   = 55056;

    private byte[] receiveData;
    private byte[] sendData;

    private CRCVerifier verifier;

    public MainConnection()
    {
        receiveData = new byte[1152];
        sendData    = new byte[1024];

        verifier = new CRCVerifier();

        Utilities.setShort(sendData, ToOffsetMap.TEAM_NUMBER, (short) 4095);
        Utilities.setLongR(sendData, ToOffsetMap.VERSION, Long.parseLong("3031303431343030", 16));

        Utilities.setShort(sendData, ToOffsetMap.PACKET_NUMBER, (short) 32000);

        try
        {
            fromSocket = new DatagramSocket(fromPort);
            toSocket = new DatagramSocket(toPort);
        } catch (SocketException e)
        {
            Log.d("Socket Error", e.getMessage());
        }

        fromRobot = new Thread(new FromRobot());
        toRobot   = new Thread(new ToRobot());
    }

    public void start()
    {
        isRunning = true;

        fromRobot.start();
        toRobot.start();

        Log.d("MainConnection", "Done Initiating");
    }

    public void stop()
    {
        isRunning = false;
    }

    class FromRobot implements Runnable
    {
        @Override
        public void run()
        {
            while (isRunning)
            {
                Log.d("FromRobot", "Running");

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                try
                {
                    fromSocket.receive(receivePacket);
                } catch (IOException e)
                {
                    Log.d("Receive Error: ", e.getMessage());
                }

                Log.d("Received", Arrays.toString(receivePacket.getData()));
            }
        }
    }

    class ToRobot implements Runnable
    {
        @Override
        public void run()
        {
            while (isRunning)
            {
                //Log.d("ToRobot", "Running");

                short number = (short) (Utilities.getShort(sendData, ToOffsetMap.PACKET_NUMBER) + 1);

                Utilities.setShort(sendData, ToOffsetMap.PACKET_NUMBER, number);

                Log.d("ToRobot", "Num: " + number);

                Utilities.setInt(sendData, ToOffsetMap.CRC, 0);
                Utilities.setInt(sendData, ToOffsetMap.CRC, verifier.verify(sendData));

                try
                {
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("10.0.2.2"), toPort);

                    try
                    {
                        toSocket.send(sendPacket);
                    } catch (IOException e)
                    {
                        Log.d("Send Error: ", e.getMessage());
                    }
                } catch (UnknownHostException e)
                {
                    Log.d("Send Error: ", e.getMessage());
                }

                try
                {
                    Thread.sleep(20, 0);
                } catch (InterruptedException e) { }
            }
        }
    }
}