package engine.ui;

import engine.util.Tuple;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UIStyleParser
{
    public static Tuple<Double, Unit> parseNumberWithUnit(String input)
    {
        if(input == null || input.isEmpty())
            return null;

        // Define a regex pattern to match the number and unit
        Pattern pattern = Pattern.compile("^(\\d+)(px|%|vw|vh|vmin|vmax|w)$");
        Matcher matcher = pattern.matcher(input);

        // Check if the input string matches the pattern
        if (matcher.matches())
        {
            // Extract the number and unit from the matcher
            String number = matcher.group(1);
            String unit = matcher.group(2);
            return new Tuple<>(Double.parseDouble(number), Unit.parse(unit));
        }

        throw new IllegalStateException("Failed to parse number with unit: " + input);
    }





    public static ArrayList<TokenChunk> tokenize(String input)
    {
        int state = 0;

        var list = new ArrayList<TokenChunk>();
        StringBuilder builder = new StringBuilder();
        String property = "";
        String value;

        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);

            switch (state)
            {
                case 0:
                {
                    if(Character.isLetter(c) || Character.isDigit(c))
                    {
                        builder.append(c);
                        state++;
                    }
                    else if(!Character.isWhitespace(c))
                        throw new IllegalStateException("found invalid character while parsing: " + c + " in " + input);
                    break;
                }
                case 1:
                {
                    if(Character.isLetter(c) || c == '-')
                        builder.append(c);
                    else if(Character.isWhitespace(c) || c == ':')
                    {
                        property = builder.toString();
                        state += c == ':' ? 2 : 1;
                    }
                    else throw new IllegalStateException("found invalid character while parsing: " + c + " in " + input);
                    break;
                }
                case 2:
                {
                    if(c == ':')
                        state++;
                    else if(!Character.isWhitespace(c))
                        throw new IllegalStateException("found invalid character while parsing: " + c + " in " + input);
                    break;
                }
                case 3:
                {
                    if(Character.isLetter(c) || Character.isDigit(c))
                    {
                        builder = new StringBuilder();
                        builder.append(c);
                        state++;
                    }
                    else if(!Character.isWhitespace(c))
                        throw new IllegalStateException("found invalid character while parsing: " + c + " in " + input);
                    break;
                }
                case 4:
                {
                    if(c == ';')
                    {
                        value = builder.toString();
                        list.add(new TokenChunk(property, value));
                        builder = new StringBuilder();
                        state = 0;
                    }
                    else builder.append(c);
                    break;
                }
            }
        }

        if(state == 4)
            throw new IllegalStateException("Missing ';' in string: " + input);

        if(state != 0)
            throw new IllegalStateException("Finished parsing on non null state. Final state: " + state + ", for input: " + input);

        return list;
    }

    public record TokenChunk(String property, String value) { }
}
