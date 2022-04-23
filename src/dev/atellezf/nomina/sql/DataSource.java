package dev.atellezf.nomina.sql;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    public static Connection getConnection() throws SQLException {
        Properties config = cargarConfiguracion("config.properties");
        String dburl = config.getProperty("url");
        return DriverManager.getConnection(dburl, config);
    }

    private static Properties cargarConfiguracion(String nombreArchivo) {
        Properties props = new Properties();
        try (FileReader reader = new FileReader(nombreArchivo)) {            ;
            props.load(reader);
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
        return props;
    }

}
