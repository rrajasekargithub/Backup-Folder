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

		String srcPackagePath="\\\\server\\E\\Java(BUP)\\Source\\";
		String destPackagePath="E:\\Rajasekar\\Backup\\Projects\\";
		
		//Package
		Map<String,String> srcDestPackageMap=new LinkedHashMap<String,String>();
		srcDestPackageMap.put("JenixBooks\\JENIXBOOKS_LIVE", "Jenix Books\\JENIXBOOKS_LIVE");
//		srcDestPackageMap.put("CORE\\CORE_LIVE", "Core\\CORE_LIVE");
		srcDestPackageMap.put("Tannery\\TANNERY_LIVE", "Tannery\\TANNERY_LIVE");
		srcDestPackageMap.put("TPCSFASHION\\FASHION_LIVE", "TPCS Fashion\\FASHION_LIVE");
		srcDestPackageMap.put("STELLAR\\STELLAR_LIVE", "STELLAR\\STELLAR_LIVE");
		
//		srcDestPackageMap.put("JenixCloud\\ZOOMMET_LIVE", "Zoommet\\ZOOMMET_LIVE");
//		srcDestPackageMap.put("VFC\\VFC_LIVE", "VFC\\VFC_LIVE");
		
		
		Calendar cal = Calendar.getInstance();
		Date result = cal.getTime();
		String currentDate=new SimpleDateFormat("yyyyMMddHHmm").format(result);
//		System.out.println("currentDate :"+currentDate);
		
		try {
			
			ZipUtils appZip = null;
			Iterator it = srcDestPackageMap.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry mapEnt= (Map.Entry)it.next();

				System.out.println(destPackagePath+mapEnt.getValue()+"_"+currentDate+".zip Transfering...\n");
//				FileUtils.copyDirectory(new File(srcPackagePath+mapEnt.getKey()), new File(destPackagePath+mapEnt.getValue()+"_"+currentDate));
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
