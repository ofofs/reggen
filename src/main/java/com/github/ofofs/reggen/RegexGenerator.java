package com.github.ofofs.reggen;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.State;
import dk.brics.automaton.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author kangyonggan
 * @since 7/25/18
 */
public final class RegexGenerator {

    private RegexGenerator() {
    }

    public static String generate(String regex) {
        regex = dealRegexp(regex);
        Automaton automaton = new RegExp(regex).toAutomaton();
        Random random = new Random();

        StringBuilder result = new StringBuilder();
        generate(result, automaton.getInitialState(), random);
        return result.toString();
    }

    public static List<String> generate(String regex, int count) {
        regex = dealRegexp(regex);
        Automaton automaton = new RegExp(regex).toAutomaton();
        Random random = new Random();

        List<String> result = new ArrayList<String>(count);
        for (int i = 0; i < count; i++) {
            StringBuilder sb = new StringBuilder();
            generate(sb, automaton.getInitialState(), random);
            result.add(sb.toString());
        }
        return result;
    }

    private static String dealRegexp(String regex) {
        regex = regex.replaceAll("^\\^", "");
        regex = regex.replaceAll("\\(\\^", "(");
        regex = regex.replaceAll("\\$$", "");
        regex = regex.replaceAll("\\$\\)", ")");

        regex = regex.replaceAll("\\*", "{0,}");
        regex = regex.replaceAll("\\+", "{1,}");
        regex = regex.replaceAll("\\?", "{0,1}");
        regex = regex.replaceAll("\\\\d", "[0-9]");
        regex = regex.replaceAll("\\\\D", "[^0-9]");
        regex = regex.replaceAll("\\\\s", "[ \t] ");
        regex = regex.replaceAll("\\\\S", "[A-Za-z0-9_]");
        regex = regex.replaceAll("\\\\t", "\t");
        regex = regex.replaceAll("\\\\w", "[A-Za-z0-9_]");
        regex = regex.replaceAll("\\\\w", "[^A-Za-z0-9_]");

        return regex;
    }

    private static void generate(StringBuilder builder, State state, Random random) {
        List<Transition> transitions = state.getSortedTransitions(false);
        if (transitions.size() == 0) {
            assert state.isAccept();
            return;
        }
        int max = state.isAccept() ? transitions.size() : transitions.size() - 1;
        int option = getRandomInt(0, max, random);
        if (state.isAccept() && option == 0) {
            return;
        }
        Transition transition = transitions.get(option - (state.isAccept() ? 1 : 0));
        appendChoice(builder, transition, random);
        generate(builder, transition.getDest(), random);
    }

    private static void appendChoice(StringBuilder builder, Transition transition, Random random) {
        char c = (char) getRandomInt(transition.getMin(), transition.getMax(), random);
        builder.append(c);
    }

    private static int getRandomInt(int min, int max, Random random) {
        int maxForRandom = max - min + 1;
        return random.nextInt(maxForRandom) + min;
    }
}
