package com.ddd.processor;

import java.io.File;
import java.io.IOException;

public interface Processable {
    void processFolder(File folder) throws IOException;
}
