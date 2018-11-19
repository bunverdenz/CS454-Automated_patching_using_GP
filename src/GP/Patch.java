package GP;

public class Patch {
    private int operation;
    private int sourceNode;
    private int targetNode;

    public Patch(int operation, int sNode, int tNode) {
        this.operation = operation;
        this.sourceNode = sNode;
        this.targetNode = tNode;
    }

    public int getOperation() {
        return operation;
    }

    public int getSourceNode() {
        return sourceNode;
    }

    public int getTargetNode() {
        return targetNode;
    }

    public void addEdit(int op, int sn, int tn){
        this.operation = op;
        this.sourceNode = sn;
        this. targetNode = tn;
    }
    public int getTargetNode() {
        return targetNode;
    }
}

