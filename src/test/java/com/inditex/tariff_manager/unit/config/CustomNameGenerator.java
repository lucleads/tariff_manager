package com.inditex.tariff_manager.unit.config;

import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGenerator;

@SuppressWarnings("unused")
public class CustomNameGenerator {

    public static class ReplaceLowerCamelCase implements DisplayNameGenerator {

        private static String camelCaseToHuman(final String input) {

            String[] words = splitByCharacterTypeCamelCase(input);
            List<String> sb = new ArrayList<>();
            for (String word : words) {
                if (!word.equals("A") && word.equals(word.toUpperCase())) {
                    sb.add(word);
                } else {
                    sb.add(word.toLowerCase());
                }
            }

            return capitalize(join(sb, SPACE));
        }

        @Override
        public String generateDisplayNameForClass(Class<?> testClass) {
            return testClass.getSimpleName();
        }

        @Override
        public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
            return nestedClass.getSimpleName();
        }

        @Override
        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            return camelCaseToHuman(testMethod.getName());
        }
    }

}