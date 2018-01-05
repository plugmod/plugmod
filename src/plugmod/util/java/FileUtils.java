package plugmod.util.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

import com.google.common.io.Files;

public class FileUtils {
	
	private static Logger logger = Bukkit.getLogger();

	//=====================================================\\
	//                  	  Get	    	               \\
	//=====================================================\\
	
	/**
	 * Gets an inputstream from File file.
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static InputStream getFileInput(File file) throws FileNotFoundException {
		return new FileInputStream(file);
	}
	
	/**
	 * Gets an inputstream from a local file using its url.
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static InputStream getFileInput(URL file) throws IOException {
		URLConnection connection = file.openConnection();
        connection.setUseCaches(false);
		return connection.getInputStream();
	}
	
	/**
	 * Gets an outputstream from File file.
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static OutputStream getFileOutput(File file) throws FileNotFoundException {
		return new FileOutputStream(file);
	}
	
	/**
	 * Gets an outputstream from a local file using its url.
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static OutputStream getFileOutput(URL file) throws IOException {
		URLConnection connection = file.openConnection();
        connection.setUseCaches(false);
		return connection.getOutputStream();
	}
	
	//=====================================================\\
	//                  	  Copy	    	               \\
	//=====================================================\\
	
	/**
	 * Convenience method to copy local file from URL from to File to.
	 * @param from
	 * @param to
	 */
	public static void copyFile(URL from, File to) {
		try {
			copyFile(getFileInput(from), to);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Copy error - io error with inputfile!", e);
		}
	}
	
	/**
	 * Convenience method to copy file, from File from to file to.
	 * @param from
	 * @param to
	 */
	public static void copyFile(File from, File to) {
		try {
			copyFile(getFileInput(from), to);
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Copy error - inputfile not found!", e);
		}
	}
	
	/**
	 * Convenience method to read data from InputStream from to File to.
	 * @param from
	 * @param to
	 */
	public static void copyFile(InputStream from, File to) {
		try {
			Files.createParentDirs(to);
			copy(from, getFileOutput(to));
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Copy error - outputfile not found!", e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Copy error - failed to create parent dirs!", e);
		}
	}
	
	/**
	 * Reads data from InputStream in to OutputStream out using a byte buffer.
	 * @param in
	 * @param out
	 */
	public static void copy(InputStream in, OutputStream out) {
		try {
	        byte[] buf = new byte[1024];
	        int len;
	        while ((len = in.read(buf)) > 0) {
	            out.write(buf, 0, len);
	        }
	        out.close();
	        in.close();
		} catch(IOException e) {
			logger.log(Level.SEVERE, "Copy error - IO!", e);
		}
	}
	
}
