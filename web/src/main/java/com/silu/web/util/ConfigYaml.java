package com.silu.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * @author liaojl
 * @date 2018年5月19日下午9:28:47
 */
@SuppressWarnings("unchecked")
public class ConfigYaml {
	private static AppUtil appUtil = null;
	private static ConfigYaml configYaml = new ConfigYaml();
	static {
		try {
			appUtil = new Yaml().loadAs(
					new FileInputStream(new File(ConfigYaml.class.getResource("../../../../config.yml").getPath())),
					AppUtil.class);
			Class<ConfigYaml> temp = ConfigYaml.class;
			// configYaml = (ConfigYaml) temp.newInstance();
			Field[] fields = temp.getDeclaredFields();
			for (Field field : fields) {
				if (!field.getName().equalsIgnoreCase("appUtil") && !field.getName().equalsIgnoreCase("configYaml")) {
					field.setAccessible(true);
					String temp_name = field.getName();
					String[] names = temp_name.split("_");
					Object value = null;
					Map<Object, Object> tempMap = appUtil.getApp();
					// System.out.println(tempMap);
					for (int i = 0; i < names.length; i++) {
						if (i < names.length - 1) {
							tempMap = (Map<Object, Object>) tempMap.get(names[i].toLowerCase());
							// System.out.println(names[i]);
							// System.out.println(tempMap);
							continue;
						} else {
							// System.out.println(tempMap);
							// System.out.println(names[i]);

							value = tempMap.get(names[i].toLowerCase());
						}
					}
					// System.out.println(value);
					field.set(configYaml, value);

				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(configYaml);
	}

	private ConfigYaml() {
		// TODO Auto-generated constructor stub
	}

	public ConfigYaml(String path) throws Exception {
		// TODO Auto-generated constructor stub
		appUtil = new Yaml().loadAs(
				new FileInputStream(new File(ConfigYaml.class.getResource("../../../../config.yml").getPath())),
				AppUtil.class);
		Class<ConfigYaml> temp = ConfigYaml.class;
		// configYaml = (ConfigYaml) temp.newInstance();
		Field[] fields = temp.getDeclaredFields();
		for (Field field : fields) {
			if (!field.getName().equalsIgnoreCase("appUtil") && !field.getName().equalsIgnoreCase("configYaml")) {
				field.setAccessible(true);
				String temp_name = field.getName();
				String[] names = temp_name.split("_");
				Object value = null;
				Map<Object, Object> tempMap = appUtil.getApp();
				// System.out.println(tempMap);
				for (int i = 0; i < names.length; i++) {
					if (i < names.length - 1) {
						tempMap = (Map<Object, Object>) tempMap.get(names[i].toLowerCase());
						// System.out.println(names[i]);
						// System.out.println(tempMap);
						continue;
					} else {
						// System.out.println(tempMap);
						// System.out.println(names[i]);

						value = tempMap.get(names[i].toLowerCase());
					}
				}
				// System.out.println(value);
				field.set(configYaml, value);

			}
		}
	}

	// URL 地址
	private static String PHP_URL;
	private static String JAVA_URL;
	private static String NODEJS_URL;
	// 实例名称
	private static String PHP_NAME;
	private static String JAVA_NAME;
	private static String NODEJS_NAME;

	public static String getPHP_URL() {
		return PHP_URL;
	}

	public static void setPHP_URL(String pHP_URL) {
		PHP_URL = pHP_URL;
	}

	public static String getJAVA_URL() {
		return JAVA_URL;
	}

	public static void setJAVA_URL(String jAVA_URL) {
		JAVA_URL = jAVA_URL;
	}

	public static String getNODEJS_URL() {
		return NODEJS_URL;
	}

	public static void setNODEJS_URL(String nODEJS_URL) {
		NODEJS_URL = nODEJS_URL;
	}

	public static String getPHP_NAME() {
		return PHP_NAME;
	}

	public static void setPHP_NAME(String pHP_NAME) {
		PHP_NAME = pHP_NAME;
	}

	public static String getJAVA_NAME() {
		return JAVA_NAME;
	}

	public static void setJAVA_NAME(String jAVA_NAME) {
		JAVA_NAME = jAVA_NAME;
	}

	public static String getNODEJS_NAME() {
		return NODEJS_NAME;
	}

	public static void setNODEJS_NAME(String nODEJS_NAME) {
		NODEJS_NAME = nODEJS_NAME;
	}

}
