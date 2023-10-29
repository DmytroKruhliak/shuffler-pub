package com.ddd.processor;

import java.io.File;
import java.io.IOException;

public interface Processable {

    /*
    *
    * Interface invented for processing folders with music
    *  */

    void processFolder(File folder) throws IOException;
}
