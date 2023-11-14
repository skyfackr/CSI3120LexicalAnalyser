package wang.skycloud.uocsi3120.SyntaxAnalyser;

public class StatusStack {
    private final NodeStatus[] stack;
    private int top = -1;

    public StatusStack(int size) {
        stack = new NodeStatus[size];
    }

    public void push(NodeStatus status) {
        stack[++top] = status;
    }

    public NodeStatus pop() {
        return stack[top--];
    }

    public NodeStatus peek() {
        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
    public void doOperation(StatusLayerOperation operation)
    {
        switch (operation.op)
        {
            case add:
                push(operation.instance);
                break;
            case delete:
                pop();
                break;
            case replace:
                pop();
                push(operation.instance);
                break;
        }
    }
    StatusStack()
    {
        this.stack = new NodeStatus[1000];
        this.push(StatusGenerator.generate("start"));
    }
}
