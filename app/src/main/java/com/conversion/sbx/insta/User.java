package com.conversion.sbx.insta;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.security.PublicKey;

@ParseClassName("UserProfile")
public class User extends ParseObject {

    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_OBJECTID = "user";
    public static final String KEY_DESCRIPTION = "Description";

    public String getPassword(){
        return getString(KEY_PASSWORD);
    }

    public void setPassword(String password){
        put(KEY_PASSWORD, password);
    }

    public String getUsername(){
        return getString(KEY_USERNAME);
    }

    public void setUsername(String username) {
        put(KEY_USERNAME, username);
    }


    public ParseUser getObjectID(){
        return getParseUser(KEY_OBJECTID);
    }

    public void setObjectID(ParseUser parseUser){
        put(KEY_OBJECTID, parseUser);
    }


    public  String getDescription(){ return getString(KEY_DESCRIPTION);}

    public void setKeyDescription(String description){ put(KEY_DESCRIPTION, description);}

}
