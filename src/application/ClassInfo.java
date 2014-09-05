package application;

public class ClassInfo {

	private String			className;

	private int				classType;

	private String			classPackage;

	private String			classPath;

	private String			classDescription;

	private Class<?>		classObj;

	public static final int	MODEL	= 1;

	public static final int	PROCESS	= 2;

	public static final int	RANDOM	= 3;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getClassType() {
		return classType;
	}

	public void setClassType(int classType) {
		this.classType = classType;
	}

	public String getClassPackage() {
		return classPackage;
	}

	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getClassDescription() {
		return classDescription;
	}

	public void setClassDescription(String classDescription) {
		this.classDescription = classDescription;
	}

	public Class<?> getClassObj() {
		return classObj;
	}

	public void setClassObj(Class<?> classObj) {
		this.classObj = classObj;
	}

}
