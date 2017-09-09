package com.example.avma1997.tmdb;

import android.arch.persistence.room.TypeConverter;

/**
 * Created by Avma1997 on 8/2/2017.
 */

public class Convertors {


    @TypeConverter
    public static int[] stringtointegerarray(String value) {

        int count=0;
        for(int i=0;i<value.length();i++) {
            if (value.charAt(i) == ',') {
                count++;
            }
        }
        int[] a=new int[count];
        int i=0;
        int j=0;
        int k=0;



        while(i<value.length())
        {
            if(value.charAt(i)==',')
            {
                a[k]=Integer.valueOf(value.substring(j,i));
                k++;
                i++;
                j=i;
            }
            i++;

        }
        return a;

    }

    @TypeConverter
    public static String integerarraytostring(int[] a) {
        String s="";
        for(int i=0;i<a.length;i++)
        {
            s=s+a[i]+",";
        }
        return s;

    }
}


