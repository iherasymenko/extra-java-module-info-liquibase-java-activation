@SuppressWarnings("opens") // the db package contains a resource file
module org.example.app {
    requires liquibase.core;
    requires org.hsqldb;
    //opens org.example.db to liquibase.core; -- this is too strict, the package needs to be "opened" globally so that liquibase's resource scan mechanism can detect resource files there
    opens org.example.db;
}
