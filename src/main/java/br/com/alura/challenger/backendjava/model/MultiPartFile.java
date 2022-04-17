package br.com.alura.challenger.backendjava.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultiPartFile {
    private File file;
    private String name;
    private String contentType;

    public byte[] getBytes() throws IOException{
        return Files.readAllBytes(file.toPath());
    }

    public Long size(){
        try {
            return Files.size(file.toPath());
        } catch (IOException e) {
           return null;
        }
    }
}
