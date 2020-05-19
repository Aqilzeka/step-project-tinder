package org.tinder.project.heroku;

public class HerokuEnv {

    public static int port(){
        try {
            String port = System.getenv("PORT");
            return Integer.parseInt(port);
        } catch (NumberFormatException x){
            return 5000;
        }
    }
}
