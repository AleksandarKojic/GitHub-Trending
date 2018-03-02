package com.xapo.githubtrending.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.xapo.githubtrending.R;
import com.xapo.githubtrending.adapter.RepositoryListRecyclerAdapter;
import com.xapo.githubtrending.model.GitHubRepository;
import com.xapo.githubtrending.model.GitHubResponseEvent;
import com.xapo.githubtrending.network.GithubRestCalls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Fragment containing RecyclerView with a list of trending Github repositories
 */

public class RepositoryListFragment extends Fragment{

    private RecyclerView mRepositoryListRecycler;
    private ProgressBar mProgressBar;

    private List<GitHubRepository> mGitHubRepositoryList;

    private RepositoryListFragmentCallback mFragmentActivityCallback;

    /**
     * Interface implemented by hosting Activity, needed for Fragment-Activity communication
     */
    public interface RepositoryListFragmentCallback {
        void onRepositorySelected(GitHubRepository gitHubRepository);
    }


    public static RepositoryListFragment newInstance() {
        Bundle args = new Bundle();

        RepositoryListFragment fragment = new RepositoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentActivityCallback = (RepositoryListFragmentCallback) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        // asynchronously getting the list of trending GitHub Android repositories
        GithubRestCalls.getRepositoryList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository_list, container, false);

        mRepositoryListRecycler = view.findViewById(R.id.repository_list_recycler);
        mRepositoryListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        mProgressBar = view.findViewById(R.id.loading_progress);

        if(mGitHubRepositoryList != null) {
            mProgressBar.setVisibility(View.GONE);
            RepositoryListRecyclerAdapter adapter = new RepositoryListRecyclerAdapter(mGitHubRepositoryList,
                    mFragmentActivityCallback, getActivity());
            mRepositoryListRecycler.setAdapter(adapter);
        }

        return view;
    }


    /**
     * Will be called from Retrofit's onResponse() method
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GitHubResponseEvent event) {
        mGitHubRepositoryList = event.getGitHubRepositoryList();
        mProgressBar.setVisibility(View.GONE);
        RepositoryListRecyclerAdapter adapter = new RepositoryListRecyclerAdapter(mGitHubRepositoryList,
                mFragmentActivityCallback, getActivity());
        mRepositoryListRecycler.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentActivityCallback = null;
    }
}
