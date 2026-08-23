class TrieNode:

  def __init__(self):
    self.children = {}
    self.is_end = False


class Solution:

  def replaceWords(self, dictionary: list[str], sentence: str) -> str:
    root = TrieNode()

    for word in dictionary:
      node = root
      for char in word:
        if char not in node.children:
          node.children[char] = TrieNode()
        node = node.children[char]
      node.is_end = True

    def find_shortest_root(word: str) -> str:
      node = root
      prefix = []
      for char in word:
        if char not in node.children:
          break
        prefix.append(char)
        node = node.children[char]
        if node.is_end:
          return "".join(prefix)
      return word

    words = sentence.split(" ")
    return " ".join(find_shortest_root(w) for w in words)