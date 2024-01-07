package file;

import java.io.File;

public class FileData {

	private File file;

	public FileData(File file) {
		this.file = file;
	}

	public String getFileName() {
		return file.getName();
	}

	public String ownerPermissions() {
		String permissionsString = "";
		permissionsString += (file.canRead()) ? "r" : "-";
		permissionsString += (file.canWrite()) ? "w" : "-";
		permissionsString += (file.canExecute()) ? "x" : "-";
		return permissionsString;
	}

	public void setExecute(boolean exe) {
		file.setExecutable(exe);
	}

	public String fileType() {
		if (file.isDirectory()) {
			return "Directory";
		} else {
			return "File";
		}
	}
}