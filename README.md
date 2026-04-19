# Pandas-in-Java

#### Description:

This is a simple implementation of the Pandas library in Java. It performs basic data cleaning and allows for elementary data analysis such as calculating the mean, median, and standard deviation. 

## Usage:

- Create a Dataset object and add the file path as a parameter
- Perform data analysis using such as methods such as .getPopMean or .getPopMode.

## CSV Format:

The program will raise an error if the CSV meets any of the following conditions:
- Is empty
- Missing the header row
- Missing a header (not to be confused with a blank header)

The program will detect blank headers and insert a placeholder column called "Unnamed_Column_(index)" 
Header1,__,Header3,Header4 --> Header1,Unnamed_Column_2,Header3,Header4

The program accepts CSVs with any number of columns or rows featuring numerical or categorical data making it a viable option for unpredictable, real-world data. 

## Cleaning and Normalization:

This program applies basic cleaning principles such as trimming whitespace and lowercasing all observations. 

Each column is treated as a numerical or categorical column. The program discovers a column's behavior by attemping to parse each observation. If more observations can be successfully parsed then cannot, the program is declared as numerical, otherwise it is treated as categorical. 

Numerical data is treated as doubles while categorical data is treated as strings.

Observations that are blank, missing, or some way corrupted as replaced with 0.0 if numerical and N/A if categorical. 

## Error Handling:

The program will throw an error under the follwing circumstances:
- The file is empty
- The header row is missing
- A header is missing
- The user attempts to perform a numerical summary on a categorical column
- The user attempts to perform a categorical summary on a numerical column
- The user attempts to performs a summary on a column that does not exist

## Testing

Some basic tests are written in the Main file covering some of the errors above. 
