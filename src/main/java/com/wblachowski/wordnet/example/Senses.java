package com.wblachowski.wordnet.example;

import java.util.stream.Collectors;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Word;
import net.sf.extjwnl.dictionary.Dictionary;

public class Senses {
    public static void main(String[] args) throws JWNLException {
        var dict = Dictionary.getDefaultResourceInstance();
        var indexWord = dict.lookupIndexWord(POS.NOUN, "car");
        System.out.println("Senses of car:");
        for (var sense : indexWord.getSenses()) {
            var lemmas = sense.getWords().stream().map(Word::getLemma).collect(Collectors.joining(", "));
            var definition = sense.getGloss();
            System.out.println(String.format("• %s%n\t%s", lemmas, definition));
        }
    }
}
