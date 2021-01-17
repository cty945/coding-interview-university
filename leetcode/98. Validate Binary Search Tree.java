class Scratch {
    public static void main(String[] args) {

    }

    public class TreeNode {
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

    public boolean isValidBST(TreeNode root) {

        boolean valid = valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
        System.out.println("valid:" + valid);
        return valid;

    };

    private boolean valid(TreeNode root, Long min, Long max){

        if (root.val <= min || root.val >= max){
            return false;
        };

        boolean validLeft = true;
        boolean validRight = true;

        if (root.left != null){
            validLeft = valid(root.left, min, (long) root.val);
        }
        if (root.right != null){
            validRight = valid(root.right, (long) root.val, max);
        }
        return validLeft && validRight;

    }

}