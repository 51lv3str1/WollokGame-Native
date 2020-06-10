package utils;

import java.util.Iterator;

import org.uqbar.project.wollok.interpreter.core.WollokObject;
import org.uqbar.project.wollok.wollokDsl.WMethodDeclaration;

public class WollokMetaUtils {

	/**
	 * Singleton class instance.
	 */
	private static WollokMetaUtils instance = null;

	/** Singleton private constructor*/
	private WollokMetaUtils() {
		
	}

	/**
	 * Gets the current Class instance.
	 */
	public static WollokMetaUtils getInstance() {
		if (WollokMetaUtils.instance == null)
			WollokMetaUtils.instance = new WollokMetaUtils();

		return WollokMetaUtils.instance;
	}

	/**
	 * Checks if a WollokObject has a method.
	 * @param wko - the wollok object.
	 * @param name - the method name.
	 */
	public Boolean hasMethod(WollokObject wko, String name, Integer arity) {
		Boolean hasMethod = false;
		for (Iterator<WMethodDeclaration> i = wko.allMethods().iterator(); i.hasNext();) {
			if (i.hasNext()) {
				final WMethodDeclaration method = i.next();
				hasMethod = hasMethod || (method.getName().equals(name) && arity.equals(method.getParameters().size()));
			}
		}
		return hasMethod;
	}
	
	/**
	 * Checks if a WollokObject has a property.
	 * @param wko - the wollok object.
	 * @param name - the property name.
	 */
	public Boolean hasProperty(WollokObject wko, String name) {
		return wko.hasProperty(name);
	}

}