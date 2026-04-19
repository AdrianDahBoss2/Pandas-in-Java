import java.util.ArrayList;

public class Dataset {

    private final String inputFileName;
    private final DataLoader data;
    private final NumericalSummarizer numericalSummarizer;
    private final CategoricalSummarizer categoricalSummarizer;

    public Dataset(String inputFileName) {

        this.inputFileName = inputFileName;
        data = new DataLoader(inputFileName);
        numericalSummarizer = new NumericalSummarizer(data.getData());
        categoricalSummarizer = new CategoricalSummarizer(data.getData());
    }

    public void printData() {
        ArrayList<Column> cleanData = data.getData();
        for (int i = 0; i < data.getHeaders().size(); i++) {
            System.out.printf("%-25s", cleanData.get(i).getHeader());
        }
        System.out.println();
        for (int i = 0; i < cleanData.getFirst().getColumnSize(); i++) {
            for (int j = 0; j < data.getHeaders().size(); j++) {
                System.out.printf("%-25s", cleanData.get(j).getElement(i));
            }
            System.out.println();
        }
        System.out.println();
    }

    public double getPopMean(String header) {

        return numericalSummarizer.getPopMean(header);
    }

    public double getPopVar(String header) {

        return numericalSummarizer.getPopVar(header);
    }

    public double getPopSTD(String header) {

        return numericalSummarizer.getPopSTD(header);
    }

    public double getSampleMean(String header) {

        return numericalSummarizer.getSampleMean(header);
    }

    public double getSampleVar(String header) {

        return numericalSummarizer.getSampleVar(header);
    }

    public double getSampleSTD(String header) {

        return numericalSummarizer.getSampleSTD(header);
    }

    public double getPopMedian(String header) {

        return numericalSummarizer.getPopMedian(header);
    }

    public double getSampleMedian(String header) {

        return numericalSummarizer.getSampleMedian(header);
    }

    public ArrayList<String> getMode(String header) {

        return categoricalSummarizer.getMode(header);
    }

    public int getLength(String header) {

        return numericalSummarizer.getLength(header);
    }

    public ArrayList<Integer> getSize() {

        return numericalSummarizer.getSize();
    }
}