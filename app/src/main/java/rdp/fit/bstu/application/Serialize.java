package rdp.fit.bstu.application;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Serialize {
    // Метод проверки, существует ли файл и не является ли он каталогом
    public static boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory();
    }
    public static void Serialize(String path, DATA data) throws JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), data);
        return;
    }
    public static DATA Desirialise(String path) throws JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = DATA.class.getResourceAsStream(path);
        if(isFileExists(new File(this.getFilesDir,path))) {
            DATA data = DATA.getInstance();
            data = mapper.readValue(is, DATA.class);
            return data;
        }
        return DATA.getInstance();
    }
}
