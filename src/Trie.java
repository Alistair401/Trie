import java.util.Collection;

public class Trie {
    private TrieNode root;
    public Trie(){
        root = new TrieNode(' ');
    }
    public Trie(Collection<CharSequence> sequenceCollection){
        root = new TrieNode(' ');
        addAll(sequenceCollection);
    }
    public void add(CharSequence sequence){
        TrieNode current = root;
        for (int i = 0; i < sequence.length(); i++) {
            boolean last = (i == sequence.length() - 1);
            boolean found = false;
            for (TrieNode child : current.getChildren()) {
                if (child.getChar() == sequence.charAt(i)) {
                    current = child;
                    if (last) {
                        child.setEndOfSequence(true);
                        return;
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                TrieNode trieNode = new TrieNode(sequence.charAt(i));
                current.addChild(trieNode);
                current = trieNode;
                if (last) {
                    trieNode.setEndOfSequence(true);
                    return;
                }
            }
        }
    }
    public void addAll(Collection<CharSequence> sequenceCollection){
        for (CharSequence sequence : sequenceCollection) {
            add(sequence);
        }
    }
    public boolean contains(CharSequence sequence){
        TrieNode current = root;
        for (int i = 0; i < sequence.length(); i++) {
            boolean last = (i == sequence.length() - 1);
            boolean found = false;
            for (TrieNode child : current.getChildren()) {
                if (child.getChar() == sequence.charAt(i)) {
                    if (last && child.isEndOfSequence()) {
                        return true;
                    }
                    current = child;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return false;
    }
    public void remove(CharSequence sequence){
        TrieNode[] path = new TrieNode[sequence.length()];
        TrieNode current = root;
        for (int i = 0; i < sequence.length(); i++) {
            boolean last = (i == sequence.length() - 1);
            boolean found = false;
            for (TrieNode child : current.getChildren()) {
                if (child.getChar() == sequence.charAt(i)) {
                    path[i] = child;
                    if (last && child.isEndOfSequence()) {
                        deletePath(path);
                        return;
                    }
                    current = child;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return;
            }
        }
    }
    public void deletePath(TrieNode[] nodes){
        nodes[nodes.length - 1].setEndOfSequence(false);
        for (int i = nodes.length - 1; i >= 0; i--) {
            if (nodes[i].getChildren().size() == 0 && !nodes[i].isEndOfSequence()){
                nodes[i-1].removeChild(nodes[i]);
            } else {
                return;
            }
        }
    }

}
