package project.inz.scheduleChecker.converter;

public interface Converter<T, S> {
    T convert(S source);
}
