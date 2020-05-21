package org.tinder.project.migrations;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Restart {
    private final static String URL = "jdbc:postgresql://ec2-54-246-85-151.eu-west-1.compute.amazonaws.com:5432/d36isdpvdr1253";
    private final static String NAME = "mgfwnnvgwivxck";
    private final static String PASSWORD = "a7563aa462d5ab9290ca6c5ac6fdb0932487fe94b10adcc13db9690fe0c55f10";

    public static void main(String[] args) {
        DbSetup.migrate(URL,NAME,PASSWORD);
        log.warn("Restarted your database");
    }

}
