package org.qsoft.exreport.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	
	public final static void zip(String infile, String outfile) {

		// Create a buffer for reading the files
		byte[] buf = new byte[1024];

		try {
			File f = new File(infile);
			// Create the ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					outfile));

			// Compress the files
			FileInputStream in = new FileInputStream(infile);

			// Add ZIP entry to output stream.
			out.putNextEntry(new ZipEntry(f.getName()));

			// Transfer bytes from the file to the ZIP file
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}

			// Complete the entry
			out.closeEntry();
			in.close();

			// Complete the ZIP file
			out.close();
			
			f.delete();
		} catch (IOException e) {
		}

	}

}
