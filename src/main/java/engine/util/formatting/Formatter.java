package engine.util.formatting;

import engine.util.Tuple;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Formatter<E extends Enum<E> & ExtractableComponent<C>, G, T, C>
{
    private final Map<E, G> components;

    @SuppressWarnings("unchecked")
    public Formatter(Object... components)
    {
        var types = getClassTypes();
        Class<E> eClass = types.first;
        Class<G> gClass = types.second;

        var map = new LinkedHashMap<E, G>();
        E tempComponent = null;
        int i = 0;
        for (Object component : components)
        {
            if(component.getClass() == eClass)
            {
                if(tempComponent != null)
                    map.put(tempComponent, getDefaultData());

                tempComponent = (E) component;
            }

            else if(component.getClass() == gClass)
            {
                if(tempComponent == null)
                    throw new IllegalArgumentException("Invalid parameter provided (at index): " + i);

                map.put(tempComponent, (G) component);
                tempComponent = null;
            }

            else
                throw new IllegalArgumentException("Invalid parameter provided (at index): " + i);
            i++;
        }

        if(tempComponent != null)
            map.put(tempComponent, getDefaultData());

        this.components = Collections.unmodifiableMap(map);
    }
    protected abstract Tuple<Class<E>, Class<G>> getClassTypes();
    protected abstract G getDefaultData();
    protected abstract C convertData(T input, G data);
    public final String format(T data)
    {
        return components.entrySet().stream().map(entry -> mappingFunction(convertData(data, entry.getValue())).apply(entry.getKey()))
                .collect(Collectors.joining());
    }
    protected Function<E, String> mappingFunction(C data)
    {
        return component -> component.extract(data);
    }
}
