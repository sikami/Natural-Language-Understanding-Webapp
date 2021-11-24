package com.packagename.myapp.spring;

import java.io.*;
import java.nio.file.Path;
import java.util.logging.Logger;

public class PasswordReader {

    private String apiKey;
    private String url;
    private String accessToken;
    private Logger logger = Logger.getLogger(PasswordReader.class.getName());

    public boolean isExist(String config) {

        try {
            File toRead = new File(String.valueOf(Path.of(config)));
            if(toRead.exists()){
                return true;
            }
        } catch (Exception e) {
            logger.info("File doesn't exist or path is incorrect.");
        }

        return false;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUrl() {
        return url;
    }

    public String getAccessToken() { return accessToken;}

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
                } else if (input.contains("URL")){
                    url = keys[1];
                } else {
                    accessToken = keys[1];
                }
            }
            return true;
        }
        return false;
    }


}
