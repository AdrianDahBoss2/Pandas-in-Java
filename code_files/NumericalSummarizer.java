import java.util.ArrayList;

public class NumericalSummarizer extends Summarizer {

    public NumericalSummarizer(ArrayList<Column> columns) {
        super(columns);
    }

    protected double getPopMean(String header) {

        NumericalColumn column = toNumerical(header);
        double sum = 0;

        for (int i = 0; i < column.getColumnSize(); i++) {
            sum += Double.parseDouble(column.getElement(i));
        }

        return sum / column.getColumnSize();
    }

    protected double getPopVar(String header) {

        NumericalColumn column = toNumerical(header);
        double mean = getPopMean(header);
        double sum = 0;

        for (int i = 0; i < column.getColumnSize(); i++) {
            sum += Math.pow(Double.parseDouble(column.getElement(i)) - mean, 2);
        }

        return sum / column.getColumnSize();
    }

    protected double getPopSTD(String header) {

        return Math.sqrt(getPopVar(header));
    }

    protected double getSampleMean(String header) {

        NumericalColumn column = toNumerical(header);
        double sum = 0;

        for (int i = 0; i < column.getColumnSize(); i++) {
            sum += Double.parseDouble(column.getElement(i));
        }

        return sum / column.getColumnSize();
    }

    protected double getSampleVar(String header) {

        NumericalColumn column = toNumerical(header);
        double mean = getSampleMean(header);
        double sum = 0;

        for (int i = 0; i < column.getColumnSize(); i++) {
            sum += Math.pow(Double.parseDouble(column.getElement(i)) - mean, 2);
        }

        return sum / (column.getColumnSize() - 1);
    }

    protected double getSampleSTD(String header) {

        return Math.sqrt(getSampleVar(header));
    }

    protected double getPopMedian(String header) {

        NumericalColumn column = toNumerical(header);
        column.getColumn().sort(null);

        if (column.getColumnSize() % 2 == 0) {
            return (Double.parseDouble(column.getElement((column.getColumnSize() / 2) - 1)) +
                    Double.parseDouble(column.getElement(column.getColumnSize() / 2) )) / 2.0;
        }
        else {
            return Double.parseDouble(column.getElement((column.getColumnSize() / 2) - 1));
        }
    }

    protected double getSampleMedian(String header) {

        NumericalColumn column = toNumerical(header);
        column.getColumn().sort(null);

        if (column.getColumnSize() % 2 == 0) {
            return (Double.parseDouble(column.getElement(column.getColumnSize() / 2)) +
                    Double.parseDouble(column.getElement((column.getColumnSize() / 2) + 1))) / 2;
        }
        else {
            return Double.parseDouble(column.getElement(column.getColumnSize() / 2));
        }
    }
}
