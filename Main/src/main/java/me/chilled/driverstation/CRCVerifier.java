package me.chilled.driverstation;

import android.util.Log;

/**
 * Created by Connor on 5/15/14.
 */
public class CRCVerifier
{
    private int[] table = new int[256];

    public CRCVerifier()
    {
        int poly = 0xedb88320;

        for (int i = 0; i < table.length; i++)
        {
            int entry = i;

            for (int j = 0; j < 8; j++)
            {
                if ((entry & 1) == 1)
                {
                    entry = (entry >> 1) ^ poly;
                } else
                {
                    entry = entry >> 1;
                }
            }

            table[i] = entry;
        }
    }

    public int verify(byte[] data)
    {
        int crc = 0xffffffff;

        for (int i = 0; i < data.length; i++)
        {
            crc = (crc >> 8) ^ table[(data[i] & 0xff) ^ (crc & 0xff)];
        }

        return crc ^ 0xffffffff;
    }
}
