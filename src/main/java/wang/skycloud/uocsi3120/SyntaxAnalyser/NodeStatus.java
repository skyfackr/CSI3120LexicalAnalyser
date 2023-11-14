package wang.skycloud.uocsi3120.SyntaxAnalyser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * define the function for node status stack layer
 */
public abstract class NodeStatus {
    private Map<String,String> nextAllowed,changedWhen;
    private Set<String> deleteWhen;
    protected void updateNextAllowed(Map<String,String> nextAllowed)
    {
        this.nextAllowed = nextAllowed;
    }
    protected void updateChangedWhen(Map<String,String> changedWhen)
    {
        this.changedWhen = changedWhen;
    }
    protected void updateDeleteWhen(Set<String> deleteWhen)
    {
        this.deleteWhen = deleteWhen;
    }
    protected String code="";

    public StatusLayerOperation nextOperation(NodeStatus nextStatus)
    {
        if (nextAllowed.containsKey(nextStatus.getCode()))
        {
            return generateAdd(nextStatus);
        }
        else if (deleteWhen.contains(nextStatus.getCode()))
        {
            return generateDelete();
        }
        else if (changedWhen.containsKey(nextStatus.getCode()))
        {
            return generateReplace(nextStatus);
        }
        customError();
        throw new RuntimeException("unreachable");
    }
    protected abstract void customError();

    public String getCode(){
        return code;
    }
    protected StatusLayerOperation generateDelete()
    {
        return new StatusLayerOperation(this, StatusLayerOperation.operation.delete);
    }
    protected StatusLayerOperation generateAdd(NodeStatus instance)
    {
        return new StatusLayerOperation(instance, StatusLayerOperation.operation.add);
    }
    protected StatusLayerOperation generateReplace(NodeStatus instance)
    {
        return new StatusLayerOperation(instance, StatusLayerOperation.operation.replace);
    }
}
