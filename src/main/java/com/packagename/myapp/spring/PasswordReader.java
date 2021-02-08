package com.packagename.myapp.spring;

import java.io.File;
import java.nio.file.Path;

public class PasswordReader {


    public boolean read(String config) {
        File toRead = new File(String.valueOf(Path.of(config)));
        if(toRead.exists()){
            return true;
        }
        return false;
    }
}
