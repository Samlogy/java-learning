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

git merge / git rebase:

1. master (pull)
2. feature (merge)
3. master (merge + push) in case no conflict

merge: commit hisotry problems tracking ...
both rebase & merge can create conflicts

git cherry-pick hasgCommmit --> add a commit from a branch to another branch

1. go to target branch
2. execute the git command with the corresponding hash
3. solve conflicts / push

rebase interactive mode
git rebase -i hashCommit --> squash / delete / reword --> commits

git stash:
add changes uncommited to a stack

git stash / git stash pop / git stash list
git stash -m 'name' / git stash pop name
git stash clear

git blame filename --> most recent changes on file and some details

git commit --amend (change previous commit msg / files)
git add . && git commit --amend --no-commit
