package com.google.closure.template.unofficial.plugin;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.io.CharStreams;

/**
 * Used for compile Soy Templates to JS source.
 * 
 * @goal closure-library
 */
public class ClosureLibraryMojo extends AbstractMojo {
  /**
   * Source folder which will be traversed recursively for all JS scripts.
   * 
   * @parameter
   */
  private String[] roots;

  /**
   * Destination folder where single JS will be created.
   * 
   * @parameter
   */
  private String destDir;

  /**
   * Destination file name.
   * 
   * @parameter
   */
  private String destFileName;

  /**
   * Location of closure library.
   * 
   * @parameter
   */
  private String closureLibrary;

  /**
   * Ouput mode
   * 
   * @parameter
   */
  private String outputMode;

  /**
   * Namespace
   * 
   * @parameter
   */
  private String namespace;

  // Class variables.
  private String cwd;
  private String closureLibDir;
  private String destClosureJsDir;
  private String destGeneratedJsDir;

  public void execute() throws MojoExecutionException {
    cwd = FilenameUtils.normalizeNoEndSeparator(System.getProperty("user.dir")) + "/";
    // TODO(arjuns) : Add check that it is parent of closure directory.
    checkArgument(!Strings.isNullOrEmpty(closureLibrary), "closureLibrary is not set.");
    closureLibDir = getAbsoluteFilePath(closureLibrary);

    OutputMode oMode = OutputMode.getOutputModeByMode(outputMode);

    checkArgument(roots.length >= 2, "closureLibrary and root path needs to be present");
    for (String currRoot : roots) {
      checkArgument(currRoot.startsWith("/"), "All roots should be absolute path.");

      if (!getAbsoluteFilePath(currRoot).startsWith(closureLibDir)) {
        checkArgument(currRoot.startsWith(cwd), "all roots should be child of Current working dir.");
      }
    }

    checkArgument(!Strings.isNullOrEmpty(destDir), "destDir is not set.");

    if (oMode == OutputMode.List) {
      checkArgument(Strings.isNullOrEmpty(destFileName),
          "For OutputMode=List, destFileName should not bet set.");
    } else {
      checkArgument(!Strings.isNullOrEmpty(destFileName),
          "For OutputMode=Script/Compile, destFileName should be set.");
    }

    checkArgument(!Strings.isNullOrEmpty(namespace), "namespace is not set.");

    calculateDependency(oMode);
  }

  private void calculateDependency(OutputMode oMode) {
    String closureBuilderPath = FilenameUtils.normalizeNoEndSeparator(closureLibrary)
        + "/closure/bin/build/closurebuilder.py";
    File file = new File(closureBuilderPath);
    Preconditions.checkArgument(file.exists(), closureBuilderPath + " does not exist.");

    StringBuilder stringBuilder = new StringBuilder(closureBuilderPath);

    for (String currRoot : roots) {
      stringBuilder.append(" --root=").append(getAbsoluteFilePath(currRoot));
    }

    stringBuilder.append("  --namespace=").append(namespace).append(" --output_mode=")
        .append(oMode.get());

    switch (oMode) {
      case List:
        // We will get list of files needed. Copy them to destDir.
        String cmd = stringBuilder.toString();
        handleList(cmd);
        break;

      case Script:
        // All the required js will be concatenated into single file.
        stringBuilder.append(" --output_file=").append(destFileName);
        break;

      case Compiled:
        getLog().info("Compiled is not supported by this plugin. For that use compiler plugin");
        System.exit(1);
        break;
      default:
        throw new IllegalArgumentException("Unsupported OutputMode : " + oMode.get());
    }
  }

  private void handleList(String cmd) {

    getLog().info("Executing : " + cmd);

    try {
      Process child = Runtime.getRuntime().exec(cmd);
      child.waitFor();

      if (child.exitValue() != 0) {
        handleUnsuccessfulExit(child);
      }

      // Child successfully exited.
      String output = CharStreams.toString(new InputStreamReader(child.getInputStream()));
      getLog().info("Output of cmd = \n" + output);

      String destDirPath = getAbsoluteFilePath(destDir) + "/";
      String closureLibDir = FilenameUtils.normalizeNoEndSeparator(closureLibrary) + "/";
      String[] files = output.split("\n");

      for (String currFilePath : files) {
        String destAbsolutePath = getDestPath(currFilePath, destDirPath);
        getLog().info("Copying [" + currFilePath + "] to [" + destAbsolutePath + "]");
        FileUtils.copyFile(new File(currFilePath), new File(destAbsolutePath), true);
      }

      // By default, the list modes does not show the deps.js. This is because
      // The expected is that you will compile all the files into single file.
      // But here this is a hack to overcome that shortcoming.
      FileUtils.copyFile(new File(closureLibDir + "closure/goog/deps.js"), new File(destDirPath
          + "closure/goog/deps.js"), true);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private String getDestPath(String currFilePath, String destDirPath) {
    for (String currRoot : roots) {
      String normalizedRoot = getAbsoluteFilePath(currRoot);

      if (currFilePath.startsWith(normalizedRoot)) {
        File tempFile = new File(destDirPath, currFilePath.replaceFirst(normalizedRoot, ""));

        return tempFile.getAbsolutePath();
      }
    }

    throw new IllegalStateException("Code should not reach here. CurrFilePath = " + currFilePath);
  }

  private void handleUnsuccessfulExit(Process child) throws IOException {
    String errStream = CharStreams.toString(new InputStreamReader(child.getErrorStream()));
    getLog().info("Failed with following error message : \n" + errStream);
    System.exit(child.exitValue());
  }

  /**
   * This method insures that the the returned fileNames are absolute fileNames,
   * and dont end with "/"
   * 
   * @param providedName
   * @return
   */
  private String getAbsoluteFilePath(String providedName) {
    if (providedName.startsWith("/")) {
      // user provided an absolute path.
      return FilenameUtils.normalizeNoEndSeparator(providedName);
    }

    // Its a relative path.
    File currDir = new File(System.getProperty("user.dir"));
    File path = new File(currDir.getAbsolutePath(), providedName);

    return FilenameUtils.normalizeNoEndSeparator(path.getAbsolutePath());
  }

  private String getRelativeFilePath(String parent, String absoluteFileName) {
    String parentDirPath = parent + "/";

    return absoluteFileName.replaceFirst(parentDirPath, "");
  }

  private static enum OutputMode {
    List("list"), Script("script"), Compiled("compiled");

    private String mode;

    private OutputMode(String mode) {
      this.mode = mode;
    }

    public String get() {
      return mode;
    }

    public static OutputMode getOutputModeByMode(String mode) {
      Preconditions.checkArgument(!Strings.isNullOrEmpty(mode));
      for (OutputMode currMode : OutputMode.values()) {
        if (currMode.get().equals(mode)) {
          return currMode;
        }
      }

      throw new IllegalArgumentException(mode);
    }
  }
}