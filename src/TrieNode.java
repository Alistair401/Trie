import java.util.LinkedList;

class TrieNode {
    private LinkedList<TrieNode> children;
    private char content;
    private boolean endOfSequence;

    TrieNode(char content){
        this.content = content;
        children = new LinkedList<>();
    }
    LinkedList<TrieNode> getChildren(){
        return children;
    }
    void setEndOfSequence(boolean state){
        endOfSequence = state;
    }
    char getChar(){
        return content;
    }
    boolean isEndOfSequence(){
        return endOfSequence;
    }
    void addChild(TrieNode trieNode) {
        children.add(trieNode);
    }
    void removeChild(TrieNode trieNode){
        children.remove(trieNode);
    }
}
