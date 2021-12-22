import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Scratch {
    public static void main(String[] args) {

    }


    public List<Integer> preorderTraversal(TreeNode root) {

//        return preorderTraversalRecursive(root);
        return preorderTraversalStack(root);

    }


    private List<Integer> preorderTraversalStack(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();

        List<Integer> res = new ArrayList<>();

        TreeNode cur = root;

        while (cur != null | !stack.empty()) {

            //do work here.
            while (cur != null) {
                res.add(cur.val);
                if (cur.right != null) {
                    stack.add(cur.right); //push right
                }
                cur = cur.left;
            }

            if (!stack.isEmpty()){
                cur = stack.pop();
            }
        }

        return res;
    }

    private List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        util_preorder(root, res);
        return res;
    }

    private void util_preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        util_preorder(root.left, res);
        util_preorder(root.right, res);
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}