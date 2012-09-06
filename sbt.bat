set SCRIPT_DIR=%~dp0
java -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=600m -Xmx1000M -Xss2M -jar "%SCRIPT_DIR%\sbt-launcher.jar" %*
