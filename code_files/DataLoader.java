import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataLoader {

    private final File inputFile;
    private final ArrayList<Column> cleanData;
    private ArrayList<String> headers;

    public DataLoader(String inputFileName) {

        this.inputFile = new File(inputFileName);
        cleanData = new ArrayList<>();
        loadData();
    }
    
    private void loadData() {

        try {
            Scanner in = new Scanner(inputFile);

            headers = new ArrayList<>(List.of(in.nextLine().split(",", -1)));
            int headersSize = headers.size();
            for (int i = 0; i < headersSize; i++) {
                headers.set(i, headers.get(i).trim());
            }


            int unnamedColumnsCounter = 0;
            for (int i = 0; i < headersSize; i++) {
                if (headers.get(i).trim().isEmpty()) {
                    unnamedColumnsCounter++;
                    headers.set(i, "Unnamed_Column_" + unnamedColumnsCounter);
                }
            }

            List<String[]> rows = new ArrayList<>();
            while (in.hasNextLine()) {
                String[] row = in.nextLine().split(",", - 1);
                if (row.length > headersSize) {
                    throw new RuntimeException("Missing header, please add missing header and try again");
                }
                else {
                    rows.add(row);
                }
            }

            for (int i = 0; i < headersSize; i++) {
                ArrayList<String> dirtyColumn = new ArrayList<>();
                for (int j = 0; j < rows.size(); j++) {
                    try {
                        dirtyColumn.add(rows.get(j)[i]);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        dirtyColumn.add("N/A");
                    }
                }

                int doubleCounter = 0;
                int catergoricalCounter = 0;
                for (int k = 0; k < dirtyColumn.size(); k++) {
                    if (isDouble(dirtyColumn.get(k))) {
                        doubleCounter++;
                    }
                    else {
                        catergoricalCounter++;
                    }
                }
                if (doubleCounter > catergoricalCounter) {
                    NumericalColumnLoader numericalColumnLoader = new NumericalColumnLoader(dirtyColumn);
                    Column numericalColumn = new NumericalColumn(numericalColumnLoader.getColumnData(), headers.get(i));
                    cleanData.add(numericalColumn);
                }
                else {
                    CategoricalColumnLoader categoricalColumnLoader = new CategoricalColumnLoader(dirtyColumn);
                    Column categoricalColumn = new CategoricalColumn(categoricalColumnLoader.getColumnData(), headers.get(i));
                    cleanData.add(categoricalColumn);
                }
            }

            in.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        }
        catch (NoSuchElementException e) {
            throw new RuntimeException("File is empty", e);
        }
    }

    protected ArrayList<Column> getData() {
        return cleanData;
    }

    protected List<String> getHeaders() {
        return headers;
    }

    private boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
