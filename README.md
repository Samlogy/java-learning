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
git rebase -i hashCommit --> squash / delete / reword / split --> commits

git stash:
add changes uncommited to a stack

git stash / git stash pop / git stash list
git stash -m 'name' / git stash pop name
git stash clear

git blame filename --> most recent changes on file and some details

git commit --amend (change previous commit msg / files)
git add . && git commit --amend --no-commit

** NB: **

- add .gitignore file to ignore files/directories that should not belongs to the working dir (node_modules/.vscode/.env) \*\* add .gitattributes file (must be in working dir, but changes should trigger git) ex: (yarn-lock.json / package-lock.json)

- Git Workflows: (Rebase vs Merge): 2 options: Merge / Rebase --> both have pros & cons, and be effective when used well prefer using merge when you start, then rebase when you are good at it.

- opt for branch naming convention: ex: github_name/feature_name (or any other useful convention)

- Commit Messages: good commit message in the present, imperative sense (clear, brief) ex: Add name field to checkout form

- Pull Requests: small (hundred lines) but frequent pull request --> review process easier / faster (beug-free) / productivity increase use flags while working on feature state (the longer you not merging, the harder it will be) add pictures + good decription + associated task ticket to the Pull Request (make the reviewer job easier)

- Remote repo: delete branches on merge Prevent pushes directly to master Require at least one approval before merging Require passing CI tests to merge
