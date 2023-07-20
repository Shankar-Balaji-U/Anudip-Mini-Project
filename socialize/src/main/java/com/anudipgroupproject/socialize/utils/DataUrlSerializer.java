package com.anudipgroupproject.socialize.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Base64;

import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DataUrlSerializer extends JsonSerializer<File> {

    @Override
    public void serialize(File file, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    	String dataUrl = DataUrlSerializer.getDataURI(file);
    	jsonGenerator.writeString(dataUrl);
    }

    public static String getMimeType(File file) throws IOException {
        return URLConnection.guessContentTypeFromName(file.getName());
    }
    
    public static String getDataURI(File file) throws IOException {
    	String dataUrl;
    	try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] fileBytes = StreamUtils.copyToByteArray(inputStream);
            dataUrl = "data:" + DataUrlSerializer.getMimeType(file) + ";base64," + Base64.getEncoder().encodeToString(fileBytes);
        }
    	return dataUrl;
    }
}
