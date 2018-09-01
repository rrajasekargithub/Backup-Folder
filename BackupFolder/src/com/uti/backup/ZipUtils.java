package com.uti.backup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    private List <String> fileList;
//    private static final String OUTPUT_ZIP_FILE = "Folder.zip";
//    private static final String SOURCE_FOLDER = "D:\\Reports"; // SourceFolder path

    public ZipUtils() {
        fileList = new ArrayList < String > ();
    }

    public static void main(String[] args) {
        ZipUtils appZip = new ZipUtils();
        
        
        String srcPath="D:\\Reports";
        String destPath="Folder.zip";
        appZip.generateFileList(srcPath,new File(srcPath));
        appZip.zipIt(srcPath,destPath);
    }

    public void zipIt(String srcPath,String destPath) {
        byte[] buffer = new byte[1024];
        String source = new File(srcPath).getName();
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(destPath);
            zos = new ZipOutputStream(fos);

//            System.out.println("Output to Zip : " + zipFile);
            FileInputStream in = null;

            for (String file: this.fileList) {
//                System.out.println("File Added : " + file);
                ZipEntry ze = new ZipEntry(source + File.separator + file);
                zos.putNextEntry(ze);
                try {
                    in = new FileInputStream(srcPath + File.separator + file);
                    int len;
                    while ((len = in .read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                } finally {
                    in.close();
                }
            }

            zos.closeEntry();
//            System.out.println("Folder successfully compressed");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void generateFileList(String srcPath, File node) {
        // add file only
        if (node.isFile()) {
            fileList.add(generateZipEntry(srcPath,node.toString()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename: subNote) {
                generateFileList(srcPath, new File(node, filename));
            }
        }
    }

    private String generateZipEntry(String srcPath, String file) {
        return file.substring(srcPath.length() + 1, file.length());
    }
}