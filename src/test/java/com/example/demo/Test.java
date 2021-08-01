package com.example.demo;

import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.jar.JarFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.jar.JarEntry;

/**
 * @author luobo.cs@raycloud.com
 * @since 2021/7/30 9:19 下午
 */
public class Test {

    public static void main(String[] args) throws IOException {
        JarFile jarFile = new JarFile(new File("/Users/chenshun/open/demo/target/demo-0.0.1-SNAPSHOT.jar"));
        final Iterator<JarEntry> iterator = jarFile.iterator();
        while (iterator.hasNext()) {
            final JarEntry next = iterator.next();
            if (isSearchCandidate(next)) {
                System.out.println(next);
            }
        }
    }


    protected static boolean isSearchCandidate(JarEntry jarEntry) {
        return jarEntry.getName().startsWith("BOOT-INF/");
    }



}
