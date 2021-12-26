class Scratch {
    public static void main(String[] args) {

        BinarySeachTree bst = new BinarySeachTree();

        bst.insert(5);
        bst.insert(3);
        bst.insert(6);
        bst.insert(4);
//        bst.insert(2);
//        bst.insert(1);
//        bst.insert(3);

        bst.print_values();
        int size = bst.get_node_count();
        System.out.println("size: " + size);
        System.out.println("is in tree 2: " + bst.is_in_tree(1));
        System.out.println("height: " + bst.get_height());
        System.out.println("min: " + bst.get_min());
        System.out.println("is valid bst: " + bst.is_binary_search_tree());

//        bst.print_values();
//        System.out.println("delete 4:");
//        bst.delete_value(4);
//        bst.print_values();

        System.out.println("successor of 4:" + bst.get_successor(3));


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

class BinarySeachTree {

    TreeNode root;
    int size = 0;

    public void insert(int val) {
        if (this.root == null) {
            this.root = new TreeNode(val);
        } else {
            TreeNode cur = this.root;
            while (cur.left != null && cur.val >= val || (cur.right != null && cur.val < val)) {
                if (cur.val >= val) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            if (cur.val >= val) {
                cur.left = new TreeNode(val);
            } else {
                cur.right = new TreeNode(val);
            }
        }
        this.size++;
    }

    public void print_values() {
        this.inorder(this.root);
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        this.inorder(root.left);
        System.out.println(root.val);
        this.inorder(root.right);
    }

    public int get_node_count() {
        return this.size;
    }

    public void delete_tree() {
        this.root = null;
    }

    public boolean is_in_tree(int data) {
        TreeNode cur = this.root;
        while (cur != null && cur.val != data) {
            if (data <= cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return cur != null;
    }

    public int get_height() {
        return this.get_height_helper(this.root);
    }

    private int get_height_helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(get_height_helper(root.left), get_height_helper(root.right)) + 1;
    }

    public int get_min() {
        if (this.root == null) return -1;
        TreeNode cur = this.root;
        while (cur != null && cur.left != null) {
            cur = cur.left;
        }
        return cur.val;
    }

    public int get_max() {
        if (this.root == null) return -1;

        TreeNode cur = this.root;
        while (cur != null && cur.right != null) {
            cur = cur.right;
        }
        return cur.val;
    }

    public boolean is_binary_search_tree() {
        return this.is_bst_helper(this.root, -Long.MAX_VALUE, Long.MAX_VALUE);
    }

    private boolean is_bst_helper(TreeNode root, long min, long max){
        if (root == null){
            return true;
        }

        return root.val <= max && root.val > min &&
                is_bst_helper(root.left, min, root.val) &&
                is_bst_helper(root.right, root.val, max);
    };

    public void delete_value(int value){
        this.root = this.delete_root_helper(value, this.root);
    }
    private TreeNode delete_root_helper(int value, TreeNode root){

        if (root == null){
            return null;
        } else if (value < root.val){
            root.left = this.delete_root_helper(value, root.left);
        } else if (value > root.val){
            root.right = this.delete_root_helper(value, root.right);
        } else {
            if (root.left == null && root.right == null){
                return null;
            } else if (root.left == null){
                return root.right;
            } else if (root.right == null){
                return root.left;
            } else {
                TreeNode cur = root.left;
                while (cur.right != null){
                    cur = cur.right;
                }
                root.val = cur.val;
                root.left = this.delete_root_helper(cur.val, root.left);
            }
        }

        return root;

    }

    public int get_successor(int val){
        if (this.root == null){
            return -1;
        }
        return this.get_successor_helper(this.root, val);
    }


    private int get_successor_helper(TreeNode root, int val){

        TreeNode cur = root;
        TreeNode predecessor = root;
        while (cur != null && cur.val != val){
            if (val > cur.val){
                cur = cur.right;
            } else {
                predecessor = cur;
                cur = cur.left;
            }
        }
        if (cur != null){
            if (cur.right != null){
                System.out.println("first case");
                TreeNode temp = cur.right;
                while (temp.right != null){
                    temp = temp.right;
                }
                return temp.val;
            } else {
                System.out.println("2ed case");
                return predecessor.val;
            }
        } else {
            return -1;
        }
    }

}