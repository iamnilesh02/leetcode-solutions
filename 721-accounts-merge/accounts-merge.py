class DSU:

  def __init__(self, n: int):
    self.parent = list(range(n))

  def find(self, x: int) -> int:
    if self.parent[x] != x:
      self.parent[x] = self.find(self.parent[x])
    return self.parent[x]

  def union(self, x: int, y: int) -> None:
    root_x = self.find(x)
    root_y = self.find(y)
    if root_x != root_y:
      self.parent[root_x] = root_y


class Solution:

  def accountsMerge(self, accounts: list[list[str]]) -> list[list[str]]:
    n = len(accounts)
    dsu = DSU(n)
    email_to_id = {}

    for i, account in enumerate(accounts):
      for email in account[1:]:
        if email not in email_to_id:
          email_to_id[email] = i
        else:
          dsu.union(i, email_to_id[email])

    root_to_emails = {}
    for email, idx in email_to_id.items():
      root = dsu.find(idx)
      if root not in root_to_emails:
        root_to_emails[root] = []
      root_to_emails[root].append(email)

    result = []
    for idx, emails in root_to_emails.items():
      result.append([accounts[idx][0]] + sorted(emails))

    return result