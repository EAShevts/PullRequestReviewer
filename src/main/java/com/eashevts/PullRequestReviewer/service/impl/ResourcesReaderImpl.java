package com.eashevts.PullRequestReviewer.service.impl;

import com.eashevts.PullRequestReviewer.service.ResourcesReader;
import kotlin.Pair;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResourcesReaderImpl implements ResourcesReader {
    private final ResourceLoader resourceLoader;

    private final String allResourcePrefix = "/*";


    @Override
    public Map<String, String> readResources(String pattern) throws IOException {
        Resource[] pathMatchingResources = new PathMatchingResourcePatternResolver()
                .getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + pattern);
        if (pathMatchingResources.length == 0) {
            return Collections.emptyMap();
        }
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                .getResources(ResourceLoader.CLASSPATH_URL_PREFIX + pattern);
        return Arrays.stream(resources)
                .map(resource -> {
                    try {
                        InputStream inputStream = resource.getInputStream();
                        List<String> strings = IOUtils.readLines(inputStream, Charset.defaultCharset());
                        return new Pair<>(Objects.requireNonNull(resource.getFilename())
                                .split("\\.")[0], String.join("\n", strings));
                    } catch (IOException ex) {
                        log.error("Failed to read resource {}", resource.getFilename(), ex);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }
}
