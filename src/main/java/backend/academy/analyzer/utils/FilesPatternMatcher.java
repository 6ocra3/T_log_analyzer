package backend.academy.analyzer.utils;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FilesPatternMatcher {
    public static List<String> searchWithPattern(Path rootDir, String pattern) throws IOException {
        List<String> matchesList = new ArrayList<>();
        FileSystem fs = FileSystems.getDefault();
        PathMatcher matcher = fs.getPathMatcher(pattern);

        FileVisitor<Path> matcherVisitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attribs) throws IOException {
                Path relativePath = rootDir.relativize(file);
                if (matcher.matches(relativePath)) {
                    matchesList.add(file.toAbsolutePath().toString());
                }
                return FileVisitResult.CONTINUE;
            }
        };

        Files.walkFileTree(rootDir, matcherVisitor);
        return matchesList;
    }
}
