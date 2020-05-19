package org.tinder.project.migrations;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

public interface DbSetup {

    static void migrate(String path, String user, String password){
        migrate(path,user,password,false);
    }

    static void migrate(String path, String user, String password, boolean clean){

        FluentConfiguration configuration = new FluentConfiguration()
                .dataSource(path,user,password);
        Flyway flyway =new Flyway(configuration);
        if (clean)flyway.clean();
        flyway.migrate();
    }
}
