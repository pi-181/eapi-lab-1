package com.chntu_team.eapi_lab_1.util;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class StringUtil {
    public static String indexOrder(@NotNull String input) {
        final List<Character> chars = new ArrayList<>(Arrays.asList(toCharacterArray(input)));
        final List<Character> orderedChars = new ArrayList<>(chars);
        System.out.println(orderedChars);
        orderedChars.sort(Character::compare);

        StringBuilder indexed = new StringBuilder();
        for (int i = 0; i < chars.size(); i++) {
            final Character character = chars.get(i);
            final int i1 = orderedChars.indexOf(character);
            indexed.append(i1 + 1);
            orderedChars.set(i1, null);
        }

        return indexed.toString();
    }

    public static Character[] toCharacterArray(String input) {
        final Character[] characters = new Character[input.length()];
        final char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++)
            characters[i] = chars[i];
        return characters;
    }
}
