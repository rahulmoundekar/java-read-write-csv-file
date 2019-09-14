package com.app.opencsv;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OpenCSVWriter {
	private static final String STRING_ARRAY_SAMPLE = "./string-array-sample.csv";
	private static final String OBJECT_LIST_SAMPLE = "./object-list-sample.csv";

	public static void main(String[] args)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		writeFromArrayOfStrings();
		writeFromListOfObjects();
	}

	private static void writeFromArrayOfStrings() throws IOException {
		try (Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));

				CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);) {
			String[] headerRecord = { "Name", "Email", "Phone", "Country" };
			csvWriter.writeNext(headerRecord);

			csvWriter.writeNext(
					new String[] { "Rahul Moundekar", "rahulmoundekar44@gmail.com", "+91-9158565604", "India" });
			csvWriter.writeNext(new String[] { "Omkar Raut", "omkarraut7777@gmail.com", "+91-9789993994", "India" });
		}
	}

	private static void writeFromListOfObjects()
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		try (Writer writer = Files.newBufferedWriter(Paths.get(OBJECT_LIST_SAMPLE));) {
			StatefulBeanToCsv<MyUser> beanToCsv = new StatefulBeanToCsvBuilder(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			List<MyUser> myUsers = new ArrayList<>();
			myUsers.add(new MyUser("Rahul Moundekar", "rahulmoundekar44@gmail.com", "+91-9158565604", "India"));
			myUsers.add(new MyUser("Omkar Raut", "omkarraut7777@gmail.com", "+91-9789993994", "India"));

			beanToCsv.write(myUsers);
		}
	}
}
