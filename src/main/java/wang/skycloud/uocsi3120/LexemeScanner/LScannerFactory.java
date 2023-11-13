package wang.skycloud.uocsi3120.LexemeScanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;

public class LScannerFactory {
    /**
     * create a {@link LScanner} from a file. The file will be read as UTF-8.
     *
     * @param filename the file to be scanned
     * @return a {@link LScanner} that can scan the file
     */
    public static LScanner createScanner(String filename){
        try {
            return new NoviceLScanner(new BufferedReader(new FileReader(Paths.get(filename).toFile())));
        } catch (FileNotFoundException e) {
            LexemeScannerException.throwIOException(e);
        }
        throw new RuntimeException("unreachable");
    }

    /**
     * create a {@link LScanner} from a {@link BufferedReader}. The reader will not be closed.
     *
     * @param reader reader
     * @return a {@link LScanner} that can scan the reader
     */
    public static LScanner createScanner(BufferedReader reader){
        return new NoviceLScanner(reader);
    }
    public static ILexeme createLexemeUnit(String content,int line,LexemeType type){
        return new LexemeUnit(content,line,type);
    }

}
