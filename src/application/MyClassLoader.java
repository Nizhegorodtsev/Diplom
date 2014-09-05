package application;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MyClassLoader {

	/**
	 * Контейнер для хранения информации о загруженных классах
	 */
	private static HashMap<String, ClassInfo>	classMap	= new HashMap<String, ClassInfo>();

	public MyClassLoader() {
		classMap = new HashMap<String, ClassInfo>();
	}

	public static Class<?> getClassByName(String className) {
		ClassInfo info = classMap.get(className);
		if (info == null)
			return null;
		return info.getClass();
	}

	/**
	 * Рекурсивный обход указаной директории и сбор всех классов в список
	 * 
	 * @param directory
	 * @param pkgname
	 * @param classes
	 * @return
	 * @throws ClassNotFoundException
	 */
	private static ArrayList<Class<?>> getClassesByPackage(File directory, String pkgname, ArrayList<Class<?>> classes)
			throws ClassNotFoundException {
		String[] files = directory.list();
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i];
			String className = null;
			if (fileName.endsWith(".class")) {
				className = pkgname + '.' + fileName.substring(0, fileName.length() - ".class".length());
				Class<?> newClass = Class.forName(className);
				if (newClass.isInterface() || (newClass.getModifiers() & Modifier.ABSTRACT) > 0)
					continue;
				classes.add(newClass);
				continue;
			}
			File subdir = new File(directory, fileName);
			if (subdir.isDirectory()) {
				getClassesByPackage(subdir, pkgname + '.' + fileName, classes);
			}
		}
		return classes;
	}

	/**
	 * Возвращает список всех классок, которые содержатся в указаном пакете и во всех "подпакетах"
	 * 
	 * @param pkgname
	 *            название пакета с классами
	 * @return список классов
	 */
	public static ArrayList<Class<?>> getClassesByPackage(String pkgname) {
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		String relPath = pkgname.replace('.', '/');
		URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);
		if (resource == null) {
			throw new RuntimeException("Unexpected problem: No resource for " + relPath);
		}
		try {
			getClassesByPackage(new File(resource.getPath()), pkgname, classes);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return classes;
	}
}
