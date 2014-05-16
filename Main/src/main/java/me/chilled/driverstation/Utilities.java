package me.chilled.driverstation;

import java.nio.ByteBuffer;

/**
 * Created by Connor on 5/15/14.
 */
public class Utilities
{
    public static void setShort(byte[] buffer, int offset, short num)
    {
        ByteBuffer temp = ByteBuffer.allocate(2);

        temp.putShort(num);

        buffer[offset + 0] = temp.get(1);
        buffer[offset + 1] = temp.get(0);

        temp.clear();
    }

    public static void setInt(byte[] buffer, int offset, int num)
    {
        ByteBuffer temp = ByteBuffer.allocate(4);

        temp.putInt(num);

        buffer[offset + 0] = temp.get(3);
        buffer[offset + 1] = temp.get(2);
        buffer[offset + 2] = temp.get(1);
        buffer[offset + 3] = temp.get(0);

        temp.clear();
    }

    public static void setLong(byte[] buffer, int offset, long num)
    {
        ByteBuffer temp = ByteBuffer.allocate(8);

        temp.putLong(num);

        buffer[offset + 0] = temp.get(7);
        buffer[offset + 1] = temp.get(6);
        buffer[offset + 2] = temp.get(5);
        buffer[offset + 3] = temp.get(4);
        buffer[offset + 4] = temp.get(3);
        buffer[offset + 5] = temp.get(2);
        buffer[offset + 6] = temp.get(1);
        buffer[offset + 7] = temp.get(0);

        temp.clear();
    }

    public static void setLongR(byte[] buffer, int offset, long num)
    {
        ByteBuffer temp = ByteBuffer.allocate(8);

        temp.putLong(num);

        buffer[offset + 0] = temp.get(0);
        buffer[offset + 1] = temp.get(1);
        buffer[offset + 2] = temp.get(2);
        buffer[offset + 3] = temp.get(3);
        buffer[offset + 4] = temp.get(4);
        buffer[offset + 5] = temp.get(5);
        buffer[offset + 6] = temp.get(6);
        buffer[offset + 7] = temp.get(7);

        temp.clear();
    }

    public static short getShort(byte[] buffer, int offset)
    {
        ByteBuffer temp = ByteBuffer.allocate(2);

        temp.put(1, buffer[offset + 0]);
        temp.put(0, buffer[offset + 1]);

        return temp.getShort();
    }
}