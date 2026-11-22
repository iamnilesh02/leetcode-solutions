class Solution {
    public String reverseByType(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        int left = 0;
        int right = n - 1;
        while (left < right) {
            while (left < right && !isLower(arr[left])) {
                left++;
            }
            while (left < right && !isLower(arr[right])) {
                right--;
            }
            if (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        left = 0;
        right = n - 1;
        while (left < right) {
            while (left < right && isLower(arr[left])) {
                left++;
            }
            while (left < right && isLower(arr[right])) {
                right--;
            }
            if (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        return new String(arr);
    }

    private boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }
}