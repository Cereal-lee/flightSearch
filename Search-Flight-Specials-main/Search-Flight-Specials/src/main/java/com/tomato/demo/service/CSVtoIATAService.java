package com.tomato.demo.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomato.demo.dao.IataCsvReaderDao;
import com.tomato.demo.model.IATAsearch;
import com.tomato.demo.util.SearchDataFilter;

import jakarta.annotation.PostConstruct;

@Service
public class CSVtoIATAService {

	@Autowired
	private SearchDataFilter SearchDataFilter;

	// read Data from CSV file
	private final IataCsvReaderDao iataReader;

	public CSVtoIATAService(IataCsvReaderDao iataReader) {
		this.iataReader = iataReader;
	}

	private String fileName = "IATA_list.csv";

	private List<IATAsearch> cachedIATAData;

	@PostConstruct
	public void initialize() {
		iataReader.readCSVFileData(fileName);
		cachedIATAData = iataReader.getDataList();
	}

	public List<Map<String, String>> searchIATAByKeyword(String keyword) {
		return cachedIATAData.stream()
				.filter(iata -> iata.getAirportNmEng().toLowerCase().split("airport")[0].split("international")[0].contains(keyword.toLowerCase())
						|| iata.getAirportNmKr().split("공항")[0].split("국제")[0].contains(keyword)
						|| iata.getCityNmEng().toLowerCase().contains(keyword.toLowerCase()))
				.map(iata -> Map.of("label",
						iata.getAirportNmEng() + " - " + iata.getAirportNmKr() + " (" + iata.getCityNmEng() + ")",
						"value", iata.getAirportIATA()))
				.collect(Collectors.toList());
	}

	public void doProcess() {
		// write a business logic
		List<IATAsearch> IATAseachf = iataReader.getDataList();

		// Filter Search Data
		List<IATAsearch> IATAsearchData = SearchDataFilter.filterSearchData(IATAseachf);
	}

}
