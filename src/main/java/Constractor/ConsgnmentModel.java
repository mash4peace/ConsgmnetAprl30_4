package Constractor;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mash4 on 4/30/2017.
 */
public class ConsgnmentModel extends AbstractTableModel {
    ResultSet resultSet;
    int rowCount;
    int columnCount;

    public ConsgnmentModel(ResultSet rs){
        this.resultSet = rs;
        try{
            int numberOfrow = 0;
            while(resultSet.next()){
                rowCount ++;

            }
            columnCount = resultSet.getMetaData().getColumnCount();
            System.out.println("Number of rows "+ numberOfrow + ", number of columns "+ columnCount);

        }catch (SQLException sql){

        }
    }


    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
            resultSet.absolute(rowIndex +1);
            Object obj = resultSet.getObject(columnIndex +1);
            return obj;
        }catch (SQLException sqle){
            return  sqle.toString();

        }

    }
}
