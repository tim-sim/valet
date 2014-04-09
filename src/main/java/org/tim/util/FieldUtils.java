package org.tim.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tim
 */
public class FieldUtils {
    public static FieldParser simpleParser(String name) {
        return new SimpleFieldParser(name);
    }

    public static FieldParser numberParser(String name) {
        return new NumberFieldParser(name);
    }


    public static FieldParser dateParser(String name) {
        return new DateFieldParser(name);
    }

    public static FieldParser percentParser(String name) {
        return new PercentFieldParser(name);
    }

    public static interface FieldParser {
        String getName();

        Object getValue(String contentItem);
    }

    private static class SimpleFieldParser implements FieldParser {
        private String name;

        public SimpleFieldParser(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Object getValue(String contentItem) {
            return contentItem;
        }
    }

    private static class NumberFieldParser extends SimpleFieldParser {
        public NumberFieldParser(String name) {
            super(name);
        }

        @Override
        public Object getValue(String contentItem) {
            return Long.parseLong(contentItem);
        }
    }

    private static class RegexFieldParser extends SimpleFieldParser {
        private static Pattern pattern;

        public RegexFieldParser(String name, String regex) {
            super(name);
            this.pattern = Pattern.compile(regex);
        }

        @Override
        public Object getValue(String contentItem) {
            Matcher matcher = pattern.matcher(contentItem);

            return matcher.matches() ? matcher.group() : "";
        }
    }

    private static class PercentFieldParser extends RegexFieldParser {
        public PercentFieldParser(String name) {
            super(name, "(\\d+)%");
        }

        @Override
        public Long getValue(String contentItem) {
            return Long.parseLong((String) super.getValue(contentItem));
        }
    }

    private static class DateFieldParser extends SimpleFieldParser {
        private List<String> DATE_FORMATS = Arrays.asList(
                "dd.MM.yyyy"
        );

        public DateFieldParser(String name) {
            super(name);
        }

        @Override
        public Object getValue(String contentItem) {
            SimpleDateFormat format = new SimpleDateFormat();
            for (String pattern : DATE_FORMATS) {
                format.applyPattern(pattern);
                try {
                    return format.parse(contentItem);
                } catch (ParseException pe) {
                    // oops :)
                }
            }
            return null;
        }
    }
}
