package com.xapo.githubtrending.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xapo.githubtrending.R;
import com.xapo.githubtrending.fragment.RepositoryDetailsFragment;
import com.xapo.githubtrending.fragment.RepositoryListFragment;
import com.xapo.githubtrending.model.GitHubRepository;


public class MainActivity extends AppCompatActivity implements RepositoryListFragment.RepositoryListFragmentCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.repository_list_fragment_container);

        if (fragment == null) {
            fragment = RepositoryListFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.repository_list_fragment_container, fragment)
                    .commit();
        }
    }


    @Override
    public void onRepositorySelected(GitHubRepository gitHubRepository) {
        Fragment repositoryDetailsFragment = RepositoryDetailsFragment.newInstance(gitHubRepository);

        //if detail_fragment_container is present, it means we are on the tablet, so we use master/detail flow
        //Otherwise we are  on the phone, so we just replace Fragments
        if (findViewById(R.id.detail_fragment_container) == null) {
            getSupportFragmentManager().beginTransaction()
 //                   .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(R.id.repository_list_fragment_container, repositoryDetailsFragment)
                    .addToBackStack("RepositoryDetailsFragment")
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, repositoryDetailsFragment)
                    .commit();
        }
    }
}
