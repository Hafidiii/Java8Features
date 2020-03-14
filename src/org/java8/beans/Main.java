package org.java8.beans;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@FunctionalInterface
interface Test {
    public void affich();

    default void affich(String str){
        System.out.println(str);
    }

}

public class Main {

    public static String dateFormatter(String dateStr) throws ParseException {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate date = LocalDate.parse(dateStr, formatter1);
        return formatter2.format(date);
    }

    public void streamsAPIs() {
        List<String> names = Arrays.asList("Ali","Hafid","Ahmed","Mounir");
        //1
        Stream.of(names).count();

        //2.
        System.out.println("---------------------");
        names.stream().count();

        //3.
        System.out.println("---------------------");
        Stream.of("Ali","Hafid","Ahmed","Mounir")
                .filter(x -> x.startsWith("A"))
                //     .map(x -> x.toUpperCase())
                .map(String::toUpperCase)
                .sorted()
                .forEach(x -> System.out.println(x));

        //4.
        System.out.println("---------------------");
        IntStream
                .range(1,10)
                .skip(2)
                .map(x -> x * x)
                .filter(x -> x%2 == 0)
                .forEach(x -> System.out.println(x));
        //5.
        System.out.println("---------------------");
        Arrays.stream(new int[]{2,3,8,1,9})
                .map(x -> x*x)
                .average()
                .ifPresent(System.out::println);


        //6.
        System.out.println("---------------------");
        IntSummaryStatistics summary = Arrays.stream(new int[]{2,3,8,1,9})
                .summaryStatistics();

        System.out.println(summary);

        //7.
        System.out.println("---------------------");
        Arrays.stream(new int[]{2,3,8,1,9})
                .max()
                .ifPresent(System.out::println);

        //8. reduce : its a custom reduction operation on the stream
        System.out.println("---------------- REDUCE ---------------");
        Arrays.stream(new int[]{2,3,8})
                .reduce((x,y) -> x+y ).ifPresent(System.out::println);


    }





    public static void main(String[] args) {

        List<String> names = Arrays.asList("Ahmed","Hafid","Mounir","Younes");
        Test test1 = () ->  System.out.println("Test1 is called...");

        test1.affich();

        //FOREACH
        System.out.println("---------- FOR EACH -----------");
        names.forEach(item -> {
            item.toUpperCase();
            System.out.println(item);
        });


        //Custom Consumer
        System.out.println("---------- Custom Consumer LIST-----------");
        Consumer<String> myConsumer =  s -> System.out.println(s.toUpperCase());
        BiConsumer<String, Object> biConsumer = (k, v) -> System.out.println(v.toString().toUpperCase());
        names.forEach(myConsumer);


        //FOREACH FOR MAP -- BIConsumer
        System.out.println("---------- FOREACH FOR MAP -- BIConsumer-----------");
        Map<String, String> map = new HashMap<>();

        map.put("A", "Alex");
        map.put("B", "Brian");
        map.put("C", "Charles");

        map.forEach((k,v)-> System.out.println("Key:" + k + ", Value:" + v));
        map.forEach(biConsumer);

        //Stream Boxing : we cant convert a stream of primitive to a collection without boxing it
        List<Integer> ints = IntStream.of(1,4,8,2,7)
                .boxed()
                .collect(Collectors.toList());

    }
}
