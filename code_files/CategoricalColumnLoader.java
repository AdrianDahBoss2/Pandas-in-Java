import java.util.ArrayList;

public class CategoricalColumnLoader implements ColumnLoader {

    private final ArrayList<String> dirtyColumn;
    private ArrayList<String> cleanColumn;

    public CategoricalColumnLoader(ArrayList<String> dirtyColumn) {

        this.dirtyColumn = dirtyColumn;
        loadColumn();
    }

    public void loadColumn() {

        cleanColumn = new ArrayList<>();
        for (String obs : dirtyColumn) {
            if (isNotString(obs)) {
                cleanColumn.add("N/A");
            }
            else {
                cleanColumn.add(cleanObs(obs));
            }
        }
    }

    protected ArrayList<String> getColumnData() {

        return cleanColumn;
    }

    private String cleanObs(String value) {

        return value.trim().toLowerCase();
    }

    private Boolean isNotString(String value) {

        if (value == null) {
            return true;
        }
        else if (value.trim().isEmpty()) {
            return true;
        }
        return false;
    }
}