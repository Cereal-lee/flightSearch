package com.tomato.demo.dao;

import org.springframework.stereotype.Component;

import com.tomato.demo.config.PropertiesConfig;
import com.tomato.demo.model.IATAsearch;

@Component
public class IataCsvReaderDao extends CSVDataReaderDao<IATAsearch> {
	private final PropertiesConfig propertiesConfig;

    public IataCsvReaderDao(PropertiesConfig propertiesConfig) {
        this.propertiesConfig = propertiesConfig;
    }
	
	@Override
	protected IATAsearch processLine(String[] data) {

		if (data.length < propertiesConfig.getIataLength()) {
			System.out.println("Skipping invalid line: " + String.join(",", data));
			return null;
		}

		IATAsearch s = new IATAsearch();
		s.setAirportNmEng(data[0]);
		s.setAirportNmKr(data[1]);
		s.setAirportIATA(data[2]);
		s.setAirportICAO(data[3]);
		s.setContinents(data[4]);
		s.setCountryNmEng(data[5]);
		s.setCountryNmKr(data[6]);
		s.setCityNmEng(data[7]);

		return s;
	}

}
