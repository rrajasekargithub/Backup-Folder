package com.uti.backup;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class GenerateCompileCode {

	public static void main(String[] args) throws IOException{
		
		//src
		String srcPackagePath="F:\\java\\workspaceraj\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\FASHION_LIVE_JB2\\";
		String destPackagePath="F:\\java\\workspaceraj\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\FASHION_LIVE_JB2\\fashion\\";
		
		FileUtils.deleteDirectory(new File(destPackagePath));
		
		File directory = new File(destPackagePath);
		if (! directory.exists()){
			directory.mkdir();
		}
		
		//Package
		Map<String,String> srcDestPackageMap=new LinkedHashMap<String,String>();
		srcDestPackageMap.put("jsp", "jsp");
		srcDestPackageMap.put("js", "js");
		
		//js/core
		Map<String,String> srcDestJSFolderMap=new LinkedHashMap<String,String>();
		srcDestJSFolderMap.put("WEB-INF\\classes", "classes");
		
 
		try {

			System.out.println("\nTransfering jsp folder\n");
			Iterator it = srcDestPackageMap.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry mapEnt= (Map.Entry)it.next();

				FileUtils.copyDirectory(new File(srcPackagePath+mapEnt.getKey()), new File(destPackagePath+mapEnt.getValue()));
				System.out.println(destPackagePath+mapEnt.getValue());
			}
			
			System.out.println("\nTransfering class folder\n");
			it=null;
			it = srcDestJSFolderMap.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry mapEnt= (Map.Entry)it.next();

				FileUtils.copyDirectory(new File(srcPackagePath+mapEnt.getKey()), new File(destPackagePath+mapEnt.getValue()));
				System.out.println(destPackagePath+mapEnt.getValue());
			}
		    
		    System.out.println("\nTransfered");
		} catch (IOException e) {
		    e.printStackTrace();
		}
//		ZipUtils appZip = null;
//		appZip = new ZipUtils();
//		appZip.generateFileList(destPackagePath,new File(destPackagePath));
//		appZip.zipIt(destPackagePath,srcPackagePath+"fashion.zip");
//		appZip=null;
	 
		
	}
	
	
	 
}
