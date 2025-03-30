package org.example;

import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.UpdateSummaryEnum;
import liquibase.analytics.configuration.AnalyticsArgs;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.ui.UIServiceEnum;
import org.hsqldb.jdbc.JDBCDataSource;

import java.sql.Connection;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        JDBCDataSource ds = new JDBCDataSource();
        ds.setURL("jdbc:hsqldb:mem:test");
        try (Connection connection = ds.getConnection()) {
            Map<String, Object> attrs = Map.of(
                    // use logging instead of printing directly to stdout
                    Scope.Attr.ui.name(), UIServiceEnum.LOGGER.getUiServiceClass().getConstructor().newInstance(),
                    // do not send analytics
                    AnalyticsArgs.ENABLED.getKey(), false
            );
            Scope.child(attrs, () -> {
                Liquibase liquibase = new Liquibase(
                        "org/example/db/db.changelog-master.xml",
                        new ClassLoaderResourceAccessor(),
                        new JdbcConnection(connection)
                );
                liquibase.setShowSummary(UpdateSummaryEnum.OFF);
                liquibase.update();
            });
        }

    }
}
