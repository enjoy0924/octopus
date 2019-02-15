package com.octopus.taxcube.util;

import org.springframework.util.ResourceUtils;

import java.io.*;

public class AreaUtils {

    public static String area = "";

    public static String getArea(){
        if(null == area || area.trim().isEmpty()) {
            try {
                File file = ResourceUtils.getFile("classpath:area.json");
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = null;
                try {
                    isr = new InputStreamReader(fis, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //将file文件内容转成字符串
                BufferedReader bf = new BufferedReader(isr);

                String content = "";
                StringBuilder sb = new StringBuilder();
                while (content != null) {
                    content = bf.readLine();
                    if (content == null) {
                        break;
                    }
                    sb.append(content.trim());
                }
                bf.close();


                area = sb.toString();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return area;
    }
}
