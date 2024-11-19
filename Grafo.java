import java.util.*;

class Grafo {

    private Map<String, LinkedList<String>> network;

    public Grafo() {
        network = new HashMap<>();
    }

    public void createAccount(String user) {
        if (!network.containsKey(user)) {
            network.put(user, new LinkedList<>());
            System.out.println(user + " foi adicionado à rede social.");
        } else {
            System.out.println(user + " já existe na rede social.");
        }
    }

    public void removeAccount(String user) {
        if (network.containsKey(user)) {
            network.remove(user);

            for (LinkedList<String> followers : network.values()) {
                followers.remove(user);
            }
            System.out.println(user + " foi removido da rede social.");
        } else {
            System.out.println(user + " não existe na rede social.");
        }
    }

    public void follow(String user, String friend) {
        if (network.containsKey(user) && network.containsKey(friend)) {
            if (!network.get(user).contains(friend)) {
                network.get(user).add(friend);
                System.out.println(user + " agora segue " + friend + ".");
            } else {
                System.out.println(user + " já segue " + friend + ".");
            }
        } else {
            System.out.println("Ambos os usuários precisam estar na rede social para seguir.");
        }
    }

    public void unfollow(String user, String friend) {
        if (network.containsKey(user)) {
            if (network.get(user).remove(friend)) {
                System.out.println(user + " parou de seguir " + friend + ".");
            } else {
                System.out.println(user + " não segue " + friend + ".");
            }
        } else {
            System.out.println(user + " não está na rede social.");
        }
    }

    public void printGraph() {
        System.out.println("\nGrafo da rede social:");
        for (Map.Entry<String, LinkedList<String>> entry : network.entrySet()) {
            System.out.println(entry.getKey() + " segue: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Grafo socialNetwork = new Grafo();


        socialNetwork.createAccount("Alice");
        socialNetwork.createAccount("Bob");
        socialNetwork.createAccount("Carol");
        socialNetwork.createAccount("David");


        socialNetwork.follow("Alice", "Bob");
        socialNetwork.follow("Alice", "Carol");
        socialNetwork.follow("Bob", "Alice");
        socialNetwork.follow("Bob", "David");
        socialNetwork.follow("Carol", "Alice");
        socialNetwork.follow("Carol", "David");
        socialNetwork.follow("David", "Carol");


        socialNetwork.printGraph();


        socialNetwork.unfollow("Alice", "Carol");


        socialNetwork.removeAccount("Bob");

        socialNetwork.printGraph();
    }
}
