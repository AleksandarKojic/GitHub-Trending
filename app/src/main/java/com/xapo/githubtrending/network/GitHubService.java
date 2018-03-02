package com.xapo.githubtrending.network;

import com.xapo.githubtrending.model.GitHubResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubService {
    @GET("https://api.github.com/search/repositories?q=android+language:java&sort=stars&order=desc")
    Call<GitHubResponseObject> getRepositoryList();
}
