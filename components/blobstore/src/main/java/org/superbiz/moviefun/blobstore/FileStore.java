package org.superbiz.moviefun.blobstore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;


public class FileStore implements BlobStore {

    @Override
    public void put(Blob blob) throws IOException {
        File targetFile = new File(blob.name);

        targetFile.getParentFile().mkdirs();
        Files.copy(blob.inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public Optional<Blob> get(String name) throws IOException {
        File file = new File(name);

        if (!file.exists()) {
            return Optional.empty();
        }

        return Optional.of(new Blob(
            name,
            new FileInputStream(file),
            Files.probeContentType(file.toPath())
        ));
    }
}
