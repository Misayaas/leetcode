import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LockingTree_1993 {

    private int[] parent;
    private int[] lockNodeUser;
    private List<Integer>[] children;

    public LockingTree_1993(int[] parent) {
        int n = parent.length;
        this.parent = parent;
        this.lockNodeUser = new int[n];
        Arrays.fill(this.lockNodeUser, -1);
        this.children = new List[n];

        for (int i = 0; i < n; i++) {
            this.children[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i++) {
            int p = parent[i];
            if (p != -1) {
                children[p].add(i);
            }
        }
    }

    public boolean lock(int num, int user) {
        if (lockNodeUser[num] != -1) {
            lockNodeUser[num] = user;
            return true;
        }
        return false;
    }

    public boolean unlock(int num, int user) {
        if (lockNodeUser[num] == user) {
            lockNodeUser[num] = -1;
            return true;
        }
        return false;
    }

    public boolean upgrade(int num, int user) {
        boolean res = lockNodeUser[num] == -1 && !hasLockedAncestor(num) && checkAndUnlockDescendant(num);
        if (res) {
            lockNodeUser[num] = user;
        }
        return res;
    }

    private boolean hasLockedAncestor(int num) {
        num = parent[num];
        while (num != -1) {
            if (lockNodeUser[num] != -1) {
                return true;
            }
            num = parent[num];
        }
        return false;
    }

    private boolean checkAndUnlockDescendant(int num) {
        boolean res = lockNodeUser[num] != -1;
        lockNodeUser[num] = -1;
        for (int child : children[num]) {
            res |= checkAndUnlockDescendant(child);
        }
        return res;
    }
}

/**
 * Your LockingTree object will be instantiated and called as such:
 * LockingTree obj = new LockingTree(parent);
 * boolean param_1 = obj.lock(num,user);
 * boolean param_2 = obj.unlock(num,user);
 * boolean param_3 = obj.upgrade(num,user);
 */