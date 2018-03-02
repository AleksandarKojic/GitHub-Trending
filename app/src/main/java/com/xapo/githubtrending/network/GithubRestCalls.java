package com.xapo.githubtrending.network;

import android.util.Log;


import com.xapo.githubtrending.model.GitHubResponseEvent;
import com.xapo.githubtrending.model.GitHubResponseObject;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alexandar on 3/1/2018.
 */

public class GithubRestCalls {

    private static final String GITHUB_BASE_URL = "https://api.github.com/search/";
    private static String TAG = GithubRestCalls.class.getSimpleName();



    public static void getRepositoryList() {
        Call<GitHubResponseObject> call = getGithubService().getRepositoryList();
        call.enqueue(new Callback<GitHubResponseObject>() {
            @Override
            public void onResponse(Call<GitHubResponseObject> call, Response<GitHubResponseObject> response) {
                GitHubResponseEvent gitHubResponseEvent = new GitHubResponseEvent();
                gitHubResponseEvent.setGitHubRepositoryList(response.body().getItems());
                EventBus.getDefault().post(gitHubResponseEvent);
            }

            @Override
            public void onFailure(Call<GitHubResponseObject> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

    }

    private static GitHubService getGithubService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GitHubService.class);
    }


}
