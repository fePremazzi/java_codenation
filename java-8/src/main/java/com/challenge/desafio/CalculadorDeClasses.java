package com.challenge.desafio;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

public class CalculadorDeClasses implements Calculavel {
	
	private BigDecimal getBigDecimal(Object obj, Field field) {
		
		try {
			if(field.getType() != BigDecimal.class) {
				return BigDecimal.ZERO;
			} else {
				field.setAccessible(true);
				return (BigDecimal) field.get(obj);
			}
		} catch (IllegalAccessException  e) {
			return BigDecimal.ZERO;
		}
		
		
	}
	
	private BigDecimal somaCamposAnotados(Object obj, Class<? extends Annotation> tipoAnnotation) {
		
		Class<?> cls = obj.getClass();
		Field[] flds = cls.getDeclaredFields();
		
		return Arrays.stream(flds)
				.filter(field -> field.isAnnotationPresent(tipoAnnotation))
				.map(field -> getBigDecimal(obj, field))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public BigDecimal somar(Object obj) {
		return somaCamposAnotados(obj, Somar.class);
	}

	@Override
	public BigDecimal subtrair(Object obj) {
		return somaCamposAnotados(obj, Subtrair.class);
	}

	@Override
	public BigDecimal totalizar(Object obj) {
		return somar(obj).subtract(subtrair(obj));
	}


}
