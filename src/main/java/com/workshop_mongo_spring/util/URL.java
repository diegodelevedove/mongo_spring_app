package com.workshop_mongo_spring.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

	
	//Criamos essa classe para decodificar a string que vem ou que vai ser uma url
	//se voce colocar uma string no console do navegado e chamar o método encodeURIcomponent("bom dia")
	//o resultado será bom%20dia porque na url ela é encode
	//na url você usará algo mais ou menos assim title?=text é uma espécie de ternário
	
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return text;
		}
	}
	
	
	//Se ocorrer uma falha na conversão da data usar o valor defaultValue
	public static Date convertDate(String textDate,Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		try {
			return sdf.parse(textDate);
		} catch (ParseException e) {
			return defaultValue;
		}
	}
	
	
}
