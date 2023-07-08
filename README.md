# Git

## most useful commands

- How to delete a branch?

  locally:
  git branch -d localBrach
  remote:
  git push origin --delete remoteBrach

- how to undo changes:
  git revert hashCommit (keep the history unchange)

  git reset hashCommit (history will change will be reset to certain commit)
  --soft --> delete: local repository
  --mixed (default) --> delete: stagging area / local repository
  --hard --> delete: working directory / stagging area / local repository
