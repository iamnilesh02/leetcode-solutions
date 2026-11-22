import java.util.*;

class Solution {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> result = new ArrayList<>();
        int n = arr.length;

        for (int target = n; target > 1; target--) {
            int idx = findIndex(arr, target);
            if (idx == target - 1) {
                continue;
            }
            if (idx != 0) {
                flip(arr, idx + 1);
                result.add(idx + 1);
            }
            flip(arr, target);
            result.add(target);
        }

        return result;
    }

    private int findIndex(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private void flip(int[] arr, int k) {
        int left = 0;
        int right = k - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}