package com.example.demo;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author luobo.cs@raycloud.com
 * @since 2021/7/30 11:06 下午
 */
//@Component
public class View implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
//        final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
//        if (contextClassLoader instanceof URLClassLoader){
//            URLClassLoader urlClassLoader = (URLClassLoader) contextClassLoader;
//            final Field ucp = urlClassLoader.getClass().getDeclaredField("ucp");
//            ReflectionUtils.makeAccessible(ucp);
//            final URLClassPath urlClassPath = (URLClassPath) ucp.get(urlClassLoader);
//            final URL[] urLs = urlClassPath.getURLs();
//        }
        ff();
    }

    private void ff() {
        final ClassLoader classLoader = View.class.getClassLoader();
        final URL resource = classLoader.getResource("META-INF/services/com.example.demo.test");
        System.out.println(resource);
    }

    public static void load() throws IOException {
        {
//            final File file = ResourceUtils.getFile("classpath:META-INF/services/com.example.demo.Test");
            final File file = ResourceUtils.getFile("/Users/chenshun/open/demo/src/main/resources/META-INF/services/com.example.demo.Test");
            final InputStream resourceAsStream = file.toURI().toURL().openStream();
            byte[] buffers = new byte[resourceAsStream.available()];
            IOUtils.readFully(resourceAsStream, buffers);
            System.out.println(new String(buffers, StandardCharsets.UTF_8));
            System.out.println("resourceAsStream is not null");
            resourceAsStream.close();
        }
        {

            final ClassPathResource classPathResource = new ClassPathResource("classpath:META-INF/services/com.example.demo.Test",
                    View.class.getClassLoader());
            final InputStream inputStream = classPathResource.getInputStream();

            byte[] buffers = new byte[inputStream.available()];
            IOUtils.readFully(inputStream, buffers);
            System.out.println(new String(buffers, StandardCharsets.UTF_8));
            inputStream.close();
        }
    }

    private static PathMatcher pathMatcher = new AntPathMatcher();

    public static void main(String[] args) throws IOException {
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        final Resource[] resources = pathMatchingResourcePatternResolver.getResources("classpath:/*.MF");
        for (Resource resource : resources) {
            System.out.println(resource.getURL().toString());
        }
    }
}
