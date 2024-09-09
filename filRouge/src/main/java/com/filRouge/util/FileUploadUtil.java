package com.filRouge.util;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class FileUploadUtil {

    public static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    public static final String MEDIA_PATTERN = "([^\\s]+(\\.(?i)(jpg|jpeg|png|gif|bmp|tif|webp|svg))$)|.*\\.(mp4|avi|mov|mkv|webm)$";

    public static final String DATE_FORMAT = "yyyyMMddHHmmss";

    public static final String UPLOAD_DIR = "upload";
    public static final String FILE_NAME_FORMAT = "%s_%s";

    public static boolean isAllowedExtension(final String fileName, final String pattern) {
        final Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(fileName);
        boolean matches = matcher.matches();
        System.out.println("Filename: " + fileName + ", Pattern: " + pattern + ", Matches: " + matches);
        return matches;
    }

    public static void assertAllowed(MultipartFile file, String pattern) {
        final long size = file.getSize();
        if (size > MAX_FILE_SIZE) {
            throw new RuntimeException("Max file size is 5MB");
        }

        final String fileName = file.getOriginalFilename();
        System.out.println("Filename being checked: " + fileName);
        if (!isAllowedExtension(fileName, pattern)) {
            throw new RuntimeException("Only allowed file types are: " + pattern);
        }
    }

    public static String getFileName(final String name) {
        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        final String date = dateFormat.format(System.currentTimeMillis());
        return String.format(FILE_NAME_FORMAT, name, date);
    }
}