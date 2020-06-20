package com.easyArch.client.file;

import java.io.File;
import java.io.IOException;

public class GetPath {
    public static String getfile(){
        File directory = new File("");//参数为空
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(courseFile);
        return courseFile+"/";
    }

    public static void main(String[] args) {
        GetPath test=new GetPath();
        System.out.println(getfile());
    }
}
