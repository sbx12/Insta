package com.conversion.sbx.insta;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("instainstax ")
                // if desired
                .clientKey("B154BGFD5LVFNX0X ")
                .server("https://instainstax.herokuapp.com/parse")
                .build()
        );
    }
}
