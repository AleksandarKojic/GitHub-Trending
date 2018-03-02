package com.xapo.githubtrending.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xapo.githubtrending.R;
import com.xapo.githubtrending.model.GitHubRepository;

import java.util.List;

import static com.xapo.githubtrending.fragment.RepositoryListFragment.*;

/**
 * Created by Alexandar on 3/2/2018.
 */

public class RepositoryListRecyclerAdapter extends RecyclerView.Adapter<RepositoryListRecyclerAdapter.RepositoryViewHolder>{
    private Activity mActivity;
    private List<GitHubRepository> mGitHubRepositoryList;
    private RepositoryListFragmentCallback mFragmentActivityCallback;

    public RepositoryListRecyclerAdapter(List<GitHubRepository> gitHubRepositoryList,
                                         RepositoryListFragmentCallback fragmentActivityCallback, Activity activity) {
        mActivity = activity;
        mGitHubRepositoryList = gitHubRepositoryList;
        mFragmentActivityCallback = fragmentActivityCallback;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_repository, parent, false);

        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        holder.bindRepositoryItem(position);
    }

    @Override
    public int getItemCount() {
        return mGitHubRepositoryList.size();
    }



    public class RepositoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mRepositoryTitle;
        private TextView mRepositoryDescription;
        private GitHubRepository mGitHubRepository;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mRepositoryTitle = itemView.findViewById(R.id.repository_title);
            mRepositoryDescription = itemView.findViewById(R.id.repository_description);
        }

        public void bindRepositoryItem(int position) {
            mGitHubRepository = mGitHubRepositoryList.get(position);
            mRepositoryTitle.setText(mGitHubRepository.getFullName());
            mRepositoryDescription.setText(mGitHubRepository.getDescription());
        }

        @Override
        public void onClick(View v) {
            mFragmentActivityCallback.onRepositorySelected(mGitHubRepository);
        }
    }
}
