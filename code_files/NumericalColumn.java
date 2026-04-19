import java.util.ArrayList;

public class NumericalColumn implements Column {

    private final String header;
    private final ArrayList<Double> column;
    private final Boolean isNumerical;

    public NumericalColumn(ArrayList<Double> column, String header) {

        this.header = header;
        this.column = column;
        isNumerical = true;
    }

    protected ArrayList<Double> getColumn() {

        return column;
    }

    public int getColumnSize() {

        return column.size();
    }

    public String getHeader() {

        return header;
    }

    public String getElement(int index) {

        return String.valueOf(column.get(index));
    }

    public Boolean getIsNumerical() {

        return isNumerical;
    }
}
