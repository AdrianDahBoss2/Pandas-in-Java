import java.util.ArrayList;

public class NumericalColumnLoader implements ColumnLoader {

    private final ArrayList<String> dirtyColumn;
    private ArrayList<Double> cleanColumn;

    public NumericalColumnLoader(ArrayList<String> dirtyColumn) {

        this.dirtyColumn = dirtyColumn;
        loadColumn();
    }

    public void loadColumn() {

        cleanColumn = new ArrayList<>();
        for (String obs : dirtyColumn) {
            if (isNotNumerical(obs)) {
                cleanColumn.add(0.0);
            } else {
                cleanColumn.add(cleanObs(obs));
            }
        }
    }

    protected ArrayList<Double> getColumnData() {

        return cleanColumn;
    }

    private double cleanObs(String value) {

        return Double.parseDouble(value.trim());
    }

    private Boolean isNotNumerical(String value) {

        try {
            cleanObs(value);
        }
        catch (NumberFormatException e) {
            return true;
        }

        return false;
    }
}
