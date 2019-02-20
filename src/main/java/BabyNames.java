import java.util.*;

class ListBabyNames {
    public static void main(String[] args) {

        //Test data for parts 1 & 2:
        Map<String, List<String>> namesBySourceList = new HashMap<>();
        namesBySourceList.put("list1", Arrays.asList("Sophia", "Emma", "Ollie", "Olivia"));
        namesBySourceList.put("list2", Arrays.asList("Jackson", "Aiden", "Lucas", "Sophia"));
        namesBySourceList.put("list3", Arrays.asList("Sophia", "Emma", "Olivia"));
        namesBySourceList.put("list4", Arrays.asList("Ollie", "Edward", "Sophia","Emmit", "Olive"));

        //Part 1 test:
        System.out.println("Part 1:");
        List<ListWithRank> listsWithRank = compileListsByRank(namesBySourceList, "Sophia");
        listsWithRank.forEach(System.out::println);

        //Part 2 test:
        System.out.println("\nPart 2:");
        Map<String, List<ListWithRank>> namesWithRanks = compileListsByNamePrefix(namesBySourceList, "Ol");

        namesWithRanks.keySet().forEach(x ->
                { System.out.println(x);
                    System.out.println(namesWithRanks.get(x));
                }
        );
    }

    //Part 1:
    private static List<ListWithRank> compileListsByRank(Map<String, List<String>> namesBySourceList, String name) {
        List<ListWithRank> listsWithRank = new ArrayList<>();

        //O(n * m)
        //where n is the number of lists and m is the number of names in a list
        //+
        //O(nlog(n)) where n is number of lists not filtered out
        //(worst-case is no lists filtered out)
        //=
        //O(n(m+log(n)))
        for(String listTitle : namesBySourceList.keySet()){
            List<String> names = namesBySourceList.get(listTitle);
            int indexOfName = names.indexOf(name);

            if(indexOfName >= 0) {
                ListWithRank listWithRank = new ListWithRank(listTitle, indexOfName + 1);
                listsWithRank.add(listWithRank);
            }
        }

        listsWithRank.sort((a,b) -> Integer.compare(a.rank, b.rank));
        return listsWithRank;
    }

    private static Map<String, List<ListWithRank>> compileListsByNamePrefix(Map<String, List<String>> namesBySourceList, String prefix) {

        Map<String, List<ListWithRank>> namesWithRanks = new HashMap<>();

        //O(n * m)
        //where n is the number of lists and m is the number of names in a list
        for(String listTitle : namesBySourceList.keySet()){
            List<String> names = namesBySourceList.get(listTitle);
            for(int i = 0; i < names.size(); i++) {
                String name = names.get(i);
                if (name.startsWith(prefix)) {
                    namesWithRanks.putIfAbsent(name, new ArrayList<>());
                    List<ListWithRank> listsWithRanks = namesWithRanks.get(name);
                    listsWithRanks.add(new ListWithRank(listTitle, i + 1));
                }
            }
        }

        //+
        //O(k) where k is the number of names that match the prefix (worst-case, all names, n*m)
        //*
        //O(jlog(j)) where j is the number of lists that name appears in (worst-case, all lists, n)
        //=
        //n*m + n*m*nlog(n) = n^2mlog(n)

        namesWithRanks.values().forEach(listsWithRanks -> listsWithRanks.sort(Comparator.comparing(ListWithRank::getRank)));

        return namesWithRanks;
    }

    private static class ListWithRank {
        private final String listTitle;
        private final int rank;

        public ListWithRank(String listTitle, int rank) {
            this.listTitle = listTitle;
            this.rank = rank;
        }

        public int getRank() {
            return this.rank;
        }

        @Override
        public String toString() {
            return String.format("{ list: %s, rank: %d }", listTitle, rank);
        }
    }
}
