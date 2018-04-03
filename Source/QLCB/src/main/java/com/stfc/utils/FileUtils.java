package com.stfc.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Messagebox;

public class FileUtils {

	private static final Logger logger = Logger.getLogger(FileUtils.class);
	private String fileName;
	private String filePath;
	private String saveFilePath;
	private String outFilePath;
	private String filePathOutput;
	private String fileNameOutput;
	private static final String DATE_FORMAT = "yyyy_MM_dd";
	private static final String DATE_FULL_FORMAT = "yyyyMMddHHmmss";
	private static String SAVE_PATH;
	private static String SAVE_PATH_REPORT;

	public String getOutFilePath() {
		return outFilePath;
	}

	public void setOutFilePath(String outFilePath) {
		this.outFilePath = outFilePath;
	}

	public String getSaveFilePath() {
		return saveFilePath;
	}

	public void setSaveFilePath(String saveFilePath) {
		this.saveFilePath = saveFilePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePathOutput() {
		return filePathOutput;
	}

	public void setFilePathOutput(String filePathOutput) {
		this.filePathOutput = filePathOutput;
	}

	public String getFileNameOutput() {
		return fileNameOutput;
	}

	public void setFileNameOutput(String fileNameOutput) {
		this.fileNameOutput = fileNameOutput;
	}

	public void saveFile(Media media) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		Date nowDate;
		DateFormat dateFormat;
		DateFormat dateFullFormat;
		File vfile;
		File vbaseDirReport = null;
		String userName;
		String uploadPath;
		String vstrReportPath;
		try {
			SAVE_PATH = Sessions.getCurrent().getWebApp().getRealPath("") + getSaveFilePath();
			final String vstrfileName = media.getName();

			nowDate = new Date();
			dateFormat = new SimpleDateFormat(DATE_FORMAT);
			dateFullFormat = new SimpleDateFormat(DATE_FULL_FORMAT);

			uploadPath = SAVE_PATH + "banner";
			File baseDir = new File(uploadPath);
			if (!baseDir.exists()) {
				baseDir.mkdirs();
			}
			vfile = new File(baseDir + "/" + vstrfileName);

			if (!media.isBinary()) {
				Reader reader = media.getReaderData();

				Writer writer = new FileWriter(vfile);
				copyCompletely(reader, writer);
			} else {
				InputStream fin = media.getStreamData();
				in = new BufferedInputStream(fin);
				OutputStream fout = new FileOutputStream(vfile);
				out = new BufferedOutputStream(fout);
				byte buffer[] = new byte[1024];
				int ch = in.read(buffer);
				while (ch != -1) {
					out.write(buffer, 0, ch);
					ch = in.read(buffer);
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}

				if (in != null) {
					in.close();
				}

			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}

	}
	/*
	 * Ham copy file
	 */

	private void copyCompletely(Reader input, Writer output) throws IOException {
		char[] buf = new char[8192];
		while (true) {
			int length = input.read(buf);
			if (length < 0) {
				break;
			}
			output.write(buf, 0, length);
		}

		try {
			input.close();
		} catch (IOException ignore) {
			logger.error(ignore.getMessage(), ignore);
		}
		try {
			output.close();
		} catch (IOException ignore) {
			logger.error(ignore.getMessage(), ignore);
		}
	}
}
