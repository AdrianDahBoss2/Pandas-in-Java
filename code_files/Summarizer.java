import java.util.ArrayList;

public class Summarizer  {

    private final ArrayList<Column> columns;

    public Summarizer(ArrayList<Column> columns) {

        this.columns = columns;
    }

    protected int getColumnIndex(String header) {

        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).getHeader().equals(header)) {
                return i;
            }
        }
        throw new RuntimeException("Column does not exist");
    }

    protected CategoricalColumn toCategorical(String header) {

        Column column = columns.get(getColumnIndex(header));

        if (!column.getIsNumerical()) {
            return (CategoricalColumn) column;
        }
        else {
            throw new RuntimeException("Column is numerical and must be categorical");
        }
    }

    protected NumericalColumn toNumerical(String header) {

        Column column = columns.get(getColumnIndex(header));

        if (column.getIsNumerical()) {
            return (NumericalColumn) column;
        }
        else {
            throw new RuntimeException("Column is categorical and must be numerical");
        }
    }

     protected int getLength(String header) {

         int columnIndex = getColumnIndex(header);
         Column column = columns.get(columnIndex);

         return column.getColumnSize();
     }

     protected ArrayList<Integer> getSize() {

        ArrayList<Integer> size = new ArrayList<>();
        size.add(columns.size());
        size.add(columns.getFirst().getColumnSize());

        return size;
     }
}

