package wang.skycloud.uocsi3120.SyntaxAnalyser;

public class StatusLayerOperation {
    enum operation{
        add,delete,replace
    }

    public StatusLayerOperation(NodeStatus instance, operation op) {
        this.instance = instance;
        this.op = op;
    }

    final NodeStatus instance;
    final operation op;


}
