package com.jhsung.entity.columns;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserColumn {
	ID("id"), EMAIL("email"), USER_NAME("user-name"), VERIFIED("verified"), CREATED("created"), UPDATED("updated");

	private final String columnName;

	// Map<String columnName, UserColumn userColumn> 생성
	private static final Map<String, UserColumn> eMap = Maps.newHashMap();

	static {
		for (final UserColumn item : UserColumn.values()) {
			eMap.put(item.getColumnName(), item);
		}
	}

	// eMap 에서 columnName 으로 UserColumn 구하기
	public static UserColumn lookup(final String columnName) {
		final UserColumn userColumn = eMap.get(columnName);
		if (userColumn != null) {
			return userColumn;
		}
		// TODO ExceptionHandler 처리
		throw new IllegalArgumentException(String.format("enumType<%s> is not exist.", columnName));
	}
}
