package org.morozko.java.mod.codegen;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.w3c.dom.Document;

public class GenHelper {

	public List<String> strutsMessageLines = new ArrayList<String>();
	public Properties strutsMessageProps = new Properties();
	
	public NavMap navMap;
	
	public File dirOutput;
	public File dirOutputSrc;
	public File dirOutputJsp;
	public String basePackage;
	public String moduleName;
	public File dirTemplateSrc;
	public File dirTemplateJsp;

	// other config
	public String dtoPackage;
	public String dtoDir;
	public String facadePackage;
	public String facadeDir;	
	public String actionPackage;
	public String actionsDir;
	public String formPackage;
	public String formDir;
	public String jspDir;
	public String jspFormDir;
	
	public String appName;
		
	public Document navTree;
	
	public StringBuffer tilesBuffer = new StringBuffer();
	public StringBuffer struts1Buffer = new StringBuffer();
	public StringBuffer struts2Buffer = new StringBuffer();
	public StringBuffer navMap1Buffer = new StringBuffer();
	public StringBuffer navMap2Buffer = new StringBuffer();
	public StringBuffer facadeBuffer = new StringBuffer();

	public HashMap<String, BufferGen> facadeList = new HashMap<String, BufferGen>();
	
}