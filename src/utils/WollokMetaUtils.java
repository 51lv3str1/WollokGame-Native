package utils;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

public class WollokMetaUtils {

	private static WollokMetaUtils instance = null;

	private WollokMetaUtils() {

	}

	public static WollokMetaUtils getInstance() {
		if (WollokMetaUtils.instance == null)
			WollokMetaUtils.instance = new WollokMetaUtils();

		return WollokMetaUtils.instance;
	}

	public Boolean hasMethodOrPropertyDefined(WollokObject wko, String methodName) {
		return wko.hasProperty(methodName) || StreamSupport.stream(wko.allMethods().spliterator(), false)
				.map(method -> method.getName()).collect(Collectors.toList()).contains(methodName);
	}

}