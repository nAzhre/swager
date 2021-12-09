package edu.learn.cdi.demo.domain.repos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreateDatabase {
    public static String createDatabaseSQL() {
        String array = "";
        array = "DROP TABLE IF EXISTS ITEMS; " + 
		"CREATE TABLE ITEMS (ID integer primary key, name varchar(100), price double, amount int); ";
        return array;
    }

    public static List<String> loadData() {
        List<String> array = new ArrayList<>();

        array.add("insert into STUDENT values(1,'Yerzhan','Utegenov','ITSE-1910');");
        array.add("insert into STUDENT values(2,'Chief','Keef','ITSE-1910');");
        array.add("insert into STUDENT values(3,'Leonardo','Di Caprio','ITSE-1910');");
        array.add("insert into STUDENT values(4,'Kevin','Abstract ','ITSE-1901');");
        array.add("insert into STUDENT values(5,'Ricardo','Bianchi','ITSE-1902');");

        return array;
    }
}
