package plugmod.util.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;

public class FileUtils {

	/**
	 * Copies file, from from to file to.
	 * @param from
	 * @param to
	 */
	public static void copyFile(File from, File to) {
		try {
			InputStream in = new FileInputStream(from);
			OutputStream out = new FileOutputStream(to);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
		} catch (FileNotFoundException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Copy error - file not found!", e);
		} catch (IOException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Copy error - IO!", e);
		}
	}
	
}
