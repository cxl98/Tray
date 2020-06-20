package com.easyArch.client.file;



import com.easyArch.client.ui.Tray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Peizhi {
    public static HashMap readFile(String path, HashMap hashMap, String type) {
        File file = new File(path);
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            //构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {
                String ss[] = s.split(" ");
                if (ss[1] != null) {
                    if ("1".equals(type)) {
                        hashMap.put(ss[0],ss[1]);
                    } else {
                       hashMap.put(ss[0],Integer.valueOf(ss[1]));
                    }

                } else {
                    hashMap.put(ss[0], null);
                }
                System.out.println(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public static void main(String[] args) {
        System.out.println(Tray.path+"============");

    }

}
