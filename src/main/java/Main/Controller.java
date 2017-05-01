package Main;

import Constractor.ConsgnmentModel;
import Database.RecordDB;
import GUI.ConsgnmentGUI;

import java.sql.ResultSet;

/**
 * Created by mash4 on 4/30/2017.
 */
public class Controller {
    static ConsgnmentGUI gui;
    static RecordDB recordDB;

    public static void main(String[] args) {
        Controller cntl = new Controller();
        ResultSet resultSet = RecordDB.setup();
        if(resultSet == null){
            System.exit(-1);
        }
        try{
            ConsgnmentModel cnModel = new ConsgnmentModel(resultSet);
             gui = new ConsgnmentGUI(cnModel);
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        cntl.startApp();
    }

    private void startApp() {
        recordDB = new RecordDB();
        //gui = new ConsgnmentGUI(t);
        //gui.diplayAllRecods_InTable(recordDB);

    }
}
