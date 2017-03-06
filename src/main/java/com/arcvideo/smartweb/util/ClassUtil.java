package com.arcvideo.smartweb.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.FileFilter;
import java.io.IOException;
import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by st@arcvideo.com on 2017/3/6.
 */
public class ClassUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className,boolean isInitialied){
         Class<?> cls;
        try{
            cls = Class.forName(className,isInitialied,getClassLoader());
        }
        catch (ClassNotFoundException ex){
            LOGGER.error("load class : " + className + " failed!",ex);
            throw new RuntimeException(ex);
        }
        return cls;
    }

    public static Set<Class<?>> getClasses(String packageName){
        Set<Class<?>> classes = new HashSet<Class<?>>();
        try {
            final Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while(urls.hasMoreElements()){
                URL url = urls.nextElement();
                if (url != null){
                    String protocol = url.getProtocol();
                    if(protocol.equals("file")){
                        String packagePath = url.getPath().replace("%20", " ");
                        addClass(classes,packagePath,packageName);
                    }
                    else if(protocol.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
                        if(jarURLConnection != null){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if(jarFile != null){
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while(jarEntries.hasMoreElements()){
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.endsWith(".class")){
                                        String className
                                                = jarEntryName.substring(0,jarEntryName.lastIndexOf("."))
                                                .replaceAll("/",".");
                                        doAddClass(classes, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    private static void doAddClass(Set<Class<?>> classes, String className) {
        Class<?> cls = loadClass(className,false);
        classes.add(cls);
    }

    private static void addClass(Set<Class<?>> classes, String packagePath, String packageName) {
        File pack = new File(packagePath);
        if(pack.isDirectory()){
            File[] files = pack.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return (file.isFile() && file.getName().endsWith(".class"));
                }
            });

            for(File f : files){
                String fileName = f.getName();
                if(f.isFile()){
                    String className = fileName.substring(0,fileName.lastIndexOf("."));
                    if(!StringUtil.isEmpty(packageName)){
                        className = packageName + "." + className;
                        doAddClass(classes,className);
                    }
                }
                else{
                    String subPackagePath = fileName;
                    if(!StringUtil.isEmpty(packageName)){
                        subPackagePath = packagePath + "/" + subPackagePath;
                    }
                    String subPackageName = fileName;
                    if(StringUtil.isEmpty(packageName)){
                        subPackageName = packageName + "." + subPackageName;
                    }
                    addClass(classes,subPackagePath,subPackageName);
                }
            }
        }
    }

}
