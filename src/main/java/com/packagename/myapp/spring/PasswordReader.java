package com.packagename.myapp.spring;

import java.io.*;
import java.nio.file.Path;

public class PasswordReader {

    private String apiKey;
    private String url;

    public boolean isExist(String config) {
        File toRead = new File(String.valueOf(Path.of(config)));
        if(toRead.exists()){
            return true;
        }
        return false;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUrl() {
        return url;
    }

    public boolean read(String config) throws IOException {

        if(isExist(config)) {
            File toRead = new File(String.valueOf(Path.of(config)));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(toRead));
            String input = "";
            String[] keys;
            while ((input = bufferedReader.readLine()) != null) {
                keys = input.split(" ");
                if (input.contains("APIKEY")) {
                    apiKey = keys[1];
                } else {
                    url = keys[1];
                }
            }
            return true;
        }
        return false;
    }
}
