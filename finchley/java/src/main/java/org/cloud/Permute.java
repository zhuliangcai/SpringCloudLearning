package org.cloud;

public class Permute {
    public void permutation(int[] a, int length, int index) {
        if (index == length) {
            for (int i = 0; i < length; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = index; i < length; i++) {
                if (isSwap(a, length, i)) {
                    swap(a, index, i);
                    permutation(a, length, index + 1);
                    swap(a, index, i);
                }
            }
        }
    }

    private boolean isSwap(int[] a, int length, int index) {
        for (int i = index + 1; i < length; i++) {
            if (a[index] == a[i]) {
                return false;
            }
        }
        return true;
    }

    private void swap(int[] a, int index, int i) {
        int temp = a[index];
        a[index] = a[i];
        a[i] = temp;
    }

    public static void main(String[] args) {
        int[] b = {1, 2, 3};
        new Permute().permutation(b,2, 0);
    }
}
