package com.uti.backup;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class BackupSource {

	public static void main(String[] args){

		//Source Path
		String srcPackagePath="E:\\Source Path\\";
		//Destination Path
		String destPackagePath="F:\\Destination Path\\";

		//Source & Destination Folder Map
		Map<String,String> srcDestPackageMap=new LinkedHashMap<String,String>();
		srcDestPackageMap.put("Source Folder", "Destination Folder");
		Calendar cal = Calendar.getInstance();
		Date result = cal.getTime();
		String currentDate=new SimpleDateFormat("yyyyMMddHHmm").format(result);

		try {
//			Convert files of Source parth to Zip file
			ZipUtils appZip = null;
			Iterator it = srcDestPackageMap.entrySet().iterator();
			while(it.hasNext()){

				Map.Entry mapEnt= (Map.Entry)it.next();
				System.out.println(destPackagePath+mapEnt.getValue()+"_"+currentDate+".zip Transfering...\n");
				
				appZip = new ZipUtils();
				appZip.generateFileList(srcPackagePath+mapEnt.getKey(),new File(srcPackagePath+mapEnt.getKey()));
				appZip.zipIt(srcPackagePath+mapEnt.getKey(),destPackagePath+mapEnt.getValue()+"_"+currentDate+".zip");
				appZip=null;

				System.out.println(destPackagePath+mapEnt.getValue()+"_"+currentDate+".zip Transfered\n");
			}


			System.out.println("All are Transfered");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
