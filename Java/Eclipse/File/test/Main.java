import java.io.File;
import file.FileData;

public class Main {

    public static void main(String[] args) {

        FileData fileData;
        if (args.length > 0) {
            fileData = new FileData(new File(args[0]));
        } else {
            fileData = new FileData(new File(".."));
        }
        System.out.println(fileData.getFileName());
        System.out.println(fileData.ownerPermissions());
        System.out.println(fileData.fileType());
    }
}

