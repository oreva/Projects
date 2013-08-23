package ua.org.oreva.studyNN.image;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/19/13
 * Time: 5:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageFile {
	private String sourceDirectoryPath;
	private String sourceFileName; //includes extension
	private String resultDirectoryPath;

	private String pathSeparator = "/";

	public ImageFile(String sourceDir,
	                 String sourceFileName,
	                 String resultDir) {
		this.sourceDirectoryPath = fixPathSeparators(sourceDir);
		this.sourceFileName = fixPathSeparators(sourceFileName);
		this.resultDirectoryPath = fixPathSeparators(resultDir);
	}

	public String fixPathSeparators(String path) {
		String result = path.replace("\\", pathSeparator);
		return result;
	}

	public String getSourceDirectoryPath() {
		return sourceDirectoryPath;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public String getResultDirectoryPath() {
		return resultDirectoryPath;
	}

	public String getAbsoluteSourcePath() {
		return sourceDirectoryPath + pathSeparator + sourceFileName;
	}

	/**
	 *
	 * @param size is the new file's size
	 * @return name for a new image file with bounds==size.
	 * Name is the old file's name with resolution in it, like "example_15x35"
	 */
	public String getResultFileNameWithoutExtension(Rectangle size) {
		String sourceNameWithoutExtension = getSourceFileNameWithoutExtension();
		String suffix = "_" + String.valueOf(size.width) + "x" + String.valueOf(size.height);
		return sourceNameWithoutExtension + suffix;
	}

	public String getResultFileNameWithExtension(Rectangle size, String extension) {
		return getResultFileNameWithoutExtension(size) + "." + extension;
	}

	public String getSourceFileNameWithoutExtension() {
		return sourceFileName.substring(0, sourceFileName.lastIndexOf("."));
	}

	public String getAbsoluteResultPath(Rectangle resultSize, String resultExtension) {
		String fName = getResultFileNameWithExtension(resultSize, resultExtension);
		return resultDirectoryPath + pathSeparator + fName;
	}
}
