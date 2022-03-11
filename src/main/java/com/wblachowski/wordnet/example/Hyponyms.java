package com.wblachowski.wordnet.example;

import java.util.stream.Collectors;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Word;
import net.sf.extjwnl.data.list.PointerTargetNode;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.dictionary.Dictionary;

public class Hyponyms {
    public static void main(String[] args) throws JWNLException {
        var dict = Dictionary.getDefaultResourceInstance();
        var indexWord = dict.lookupIndexWord(POS.NOUN, "dog");
        var mainSense = indexWord.getSenses().get(0);
        PointerTargetNodeList hyponyms = PointerUtils.getDirectHyponyms(mainSense);
        System.out.println(String.format("Definition of dog:%nt%s%n%nDirect hyponyms:", mainSense.getGloss()));

        for (PointerTargetNode node : hyponyms) {
            var synset = node.getSynset();
            var lemmas = synset.getWords().stream().map(Word::getLemma).collect(Collectors.joining(", "));
            var definition = synset.getGloss();
            System.out.println(String.format("• %s%n\t%s", lemmas, definition));
        }
    }
}
