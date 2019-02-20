
/*

payments = [(m0, c0), (m1, c1), (m2, c1), (m2, c2), (m3, c2), (m3, c3), (m4, c3), (m4, c4)]

connected_merchants(m1) = {m1, m2, m3, m4}

immediate immediateNeighbors:

Map<String, Node> namesToNodes
Map<Node, List<Node>> immediateNeighborNetwork
"m1" -> m1 node (category, id) -> neighbor nodes

m0 -> {c0}
c0 -> {m0}
m1 -> {c1}
c1 -> {m1, m2}
m2 -> {c2, c1}
c2 -> {m2, m3}
m3 -> {c2, c3}
c3 -> {m3, m4}
m4 -> {c3, c4}
c4 -> {m4}

connected merchants:
add immediate immediateNeighbors to queue.
go through all their immediateNeighbors, add them to queue. If already visited, don't add to queue.
keep going until no more neigbors in queue.
add merchants found to list of connected merchants.

m0 -> {c0} -> immediateNeighbors(c0) -> {m0}
m1 -> {c1} -> immediateNeighbors(c1) -> {m1, m2} -> add m2 to queue. add m2 to list
           -> immediateNeighbors(m2) -> {c2, c1} -> add c2 to queue
           -> immediateNeighbors(c2) -> {m2, m3} -> add m3 to queue. add m3 to list
           -> immediateNeighbors(m3) -> {c2, c3} -> add c3 to queue
           -> immediateNeighbors(c3) -> {m3, m4} -> add m4 to queue. add m4 to list
           -> immediateNeighbors(m4) -> {c3, c4} -> add c4 to queue
           -> immediateNeighbors(c4) -> {m4} -> nothing

           -> {m2, m3, m4}

           private Set<Node> allNeighbors(Node n) {
            if (allNeighbors.containsKey(n)) return allNeighbors.get(n);
            // compute neighbors
            allNeighbors.put(n, neighbors);
            return neighbors;

m2 -> {c2, c1}
    -> immediateNeighbors(c2) -> {m2}
    -> immediateNeighbors(c1) -> {m1}, in graph already, add all of it's dependencies.
m3 -> {c2, c3}
m4 -> {c3, c4}


Cache:
m-1 -> {m-2, m0}
m-2 -> {m-1, m0}
m0  -> {m-2, m-1}
m1  -> {m2, m3, m4}
m2  -> {m1, m3, m4}
m3  -> {m2, m1, m4}
m4  -> {m3, m2, m1}


 */

import java.util.*;
import java.util.stream.Stream;


public class PaymentAggregator {

//    private Foo something(int i) {
//        if (map.contains(i))
//            return map.get(i);
//
//        // compute Foo
//
//        map.put(i, foo);
//        return foo;
//    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<PaymentTuple> paymentTuples = Arrays.asList(

                new PaymentTuple("m-2", "c0"),
                new PaymentTuple("m-1", "c0"),
                new PaymentTuple("m0", "c0"),
                new PaymentTuple("m1", "c1"),
                new PaymentTuple("m2", "c1"),
                new PaymentTuple("m2", "c1"),
                new PaymentTuple("m2", "c2"),
                new PaymentTuple("m3", "c2"),
                new PaymentTuple("m3", "c3"),
                new PaymentTuple("m4", "c3"),
                new PaymentTuple("m4", "c4")
        );

        PaymentNetwork paymentNetwork = new PaymentNetwork(paymentTuples);

        Map<String, List<String>> merchantConnections = paymentNetwork.buildMerchantConnections();

        merchantConnections.forEach((merchantID, findConnectedMerchantsv0) -> {
                    System.out.println(merchantID);
                    System.out.println(String.join(", ", findConnectedMerchantsv0));
                    System.out.println();
                }
        );

        String merchantID = "m3";
        List<String> connectedMerchants = merchantConnections.get(merchantID);
        System.out.println(String.format("%s -> %s", merchantID, connectedMerchants));
        System.out.println(System.currentTimeMillis() - startTime);

    }


    private static class PaymentNetwork {
        private final Map<String, List<String>> merchantConnectionsCache = new HashMap<>();
        private final Map<String, Node> namesToNodes;

        private PaymentNetwork(List<PaymentTuple> paymentTuples) {
            this.namesToNodes = new HashMap<>();
            paymentTuples.forEach(this::addTuple);
        }

        private void addTuple(PaymentTuple paymentTuple) {
            namesToNodes.putIfAbsent(paymentTuple.merchant, new Node(true, paymentTuple.merchant));
            Node merchantNode = namesToNodes.get(paymentTuple.merchant);

            namesToNodes.putIfAbsent(paymentTuple.creditCard, new Node(false, paymentTuple.creditCard));
            Node ccNode = namesToNodes.get(paymentTuple.creditCard);

            merchantNode.immediateNeighbors.add(ccNode);
            ccNode.immediateNeighbors.add(merchantNode);
        }

        private Map<String, List<String>> buildMerchantConnections() {
            Map<String, List<String>> merchantConnections = new HashMap<>();

            Stream<Node> merchantNodeStream = namesToNodes.values().stream().filter(x -> x.isMerchant);

            merchantNodeStream.forEach(merchantNode -> {
                        List<String> connectedMerchants = findConnectedMerchantsv0(merchantNode, merchantConnections);
                        merchantConnections.put(merchantNode.id, connectedMerchants);
                    }
            );
            return merchantConnections;
        }


        private List<String> findConnectedMerchants(Node merchantNode, Map<String, List<String>> merchantConnections) {

            return null;
        }


        private List<String> findConnectedMerchantsv0(Node merchantNode, Map<String, List<String>> merchantConnections) {
            Queue<Node> nodesToVisit = new LinkedList<>();
            nodesToVisit.add(merchantNode);

            Set<String> visited = new HashSet<>();

            List<String> connectedMerchants = new ArrayList<>();

            while (!nodesToVisit.isEmpty()) {
                Node currentNode = nodesToVisit.poll();

                if (visited.contains(currentNode.id)) {
                    continue;
                }

                if (currentNode.isMerchant && !currentNode.equals(merchantNode)) {
                    connectedMerchants.add(currentNode.id);
                }

                if (merchantConnections.containsKey(currentNode.id)) {
                    merchantConnections.get(currentNode.id).forEach(x -> {
                                if (!visited.contains(x)) {
                                    connectedMerchants.add(x);
                                    visited.add(x);
                                }
                            }
                    );
                } else {
                    for (Node immediateNeighbor : currentNode.immediateNeighbors) {
                        if (!visited.contains(immediateNeighbor.id)) {
                            nodesToVisit.add(immediateNeighbor);
                        }
                    }
                }
                visited.add(currentNode.id);
            }
            return connectedMerchants;
        }
    }

    private static class PaymentTuple {
        private String merchant;
        private String creditCard;

        private PaymentTuple(String merchant, String creditCard) {
            this.merchant = merchant;
            this.creditCard = creditCard;
        }
    }

    private static class Node {
        private boolean isMerchant;
        private String id;
        private List<Node> immediateNeighbors;

        private Node(boolean isMerchant, String id) {
            this(isMerchant, id, new ArrayList<>());
        }

        private Node(boolean isMerchant, String id, List<Node> immediateNeighbors) {
            this.isMerchant = isMerchant;
            this.id = id;
            this.immediateNeighbors = immediateNeighbors;
        }

        @Override
        public String toString() {
            return this.id;
        }
    }
}


