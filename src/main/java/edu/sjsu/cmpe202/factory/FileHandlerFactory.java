package edu.sjsu.cmpe202.factory;

import edu.sjsu.cmpe202.config.FileType;
import edu.sjsu.cmpe202.entity.file.CsvFileHandler;
import edu.sjsu.cmpe202.entity.file.FileHandler;
import edu.sjsu.cmpe202.entity.file.JsonFileHandler;
import edu.sjsu.cmpe202.entity.file.XmlFileHandler;

public class FileHandlerFactory {

    public static FileHandler getFileHandler(String extension) {
        switch(extension.toLowerCase()) {
            case FileType.XML : return new XmlFileHandler();
            case FileType.CSV : return new CsvFileHandler();
            default : return new JsonFileHandler();
        }
    }

}
