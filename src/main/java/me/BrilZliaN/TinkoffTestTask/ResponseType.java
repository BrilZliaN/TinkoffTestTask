package me.BrilZliaN.TinkoffTestTask;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public enum ResponseType {

	JSON, XML;

	public String processObject(Object object) {
		ObjectMapper mapper = null;
		switch (this) {
		case JSON:
			mapper = new ObjectMapper();
			break;
		case XML:
			mapper = new XmlMapper();
			break;
		default:
			break;
		}
		try {
			// timestamps to real date
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			mapper.setDateFormat(df);

			// jackson generates object as string
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

}
