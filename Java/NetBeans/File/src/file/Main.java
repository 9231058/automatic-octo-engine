/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.File;

/**
 *
 * @author Parham Alvani
 */
public class Main {

    /**
     * @param args the command line arguments
     */
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
