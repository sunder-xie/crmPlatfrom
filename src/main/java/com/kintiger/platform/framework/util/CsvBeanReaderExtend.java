package com.kintiger.platform.framework.util;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ITokenizer;
import org.supercsv.prefs.CsvPreference;

public class CsvBeanReaderExtend extends CsvBeanReader{
	// temporary storage of processed columns to be mapped to the bean
	private final List<Object> processedColumns = new ArrayList<Object>();
	public CsvBeanReaderExtend(final Reader reader, final CsvPreference preferences) {
		super(reader, preferences);
	}
	public CsvBeanReaderExtend(ITokenizer tokenizer, CsvPreference preferences) {
		super(tokenizer, preferences);
	}
	
	public List<String> readList( final String[] nameMapping,final CellProcessor... processors)throws IOException{
		if( processors == null ) {
			throw new NullPointerException("processors should not be null");
		}
		if( readRow() ) {
			// execute the processors then populate the bean
			executeProcessors(processedColumns, processors);
			return populateList(nameMapping);
		}
		
		return null; // EOF
		
	}
	
	private List<String> populateList(final String[] nameMapping){
		
		List<String> result = new ArrayList<String>();
		
		// map each column to its associated field on the bean
		for( int i = 0; i < nameMapping.length; i++ ) {
			
			final Object fieldValue = processedColumns.get(i);
			
			// don't call a set-method in the bean if there is no name mapping for the column or no result to store
			if( nameMapping[i] == null || fieldValue == null ) {
				continue;
			}
			
			result.add(fieldValue.toString());
			
		}
		return result;
	}
}
