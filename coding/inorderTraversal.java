import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Scratch {
    public static void main(String[] args) {

    }

    public List<Integer> inorderTraversal(TreeNode root) {

//        return inorderTraversalRecursive(root);
        return inorderTraversalStack(root);

    }

    public List<Integer> inorderTraversalStack(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();

        List<Integer> res = new ArrayList<>();

        TreeNode cur = root;

        while (cur != null || !stack.empty()) {

            while (cur != null){
                stack.push(cur); //push cur
                cur = cur.left;
            }

            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;

        }

        return res;
    }

    private List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        util_inorder(root, res);
        return res;
    }

    private void util_inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        util_inorder(root.left, res);
        res.add(root.val);
        util_inorder(root.right, res);
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