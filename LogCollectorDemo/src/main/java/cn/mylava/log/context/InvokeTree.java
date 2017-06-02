package cn.mylava.log.context;

import com.alibaba.fastjson.annotation.JSONType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipengfei on 2017/6/1.
 */
@JSONType(ignores ={"rootNode"})
public class InvokeTree {
    //方法起始节点
    public InvokeNode rootNode;
    //当前节点
    public InvokeNode curNode;

    public static class InvokeNode {
        public InvokeNode(int deep) {
            this.deep = deep;
            //调用次数
            this.invokeCount = 1;
        }
        public InvokeNode parentNode;
        public List<InvokeNode> childNodes;
        public String invokeMethod;
        public int deep;
        public int invokeCount;
        public boolean equals(InvokeNode other) {
            if (other == null) {
                return false;
            }
            if (!invokeMethod.equals(other.invokeMethod)) {
                return false;
            }
            if (childNodes == null && other.childNodes == null) {
                return true;
            }
            if (childNodes == null || other.childNodes == null) {
                return false;
            }
            int size = childNodes.size();
            if (size != other.childNodes.size()) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                InvokeNode x = childNodes.get(i);
                InvokeNode y = other.childNodes.get(i);
                if (!x.equals(y)) {
                    return false;
                }
            }
            return true;
        }
    }

    public InvokeTree() {
    }

    /**
     * 调用方法开始
     * @param invokeMethod
     */
    public void start(String invokeMethod) {
        InvokeNode rootNode = new InvokeTree.InvokeNode(0);
        rootNode.invokeMethod = invokeMethod;
        this.rootNode = rootNode;
        this.curNode = rootNode;
    }

    /**
     * 进入方法
     * @param invokeMethod
     */
    public void enter(String invokeMethod) {
        InvokeNode parentNode = this.curNode;
        InvokeNode newNode = new InvokeNode(parentNode.deep + 1);
        newNode.invokeMethod = invokeMethod;
        newNode.parentNode = parentNode;
        if (parentNode.childNodes == null) {
            parentNode.childNodes = new ArrayList();
        }
        parentNode.childNodes.add(newNode);

        this.curNode = newNode;
        //重复调用整理
        cleanRepeatNode(parentNode);
    }

    /**
     * 删除重复调用的方法
     * @param parentNode
     */
    public void cleanRepeatNode(InvokeNode parentNode) {
        if (parentNode == null) {
            return;
        }
        int len = parentNode.childNodes.size();
        if (len <= 1) {
            cleanRepeatNode(parentNode.parentNode);
            return;
        }
        InvokeNode a = parentNode.childNodes.get(len - 2);
        InvokeNode b = parentNode.childNodes.get(len - 1);
        if (a.equals(b)) {
            parentNode.childNodes.remove(len - 1);
            a.invokeCount++;
        }
        cleanRepeatNode(parentNode.parentNode);
    }

    public void exit() {
        this.curNode = this.curNode.parentNode;
    }

/*    public void clear() {
        local.set(null);
    }

    public InvokeTree getCurrentTree() {
        return local.get();
    }*/

    public String toString() {
        if (rootNode == null) {
            rootNode = curNode;
        }
        if (rootNode == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            buildShow(rootNode, sb);
            return sb.toString();
        }
    }

    private void buildShow(InvokeNode node, StringBuilder sb) {

        if (node != null) {
            if (node.parentNode != null) {
                sb.append("->");
            }
            sb.append(node.invokeMethod).append(node.invokeCount > 1 ? ("[repeat@" + node.invokeCount) + "]" : "");
            //控制调用深度只打印8以内的
            if (node.deep <= 8) {
                if (node.childNodes != null && node.childNodes.size() > 0) {

                    for (int i = 0; i < node.childNodes.size(); i++) {
                        InvokeNode chNode = node.childNodes.get(i);
                        buildShow(chNode, sb);
                    }

                }
            }
        }
    }

    public static void main(String[] args) {
//        ClientConfig clientConfig = new ClientConfig();
//        clientConfig.setOpenInvokeTreeAnalyse(true);
//        BootConfig.clientConfig = clientConfig;
        InvokeTree invokeTree = new InvokeTree();
        invokeTree.start("controller.test");
        invokeTree.enter("service.hello");
        invokeTree.enter("invoke1");
        invokeTree.enter("invokeSub1");
        invokeTree.exit();
        invokeTree.enter("invokeSub2");
        invokeTree.exit();
        invokeTree.enter("invokeSub2");
        invokeTree.exit();
        invokeTree.exit();

        invokeTree.enter("invoke2");
        invokeTree.enter("invoke21");
        invokeTree.exit();
        invokeTree.exit();

        invokeTree.enter("invoke2");
        invokeTree.enter("invoke21");
        invokeTree.exit();
        invokeTree.exit();

        invokeTree.exit();
        invokeTree.exit();
        System.out.println(invokeTree.toString());
    }
}
