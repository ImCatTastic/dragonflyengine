package engine.ui;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BindingWriter
{
    private enum OperationToken
    {
        ADD("+"),
        SUBTRACT("-"),
        MULTIPLY("*"),
        DIVIDE("/");
        String value;
        OperationToken(String value)
        {
            this.value = value;
        }
    }
    private enum NextInput
    {
        NONE,
        BOOLEAN,
        NUMBER;
    }
    private enum FlagToken
    {
        MIN("min", NextInput.NUMBER),
        MAX("max", NextInput.NUMBER);
        final String value;
        final NextInput nextInput;
        FlagToken(String value, NextInput nextInput)
        {
            this.value = value;
            this.nextInput = nextInput;
        }
    }
    private enum State
    {
        NO_TARGET,
        NO_BINDING,
        NO_START,
        NO_VALUE,
        NO_OPERAND,
        NO_EQUALS,
    }
    private final HashMap<String, DoubleProperty> propertyTokens = new HashMap<>();
    public void addPropertyToken(String name, DoubleProperty value)
    {
        if(propertyTokens.containsKey(name))
            throw new IllegalArgumentException("property name already occupied!");

        propertyTokens.put(name, value);
    }
    public void define(String value)
    {
        Pattern pattern = Pattern.compile("([+\\-*/]|[a-zA-Z]\\w*|[0-9.]+|<<)\\s*");
        Matcher matcher = pattern.matcher(value);

        ArrayList<String> tokens = new ArrayList<>();
        while(matcher.find())
                if(matcher.group(1) != null)
                    tokens.add(matcher.group(1));

        if(tokens.isEmpty()) throw new IllegalStateException("Regex mismatch");

        boolean negate = false;
        State state = State.NO_TARGET;
        DoubleProperty target = null;
        DoubleProperty start = null;
        byte modifierType;
        double numericModifier = 0;
        DoubleProperty propertyModifier = null;
        DoubleBinding binding = null;
        OperationToken operationToken = null;

        FlagToken flag = null;

        for (String token : tokens)
        {
            switch (state)
            {
                case NO_TARGET ->
                {
                    var property = propertyTokens.get(token);
                    if (property == null)
                        throw new IllegalStateException("unknown property found for tokenType: NAME");
                    target = property;
                    state = State.NO_BINDING;
                }
                case NO_BINDING ->
                {
                    if (!token.equals("<<"))
                        throw new IllegalStateException("Expected Token: <<, received: " + token);
                    state = State.NO_START;
                }
                case NO_START ->
                {
                    if (token.equals(OperationToken.SUBTRACT.value))
                    {
                        if (negate)
                            throw new IllegalStateException("Token mismatch");
                        negate = true;
                        continue;
                    }
                    var property = propertyTokens.get(token);
                    if (property == null)
                        throw new IllegalStateException("unknown property found for tokenType: NAME | OP(-)");
                    start = property;
                    if (negate)
                        binding = start.negate();
                    state = State.NO_OPERAND;
                }
                case NO_VALUE ->
                {
                    if (isNumeric(token))
                    {
                        modifierType = 1;
                        numericModifier = Double.parseDouble(token);
                    }

                    else
                    {
                        var property = propertyTokens.get(token);
                        if (property == null)
                            throw new IllegalStateException("unknown property found for tokenType: NAME");
                        modifierType = 2;
                        propertyModifier = property;
                    }

                    if (operationToken != null)
                    {
                        if (modifierType == 1)
                        {
                            switch (operationToken)
                            {
                                case ADD -> binding = (binding == null) ? start.add(numericModifier) : binding.add(numericModifier);
                                case SUBTRACT -> binding = (binding == null) ? start.subtract(numericModifier) : binding.subtract(numericModifier);
                                case MULTIPLY -> binding = (binding == null) ? start.multiply(numericModifier) : binding.multiply(numericModifier);
                                case DIVIDE -> binding = (binding == null) ? start.divide(numericModifier) : binding.divide(numericModifier);
                            }
                        }
                        else
                        {
                            switch (operationToken)
                            {
                                case ADD -> binding = (binding == null) ? start.add(propertyModifier) : binding.add(propertyModifier);
                                case SUBTRACT -> binding = (binding == null) ? start.subtract(propertyModifier) : binding.subtract(propertyModifier);
                                case MULTIPLY -> binding = (binding == null) ? start.multiply(propertyModifier) : binding.multiply(propertyModifier);
                                case DIVIDE -> binding = (binding == null) ? start.divide(propertyModifier) : binding.divide(propertyModifier);
                            }
                        }
                    }
                    state = State.NO_OPERAND;
                }
                case NO_OPERAND ->
                {
                    if(isFlag(token))
                    {

                    }



                    if (!isOperand(token))
                        throw new IllegalStateException("illegal token for type: OPERAND");

                    operationToken = switch (token)
                    {
                        case "+" -> OperationToken.ADD;
                        case "-" -> OperationToken.SUBTRACT;
                        case "*" -> OperationToken.MULTIPLY;
                        case "/" -> OperationToken.DIVIDE;
                        default -> operationToken;
                    };

                    state = State.NO_VALUE;
                }
            }
        }

        if (state == State.NO_VALUE)
            throw new IllegalStateException("Translation must end on value");

        if(binding == null)
        {
            target.bind(start);
            return;
        }
        target.bind(binding);
    }



    private static boolean isFlag(String token)
    {
        return Arrays.stream(FlagToken.values()).anyMatch(value -> token.equals(value.value));
    }
    private static boolean isNumeric(String str)
    {
        try
        {
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }
    private static boolean isOperand(String token)
    {
        return Arrays.stream(OperationToken.values()).anyMatch(value -> token.equals(value.value));
    }
}
