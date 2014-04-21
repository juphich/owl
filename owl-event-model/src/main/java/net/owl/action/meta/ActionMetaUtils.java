package net.owl.action.meta;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.owl.action.Action;
import net.owl.event.EventType;

public class ActionMetaUtils {

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	public static ActionDefinition extractMeta(Action<?> action, Class<?> eventType) {
		net.owl.annotation.Action annotation 
				= action.getClass().getAnnotation(net.owl.annotation.Action.class);
		
		ActionDefinition actionMeta = new ActionDefinition(action.getClass().getName());
		actionMeta.setActionName(action.getName());
		
		for (String trigger : annotation.triggers()) {			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			EventType event = ((EventType) Enum.valueOf((Class<Enum>)eventType, trigger));
			actionMeta.addTrigger(event);
		}
		
		return actionMeta;
	}
	
	public static List<ActionProperty> extractProperties(Action<?> action) {
		List<ActionProperty> properties = new ArrayList<>();
		
		List<Field> fields = findFields(action.getClass());
		for (Field field : fields) {
			net.owl.annotation.ActionProperty annotation 
					= field.getAnnotation(net.owl.annotation.ActionProperty.class);
			
			String key = !annotation.value().isEmpty() ? annotation.value() :
							!annotation.name().isEmpty() ? annotation.name() : field.getName();
			String value = annotation.init().isEmpty() ? null : annotation.init();
			
			ActionProperty property = new ActionProperty(key);
			property.setPropertyValue(value);
			
			properties.add(property);
		}
		
		return properties;
	}
	
	private static List<Field> findFields(Class<?> type) {
		List<Field> fields = new ArrayList<>();
		
		Field[] fs = type.getDeclaredFields();
		
		for (Field field : fs) {
			if (field.isAnnotationPresent(net.owl.annotation.ActionProperty.class)) {
				fields.add(field);
			}
		}
		
		Class<?> superType = type.getSuperclass();
		if (superType != Object.class) {
			fields.addAll(findFields(superType));
		}
		
		return fields;
	}

	public static void mapping(Action<?> action, List<ActionProperty> properties) {
		Map<String, String> propMap = new HashMap<>();
		
		for (ActionProperty prop : properties) {
			propMap.put(prop.getPropertyName(), prop.getPropertyValue());
		}
		
		List<Field> fields = findFields(action.getClass());
		for (Field field : fields) {
			net.owl.annotation.ActionProperty ann = field.getAnnotation(net.owl.annotation.ActionProperty.class);
			String key = !ann.value().isEmpty() ? ann.value() :
							!ann.name().isEmpty() ? ann.name() : field.getName();

			try {
				Object value = convertValue(propMap.get(key), field.getClass());
				field.setAccessible(true);
				field.set(action, value);
				field.setAccessible(false);
			} catch (Exception e) {
				throw new IllegalStateException("fail to bind property...", e);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object convertValue(String valueText, Class<?> type) {
		if (type == Integer.class || type == int.class) {
			return Integer.parseInt(valueText);
		} else if (type == Long.class || type == long.class) {
			return Long.parseLong(valueText);
		} else if (type == Double.class || type == double.class) {
			return Double.parseDouble(valueText);
		} else if (type.isEnum()) {
			return Enum.valueOf((Class<Enum>)type, valueText);
		} else if (Date.class.isAssignableFrom(type)) {
			try {
				return format.parse(valueText);
			} catch (ParseException e) {
				throw new IllegalArgumentException(e);
			}
		}
		
		return valueText;
	}
	
	public static int keyHash(String id) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(id.getBytes("UTF-8"));
			
			byte[] hash = md.digest();
			return new BigInteger(hash).intValue() & 0xFF;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}		
	}
}
