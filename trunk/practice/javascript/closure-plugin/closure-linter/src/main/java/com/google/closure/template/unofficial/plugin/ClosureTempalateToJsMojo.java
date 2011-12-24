package com.google.closure.template.unofficial.plugin;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * Used for compile Soy Templates to JS source.
 * 
 * @goal closure-linter
 */
public class ClosureTempalateToJsMojo extends AbstractMojo {
    /**
     * Source folder which will be traversed recursively for all JS for GJSLint Checks.
     * 
     * @parameter
     */
    private String srcDir;

    public void execute() throws MojoExecutionException {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(srcDir),
                "srcDir is not set.");

        String srcPath = getFileName(srcDir);

        StringBuilder stringBuilder = new StringBuilder();

        String[] extensions = { "js" };
        Collection<File> fileList = FileUtils.listFiles(new File(srcPath),
                extensions, true);

        for (File currFile : fileList) {
            gjsLint(currFile.getAbsolutePath());
        }

        getLog().info(stringBuilder.toString());
    }

    private void gjsLint(String srcDir) {
        String cmd = "gjslint -r --strict " + srcDir;

        getLog().info("Executing command = " + cmd);
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method insures that the the returned fileNames are absolute
     * fileNames, and dont end with "/"
     * 
     * @param providedName
     * @return
     */
    private String getFileName(String providedName) {
        if (providedName.startsWith("/")) {
            // user provided an absolute path.
            return FilenameUtils.normalizeNoEndSeparator(providedName);
        }

        // Its a relative path.
        File currDir = new File(System.getProperty("user.dir"));
        File path = new File(currDir.getAbsolutePath(), providedName);

        return FilenameUtils.normalize(path.getAbsolutePath());
    }
}
