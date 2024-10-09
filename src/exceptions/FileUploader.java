package exceptions;
import java.io.File;

class FileUploadException extends Exception {
    public FileUploadException(String message) {
        super(message);
    }
}

public class FileUploader {
    public static void main(String[] args) {
        String filePath = "path/to/resume.pdf"; // Example file path
        try {
            uploadFile(filePath);
            System.out.println("File uploaded successfully.");
        } catch (FileUploadException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void uploadFile(String filePath) throws FileUploadException {
        File file = new File(filePath);
        
        if (!file.exists()) {
            throw new FileUploadException("File not found: " + filePath);
        }

        if (file.length() > 2 * 1024 * 1024) { // 2 MB limit
            throw new FileUploadException("File size exceeded. Maximum allowed size is 2MB.");
        }

        String fileExtension = getFileExtension(file);
        if (!isSupportedFileFormat(fileExtension)) {
            throw new FileUploadException("File format not supported: " + fileExtension);
        }
        
        // Logic to upload the file
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    private static boolean isSupportedFileFormat(String extension) {
        return extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("doc") || extension.equalsIgnoreCase("docx");
    }
}
