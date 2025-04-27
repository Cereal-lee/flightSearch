package com.tomato.demo.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public abstract class CSVDataReaderDao<T> {
	@Getter
	protected List<T> dataList = new ArrayList<>();

	//Overrideで作成禁止（Generic）
	public List<T> readCSVFileData(String fileName) {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

			if (inputStream == null) {
				throw new IllegalArgumentException("File not found: " + fileName);
			}

			String readLine;
			while ((readLine = br.readLine()) != null) {
				String[] data = readLine.split(",");
				T item = processLine(data);
				if (item != null) {
					dataList.add(item);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	//Overrideで各csvファイルことに作成すること
	protected abstract T processLine(String[] data);
}
