Direct Method Invocation (DMI) DataSource on Google Application Engine
-----------------------------------------
This example illustrates setting up a DataSource using JPA to process the requests and
running under Google Application Engine (GAE).

Using sample projects 
--------------------- 
Instructions are provided below for importing the sample project with Eclipse or
building it from the command line with Ant.

If you instead want to add SmartGWT Pro/EE to an existing project, see these
instructions:
 
   http://www.smartclient.com/smartgwtee/javadoc/com/smartgwt/client/docs/SgwtEESetup.html
 
For instructions for launching tools such as the Developer Console, Visual
Builder or DataSource Wizards, see the SmartGWT FAQ:
 
   http://forums.smartclient.com/showthread.php?t=8159


Build Prerequisites
-------------------
- Google Application Engine 1.3.4
  http://code.google.com/appengine/downloads.html#Google_App_Engine_SDK_for_Java

  Ensure APPENGINE_HOME to the location of your Google Application Engine SDK directory.

- Google Web Toolkit, 1.5.3 or later (2.0 or later recommended)
  http://code.google.com/webtoolkit/download.html

  Ensure GWT_HOME to the location of your Google Web Toolkit SDK directory.

- If building with ant:
  Apache Ant, 1.6.5 or later (1.7.1 or later recommended)
  http://ant.apache.org/

  A copy of Ant is included in the SmartGWT distribution under the
  'apache-ant-1.7.1' directory.

  Ensure ANT_HOME to the location of your Apache Ant directory, and the
  'ant' command is in your PATH.

- NOTE: Project uses only necessary jars to run this project. Edit build.xml target "libs"
        to include additional jars.

- If building with Eclipse:
  Google Eclipse Plugin (GEP)
  http://code.google.com/eclipse/docs/download.html

  The GEP is highly recommended if using Eclipse. Its use is assumed in the
  instructions below.

Build and deployment using ant
------------------------------
- 'ant hosted'
  Run in GWT Hosted Mode.

  
- 'ant'
  Compile for deployment.

  
- 'ant war'
  Compile for deployment and bundle into GAEDS.war file.

  Please read information on uploading your application to Google Apps:
  http://code.google.com/appengine/docs/java/tools/uploadinganapp.html#Uploading_the_App
  

Eclipse Configuration
---------------------
Eclipse and GEP may be configured to open this sample as follows:

- Set the Eclipse Classpath variable SGWTEE_HOME to point to the root directory
  of the SmartGWT EE distribution. This is configured through
    Windows: Window  -> Preferences -> Java -> Build Path -> Classpath Variables
    MacOS X: Eclipse -> Preferences -> Java -> Build Path -> Classpath Variables
  The included Eclipse project files load JARs from $SGWTEE_HOME/libs.
     
- Follow the instructions for  "Working with Existing Projects" with the GEP:
  http://code.google.com/eclipse/docs/existingprojects.html

- Finally, create a launch configuration and launch the app as described here:
  http://code.google.com/eclipse/docs/running_and_debugging.html


Database Configuration
----------------------
While running hosted mode you can browse local database:

http://localhost:8080/_ah/admin


Debugging for the first time
----------------------------
Due to a bug in GWT, the very first time you launch hosted mode, tools such as
the Admin Console will not work. Restart to correct this problem. Compiled mode
is not affected.
