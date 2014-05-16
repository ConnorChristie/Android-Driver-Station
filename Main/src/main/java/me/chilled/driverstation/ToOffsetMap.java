package me.chilled.driverstation;

/**
 * Created by Connor on 5/15/14.
 */
public class ToOffsetMap
{
    public static int PACKET_NUMBER = 0; // 2 bytes
    public static int CONTROL       = 2; // 1 byte

    public static int DIGITAL_IN  = 3; // 1 byte
    public static int TEAM_NUMBER = 4; // 2 bytes

    public static int ALLIANCE = 6; // 1 byte
    public static int POSITION = 7; // 1 byte

    public static int[] STICK1_AXIS = {8,  9,  10, 11, 12, 13};
    public static int[] STICK2_AXIS = {14, 15, 16, 17, 18, 19};
    public static int[] STICK3_AXIS = {20, 21, 22, 23, 24, 25};
    public static int[] STICK4_AXIS = {26, 27, 28, 29, 30, 31};

    public static int STICK1_BUTTONS = 32; // 2 bytes
    public static int STICK2_BUTTONS = 34; // 2 bytes
    public static int STICK3_BUTTONS = 36; // 2 bytes
    public static int STICK4_BUTTONS = 38; // 2 bytes

    public static int ANALOG1 = 40; // 2 bytes
    public static int ANALOG2 = 42; // 2 bytes
    public static int ANALOG3 = 44; // 2 bytes
    public static int ANALOG4 = 46; // 2 bytes

    public static int CRIO_CHECKSUM  = 48; // 8 bytes
    public static int FPGA1_CHECKSUM = 56; // 8 bytes
    public static int FPGA2_CHECKSUM = 64; // 8 bytes

    public static int VERSION = 72; // 8 bytes

    public static int CRC = 1020; // 4 bytes
}