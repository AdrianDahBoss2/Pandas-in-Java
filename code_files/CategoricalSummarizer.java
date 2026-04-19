import java.util.ArrayList;

public class CategoricalSummarizer extends Summarizer {

    public CategoricalSummarizer(ArrayList<Column> columns) {
        super(columns);
    }

    static class FrequencyCounter {

        String word;
        int count;

        public FrequencyCounter(String word, int count) {

            this.word = word;
            this.count = count;
        }

        private String getWord() {

            return word;
        }

        private int getCount() {

            return count;
        }
    }

    protected ArrayList<String> getMode(String header) {

        Column column = toCategorical(header);
        ArrayList<String> uniqueWords = new ArrayList<>();

        for (int i = 0; i < column.getColumnSize(); i++) {
            if (!uniqueWords.contains(column.getElement(i))) {
                uniqueWords.add(column.getElement(i));
            }
        }

        ArrayList<FrequencyCounter> frequencyCounters = new ArrayList<>();
        for (String uniqueWord : uniqueWords) {
            int counter = 0;
            for (int j = 0; j < column.getColumnSize(); j++) {
                if (uniqueWord.equals(column.getElement(j))) {
                    counter++;
                }
            }
            FrequencyCounter frequencyCounter = new FrequencyCounter(uniqueWord, counter);
            frequencyCounters.add(frequencyCounter);
        }

        ArrayList<String> modes = new ArrayList<>();
        int highestCounter = 0;
        for (FrequencyCounter frequencyCounter : frequencyCounters) {
            if (frequencyCounter.getCount() > highestCounter) {
                highestCounter = frequencyCounter.getCount();
                modes.clear();
                modes.add(frequencyCounter.getWord());
            }
            else if (frequencyCounter.getCount() == highestCounter) {
                modes.add(frequencyCounter.getWord());
            }
        }

        return modes;
    }
}
