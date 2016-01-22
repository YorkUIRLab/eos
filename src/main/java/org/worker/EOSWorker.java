package org.worker;

import org.util.text.analyzer.NamedEntityExtractor;
import org.util.xml.XMLReader;

import javax.lang.model.element.Name;
import java.io.File;
import java.util.ArrayList;

/**
 * PackALunch
 * Created by Sadra on 12/30/15.
 */
public class EOSWorker {

    public static void main(String[] args) throws Exception {

        ArrayList<String> fileList = new ArrayList<String>();

        File[] files = new File("data/eos/").listFiles();

        for (File file : files) {
            if (file.isFile()) {
                fileList.add(file.getAbsolutePath());
            }
        }

        XMLReader.readXML(fileList);

        // reading files directly.
        // NamedEntityExtractor namedEntityExtractor = new NamedEntityExtractor();
        //namedEntityExtractor.extractNamedEntities(fileList);



    }



}
