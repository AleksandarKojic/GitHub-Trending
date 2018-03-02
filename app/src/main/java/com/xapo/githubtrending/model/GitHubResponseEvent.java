package com.xapo.githubtrending.model;

import java.util.List;

/**
 * Created by Alexandar on 3/1/2018.
 */

public class GitHubResponseEvent {

    List<GitHubRepository> mGitHubRepositoryList;

    public GitHubResponseEvent(){}

    public List<GitHubRepository> getGitHubRepositoryList() {
        return mGitHubRepositoryList;
    }

    public void setGitHubRepositoryList(List<GitHubRepository> mGitHubRepositoryList) {
        this.mGitHubRepositoryList = mGitHubRepositoryList;
    }
}
