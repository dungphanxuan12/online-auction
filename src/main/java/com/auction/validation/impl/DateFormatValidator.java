package com.auction.validation.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import com.auction.validation.DateValidator;

public class DateFormatValidator implements DateValidator {

	private String dateFormat;

	public DateFormatValidator(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public boolean isValid(String date) {
		try {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(this.dateFormat)
					.withResolverStyle(ResolverStyle.STRICT);
			LocalDate.parse(date, dateTimeFormatter);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
