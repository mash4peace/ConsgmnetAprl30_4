package Database;

import Constractor.Record;

import java.sql.*;
/**
 * Created by mash4 on 4/30/2017.
 */
public class RecordDB {
    private static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/consignment";
    private static final String USER = ("mash4peace");
    private static final String PASSWORDS = System.getenv("MYSQL_pw");
    private static final String TABLE_NAME = "records";

    public RecordDB() {
        try {
            Class.forName(JDBC_Driver);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; " +
                    "check you have drives and classpath configured" +
                    " correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program

        }

    }


    public void addNewRecord(Record record) {
    }

    public void createNewRecordTable() {

        final String CONSIGNORID_COL = "consgnrID";
        final String CONSIGNorNAme_COL = "consgnorName";
        final String CONSGnorID_COL = "consgmentID";
        final String ITEM_COL = "item";
        final String SALEPRX_COL = "salePrx";
        final String DATE_COL = "date";

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORDS);
             Statement statement = conn.createStatement()) {
            String createTableSQLTemp =
                    "Create Table if not exists records ( consgnrID Integer Not Null Auto_Increment,  consgnorName Varchar (100)," +
                            " item Integer , salePrx Double, date Date,  Primary Key(consgnrID))";
            String createSQL = String.format(createTableSQLTemp, TABLE_NAME, CONSIGNORID_COL, CONSIGNorNAme_COL, ITEM_COL, DATE_COL);
            System.out.println(createSQL);
            statement.executeUpdate(createSQL);
            System.out.println(TABLE_NAME + " table is created !!!");
            statement.close();
            conn.close();

        } catch (SQLException sq) {
            sq.getCause();
            sq.printStackTrace();
        }

    }


    public void addNewRecords(Record record) {
        final String CONSIGNorNAme_COL = "consgnorName";

        final String ITEM_COL = "item";
        final String SALEPRX_COL = "salePrx";
        final String DATE_COL = "date";

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORDS)) {

            String addNewRecord = "INSERT INTO " + TABLE_NAME + "( consgnorName, item, salePrx, date )" + " Values ( ?, ?, ?, ? )";
            PreparedStatement addConsPS = conn.prepareStatement(addNewRecord);
            System.out.println(addNewRecord);
            // I got this statement from 'http://stackoverflow.com/questions/28315139/java-util-date-cannot-be-cast-to-java-sql-date'
            //It's here formatting my date and instantiting new Date for the record.
            java.sql.Date sqlDate = new java.sql.Date(record.getDate().getTime());

            addConsPS.setString(1, record.getName());
            addConsPS.setInt(2, record.getItem());
            addConsPS.setDouble(3, record.getSalePrx());
            addConsPS.setDate(4, sqlDate);

            addConsPS.execute();


            System.out.println("Added a new consignor record into " + TABLE_NAME + " table !!");

            addConsPS.close();
            conn.close();


        } catch (SQLException sql) {
            sql.getCause();
            sql.printStackTrace();
        }


    }

    public static ResultSet setup() {
        ResultSet resultSet;
        try {

            Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORDS);
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //statement.executeUpdate("DROP TABLES IF EXISTS  records");

            String getAllData = " Select * From records ";
            resultSet = statement.executeQuery(getAllData);
            //System.out.println(resultSet);

        } catch (SQLException sql) {
            sql.getCause();
            sql.printStackTrace();
            return null;
        }
        return resultSet;
    }

    public void saleItems() {



    }

    public void createSaleTable() {
        final String SALETABLE_COL = "sales";
        final String SALEID_COL = "saleID";
        final String CONSIGNRID_COL = "consgrid";
        final String ITEMS_COL = "item";
        final String SALEPRX_COL = "salePrx";
        final String TOTAL_COL = "totalAmount";

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORDS)) {
            String addConsgmtSQl = "INSERT INTO " + SALETABLE_COL + "( saleID, consgrid, item, salePrx, totalAmount  )" + " VALUES( ?, ?)";

        } catch (SQLException sql){
            sql.getCause();
            sql.printStackTrace();
        }

    }

}
