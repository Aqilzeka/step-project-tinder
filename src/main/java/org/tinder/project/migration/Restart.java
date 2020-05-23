package org.tinder.project.migration;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Restart {

    private final static String URL = "jdbc:postgresql://ec2-34-232-147-86.compute-1.amazonaws.com:5432/d79pqhfsbv3cgk";
    private final static String NAME = "gbygpgpuvrardi";
    private final static String PASSWORD = "9f841d1e93fa7f69cbd0a55098f98ae1ef5d081ad9ba0cf84688bedd00261b8a";

    public static void main(String[] args) {
        DbSetup.migrate(URL,NAME,PASSWORD);
        log.warn("Restarted your database");
    }

}