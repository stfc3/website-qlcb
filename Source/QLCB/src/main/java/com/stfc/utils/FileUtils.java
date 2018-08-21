package com.stfc.utils;

import com.stfc.website.bean.ConfigEntity;
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
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Session;

public class FileUtils {

    private static final Logger logger = Logger.getLogger(FileUtils.class);

    private String filePathConfig;
    private String filePathOutput;
    private String key;
    private static String SAVE_PATH;
    private String IMAGE_FORDER = "images/";
    private String DOCUMENT_FORDER = "/documents/";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFilePathConfig() {
        return filePathConfig;
    }

    public void setFilePathConfig(String filePathConfig) {
        this.filePathConfig = filePathConfig;
    }

    public String getFilePathOutput() {
        return filePathOutput;
    }

    public void setFilePathOutput(String filePathOutput) {
        this.filePathOutput = filePathOutput;
    }

    public void saveFile(Media media, Session session, int isImage) {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        File vfile;
        String uploadPath;
        try {
            LoadProperties properties = new LoadProperties();
            ConfigEntity entity = properties.loadConfig();
            if (!StringUtils.valiString(filePathConfig)) {
                SAVE_PATH = entity.getPathUpload();
            } else {
                SAVE_PATH = filePathConfig;
            }
            final String vstrfileName = media.getName();
            if (isImage == 0) {
                uploadPath = session.getWebApp().getRealPath(IMAGE_FORDER);
            } else {
                uploadPath = session.getWebApp().getRealPath(DOCUMENT_FORDER);
            }
            File baseDir = new File(uploadPath);
            if (!baseDir.exists()) {
                baseDir.mkdirs();
            }

            if (isImage == 0) {
                vfile = new File(baseDir + File.separator + StringUtils.convertSlug(vstrfileName));
                filePathOutput = IMAGE_FORDER + StringUtils.convertSlug(vstrfileName);
            } else {
                vfile = new File(baseDir + File.separator + StringUtils.convertSlug(vstrfileName));
                filePathOutput = DOCUMENT_FORDER + StringUtils.convertSlug(vstrfileName);
            }
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

    /**
     * Ham tra ve danh sach ten file anh
     *
     * @param path: duong dan thu muc
     * @return
     */
    public static List<String> getImages(String path) {
        List<String> listFileExtend = new ArrayList<>();
        listFileExtend.add("jpg");
        listFileExtend.add("png");
        listFileExtend.add("gif");
        List<String> listFileImage = new ArrayList<>();
        File directoryImage = new File(path);
        File[] files = directoryImage.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    String[] tmp = file.getName().split("\\.");
                    String fileExtend = tmp[tmp.length - 1];
                    if (listFileExtend.contains(fileExtend.toLowerCase())) {
                        listFileImage.add(file.getName());
                    }
                }
            }
        }
        return listFileImage;
    }

    public static List<Object> findFile(String name, File file) {
        List<Object> listFile = new ArrayList<>();
        List<String> listFileExtend = new ArrayList<>();
        listFileExtend.add("jpg");
        listFileExtend.add("png");
        listFileExtend.add("gif");
        File[] list = file.listFiles();
        if (list != null) {
            for (File fil : list) {
                if (!StringUtils.valiString(name)) {
                    String[] tmp = file.getName().split("\\.");
                    String fileExtend = tmp[tmp.length - 1];
                    if (listFileExtend.contains(fileExtend.toLowerCase())) {
                        listFile.add(fil.getName());
                        continue;
                    }
                }
                if (fil.isDirectory()) {
                    findFile(name, fil);
                } else if (fil.getName().toLowerCase().contains(name.toLowerCase())) {
                    String[] tmp = file.getName().split("\\.");
                    String fileExtend = tmp[tmp.length - 1];
                    if (listFileExtend.contains(fileExtend.toLowerCase())) {
                        listFile.add(fil.getName());
                    }
                }
            }
        }
        return listFile;
    }
}
