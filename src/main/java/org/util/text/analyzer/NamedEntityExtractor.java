package org.util.text.analyzer;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sequences.DocumentReaderAndWriter;
import edu.stanford.nlp.util.Triple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PackALunch
 * Created by Sadra on 1/6/16.
 */
public class NamedEntityExtractor {

    public static final String LIB_STANFORD_7_CLASSES = "lib/stanford-ner-2015-12-09/classifiers/english.muc.7class.distsim.crf.ser.gz";

    AbstractSequenceClassifier<CoreLabel> classifier = null;
    public NamedEntityExtractor() {

        String serializedClassifier = LIB_STANFORD_7_CLASSES;
        try {
            this.classifier = CRFClassifier.getClassifier(serializedClassifier);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void extractNamedEntities (String content) {
        System.out.print(classifier.classifyToString(content, "slashTags", false));
    }

    public void extractNamedEntities (ArrayList<String> fileList) throws IOException, ClassNotFoundException {

        String fileContents = IOUtils.slurpFile(fileList.get(0));
        List<List<CoreLabel>> out = this.classifier.classify(fileContents);
//        for (List<CoreLabel> sentence : out) {
//            for (CoreLabel word : sentence) {
//                System.out.print(word.word() + '/' + word.get(CoreAnnotations.AnswerAnnotation.class) + ' ');
//            }
//            System.out.println();
//        }

        System.out.println("---");
        out = classifier.classifyFile(fileList.get(0));
        for (List<CoreLabel> sentence : out) {
            for (CoreLabel word : sentence) {
                System.out.print(word.word() + '/' + word.get(CoreAnnotations.AnswerAnnotation.class) + ' ');
            }
            System.out.println();
        }
//
//        System.out.println("---");
//        List<Triple<String, Integer, Integer>> list = classifier.classifyToCharacterOffsets(fileContents);
//        for (Triple<String, Integer, Integer> item : list) {
//            System.out.println(item.first() + ": " + fileContents.substring(item.second(), item.third()));
//        }
//        System.out.println("---");
//        System.out.println("Ten best entity labelings");
//        DocumentReaderAndWriter<CoreLabel> readerAndWriter = classifier.makePlainTextReaderAndWriter();
//        classifier.classifyAndWriteAnswersKBest(fileList.get(0), 10, readerAndWriter);
//
//        System.out.println("---");
//        System.out.println("Per-token marginalized probabilities");
//        classifier.printProbs(fileList.get(0), readerAndWriter);

    }
}
