#jvm

-XX:+HeapDumpOnOutOfMemoryError
-XX:+DisableExplicitGC
@Xms@
@Xmx@
-XX:PermSize=@XXPermSize@
-XX:MaxPermSize=@XXMaxPermSize@
-Dcom.sun.management.jmxremote.port=@jmxremote.port@
-Dcom.sun.management.jmxremote.authenticate=@jmxremote.authenticate@
-Dcom.sun.management.jmxremote.ssl=@jmxremote.ssl@
@osgi.requiredJavaVersion@
-Dosgi.install.area=.
-DuseNaming=osgi
-Dorg.eclipse.equinox.simpleconfigurator.exclusiveInstallation=false
-Dcom.sap.core.process=ljs_node
-Declipse.ignoreApp=true
-Dosgi.noShutdown=true
-Dosgi.framework.activeThreadType=normal
-Dosgi.embedded.cleanupOnSave=true
-Dosgi.usesLimit=30
 
#main
-jar plugins/org.eclipse.equinox.launcher_1.1.0.v20100507.jar

#program
-console
