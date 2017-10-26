package domain;

import exceptions.IllegalArgumentSubstractException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olha_Chuchuk on 9/14/2017.
 */
public class SubtractionTest {

    @Test(expected = IllegalArgumentSubstractException.class)
    public void subtractTablesWithDifferentStructure() {
        TableConfig config1 = new TableConfig();
        TableConfig config2 = new TableConfig();
        config1.addColumn("id", ColumnType.IntegerType, true);
        config2.addColumn("ID", ColumnType.IntegerType, true);
        Table table1 = new Table("Table1", config1);
        Table table2 = new Table("Table2", config2);
        table1.subtract(table2);
    }

    @Test
    public void subtractTablesWithSameStructureAndRows() {
        TableConfig config = new TableConfig();
        config.addColumn("id", ColumnType.IntegerType, true);
        config.addColumn("Char column", ColumnType.CharType, false);
        config.addColumn("Picture column", ColumnType.PictureType, false);
        Table table1 = new Table("Table1", config);
        Table table2 = new Table("Table2", config);
        table1.addRow(Arrays.asList("1", "a", "http://worldartsme.com/images/123-clipart-1.jpg"));
        table1.addRow(Arrays.asList("2", "b",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/NY-234.svg/750px-NY-234.svg.png"));
        table2.addRow(Arrays.asList("1", "a", "http://worldartsme.com/images/123-clipart-1.jpg"));
        table2.addRow(Arrays.asList("2", "b",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/NY-234.svg/750px-NY-234.svg.png"));

        Table table3 = table1.subtract(table2);

        assertEquals(0, table3.getRowsCount());
    }

    @Test
    public void subtractTablesNonEmptyResult() {
        TableConfig config = new TableConfig();
        config.addColumn("id", ColumnType.IntegerType, true);
        config.addColumn("Char column", ColumnType.CharType, false);
        config.addColumn("Picture column", ColumnType.PictureType, false);
        Table table1 = new Table("Table1", config);
        Table table2 = new Table("Table2", config);
        List<String> row1 = Arrays.asList("1", "a", "http://worldartsme.com/images/123-clipart-1.jpg");
        List<String> row2 = Arrays.asList("2", "b",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/NY-234.svg/750px-NY-234.svg.png");
        List<String> row3 = Arrays.asList("3", "c",
                "http://worldartsme.com/images/123-clipart-1.jpg");
        table1.addRow(row1);
        table1.addRow(row2);
        table2.addRow(row1);
        table2.addRow(row3);

        Table table3 = table1.subtract(table2);

        assertEquals(1, table3.getRowsCount());
        assertEquals(row2, table3.getRows().get(0));

        Table table4 = table2.subtract(table1);

        assertEquals(1, table4.getRowsCount());
        assertEquals(row3, table4.getRows().get(0));
    }
}