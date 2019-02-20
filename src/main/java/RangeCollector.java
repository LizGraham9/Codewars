/*

Given unordered list of numbers + a range, return all numbers (list duplicates separately) within range.
Optimize for quick lookup

given: {32, 11, 2, 5, 7, 99, 22, 13, -1, 2, 16, 45, 44, 44, 5} & {3, 20}

return: {5, 5, 7, 13, 16}


Needs to be sorted. each node needs to know who is next. need to have random access.



 */

import java.util.*;

public class RangeCollector {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<Integer> input = new ArrayList<>(Arrays.asList(32, 11, 2, 5, 7, 99, 22, 13, -1, 2, 16, 45, 44, 44, 5));
        RangeCollector rangeCollector = new RangeCollector();
        input.forEach(rangeCollector::add);
        System.out.println(rangeCollector.numbers);

        List<Integer> valuesInRange = rangeCollector.rangeSearch(3, 20);
        System.out.println(valuesInRange);

        System.out.println(System.currentTimeMillis() - startTime);
    }

    private SortedMap<Integer, Integer> numbers;

    public RangeCollector() {
        numbers = new TreeMap<>();
    }

    public void add(int number) {
        numbers.put(number, numbers.getOrDefault(number, 0) + 1);
    }

    public List<Integer> rangeSearch(int lower, int upper) {
        SortedMap<Integer, Integer> subMap = numbers.subMap(lower, upper + 1);
        List<Integer> valuesInRange = new ArrayList<>();

        subMap.forEach((value, count) -> {
                    for (int i = 0; i < count; i++) {
                        valuesInRange.add(value);
                    }
                }
        );

        return valuesInRange;
    }
}
