import java.util.ArrayList;

public class CategoricalColumn implements Column {

    private final String header;
    private final ArrayList<String> column;
    private final Boolean isNumerical;

    public CategoricalColumn(ArrayList<String> column, String header) {

        this.header = header;
        this.column = column;
        isNumerical = false;
    }

    public int getColumnSize() {

        return column.size();
    }

    public String getHeader() {

        return header;
    }

    public String getElement(int index) {

        return column.get(index);
    }

    public Boolean getIsNumerical() {
        return isNumerical;
    }
}
