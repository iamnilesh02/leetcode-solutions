class Solution:

  def longestWord(self, words: list[str]) -> str:
    words.sort()
    built_words = {""}
    longest = ""

    for word in words:
      if word[:-1] in built_words:
        built_words.add(word)
        if len(word) > len(longest):
          longest = word

    return longest